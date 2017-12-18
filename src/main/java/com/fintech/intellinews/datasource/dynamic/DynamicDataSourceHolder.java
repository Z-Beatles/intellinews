package com.fintech.intellinews.datasource.dynamic;


/**
 * @author wanghao
 * create 2017-12-05 12:59
 **/
class DynamicDataSourceHolder {

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    static final String DATA_SOURCE_MASTER = "master";

    static final String DATA_SOURCE_SALVE = "salve";

    private DynamicDataSourceHolder() {
    }

    static String getDataSourceType() {
        String type = contextHolder.get();
        if (type == null) {
            type = DATA_SOURCE_MASTER;
        }
        return type;
    }

    static void setDataSourceType(String type) {
        contextHolder.set(type);
    }

    static void clearDataSourceType() {
        contextHolder.remove();
    }
}
