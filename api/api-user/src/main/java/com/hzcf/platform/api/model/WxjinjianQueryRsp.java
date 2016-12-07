package com.hzcf.platform.api.model;


import java.io.Serializable;
import java.util.List;

public class WxjinjianQueryRsp extends BaseState implements Serializable{
	private static final long serialVersionUID = 2978365969221886180L;
	
	private List<WeiXinApplyList> weiXinApplyList;
	
	
	
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
		private String createTime;
		public String getWeiXinApplicationStatus() {
			return weiXinApplicationStatus;
		}
		public void setWeiXinApplicationStatus(String weiXinApplicationStatus) {
			this.weiXinApplicationStatus = weiXinApplicationStatus;
		}
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		
	}
	
	
	

}
