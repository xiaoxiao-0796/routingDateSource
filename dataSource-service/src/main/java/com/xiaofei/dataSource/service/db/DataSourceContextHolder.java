package com.xiaofei.dataSource.service.db;

/**
 * DataSource上下文句柄，通过此类设置需要访问的对应数据源
 * Created by Administrator on 2016/12/14 0014.
 */
public class DataSourceContextHolder {

    public static final ThreadLocal contextHolder = new ThreadLocal();

    public static void setDataSource(String dbName){
        contextHolder.set(dbName);
    }

    public static String getDataSource(){
        return contextHolder.get().toString();
    }

    public static void clearDataSource(){
        contextHolder.remove();
    }
}
