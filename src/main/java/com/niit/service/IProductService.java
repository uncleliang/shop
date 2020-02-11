package com.niit.service;

import com.niit.model.Product;

import java.util.List;

public interface IProductService {

    /**
     * 查询10条热门商品
     * @return
     */
    List<Product> getHotProducts();

    /**
     * 查询10条最新商品
     * @return
     */
    List<Product> getNewProducts();

    /**
     *
     * @Param cid 一级分类id
     * @return
     */
    List<Product> getByCid(Integer cid);


    /**
     *
     * @Param csid 二级分类id
     * @return
     */
    List<Product> getByCsid(Integer csid);


    /**
     *  按照商品id查询商品信息
     * @param pid
     * @return
     */
    Product selectByPrimaryKey(Integer pid);
}
