package com.fintech.intellinews.base.dynamic;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;

/**
 * @author wanghao
 * create 2017-12-05 13:05
 **/
@Intercepts({
        @Signature(
                type = Executor.class, method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(
                type = Executor.class, method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class DynamicDataSourceInterceptor implements Interceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 当前有没有开启事务
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        String lookUpKey = DynamicDataSourceHolder.DATA_SOURCE_MASTER;
        if (!synchronizationActive) {
            //当前查询不在事务管理状态下
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    lookUpKey = DynamicDataSourceHolder.DATA_SOURCE_MASTER;
                } else {
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(args[1]);
                    // 将sql中的换行符，指标符替换成空格
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("\\t\\r\\n", " ");
                    if (sql.matches(REGEX)) {
                        lookUpKey = DynamicDataSourceHolder.DATA_SOURCE_MASTER;
                    } else {
                        lookUpKey = DynamicDataSourceHolder.DATA_SOURCE_SALVE;
                    }
                }
            }
        } else {
            lookUpKey = DynamicDataSourceHolder.DATA_SOURCE_MASTER;
        }
        DynamicDataSourceHolder.setDataSourceType(lookUpKey);
        return invocation.proceed();
    }

    /**
     * 返回本体还是代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
