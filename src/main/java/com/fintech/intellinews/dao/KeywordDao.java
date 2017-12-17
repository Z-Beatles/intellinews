package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.KeywordEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface KeywordDao {

    /**
     * 获取热门关键字
     *
     * @return 关键字列表
     */
    List<KeywordEntity> listHotKeywords();

    /**
     * 更新关键字热度
     *
     * @param keyword 关键字
     * @return 影响的行数
     */
    int updateKeywordCount(String keyword);

    void insertKeyword(KeywordEntity entity);
}