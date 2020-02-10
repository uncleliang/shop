package com.niit.service;

import com.niit.model.Categorysecond;

import java.util.List;

public interface ICategorySecondService {
    List<Categorysecond> getByCategoryId(Integer cid);
}
