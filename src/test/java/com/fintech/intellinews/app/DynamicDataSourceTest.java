package com.fintech.intellinews.app;

import com.fintech.intellinews.config.SpringAppConfig;
import com.fintech.intellinews.config.SpringWebConfig;
import com.fintech.intellinews.dao.ArticleCountDao;
import com.fintech.intellinews.dao.ArticleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author waynechu
 * Created 2018-01-31 12:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringAppConfig.class, SpringWebConfig.class})
@ActiveProfiles("develop")
public class DynamicDataSourceTest {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleCountDao articleCountDao;

    @Test
    public void testReadOnly() {
        articleDao.getArticleById(1L);
    }

    @Test
    @Transactional
    public void testWriteOnly() {
        articleCountDao.updateViewCountByArticleId(1L);
    }

    @Test
    public void testTransactional(){
        articleDao.getArticleById(1L);
        articleCountDao.updateViewCountByArticleId(1L);
    }
}
