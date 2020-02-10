package com.niit.service.impl;

import com.niit.dao.ProductMapper;
import com.niit.model.Product;
import com.niit.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-07 15:17
 **/
@Service
public class ProductService implements IProductService {
    @Autowired
    ProductMapper dao;

    @Override
    public List<Product> getHotProducts() {
        return dao.getHotProducts();
    }

    @Override
    public List<Product> getNewProducts() {
        return dao.getNewProducts();
    }

    @Override
    public List<Product> getByCid(Integer cid) {
        return dao.getByCid(cid);
    }

    @Override
    public List<Product> getByCsid(Integer csid) {
        return dao.getByCsid(csid);
    }
}
