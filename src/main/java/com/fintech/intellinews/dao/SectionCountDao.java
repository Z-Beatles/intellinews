package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.SectionCountEntity;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SectionCountDao extends BaseDao<SectionCountEntity> {
    /**
     * 根据条目id获取条目统计信息
     *
     * @param id id
     * @return 条目统计信息
     */
    SectionCountEntity getBySectionId(Long id);

    /**
     * 根据条目id列表获取条目统计信息列表
     *
     * @param sectionIds 条目id列表
     * @return 条目统计信息列表
     */
    @MapKey("sectionId")
    Map<Long, SectionCountEntity> mapSectionCountByIds(List<Long> sectionIds);

    /**
     * 更新条目浏览量
     *
     * @param sectionId 条目id
     */
    void updateViewCountBySectionId(Long sectionId);

    /**
     * 获取最大浏览量
     *
     * @return
     */
    Integer getMaxViewCount();
}