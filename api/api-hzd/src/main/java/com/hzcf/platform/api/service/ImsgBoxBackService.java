package com.hzcf.platform.api.service;

import com.hzcf.platform.api.common.BackResult;

/**
 * Created by leijiaming on 2017-04-13.
 */
public interface ImsgBoxBackService {

    BackResult msgBoxBack(String msgBoxBack, String checkSource, String borrowerApplyId);
}
