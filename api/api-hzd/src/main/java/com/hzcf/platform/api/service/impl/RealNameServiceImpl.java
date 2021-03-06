package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.service.IRealNameService;
import com.hzcf.platform.api.util.CustomerUtils;
import com.hzcf.platform.api.util.ImageUrlUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.utils.ServiceUtil;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.core.user.service.UserImageService;
import com.hzcf.platform.core.user.service.UserService;
import com.imageserver.ImageServer;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
  * @Description:实名认证的操作
  * @author 作者:裴高祥 E-mail:lettger@163.com Tel:13241706779
  * @date 创建时间：2016年12月29日 下午3:50:16 
  * @version 1.0 
  * @since  JDK1.7
  */
@Service
public class RealNameServiceImpl implements IRealNameService {
	private static final Log logger = Log.getLogger(RealNameServiceImpl.class);
	public DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期操作类
    @Autowired
    public UserService userSerivce;//借款人service
	@Autowired
	public UserApplyInfoSerivce userApplyInfoSerivce;//用户申请信息service

	@Autowired
	private ImageServer imageServer;

	@Autowired
	public UserImageService userImageService;//借款人图片信息service

	/**
	 * @Title: getSuffix 截取图片的后缀
	 * @Description: 返回“.”以后的内容，用于截取“.”以后的内容
	 * @time: 2017年1月5日 下午6:24:37  
	 * @return:String
	 */
	private static String getSuffix(String url) {
		if (url != null) {
			int index = url.lastIndexOf(".");
			if (index > 0) {
				return url.substring(index + 1);
			}
		}
		return url;
	}
	/**查询借款人的实名认证信息,状态
     * 
     * 
     */
	@Override
	@LogAnnotation
	public BackResult selectRealName(UserVO user) {
		/**初始化参数：根据借款人的手机号查询用户信息*/
		try {
			Result<UserVO> byMobile=userSerivce.getByMobile(user.getMobile());
			if (StatusCodes.OK != (byMobile.getStatus())) {
				logger.i("数据查询失败 。byMobile   >>>>500。。。。。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
						HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
			}
			UserVO items=byMobile.getItems();
			if(items ==null){
				logger.i("查询实名认证信息失败,通过手机号未查询到任何信息");
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),HzdStatusCodeEnum.HZD_CODE_0001.getMsg(),null);//返回“查询成功”，借款人的实名认证信息

			}
			Map<String,Object> result=new HashMap<String,Object>();
			result.put("name",items.getName());
			result.put("idCard",items.getIdCard());
			result.put("checkStatus",items.getCheckStatus());

			return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),result);//返回“查询成功”，借款人的实名认证信息

		}catch (Exception e){
			logger.e("查询实名认证状态失败, 请检查");
			e.printStackTrace();
		}
		return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),HzdStatusCodeEnum.HZD_CODE_0001.getMsg(),null);//返回“查询成功”，借款人的实名认证信息

	}
	/**保存借款人的实名认证信息
	 * 
	 */
	@Override
	@LogAnnotation
	public BackResult saveRealName(UserVO user,Map map) {
		/**初始化参数：根据借款人的手机号查询借款人信息,该信息包含“实名认证信息”*/
		Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
		if (StatusCodes.OK != byMobile.getStatus()) {
			logger.i("数据查询失败 。byMobile   >>>>500。。。。。。。。。。。 ");
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
					HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
		}
        UserVO items=byMobile.getItems();
        if(items==null){
        	logger.i("用户未注册,不能进行实名认证");
        	//返回“保存失败”，"1011"，"用户未注册"
        	return new BackResult(HzdStatusCodeEnum.HZD_CODE_1011.getCode(),HzdStatusCodeEnum.HZD_CODE_1011.getMsg(),null);
        }
        //重复提交，必须是待审核的状态，否则视为实名认证修改
        if(!BaseConfig.card_status_1.equals(items.getCheckStatus())){
	        if(StringUtils.isNotBlank(items.getName() )|| StringUtils.isNotBlank(items.getIdCard())){
	        	logger.i("用户已经提交实名认证信息,不能重复提交");
				return new BackResult(HzdStatusCodeEnum.HZD_CODE_1088.getCode(),HzdStatusCodeEnum.HZD_CODE_1088.getMsg(),null);
			}
        }
        String realName=String.valueOf(map.get("name"));
        String idCard=String.valueOf(map.get("idCard"));
        /*第一步验证：验证实名认证信息是否符合要求
         *1、“姓名”“身份证号”是否符合正则表达式的要求
         *2、“姓名”“身份证号”是否真实存在，是否对应（第2点暂时不做）
         */
        if(StringUtils.isBlank(realName) || !CustomerUtils.isNameString(realName)){		//!JudgeNumberLegal.isNameString(realName)
        	//返回“保存失败”，用户的“真实姓名”不符合要求，null
			logger.i("实名认证保存失败:用户传入姓名不合法,realName:"+realName);
        	return new BackResult(HzdStatusCodeEnum.HZD_CODE_1031.getCode(),HzdStatusCodeEnum.HZD_CODE_1031.getMsg(),null);
        }
        if(StringUtils.isBlank(idCard) || !ServiceUtil.validateIdNo(idCard)){
			logger.i("实名认证保存失败:用户传入身份证号码不合法,idCard:"+idCard);
        	//返回“保存失败”，用户的“身份证号码”不符合要求，null
        	return new BackResult(HzdStatusCodeEnum.HZD_CODE_1032.getCode(),HzdStatusCodeEnum.HZD_CODE_1032.getMsg(),null);
        }
        /*第二步验证：验证实名认证信息是否已经存在，
         *存在：该身份信息已经使用，实名认证失败
         *不存在：该身份信息没有使用，实名认证符合要求
         *请求数据：
			格式：Map<String,Object>
			参数：name，idCard
		返回数据：
			格式：Map<String,Object>
			参数：realnamerepeat（真实姓名重复的数量），idcardrepeat（身份证号码重复的数量）,allrepeat（真实姓名和身份证号码总共的重复数量）
         *//*  
        Map<String,Object> paramsMap=new HashMap<String,Object>();//初始化Map
        paramsMap.put("name", realName);//存储“真实姓名”
        paramsMap.put("idCard", idCard);//存储“身份证号”
        Map<String,Object> resultMap=userSerivce.selectNameAndIdCardRepeat(paramsMap);//查询“真实姓名”，“身份证号”重复的数量
      int realnamerepeat=(Integer) resultMap.get("realnamerepeat");//“真实姓名”重复的数量
        int idcardrepeat=(Integer) resultMap.get("idcardrepeat");//“身份证号”重复的数量
        if(realnamerepeat>0){
        	//返回“保存失败”，“真实姓名”重复，null
        	return new BackResult(HzdStatusCodeEnum.HZD_CODE_1033.getCode(),HzdStatusCodeEnum.HZD_CODE_1033.getMsg(),null);
        }
        if(idcardrepeat>0){
        	//返回“保存失败”，“身份证号码”重复，null
        	return new BackResult(HzdStatusCodeEnum.HZD_CODE_1034.getCode(),HzdStatusCodeEnum.HZD_CODE_1034.getMsg(),null);
        }*/
		Long aLong = userSerivce.selectNameAndIdCardRepeat(idCard);
		if(aLong>0){
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_1034.getCode(),HzdStatusCodeEnum.HZD_CODE_1034.getMsg(),null);//返回“保存成功”，用户的实名认证信息

		}

		/**更新借款人的实名状态*/
        UserVO updateUserVO=new UserVO();
        updateUserVO.setId(items.getId());//用户id
        updateUserVO.setName(realName);//姓名
        updateUserVO.setIdCard(idCard);//身份证号
		updateUserVO.setCheckStatus(BaseConfig.card_status_2);
        updateUserVO.setSubmitTime(sdf.format(new Date()));//提交实名认证时间
        Result<Boolean> updateResult=userSerivce.updateByPrimaryKeySelective(updateUserVO);
        /**判断更新操作结果，设置返回结果*/
        if(StatusCodes.OK==updateResult.getStatus()){//更新借款人实名认证信息成功
			logger.i("实名认证成功,手机号:"+user.getMobile());
        	return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),null);//返回“保存成功”，用户的实名认证信息
        }else{
			logger.i("实名认证失败,请检查数据源,手机号:"+user.getMobile());
        	return new BackResult(HzdStatusCodeEnum.HZD_CODE_1035.getCode(),HzdStatusCodeEnum.HZD_CODE_1035.getMsg(),null);//返回“保存失败”，null
        }
	}

    @Override
	@LogAnnotation
    public BackResult findImageInfo(UserVO user) {
        List<UserImageVO> items=null;
        if(StringUtils.isNotBlank(user.getId())){

            Result<List<UserImageVO>> UserImageVOList = userImageService.getUserId(user.getId());
            items= UserImageVOList.getItems();
            if (items == null) {
            	logger.i("查询身份认证图片失败,通过userId未查询到图片信息:"+user.getId());
                return new BackResult(HzdStatusCodeEnum.HZD_CODE_2400.getCode(),
                        HzdStatusCodeEnum.HZD_CODE_2400.getMsg());
            }
            for(UserImageVO u:items){
                u.setArtWork(ImageUrlUtil.geturl(u.getArtWork()));
            }
			logger.i("查询身份认证图片成功");
            return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),items);
        }


        return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
                HzdStatusCodeEnum.HZD_CODE_0001.getMsg(),null);
    }

    /**保存借款人上传的图片信息
	 * 需要2个参数：借款人信息，实名认证的图片信息
	 */
	@Override
	@LogAnnotation
	public BackResult saveRealNamePic(HttpServletRequest request, UserVO user, UserImageVO userImageVO) {
		if(StringUtils.isBlank(userImageVO.getImageType())){
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
					"图片类型不能为空",null);
		}
		if(StringUtils.isBlank(user.getId())){
			return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
					"用户ID不能为空",null);
		}
		Result<List<UserImageVO>> UserImageVOList = userImageService.getUserId(user.getId());
		List<UserImageVO> itemsList = UserImageVOList.getItems();

		if(itemsList != null && itemsList.size() >=3) {
			String imageId = "";
			for (UserImageVO uv : itemsList) {
				imageId = uv.getImageId();
				break;
			}
			this.userImageService.deleteByImageId(imageId);
		}



		long startTime = System.currentTimeMillis();//获取当前时间戳
		//将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		String file_url = "";
		//检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)){
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()){
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null){
					String myFileName = file.getOriginalFilename();//获取文件的名称
					try {
						if(StringUtils.isNotBlank(myFileName)){
							//synchronized (this) {
								//上传图片，上传操作完成后返回图片的路径地址：file_url
								file_url = imageServer.uploadFile(file.getBytes(), getSuffix(myFileName));
							//}
						//	userImageService
						}
						//若file_url为空，即：上传图片失败
						if(StringUtils.isBlank(file_url)){
							logger.i("身份认证上传图片失败,原因:file_url为null");
							//返回“4100”，“图片上传失败请重新上传”
							return new BackResult(HzdStatusCodeEnum.HZD_CODE_4100.getCode(), HzdStatusCodeEnum.HZD_CODE_4100.getMsg());
						}
						userImageVO.setImageId(UUIDGenerator.getUUID());//图片id
						userImageVO.setUserId(user.getId());
						userImageVO.setArtWork(file_url);//服务器存储的图片的地址
						userImageVO.setCreateTime(new Date());//创建时间

						Result<Boolean> booleanResult = userImageService.insertSelective(userImageVO);
						if (StatusCodes.OK != (booleanResult.getStatus())) {
							logger.i("身份认证上传图片失败,原因:存储失败");

							return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
									HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
						}
						long endTime = System.currentTimeMillis();
						Map   map = new HashedMap();
						map.put("url", ImageUrlUtil.geturl(file_url));
						map.put("imageType",userImageVO.getImageType());

						logger.i("上传图片运行时间：" + String.valueOf(endTime - startTime) + "ms" +file_url);
						logger.i("身份认证上传图片成功,file_url"+file_url);
						return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(), HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),map);

					} catch (Exception e) {
						logger.e("-----------系统异常,请检查数据源-------");
						e.printStackTrace();
						return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(), HzdStatusCodeEnum.HZD_CODE_9999.getMsg(),
								null);
					}
				}

			}
		}
		return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(), HzdStatusCodeEnum.HZD_CODE_0001.getMsg());
	}
}