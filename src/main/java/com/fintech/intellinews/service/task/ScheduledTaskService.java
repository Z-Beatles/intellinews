package com.fintech.intellinews.service.task;

import com.fintech.intellinews.dao.KeywordDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author waynechu
 * Created 2018-01-31 09:09
 */
@Service
public class ScheduledTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);

    @Autowired
    private KeywordDao keywordDao;

    /**
     * 每天凌晨1点清理单个字个热搜关键字
     */
    @Scheduled(cron = "0 0 1 * * *")
    public void reviseHostKeywords() {
        int count = keywordDao.deleteSingleWordKeywords();
        logger.info("【ScheduledTask】delete {} unused single word Keywords", count);
    }
}
