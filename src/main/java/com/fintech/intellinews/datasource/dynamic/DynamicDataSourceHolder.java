package com.fintech.intellinews.datasource.dynamic;

/**
 * @author wanghao
 * create 2017-12-05 12:59
 **/
class DynamicDataSourceHolder {

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    static final String DATASOURCE_TYPE_MASTER = "master";

    static final String DATASOURCE_TYPE_SALVE = "salve";

    private DynamicDataSourceHolder() {
    }

    static String getDataSourceType() {
        return contextHolder.get();
    }

    static void setDataSourceType(String type) {
        contextHolder.set(type);
    }

    static void clearDataSourceType() {
        contextHolder.remove();
    }
}
