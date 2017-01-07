package com.hzcf.platform.core.user.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by leijiaming on 2017/1/3 0003.
 */
public class UserDictJson implements Serializable{
    private static final long serialVersionUID = -4272715414866892350L;
    private  String dict_value;
    private  String dict_text;
    private Map<String,Object> map;

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

    public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	@Override
    public String toString() {
        return "UserDictJson{" +
                "dict_value:'" + dict_value + '\'' +
                ", dict_text:'" + dict_text + '\'' +
                '}';
    }
}
