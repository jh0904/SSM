package com.how2java.controller;

import com.how2java.pojo.Items;
import com.how2java.pojo.ItemsCustom;
import com.how2java.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    ItemsService itemsService;

    @RequestMapping("queryItems")
    public ModelAndView queryItems(HttpServletRequest request) throws Exception {
        //测试forward后request是否可以共享
        System.out.println ("-------------->"+request.getParameter ("id"));
        // 调用service查找 数据库，查询商品列表
        List<Items> itemsList = itemsService.findItemsList (null);
        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView ();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject ("itemsList", itemsList);
        //指定视图（实现Controller的不能写全路径，需要包加项目，不写项目后缀）
        modelAndView.setViewName ("itemsList");
        return modelAndView;
    }


    /**@RequestMapping("editItems")*/
   /* @RequestMapping(value ="editItems",method ={RequestMethod.POST,RequestMethod.GET} )
    public ModelAndView editItems() throws Exception {
        ModelAndView modelAndView = new ModelAndView ();
        ItemsCustom itemsCustom = itemsService.findItemsById(4);
        modelAndView.addObject ("itemsCustom",itemsCustom);

        modelAndView.setViewName ("editItems");
        return modelAndView;
    }*/
/**
 * 利用String类型返回
 * */
    @RequestMapping(value ="editItems",method ={RequestMethod.POST,RequestMethod.GET} )
    public String editItems(Model model, @RequestParam( value = "id",required = true)Integer Item_id) throws Exception {

        ItemsCustom itemsCustom = itemsService.findItemsById(Item_id);
        model.addAttribute ("itemsCustom",itemsCustom);
        return "editItems";
    }

    @RequestMapping("editItemsSubmit")
    public String editItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom) throws Exception {
        /*ModelAndView modelAndView = new ModelAndView ();
        modelAndView.setViewName ("success");*/
        //        //return "redirect:queryItems";
        System.out.println ("---------------------->"+itemsCustom.getCreatetime ());
        itemsService.updateItems (id,itemsCustom);
        //return "forward:queryItems";
        return "success";
    }


}
