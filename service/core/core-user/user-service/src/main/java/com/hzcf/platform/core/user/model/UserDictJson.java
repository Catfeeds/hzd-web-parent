package com.hzcf.platform.core.user.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by leijiaming on 2017/1/3 0003.
 */
public class UserDictJson implements Serializable{
    private static final long serialVersionUID = -4272715414866892350L;
    private  String dict_value;
    private  String dict_text;
    //private Map<String,Object> map;
    private List<UserDictJson> list;
    
    //网内/网外
    private String isInside;
    //电话区号
    private String areacode;
    //邮编
    private String postcode; 

    public String getDict_value() {
        return dict_value;
    }

    public void setDict_value(String dict_value) {
        this.dict_value = dict_value;
    }

    public String getDict_text() {
        return dict_text;
    }

    public void setDict_text(String dict_text) {
        this.dict_text = dict_text;
    }
/*
    public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
*/
	public List<UserDictJson> getList() {
		return list;
	}

	public void setList(List<UserDictJson> list) {
		this.list = list;
	}

	@Override
    public String toString() {
        return "UserDictJson{" +
                "dict_value:'" + dict_value + '\'' +
                ", dict_text:'" + dict_text + '\'' +
                '}';
    }

	public String getIsInside() {
		return isInside;
	}

	public void setIsInside(String isInside) {
		this.isInside = isInside;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
}
