package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.CategoryEntity;

public interface CategoryDao extends BaseDao<CategoryEntity> {
    CategoryEntity getCurrentUserCategory(Long id);
}