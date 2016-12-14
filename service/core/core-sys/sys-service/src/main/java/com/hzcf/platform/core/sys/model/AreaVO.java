/**
 * 
 */
package com.hzcf.platform.core.sys.model;

import com.hzcf.platform.common.model.BaseVO;

/**
 * @author allen.shen
 * @Date 2015年10月15日
 * 
 * description: 
 */
public class AreaVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer parentId;
	
	private String areaCode;
	
	private String provinceName;
	
	private String cityName;
	
	private String countyName;
	
	private Integer type;
	
	private Integer tuiguang;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTuiguang() {
		return tuiguang;
	}

	public void setTuiguang(Integer tuiguang) {
		this.tuiguang = tuiguang;
	}

	@Override
	public String toString() {
		return "AreaVO {parentId=" + parentId + ", areaCode=" + areaCode
				+ ", provinceName=" + provinceName + ", cityName=" + cityName
				+ ", countyName=" + countyName + ", type=" + type
				+ ", tuiguang=" + tuiguang + "}";
	}

	
}
