package com.how2java.mapper;

import com.how2java.pojo.Items;

import java.util.List;

public interface ItemsMapperCustom {
    //商品查询列表
	public List<Items> findItemsList(String s)throws Exception;
}