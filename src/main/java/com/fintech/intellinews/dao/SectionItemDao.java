package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.SectionItemEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author waynechu
 * Created 2017-11-14 13:20
 */
@Repository
public interface SectionItemDao {

    /**
     * 根据条目id查询条目扩展信息
     *
     * @param sectionId 条目id
     * @return 条目扩展信息
     */
    SectionItemEntity getBySectionId(@Param("id") Long sectionId);
}
