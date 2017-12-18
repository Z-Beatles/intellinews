package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.SectionEntity;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface SectionDao {

    /**
     * 获取所有条目列表
     *
     * @return 条目列表
     */
    List<SectionEntity> listAll();

    /**
     * 根据关键字查询条目
     *
     * @param keyword 关键字
     * @return 条目列表
     */
    List<SectionEntity> listSectionsByKeyword(String keyword);

    /**
     * 根据条目id列表获取条目列表
     *
     * @param ids 条目id列表
     * @return 条目列表
     */
    @MapKey("id")
    Map<Long, SectionEntity> mapSectionByIds(List<Long> ids);

    /**
     * 根据条目id获取条目详情
     *
     * @param sectionId 条目id
     * @return 条目详情
     */
    SectionEntity getSectionById(Long sectionId);
}