package com.fintech.intellinews.service.async;

import com.fintech.intellinews.dao.ArticleCountDao;
import com.fintech.intellinews.dao.KeywordDao;
import com.fintech.intellinews.entity.KeywordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author waynechu
 * Created 2017-12-15 14:27
 */
@Service
public class AsyncTaskService {

    @Autowired
    private ArticleCountDao articleCountDao;

    @Autowired
    private KeywordDao keywordDao;

    /**
     * 更新文章浏览量
     *
     * @param id 文章id
     */
    @Async
    public void updateViewCountByArticleId(Long id) {
        articleCountDao.updateViewCountByArticleId(id);
    }

    /**
     * 更新关键字热度
     *
     * @param keyword 关键字
     */
    @Async
    public void updateKeywordDegree(String keyword) {
        int count = keywordDao.updateKeywordCount(keyword);
        if (count == 0) {
            KeywordEntity entity = new KeywordEntity();
            entity.setName(keyword);
            entity.setCount(1);
            entity.setGmtCreate(new Date());
            keywordDao.insertKeyword(entity);
        }
    }
}
