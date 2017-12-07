package com.fintech.intellinews.properties;

import com.fintech.intellinews.base.dynamic.DataSourceConnector;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanghao
 * create 2017-12-06 16:40
 **/
@Configuration
public class DataSourceProperties {

    private static final String prefix = "spring.datasource.split";

    private DataSourceConnector master;

    public DataSourceConnector getMaster() {
        return master;
    }

    public void setMaster(DataSourceConnector master) {
        this.master = master;
    }

}
