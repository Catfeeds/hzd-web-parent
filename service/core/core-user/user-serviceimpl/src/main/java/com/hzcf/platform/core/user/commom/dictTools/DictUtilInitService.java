package com.hzcf.platform.core.user.commom.dictTools;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.core.user.dao.UserDictDao;
import com.hzcf.platform.core.user.data.UserDict;
import com.hzcf.platform.core.user.data.UserDictJson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
public class DictUtilInitService {

    @Autowired
    private UserDictDao userDictDao; //受Spring 管理的Service 方法 调用Dao取数据
    @Autowired
    private ICache cache;    /**
     * Spring 容器初始化时加载
     */
    public void loadData() {

        List<UserDict> userDicts = userDictDao.selectJkytList();
        Map<String, Object> stringObjectMap = initJkytDictinfo(userDicts);
        System.out.println("----------1-------"+stringObjectMap.toString());
       // System.out.println("----------2-------"+stringObjectMap1.toString());
    }


    /**
     *
     */
    public  Map<String,Object> initUserDictinfo(List<UserDict> userDictList){
        Map<String,Object> dictionaryInfoMap = new HashMap<String, Object>(); //保存国籍信息

        String key = "";
        //Map<String,Object> tempMap = new HashMap();
        List<UserDictJson> tempList= new  ArrayList();
        UserDictJson userDictJson = null;
        for(UserDict userDice:userDictList ){
            key = userDice.getDictType();
            if(dictionaryInfoMap.containsKey(key)){
                userDictJson = new UserDictJson();
                tempList = (List)dictionaryInfoMap.get(key);
                userDictJson.setDict_value(userDice.getDictValue());
                userDictJson.setDict_text(userDice.getDictText());
                tempList.add(userDictJson);
                //tempMap.put(key, userDictJson);
                dictionaryInfoMap.put(userDice.getDictType(),tempList);
            }else{
                tempList = new ArrayList<UserDictJson>();
                userDictJson = new UserDictJson();
                userDictJson.setDict_value(userDice.getDictValue());
                userDictJson.setDict_text(userDice.getDictText());
                tempList.add(userDictJson);

                dictionaryInfoMap.put(userDice.getDictType(), tempList);
            }

        }

      return dictionaryInfoMap;
    }
    public  Map<String,Object> initJkytDictinfo(List<UserDict> userDictList){
        Map<String,Object> dictionaryInfoMap = new HashMap<String, Object>();
        List<UserDictJson> tempList= new  ArrayList();
        List<Map> mapList= new  ArrayList();
        Map<String,Object> Map = new HashMap<String, Object>();
        String key = "";
        String  dictId = "";
        UserDictJson userDictJson = null;
        for(UserDict userDice:userDictList ){
            if(dictionaryInfoMap.containsKey(key)){
                userDictJson = new UserDictJson();
                tempList = (List)dictionaryInfoMap.get(key);
                userDictJson.setDict_value(userDice.getDictValue());
                userDictJson.setDict_text(userDice.getDictText());
                tempList.add(userDictJson);
                //tempMap.put(key, userDictJson);
                dictionaryInfoMap.put(userDice.getDictType(),tempList);
            }else{
                tempList = new ArrayList<UserDictJson>();
                userDictJson = new UserDictJson();
                userDictJson.setDict_value(userDice.getDictValue());
                userDictJson.setDict_text(userDice.getDictText());
                if(userDice.getDictSort().equals("")){
                    dictionaryInfoMap.put(userDice.getDictType(),userDictJson);
                }else{
                    tempList.add(userDictJson);

                    dictionaryInfoMap.put(userDice.getDictType(), tempList);
                }


            }
        }


        return dictionaryInfoMap;
    }
}
