package com.hzcf.platform.core.user.commom.dictTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.core.user.dao.DistrictDao;
import com.hzcf.platform.core.user.dao.UserDictDao;
import com.hzcf.platform.core.user.data.District;
import com.hzcf.platform.core.user.data.UserDict;
import com.hzcf.platform.core.user.model.UserDictJson;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
public class DictUtilInitService {

    @Autowired
    private UserDictDao userDictDao; //受Spring 管理的Service 方法 调用Dao取数据
    @Autowired
    private DistrictDao districtDao;
    @Autowired
    private ICache cache;    /**
     * Spring 容器初始化时加载
     */
    public final static String AREA_CODE = "00";	//区县
    public final static String CITY_CODE = "0000";	//市
    
    public void loadData() {
        //  KYE applyDictionaryJkyt 借款用途
        //  KYE applyDictionaryinfo 个人信息
        //  KYE applyDictionaryRegionsheng 省
        //  KYE applyDictionaryRegionshi 市
        //  KYE applyDictionaryRegioqu 区
       /*List<UserDict> userDicts = userDictDao.selectJkytList();
        Map<String, Object> stringObjectMap = initLoanuse(userDicts);
        cache.save("applyDictionaryJkyt",stringObjectMap);*/

    	//借款用途
    	List<UserDict> userDicts = userDictDao.selectJkytList();
        List<UserDictJson> applyDictionaryJkyt = initLoanuse(userDicts);
        cache.save("applyDictionaryJkyt",applyDictionaryJkyt);
        System.out.println("applyDictionaryJkyt"+applyDictionaryJkyt);




        List<UserDict> userDicts1 = userDictDao.selectList();
        Map<String, Object> applyDictionaryinfo = initUserDictinfo(userDicts1);
        cache.save("applyDictionaryinfo",applyDictionaryinfo);
        System.out.println("applyDictionaryinfo"+applyDictionaryinfo);



    	//省
        List<District> userDicts2 = districtDao.selectAllProvince();
        List<UserDictJson> applyDictionaryRegionsheng = initProvince(userDicts2);
        cache.save("applyDictionaryRegionsheng",applyDictionaryRegionsheng);
        System.out.println("applyDictionaryRegionsheng"+applyDictionaryRegionsheng);


    	//市
    	List<District> userDicts3 = districtDao.selectAllCity();
        Map<String, Object> applyDictionaryRegionshi = initCity(userDicts3);
        cache.save("applyDictionaryRegionshi",applyDictionaryRegionshi);
        System.out.println("applyDictionaryRegionshi"+applyDictionaryRegionshi);



        //区县
    	List<District> userDicts4 = districtDao.selectAllArea();
        Map<String, Object> applyDictionaryRegionqu = initArea(userDicts4);
        cache.save("applyDictionaryRegionqu",applyDictionaryRegionqu);
        System.out.println("applyDictionaryRegionqu"+applyDictionaryRegionqu);

    }

    /**
     * 区县 
     * @param distList
     * @return
     */
    public  Map<String,Object> initArea(List<District> distList){
    	Map<String,Object> dictionaryInfoMap = new HashMap<String, Object>(); 
        String key = "";
        String pid = "";
        List<UserDictJson> tempList=null;
        UserDictJson userDictJson = null;
        String temp = "";
        for(District userDice:distList){
        	temp = userDice.getValue().substring(userDice.getValue().length() -2, userDice.getValue().length());
        	if(AREA_CODE.equals(temp)){
        		pid = userDice.getId();
        		key = userDice.getValue();
        		tempList= new ArrayList();
        		//MAP KEY
        		dictionaryInfoMap.put(key,tempList);
        	}else{
	    		if(pid.equals(userDice.getPid())){
	    			tempList = (List)dictionaryInfoMap.get(key);
	                userDictJson = new UserDictJson();
	                userDictJson.setDict_value(userDice.getValue());
	                userDictJson.setDict_text(userDice.getName());
	                tempList.add(userDictJson);
	                dictionaryInfoMap.put(key,tempList);
	            }
        	}
        }
      return dictionaryInfoMap;
    }

