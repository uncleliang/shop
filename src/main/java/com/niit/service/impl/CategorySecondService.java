package com.niit.service.impl;

import com.niit.dao.CategorysecondMapper;
import com.niit.model.Categorysecond;
import com.niit.service.ICategorySecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-10 10:05
 **/
@Service
public class CategorySecondService implements ICategorySecondService {

    @Autowired
    CategorysecondMapper dao;

    @Override
    public List<Categorysecond> getByCategoryId(Integer cid) {
        return dao.getByCategoryId(cid);
    }
}
