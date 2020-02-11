package com.niit.controller;

import com.niit.model.Product;
import com.niit.model.ResultBean;
import com.niit.service.ICategorySecondService;
import com.niit.service.IProductService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: shop
 * @description: 商品控制器
 * @author: hanliang
 * @create: 2020-02-07 15:28
 **/
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    ICategorySecondService categorySecondService;

    /**
     * 查询热门商品
     * @return
     */
    @RequestMapping("/getNewProducts")
    @ResponseBody
    public ResultBean<List<Product>> getNewProducts(){

        ResultBean<List<Product>> resultBean = new ResultBean<>();

        resultBean.setData(productService.getNewProducts());

        return resultBean;
    }

    /**
     * 按照id查询商品信息
     * @return
     */
    @RequestMapping("/details/{cid}/{pid}")
    public String details(@PathVariable("pid")Integer pid,
                          @PathVariable("cid")Integer cid,
                          ModelMap map){
        // 1.查询 左边的二级菜单列表
        map.addAttribute("csList",categorySecondService.getByCategoryId(cid));
        // 2. 查询 商品信息
        map.addAttribute("product",productService.selectByPrimaryKey(pid));

        return "product";
    }
}
