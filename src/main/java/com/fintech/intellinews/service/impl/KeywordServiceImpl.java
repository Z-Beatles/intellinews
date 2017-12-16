package com.fintech.intellinews.service.impl;

import com.fintech.intellinews.dao.KeywordDao;
import com.fintech.intellinews.entity.KeywordEntity;
import com.fintech.intellinews.service.KeywordService;
import com.fintech.intellinews.vo.HotKeywordVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waynechu
 * Created 2017-11-24 09:11
 */
@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordDao keywordDao;

    /**
     * 获取热门关键字
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return 关键字列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageInfo<HotKeywordVO> listHotKeywords(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "count DESC");
        List<KeywordEntity> entities = keywordDao.listHotKeywords();
        List<HotKeywordVO> hotKeywordVOS = new ArrayList<>();
        HotKeywordVO hotKeywordVO;
        for (KeywordEntity entity : entities) {
            hotKeywordVO = new HotKeywordVO();
            hotKeywordVO.setId(entity.getId());
            hotKeywordVO.setName(entity.getName());
            hotKeywordVOS.add(hotKeywordVO);
        }
        PageInfo pageInfo = new PageInfo(entities);
        pageInfo.setList(hotKeywordVOS);
        return pageInfo;
    }
}