    /**
     * 市 
     * 7100台湾省/810000香港特别行政区/820000/澳门特别行政区  没有市
     * @param distList
     * @return
     */
    public  Map<String,Object> initCity(List<District> distList){
    	Map<String,Object> dictionaryInfoMap = new HashMap<String, Object>(); 
        String key = "";
        String pid = "";
        List<UserDictJson> tempList=null;
        UserDictJson userDictJson = null;
        String temp="";
        for(District userDice:distList){
        	temp = userDice.getValue().substring(userDice.getValue().length() -4, userDice.getValue().length());
        	if(CITY_CODE.equals(temp)){
        		pid = userDice.getId();
        		key = userDice.getValue();
        		tempList= new ArrayList();
        		//MAP KEY
        		dictionaryInfoMap.put(key,tempList);
        	}else{
	    		if(pid.equals(userDice.getPid())){
	    			tempList = (List)dictionaryInfoMap.get(key);
	                userDictJson = new UserDictJson();
	                userDictJson.setDict_value(userDice.getValue());
	                userDictJson.setDict_text(userDice.getName());
	                tempList.add(userDictJson);
	                dictionaryInfoMap.put(key,tempList);
	            }
        	}
        }
      return dictionaryInfoMap;
    }

    
    /**
     * 省份
     * @param distList
     * @return
     */
    public List<UserDictJson> initProvince(List<District> distList){
        Map<String,Object> dictionaryInfoMap = new HashMap<String, Object>(); 
        List<UserDictJson> tempList= new ArrayList();
        UserDictJson userDictJson = null;
        for(District dist :distList ){
        	userDictJson = new UserDictJson();
        	userDictJson.setDict_value(dist.getValue());
        	userDictJson.setDict_text(dist.getName());
        	tempList.add(userDictJson);
        }
        //dictionaryInfoMap.put("province", tempList);
        return tempList;
    }
    
   
    
    //借款用途
    public  List<UserDictJson> initLoanuse(List<UserDict> userDictList){
    	Map<String,Object> dictionaryInfoMap = new HashMap<String, Object>(); 
        String key = "";
        String pid = "";
        List<UserDictJson> tempList=new ArrayList();;
        List<UserDictJson> neiList = null;
        UserDictJson userDictJson = null;
        for(UserDict userDice:userDictList){
            //子类
        	if(userDice.getDictValue().length() > 2){
        		for(int i=0;i<tempList.size();i++){
        			UserDictJson json = (UserDictJson)tempList.get(i);
        			//找到父类
        			if(userDice.getDictValue().indexOf(json.getDict_value()) > -1){
        				neiList = json.getList();
        				userDictJson = new UserDictJson();
        				userDictJson.setDict_value(userDice.getDictValue());
        				userDictJson.setDict_text(userDice.getDictText());
        				neiList.add(userDictJson);
        				tempList.set(i, json);
        			}
        		}
        	//父类
        	}else{
        		userDictJson = new UserDictJson();
                userDictJson.setDict_value(userDice.getDictValue());
                userDictJson.setDict_text(userDice.getDictText());
                neiList = new ArrayList();
                userDictJson.setList(neiList);
                tempList.add(userDictJson);
        	}
        }
    	return tempList;
    }
    
    
    /**
     * 借款用途
     * @return
     
    public  Map<String,Object> initLoanuse(List<UserDict> userDictList){
    	Map<String,Object> dictionaryInfoMap = new HashMap<String, Object>(); 
        String key = "";
        String pid = "";
        List<UserDictJson> tempList=null;
        UserDictJson userDictJson = null;
        for(UserDict userDice:userDictList){
        	if(userDice.getDictValue().length() == 1){
        		pid = userDice.getDictId();
        		key = userDice.getDictValue();
        		tempList= new ArrayList();
        		//MAP KEY
        		dictionaryInfoMap.put(key,tempList);
        	}else{
	    		if(pid.equals(userDice.getPid()==null?"":userDice.getPid().toString())){
	    			tempList = (List)dictionaryInfoMap.get(key);
	                userDictJson = new UserDictJson();
	                userDictJson.setDict_value(userDice.getDictValue());
	                userDictJson.setDict_text(userDice.getDictText());
	                tempList.add(userDictJson);
	                dictionaryInfoMap.put(key,tempList);
	            }
        	}
        }
        return dictionaryInfoMap;
    }*/
    

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
/*    public  Map<String,Object> initJkytDictinfo(List<UserDict> userDictList){
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
    }*/
}
