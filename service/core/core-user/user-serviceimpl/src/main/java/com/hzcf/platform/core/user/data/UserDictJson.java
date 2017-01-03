package com.hzcf.platform.core.user.data;

/**
 * Created by leijiaming on 2017/1/3 0003.
 */
public class UserDictJson {
    private  String dict_value;
    private  String dict_text;

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

    @Override
    public String toString() {
        return "UserDictJson{" +
                "dict_value='" + dict_value + '\'' +
                ", dict_text='" + dict_text + '\'' +
                '}';
    }
}
