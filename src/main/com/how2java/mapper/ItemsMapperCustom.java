package com.how2java.mapper;

import com.how2java.pojo.ItemsCustom;
import com.how2java.pojo.ItemsQueryVo;

import java.util.List;

public interface ItemsMapperCustom {
    //商品查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}