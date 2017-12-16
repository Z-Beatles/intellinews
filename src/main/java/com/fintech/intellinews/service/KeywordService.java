package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.HotKeywordVO;
import com.github.pagehelper.PageInfo;

/**
 * @author waynechu
 * Created 2017-11-24 09:11
 */
public interface KeywordService {

    /**
     * 获取热门关键字
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return 关键字列表
     */
    PageInfo<HotKeywordVO> listHotKeywords(Integer pageNum, Integer pageSize);
}
