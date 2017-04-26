package com.hzcf.platform.api.service.impl;


import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.service.IApplyImgUrInfoUrlService;
import com.hzcf.platform.api.util.ImageUrlUtil;
import com.hzcf.platform.api.util.StringUtil;
import com.hzcf.platform.api.util.UploadImgUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.core.user.service.UserImageService;
import com.hzcf.platform.webService.LoadService;
import com.hzcf.platform.webService.model.PatchBoltImage;
import com.imageserver.ImageServer;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by leijiaming on 2017/1/9 0009.
 */
@Service
public class ApplyImgUrInfoServiceImpl implements IApplyImgUrInfoUrlService {
    private static final Log logger = Log.getLogger(ApplyImgUrInfoServiceImpl.class);
    //身份证明:B1  征信报告:E1  工作证明:D8  收入证明:C8  个人住址证明:F7  社保公积金证明:C3  其他资料:L5
    private final String B1 = "B1";
    private final String E1 = "E1";
    private final String D8 = "D8";
    private final String C8 = "C8";
    private final String F7 = "F7";
    private final String L5 = "L5";
    private final String C3 = "C3";

    @Autowired
    UserImageService userImageService;
    @Autowired
    ImageServer imageServer;
    @Autowired
    UserApplyInfoSerivce userApplyInfoSerivce;
    @Autowired
    UploadImgUtil uploadImgUtil;

    @Override
    @LogAnnotation
    public BackResult deleteImgUrl(UserVO userVO, UserImageVO userImageVO) {


        if (StringUtils.isBlank(userImageVO.getArtWork())) {
            logger.i("artWork为空");
            return new BackResult(HzdStatusCodeEnum.HZD_CODE_9000.getCode(),
                    "artWork为空", null);
        }
        String sufFirst = StringUtil.replaceAll(userImageVO.getArtWork());


        try {
            if(StringUtils.isBlank(userImageVO.getApplyId())){
                imageServer.deleteFile(sufFirst);
                logger.i("删除图片成功");
                return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),
                        HzdStatusCodeEnum.HZD_CODE_0000.getMsg(), null);
            }

            userImageVO.setUserId(userVO.getId());
            userImageVO.setArtWork(sufFirst);
            Result<Boolean> booleanResult = userImageService.deleteByPrimaryKey(userImageVO);
            if (StatusCodes.OK == (booleanResult.getStatus())) {

                boolean b = imageServer.deleteFile(sufFirst);
                if (!b) {
                    logger.i("删除图片失败  图片服务器异常 url："+sufFirst);
                    return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(),
                            HzdStatusCodeEnum.HZD_CODE_9999.getMsg(), null);
                }

