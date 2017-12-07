package com.fintech.intellinews.datasource;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;

import java.sql.SQLException;

/**
 * @author wanghao
 * create 2017-12-07 15:01
 **/
public class DruidDataSourceFactory {

    public static class DruidDataSourceBuilder {
        private String username;
        private String password;
        private String url;
        private int initialSize = 4;
        private int maxWait = 60000;
        private int maxActive = 20;
        private int timeBetweenConnectErrorMillis = 6000;
        private int minEvictableIdleTimeMillis = 300000;
        private String validationQuery = "SELECT 'x'";
        private boolean testOnBorrow = true;
        private boolean testOnReturn = false;
        private int maxPoolPreparedStatementPerConnectionSize = 20;
        private boolean poolPreparedStatements = true;
        private String filters;
        private StatFilter statFilter;

        public DruidDataSource build() {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setUrl(url);
            dataSource.setInitialSize(initialSize);
            dataSource.setMaxWait(maxWait);
            dataSource.setMaxActive(maxActive);
            dataSource.setTimeBetweenConnectErrorMillis(timeBetweenConnectErrorMillis);
            dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            dataSource.setValidationQuery(validationQuery);
            dataSource.setTestOnBorrow(testOnBorrow);
            dataSource.setTestOnReturn(testOnReturn);
            dataSource.setPoolPreparedStatements(poolPreparedStatements);
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
            try {
                dataSource.setFilters(filters);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return dataSource;
        }

        public DruidDataSourceBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public DruidDataSourceBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public DruidDataSourceBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public DruidDataSourceBuilder setInitialSize(int initialSize) {
            this.initialSize = initialSize;
            return this;
        }

        public DruidDataSourceBuilder setMaxWait(int maxWait) {
            this.maxWait = maxWait;
            return this;
        }

        public DruidDataSourceBuilder setMaxActive(int maxActive) {
            this.maxActive = maxActive;
            return this;
        }

        public DruidDataSourceBuilder setTimeBetweenConnectErrorMillis(int timeBetweenConnectErrorMillis) {
            this.timeBetweenConnectErrorMillis = timeBetweenConnectErrorMillis;
            return this;
        }

        public DruidDataSourceBuilder setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
            this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
            return this;
        }

        public DruidDataSourceBuilder setValidationQuery(String validationQuery) {
            this.validationQuery = validationQuery;
            return this;
        }

        public DruidDataSourceBuilder setTestOnBorrow(boolean testOnBorrow) {
            this.testOnBorrow = testOnBorrow;
            return this;
        }

        public DruidDataSourceBuilder setTestOnReturn(boolean testOnReturn) {
            this.testOnReturn = testOnReturn;
            return this;
        }

        public DruidDataSourceBuilder setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
            this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
            return this;
        }

        public DruidDataSourceBuilder setPoolPreparedStatements(boolean poolPreparedStatements) {
            this.poolPreparedStatements = poolPreparedStatements;
            return this;
        }

        public DruidDataSourceBuilder setFilters(String filters) {
            this.filters = filters;
            return this;
        }

        public DruidDataSourceBuilder setStatFilter(StatFilter statFilter){
            this.statFilter = statFilter;
            return this;
        }
    }

}
