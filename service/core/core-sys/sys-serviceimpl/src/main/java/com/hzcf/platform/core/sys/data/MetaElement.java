package com.hzcf.platform.core.sys.data;

import com.hzcf.platform.common.model.DataEntity;

public class MetaElement extends DataEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 元数据名称
	 */
	private String dataName;
	/**
	 * 数据编码，1--输入类，2--选项类
	 */
	private String dataCode;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 是否可用
	 */
	private Integer enable;
	

	/**
	 * 数据内容 中文
	 */
	private String ContentCn;

	/**
	 * 数据内容 英文
	 */
	private String ContentEn;
	/**
	 * 索引
	 */
	private Integer position;

	/**
	 * 备注信息
	 */
	private String remarks;
	
	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getContentCn() {
		return ContentCn;
	}

	public void setContentCn(String contentCn) {
		ContentCn = contentCn;
	}

	public String getContentEn() {
		return ContentEn;
	}

	public void setContentEn(String contentEn) {
		ContentEn = contentEn;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getposition() {
		return position;
	}

	public void setposition(Integer position) {
		this.position = position;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


}
