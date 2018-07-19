package com.how2java.service.impl;

import com.how2java.mapper.ItemsMapperCustom;
import com.how2java.pojo.Items;
import com.how2java.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: ItemsServiceImpl</p>
 * <p>Description: 商品管理</p>
 * <p>Company: www.itcast.com</p>
 *
 * @version 1.0
 * @author 传智.燕青
 * @date 2015-4-13下午3:49:54
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Override
    public List<Items> findItemsList(String s) throws Exception {
        return itemsMapperCustom.findItemsList (s);
    }
}
