package com.fintech.intellinews.base.dynamic;


/**
 * @author wanghao
 * create 2017-12-05 12:59
 **/
public class DynamicDataSourceHolder {
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final String DATA_SOURCE_MASTER = "master";
    public static final String DATA_SOURCE_SALVE = "salve";

    static String getDataSourceType(){
        String type = contextHolder.get();
        if (type == null){
            type = DATA_SOURCE_MASTER;
        }
        return type;
    }

    static void setDataSourceType(String type){
        contextHolder.set(type);
    }

    static void clearDataSourceType(){
        contextHolder.remove();
    }

}
