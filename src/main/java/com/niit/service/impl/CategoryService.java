package com.niit.service.impl;

import com.niit.dao.CategoryMapper;
import com.niit.model.Category;
import com.niit.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-10 09:17
 **/
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryMapper dao;

    @Override
    public List<Category> getAll() {
        return dao.getAll();
    }
}
