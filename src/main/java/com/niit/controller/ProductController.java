package com.niit.controller;

import com.niit.model.Product;
import com.niit.model.ResultBean;
import com.niit.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
