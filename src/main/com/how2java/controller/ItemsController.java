package com.how2java.controller;

import com.how2java.controller.validation.ValidGroup1;
import com.how2java.pojo.ItemsCustom;
import com.how2java.pojo.ItemsQueryVo;
import com.how2java.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    // 商品分类
    //itemtypes表示最终将方法返回值放在request中的key
    @ModelAttribute("itemtypes")
    public Map<String, String> getItemTypes() {

        Map<String, String> itemTypes = new HashMap<String, String> ();
        itemTypes.put ("101", "数码");
        itemTypes.put ("102", "母婴");

        return itemTypes;
    }

    @RequestMapping("queryItems")
    public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {
        //测试forward后request是否可以共享

        System.out.println (request.getParameter ("id"));

        // 调用service查找 数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList (itemsQueryVo);

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView ();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject ("itemsList", itemsList);

        // 指定视图
        // 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
        // modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        // 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
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
     */
    @RequestMapping(value = "editItems", method = {RequestMethod.POST, RequestMethod.GET})
    public String editItems(Model model, @RequestParam(value = "id", required = true) Integer Item_id) throws Exception {

        ItemsCustom itemsCustom = itemsService.findItemsById (Item_id);

      /*  if(itemsCustom==null){
            throw new CustomException ("修改的商品信息不存在！");
        }*/
        model.addAttribute ("items", itemsCustom);
        return "editItems";
    }

    /**
     * @Validated(value={ValidGroup1.class})分组校验
     */
    @RequestMapping("editItemsSubmit")
    public String editItemsSubmit(HttpServletRequest request, Model model, Integer id, @ModelAttribute("items") @Validated(value = {ValidGroup1.class}) ItemsCustom itemsCustom, BindingResult bindingResult, MultipartFile items_pic) throws Exception {
        /*ModelAndView modelAndView = new ModelAndView ();
        modelAndView.setViewName ("success");*/
        //        //return "redirect:queryItems";

        System.out.println ("---------------------->");
        if (bindingResult.hasErrors ()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors ();

            for (ObjectError objectError : allErrors) {
                // 输出错误信息
                System.out.println (objectError.getDefaultMessage ());

            }
            model.addAttribute ("allErrors", allErrors);
            model.addAttribute ("items", itemsCustom);
            return "editItems";
        }
        //原始名称
        String originalFilename = items_pic.getOriginalFilename();
        //上传图片
        if(items_pic!=null && originalFilename!=null && originalFilename.length ()>0){

            //存储图片的物理路径
            String pic_path = "D:\\java\\photo\\";


            //新的图片名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //新图片

            File newFile = new File(pic_path+newFileName);

            //将内存中的数据写入磁盘
            items_pic.transferTo(newFile);

            //将新图片名称写到itemsCustom中
            itemsCustom.setPic(newFileName);

        }

        System.out.println ("---------------------->" + itemsCustom.getCreatetime ());
        itemsService.updateItems (id, itemsCustom);
        return "forward:queryItems";
        //return "queryItems";
    }

    /**
     * 删除商品信息
     */
    @RequestMapping("deleteItems")
    public String deleteItems(Integer[] items_id) throws Exception {
        for (Integer integer : items_id) {
            System.out.println ("----->" + integer);
        }

        return "success";
    }

    // 批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
    @RequestMapping("/editItemsQuery")
    public ModelAndView editItemsQuery(HttpServletRequest request,
                                       ItemsQueryVo itemsQueryVo) throws Exception {

        // 调用service查找 数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList (itemsQueryVo);

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView ();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject ("itemsList", itemsList);

        modelAndView.setViewName ("editItemsQuery");

        return modelAndView;

    }

    // 批量修改商品提交
    // 通过ItemsQueryVo接收批量提交的商品信息，将商品信息存储到itemsQueryVo中itemsList属性中。
    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo)
            throws Exception {
        return "success";
    }
}