                logger.i("删除图片成功");
                return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),
                        HzdStatusCodeEnum.HZD_CODE_0000.getMsg(), null);
            }
        } catch (Exception e) {

            logger.e("删除图片失败  服务器异常" + e);
            return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_9999.getMsg(), null);
        }
        logger.i("删除图片失败");
        return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
                HzdStatusCodeEnum.HZD_CODE_0001.getMsg(), null);
    }

    @Override
    @LogAnnotation
    public BackResult queryImgByApplyId(String applyId) {

        Map<String,Object> map = new HashMap<String,Object>();
        List<UserImageVO> listB1 = new ArrayList<UserImageVO>();
        List<UserImageVO> listE1 = new ArrayList<UserImageVO>();
        List<UserImageVO> listD8 = new ArrayList<UserImageVO>();
        List<UserImageVO> listC8 = new ArrayList<UserImageVO>();
        List<UserImageVO> listF7 = new ArrayList<UserImageVO>();
        List<UserImageVO> listL5 = new ArrayList<UserImageVO>();
        List<UserImageVO> listC3 = new ArrayList<UserImageVO>();

        Result<List<UserImageVO>> listResult =
                userImageService.selectByApplyId(applyId);
        if(StatusCodes.OK!=listResult.getStatus()){
            logger.e("查询图片信息失败：applyId:"+applyId);
            return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_0001.getMsg(), null);
        }

        if(listResult.getItems().size()>0){
            for(UserImageVO userImage:listResult.getItems()){
                userImage.setArtWork(ImageUrlUtil.geturl(userImage.getArtWork()));
                if(B1.equals(userImage.getImageType())){
                    listB1.add(userImage);
                }
                else if(E1.equals(userImage.getImageType())){
                    listE1.add(userImage);
                }
                else if(D8.equals(userImage.getImageType())){
                    listD8.add(userImage);
                }
                else if(C8.equals(userImage.getImageType())){
                    listC8.add(userImage);
                }
                else if(F7.equals(userImage.getImageType())){
                    listF7.add(userImage);
                }
                else if(C3.equals(userImage.getImageType())){
                    listC3.add(userImage);
                }
                else {
                        listL5.add(userImage);
                }

            }
            map.put("B1",listB1);
            map.put("E1",listE1);
            map.put("D8",listD8);
            map.put("C8",listC8);
            map.put("F7",listF7);
            map.put("L5",listL5);
            map.put("C3",listC3);
        }
        logger.i("查询图片信息成功：applyId:"+applyId);
        return  new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),
                HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),map.size()>0?map:null);


    }

    @Override
    @LogAnnotation
    public BackResult saveImgByApplyId(UserVO userVO, String applyId, List<UserImageVO> userImage) {

        List<PatchBoltImage>  patchBoltImageList = new ArrayList<>();

        Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(applyId);
        String borrowerApplyId = userApplyInfoVOResult.getItems().getBorrowerApplyId();


        for (UserImageVO u:userImage){
            //组装线下数据
            PatchBoltImage  patchBoltImage  = new PatchBoltImage();
            patchBoltImage.setArtWork(u.getArtWork());
            patchBoltImage.setDisplayName(u.getImageType()+"-"+u.getArtWork().substring(u.getArtWork().lastIndexOf(".")-5));//图片名称，示例：B1-G8020.JPG
            patchBoltImage.setImageType(u.getImageType());
            patchBoltImageList.add(patchBoltImage);

            //保存本地数据
            u.setImageId(UUIDGenerator.getUUID());
            u.setUserId(userVO.getId());
            u.setApplyId(applyId);
            u.setType("1");
            u.setCreateTime(new Date());
            Result<Boolean> booleanResult = userImageService.insertSelective(u);
            if (StatusCodes.OK != (booleanResult.getStatus())) {
                logger.d("保存本地图片失败----------------------：" + u.getArtWork() + "---" + "手机号:" + userVO.getMobile());
                return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
                        HzdStatusCodeEnum.HZD_CODE_0001.getMsg(), null);
            }

            String result = LoadService.applyPatchBolt(patchBoltImageList,borrowerApplyId);


            if (StringUtils.isBlank(result)) {
                logger.w("补充资料线下接口返回异常,result 为 null--手机号:" + userVO.getMobile());
                return new BackResult(HzdStatusCodeEnum.HZD_CODE_2100.getCode(),
                        HzdStatusCodeEnum.HZD_CODE_2100.getMsg(), null);
            }
            JSONObject json = JSONObject.fromObject(result);
            //WxjinjianQueryRsp wxrsp =JsonUtil.string2Object(json.toString(),WxjinjianQueryRsp.class);
            String retCode = json.getString("retCode");
            String retInfo = json.getString("retInfo");
            if (!retCode.equals("0000")) {
                logger.d("补充资料线下接口失败 手机号:" + userVO.getMobile());
                return new BackResult(HzdStatusCodeEnum.HZD_CODE_6101.getCode(),
                        HzdStatusCodeEnum.HZD_CODE_6101.getMsg(), null);

            }

            logger.i("补充资料线下接口成功 手机号:" + userVO.getMobile());


            //更新本地进件状态信息
            UserApplyInfoVO userApplyInfoVO = new UserApplyInfoVO();
            userApplyInfoVO.setApplyId(applyId);
            userApplyInfoVO.setAdditionalSubmitTime(new Date());
            userApplyInfoVO.setAdditionalStatus("1");//待补充状态0 已补充状态1


            Result<Boolean> userApplyInfoSerivceResult = userApplyInfoSerivce.updateApplyId(userApplyInfoVO);
            if (StatusCodes.OK != userApplyInfoSerivceResult.getStatus()) {
                logger.d("补充资料失败 userApplyInfoSerivce信息失败 ---ApplyId：" + applyId);
                return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
                        HzdStatusCodeEnum.HZD_CODE_0001.getMsg(), null);
            }
        }
        return  new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),
                HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),null);

    }

    @Override
    @LogAnnotation
    public BackResult uploadImg(HttpServletRequest request) {
        Map map  = new HashMap();
        String url = uploadImgUtil.upLoadImg(request);
        if(StringUtils.isNotBlank(url)){
            map.put("url", ConstantsDictionary.imgUpload+url);
            return  new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_0000.getMsg(),map);
        }
        return  new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
                HzdStatusCodeEnum.HZD_CODE_0001.getMsg(),map);
    }
}
