package com.hzcf.platform.mgr.sys.common.pageModel;


/**
 * 
 * @author miaoxiong
 * 
 *
 * Description: .
 * EasyUI 分页帮助类
 *
 * CreateTime: 2014年5月5日  上午9:45:01
 *
 * Change History:
 *
 *        Date             CR Number              Name              Description of change
 *
 *
 */
public class PageHelper implements java.io.Serializable {

	private static final long serialVersionUID = 8999870540976178441L;
	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段
	private String order;// asc/desc

	private int start ;// 起始页
	private int end ;// 最终页
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	/**
	 * 根据页码和“每页显示记录数”
	 * 计算mysql limit 分页时的开始行
	 */
	public static int getRow(int page,int rowsInPerPage){
		if(page <= 0){
			return 0;
		}
		
		return (page-1)*rowsInPerPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	

}
