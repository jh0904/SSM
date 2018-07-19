package com.how2java.controller;

import com.how2java.pojo.Items;
import com.how2java.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * com.how2java.controller
 *
 * @author jh
 * @date 2018/7/19 23:00
 * description:
 */

@Controller
@RequestMapping("")
public class ItemsController {
    @Autowired
    ItemsService itemsService;

    @RequestMapping("queryItems")
    public ModelAndView queryItems(HttpServletRequest request) throws Exception {
        //测试forward后request是否可以共享

        // 调用service查找 数据库，查询商品列表
        List<Items> itemsList = itemsService.findItemsList (null);
        System.out.println ("----------->"+itemsList);
        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);
        //指定视图（实现Controller的不能写全路径，需要包加项目，不写项目后缀）
        modelAndView.setViewName ("itemsList");
        return modelAndView;
    }
}
