package com.how2java.service;

import com.how2java.pojo.Items;

import java.util.List;

/**
 * 
 * <p>Title: ItemsService</p>
 * <p>Description:商品管理service </p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2015-4-13下午3:48:09
 * @version 1.0
 */
public interface ItemsService {
	
	//商品查询列表
	public List<Items> findItemsList(String s) throws Exception;
	


}
