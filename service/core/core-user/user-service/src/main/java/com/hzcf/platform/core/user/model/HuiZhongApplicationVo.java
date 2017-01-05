package com.hzcf.platform.core.user.model;

import java.util.List;

/**
  * @Description:
  * 	HuiZhongApplicationVo：对应线下的“借款人信息”，用于线上和线下的对接转换
  * @author 作者:裴高祥 E-mail:pgx19890112@163.com Tel:13241706779
  * @date 创建时间：2017年1月5日 上午11:06:00 
  * @version 1.0 
  * @since  JDK1.7
  */
public class HuiZhongApplicationVo {
	private String systemId;//进件标识
	private String employeeId;//员工编号
	private String operatorId;//操作人ID
	private MainBorrowerVO mainBorrowerVO;//主借款人数据集合
	private List<ImageVo> imageVoList;//图片集合
	public HuiZhongApplicationVo() {
		super();
	}
	public HuiZhongApplicationVo(String systemId, String employeeId, String operatorId, MainBorrowerVO mainBorrowerVO,
			List<ImageVo> imageVoList) {
		super();
		this.systemId = systemId;
		this.employeeId = employeeId;
		this.operatorId = operatorId;
		this.mainBorrowerVO = mainBorrowerVO;
		this.imageVoList = imageVoList;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public MainBorrowerVO getMainBorrowerVO() {
		return mainBorrowerVO;
	}
	public void setMainBorrowerVO(MainBorrowerVO mainBorrowerVO) {
		this.mainBorrowerVO = mainBorrowerVO;
	}
	public List<ImageVo> getImageVoList() {
		return imageVoList;
	}
	public void setImageVoList(List<ImageVo> imageVoList) {
		this.imageVoList = imageVoList;
	}
	@Override
	public String toString() {
		return "HuiZhongApplicationVo [systemId=" + systemId + ", employeeId=" + employeeId + ", operatorId="
				+ operatorId + ", mainBorrowerVO=" + mainBorrowerVO + ", imageVoList=" + imageVoList + "]";
	}
}