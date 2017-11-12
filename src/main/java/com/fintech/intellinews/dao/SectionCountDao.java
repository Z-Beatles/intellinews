package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.SectionCountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionCountDao extends BaseDao<SectionCountEntity> {
    /**
     * 根据条目id获取条目统计信息
     *
     * @param id id
     * @return 条目统计信息
     */
    SectionCountEntity getBySectionId(Long id);
}