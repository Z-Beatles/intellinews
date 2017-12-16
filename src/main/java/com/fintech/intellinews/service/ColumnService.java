package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.ColumnVO;
import com.github.pagehelper.PageInfo;

/**
 * @author waynechu
 * Created 2017-10-31 17:54
 */
public interface ColumnService {

    /**
     * 获取专栏列表
     *
     * @param pageNum  查询页数
     * @param pageSize 页面大小
     * @return 专栏信息
     */
    PageInfo<ColumnVO> listColumns(int pageNum, int pageSize);
}
