package com.how2java.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.mapper.CategoryMapper;
import com.how2java.mapper.ItemsMapper;
import com.how2java.mapper.ItemsMapperCustom;
import com.how2java.pojo.Category;
import com.how2java.pojo.Items;
import com.how2java.pojo.ItemsCustom;
import com.how2java.pojo.ItemsQueryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	@Autowired
	private ItemsMapper itemsMapper;


	@Test
	public void testList() {
		PageHelper.offsetPage(0, 5);
		List<Category> cs=categoryMapper.list();
		System.out.println(cs.getClass());
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		System.out.println(new PageInfo(cs).getTotal());
	}
	@Test
	public void test1() throws Exception {

		ItemsQueryVo itemsQueryVo = new ItemsQueryVo();
		Items items = new Items ();
		itemsQueryVo.setItems (items);
		ItemsCustom itemsCustom1 = new ItemsCustom ();
		itemsQueryVo.setItemsCustom (itemsCustom1);
		List<ItemsCustom> itemsList = itemsMapperCustom.findItemsList (null);
		for (ItemsCustom itemsCustom : itemsList) {
			System.out.println (itemsCustom);
		}
	}

	@Test
	public void test2(){
		Items items = itemsMapper.selectByPrimaryKey(1);
		System.out.println (items);
	}
	@Test
	public void test22(){
		ItemsCustom itemsCustom = new ItemsCustom ();
		itemsCustom.setId(4);
		itemsCustom.setName ("jh");
		itemsCustom.setPrice (999f);
		itemsCustom.setPic ("...");
		itemsCustom.setDetail ("bbb");
		itemsCustom.setCreatetime (new Date ());
		int i = itemsMapper.updateByPrimaryKeyWithBLOBs (itemsCustom);
		System.out.println (i);
	}
}
