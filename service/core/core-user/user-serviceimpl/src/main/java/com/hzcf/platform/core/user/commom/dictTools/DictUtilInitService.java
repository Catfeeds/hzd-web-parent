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
        //  KYE applyDictionaryJkyt 借款用途
        //  KYE applyDictionaryinfo 个人信息
        //  KYE applyDictionaryRegion 省市区
        List<UserDict> userDicts = userDictDao.selectJkytList();
        Map<String, Object> stringObjectMap = initLoanuse(userDicts);
        String applyDictionaryJkyt = JsonUtil.json2String(stringObjectMap);
        cache.save("applyDictionaryJkyt",applyDictionaryJkyt);


    }
    
    
    /**
     * 借款用途
     * @return
     */
    public  Map<String,Object> initLoanuse(List<UserDict> userDictList){
    	Map<String,Object> dictionaryInfoMap = new HashMap<String, Object>(); 
        String key = "";
        String pid = "";
        Map<String,Object> faMap=new HashMap<String, Object>(); ; 
        List<UserDictJson> tempList= new ArrayList();
        UserDictJson jsonTemp = null;
        UserDictJson userDictJson = null;
        for(UserDict userDice:userDictList ){
        	//父ID
            if(userDice.getPid() != null){
            	key = userDice.getDictType();
            	
            	if(pid.equals(userDice.getPid().toString())){
            		userDictJson = new UserDictJson();
            		userDictJson.setDict_value(userDice.getDictValue());
                    userDictJson.setDict_text(userDice.getDictText());
                    dictionaryInfoMap.put(key, userDictJson);
            	}else{
            		if(faMap.containsKey(key)){
                        userDictJson = new UserDictJson();
                        tempList = (List)faMap.get(key);
                        userDictJson.setDict_value(userDice.getDictValue());
                        userDictJson.setDict_text(userDice.getDictText());
                        tempList.add(userDictJson);
                        //tempMap.put(key, userDictJson);
                        faMap.put(userDice.getDictType(),tempList);
                        
                        jsonTemp = (UserDictJson) dictionaryInfoMap.get(key);
                        jsonTemp.setMap(faMap);
                        dictionaryInfoMap.put(key, jsonTemp);
                    }else{
                        tempList = new ArrayList<UserDictJson>();
                        userDictJson = new UserDictJson();
                        userDictJson.setDict_value(userDice.getDictValue());
                        userDictJson.setDict_text(userDice.getDictText());
                        tempList.add(userDictJson);
                        faMap.put(userDice.getDictType(),tempList);
                    }
            		
            	}
            	
            }else{
            	pid = userDice.getDictId();
            }
        }

      return dictionaryInfoMap;
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
