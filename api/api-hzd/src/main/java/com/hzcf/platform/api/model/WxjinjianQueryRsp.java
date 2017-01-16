package com.hzcf.platform.api.model;


import java.io.Serializable;
import java.util.List;
/**
 *
 * @description:进件请求参数
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月8日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public class WxjinjianQueryRsp extends ServiceStatus implements Serializable{
	private static final long serialVersionUID = 2978365969221886180L;
	private String mtStatusCode;//状态code 0:(冻结、放款撤销、放款成功、客户放弃、拒贷、结清) 1：(其他所有)
	private List<WeiXinApplyList> weiXinApplyList;

	public String getMtStatusCode() {
		return mtStatusCode;
	}

	public void setMtStatusCode(String mtStatusCode) {
		this.mtStatusCode = mtStatusCode;
	}

	public List<WeiXinApplyList> getWeiXinApplyList() {
		return weiXinApplyList;
	}



	public void setWeiXinApplyList(List<WeiXinApplyList> weiXinApplyList) {
		this.weiXinApplyList = weiXinApplyList;
	}



	public static class  WeiXinApplyList{
		/**
		 * 
		 */
		
		private String weiXinApplicationStatus;
		private String idcard;
		private String createTime;



		public String getWeiXinApplicationStatus() {
			return weiXinApplicationStatus;
		}
		public void setWeiXinApplicationStatus(String weiXinApplicationStatus) {
			this.weiXinApplicationStatus = weiXinApplicationStatus;
		}
		public String getIdcard() {
			return idcard;
		}

		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

	}
	
	
	

}
