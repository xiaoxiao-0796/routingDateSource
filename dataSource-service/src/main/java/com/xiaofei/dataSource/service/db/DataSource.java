package com.xiaofei.dataSource.service.db;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/12/14 0014.
 * 数据库注解类
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value() default DataSource.local;

    public static String local = "local";

}
