package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.SectionEntity;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SectionDao extends BaseDao<SectionEntity> {
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

    @MapKey("id")
    Map<Long,SectionEntity> listSectionByIds(List<Long> ids);

}