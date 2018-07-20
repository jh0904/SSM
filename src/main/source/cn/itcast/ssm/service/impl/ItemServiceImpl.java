package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.mapper.ItemsMapperCustom;
import cn.itcast.ssm.po.ItemsCustom;
import cn.itcast.ssm.po.ItemsQueryVo;
import cn.itcast.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * cn.itcast.ssm.service.impl
 *
 * @author jh
 * @date 2018/7/19 13:49
 * description:
 */
public class ItemServiceImpl implements ItemsService {
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;


    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsMapperCustom.findItemsList (itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {

    }
}
