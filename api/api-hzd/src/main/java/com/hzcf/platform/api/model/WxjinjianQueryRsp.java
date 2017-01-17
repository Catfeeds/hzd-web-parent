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

	/*
         * StatusCodeWFXZ
         * 0:(允许再次进件)移动端无进件记录、进件记录全部为已处理、
         * 1:(不允许进件)移动端有意向申请且为未处理
         * */
	private String statusCodeWFXZ;
	/*
	 * StatusCodeApplyOnLine
	 * 0:(允许进件)无正式进件数据、全部进件数据的状态都在(冻结、放款撤销、放款成功、客户放弃、拒贷、结清)五个里面
	 * 1:(不允许进件)有正式进件数据不符合上述五个状态
	 * */
	private String statusCodeApplyOnLine;



	private List<WeiXinApplyList> weiXinApplyList;

	public String getStatusCodeWFXZ() {
		return statusCodeWFXZ;
	}

	public void setStatusCodeWFXZ(String statusCodeWFXZ) {
		this.statusCodeWFXZ = statusCodeWFXZ;
	}

	public String getStatusCodeApplyOnLine() {
		return statusCodeApplyOnLine;
	}

	public void setStatusCodeApplyOnLine(String statusCodeApplyOnLine) {
		this.statusCodeApplyOnLine = statusCodeApplyOnLine;
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
		/*
	 * isWFXZFlag
	 * 0:移动端意向申请状态
	 * 1：正式进件的状态
	 * */
		private String isWFXZFlag;

		private String createTime;

		public String getIsWFXZFlag() {
			return isWFXZFlag;
		}

		public void setIsWFXZFlag(String isWFXZFlag) {
			this.isWFXZFlag = isWFXZFlag;
		}

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
