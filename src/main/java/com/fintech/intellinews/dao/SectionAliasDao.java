package com.fintech.intellinews.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface SectionAliasDao {

    /**
     * 根据首字母查询条目id列表(条目导航)
     *
     * @param startWith 首字母
     * @return 条目别名列表
     */
    List<Long> listByStartWith(String startWith);
}
