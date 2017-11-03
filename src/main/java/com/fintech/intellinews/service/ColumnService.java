package com.fintech.intellinews.service;

import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.ColumnDao;
import com.fintech.intellinews.dto.ColumnDTO;
import com.fintech.intellinews.entity.ColumnEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-31 17:54
 */
@Service
public class ColumnService extends BaseService {

    private ColumnDao columnDao;

    public PageInfo<ColumnDTO> listColumns(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // List<ColumnEntity> columns = columnDao.select();
        return null;
    }
}
