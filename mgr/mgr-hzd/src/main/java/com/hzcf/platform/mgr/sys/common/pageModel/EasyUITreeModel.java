package com.hzcf.platform.mgr.sys.common.pageModel;


import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author miaoxiong
 * 
 *
 * easyui Tree 数据模型vo
 *
 * CreateTime: 2014年6月3日  上午9:46:10
 *
 * Change History:
 *
 *        Date             CR Number              Name              Description of change
 *
 *
 */
public class EasyUITreeModel implements Serializable{
	private static final long serialVersionUID = -6197027540789074591L;
    public static String STATE_OPEN = "open"; 
    public static String STATE_CLOSED = "closed"; 
	
	private String id ;
	private String text;
	private boolean checked;
	private String iconCls;
	private String state = "open";//"open" or "close"
	private Object target;
	private String attributes; 
	private List<EasyUITreeModel> children;
	
	public EasyUITreeModel() {
		this(null,null);
	}
	
	public EasyUITreeModel(String id, String text) {
		this(id,text,STATE_OPEN);
	}
	
	public EasyUITreeModel(String id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public List<EasyUITreeModel> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITreeModel> children) {
		this.children = children;
	}
	
}
