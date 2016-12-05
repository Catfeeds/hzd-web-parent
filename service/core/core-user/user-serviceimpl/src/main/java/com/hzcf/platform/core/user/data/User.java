package com.hzcf.platform.core.user.data;

import java.math.BigDecimal;
import java.util.Date;

import com.hzcf.platform.common.model.DataEntity;

public class User  extends DataEntity{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
