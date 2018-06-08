package com.zhan.influxdb;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

/**
 * Created by zhanyanjun on 2018/6/8.
 */
public class Test {

    @org.junit.Test
    public void test() {
        InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "zhan", "123456");
        influxDB.createDatabase("test1");
    }
}
