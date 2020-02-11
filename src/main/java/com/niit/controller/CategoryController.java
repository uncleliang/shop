package com.niit.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.model.Product;
import com.niit.service.ICategorySecondService;
import com.niit.service.IProductService;
import com.niit.util.ConsUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-10 09:48
 **/
@Controller
@RequestMapping("/category")
public class CategoryController {

    // 定义logger对象
    Logger logger  = Logger.getLogger(CategoryController.class);

    @Autowired
    ICategorySecondService categorySecondService;

    @Autowired
    IProductService productService;

    /**
     * 跳转到分类界面
     * @param map
     * @return
     */
    @RequestMapping("/goCategory/{cid}/{csid}/{pageNum}")
    public String goCategory(@PathVariable("cid") Integer cid,
                             @PathVariable("csid") Integer csid,
                             @PathVariable("pageNum") Integer pageNum,
                             ModelMap map){
        logger.debug("方法开始执行");
        // 1.查询 左边的二级菜单列表
        map.addAttribute("csList",categorySecondService.getByCategoryId(cid));

        // 2.查询 右边的商品列表
        PageHelper.startPage(pageNum,ConsUtil.PAGE_SIZE); // 这行代码后面第一个查询会自动分页
        List<Product> list = new ArrayList<>();
        if(csid>0){ //规定 0是无效的csid，不想要用csid做条件 传一个0
            list =  productService.getByCsid(csid);
        }else{
            list =  productService.getByCid(cid);
        }

        // 分页的信息：总行数，总的页数，当前页页码等
        PageInfo page = new PageInfo(list);

        map.addAttribute("pageInfo",page);
        map.addAttribute("pList",list);
        map.addAttribute("cid",cid);
        map.addAttribute("csid",csid);
        logger.debug("方法执行结束");
        return "category";
    }
}
