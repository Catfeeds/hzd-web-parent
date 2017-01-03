package com.hzcf.platform.core.user.commom.dictTools;

import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.core.user.dao.UserDictDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
public class DictUtilInitService {

    public static Map<String,List> dictionaryInfoMap = new HashMap<String, List>(); //保存国籍信息
    @Autowired
    private UserDictDao userDictDao; //受Spring 管理的Service 方法 调用Dao取数据
    @Autowired
    private ICache cache;    /**
     * Spring 容器初始化时加载
     */
    public void loadData() {

        List userDictList = userDictDao.selectList();
       

        cache.save("userDictMap", userDictList);
    }


}
