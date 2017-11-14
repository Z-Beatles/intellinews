package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.SectionAliasEntity;
import com.fintech.intellinews.entity.SectionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionAliasDao extends BaseDao<SectionAliasEntity> {
    /**
     * 根据首字母查询条目id列表(条目导航)
     *
     * @param startWith 首字母
     * @return 列表
     */
    List<Long> listByStartWith(String startWith);
}
