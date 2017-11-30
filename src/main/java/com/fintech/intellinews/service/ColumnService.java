package com.fintech.intellinews.service;

import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.vo.ColumnVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @author waynechu
 * Created 2017-10-31 17:54
 */
@Service
public class ColumnService extends BaseService {

    public PageInfo<ColumnVO> listColumns(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return null;
    }

}
