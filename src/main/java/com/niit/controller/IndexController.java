package com.niit.controller;

import com.niit.model.Category;
import com.niit.service.ICategoryService;
import com.niit.service.IProductService;
import com.niit.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-07 15:07
 **/
@Controller
public class IndexController {

    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    /**
     * 跳转到 首页
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public String index(ModelMap map,HttpSession session){

        // 热门商品查询
        map.addAttribute("hotProducts",productService.getHotProducts());
        // 查询全部菜单信息
        session.setAttribute("Menus",categoryService.getAll());

        return "index";
    }

    @Autowired
    private RedisService redisService;

    @RequestMapping(value="/getRedis")
    @ResponseBody
    public String getJson(String string) throws Exception {
        redisService.set("test", "test");
        String value = redisService.get("test");
        System.out.println(value);
        return null;
    }

}
