package com.fintech.intellinews.service.impl;

import com.fintech.intellinews.service.ColumnService;
import com.fintech.intellinews.vo.ColumnVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @author waynechu
 * Created 2017-10-31 17:54
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    /**
     * 获取专栏列表
     *
     * @param pageNum  查询页数
     * @param pageSize 页面大小
     * @return 专栏信息
     */
    @Override
    public PageInfo<ColumnVO> listColumns(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return null;
    }
}
