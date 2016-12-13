package com.hzcf.platform.api.user.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;

/**
 * vo转换成formBean的工具类
 * @author gavin
 *
 */
public class FormBeanHelper {
	public  static <T,V> T convertVO2FormBean(Class<T> formBeanClazz,V vo){
		T purchaseOrderForm = null;
		try {
			purchaseOrderForm = formBeanClazz.newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
		try {
			BeanUtils.copyProperties(purchaseOrderForm,vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return purchaseOrderForm;
	}
	
	public  static <T,V> List<T> convertVOList2FormBeanList(Class<T> formBeanClazz,List<V> voList){
		if(CollectionUtils.isEmpty(voList)){
			return null;
		}
		List<T> formList = new ArrayList<>(voList.size());
		for(V vo : voList){
			T form = convertVO2FormBean(formBeanClazz,vo);
			formList.add(form);
		}
		return formList;
	}
	
	public  static <T,V> Result<V> convertVOResult2FormResult(Class<V> formBeanClazz,Result<T> voResult){
		if(voResult == null){
			return null;
		}
		Result<V> formResult = new Result<V>();
		formResult.setStatus(voResult.getStatus());
		formResult.setMsg(voResult.getMsg());
		formResult.setItems(convertVO2FormBean(formBeanClazz,voResult.getItems()));
		return formResult;
	}
	
	public  static <T,V> PaginatedResult<V> convertVOPagingResult2FormResult(
			Class<V> formBeanClazz,PaginatedResult<T> voResult){
		if(voResult == null){
			return null;
		}
		PaginatedResult<V> formResult = new PaginatedResult<V>();
		formResult.setStatus(voResult.getStatus());
		formResult.setPaginate(voResult.getPaginate());
		formResult.setItems(convertVOList2FormBeanList(formBeanClazz,voResult.getItems()));
		return formResult;
	}
}
