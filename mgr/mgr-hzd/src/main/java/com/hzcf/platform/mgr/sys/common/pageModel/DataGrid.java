package com.hzcf.platform.mgr.sys.common.pageModel;


import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author miaoxiong
 * 
 *
 * Description: 
 * 
 * EasyUI DataGrid模型
 *
 * CreateTime: 2014年5月5日  上午9:44:31
 *
 * Change History:
 *
 *        Date             CR Number              Name              Description of change
 *
 *
 */
public class DataGrid implements java.io.Serializable {
	private static final long serialVersionUID = -2277986338183947012L;
	
	private Long total = 0L;
	private List<Object> rows = new ArrayList<Object>();
	private List<Object> footer = new ArrayList<Object>();
	
	public DataGrid(Long total,List<Object> rows){
		this.total=total;
		this.rows=rows;
	}
	public DataGrid(){
		
	}
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public List<Object> getFooter() {
		return footer;
	}

	public void setFooter(List<Object> footer) {
		this.footer = footer;
	}	

}
