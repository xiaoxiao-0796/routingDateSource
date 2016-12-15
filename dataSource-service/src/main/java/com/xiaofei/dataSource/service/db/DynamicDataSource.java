package com.xiaofei.dataSource.service.db;

import com.xiaofei.dataSource.service.db.DataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by Administrator on 2016/12/14 0014.
 * 获取数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

}
