package com.fintech.intellinews.service;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.CategoryDao;
import com.fintech.intellinews.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghao
 * create 2017-10-30 17:34
 **/
@Service
public class CategoryService extends BaseService {

    @Autowired
    private CategoryDao categoryDao;
}
