package com.zhan.influxdb;

import com.zhan.influxdb.pojo.WebBrowse;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.influxdb.InfluxDBProperties;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.data.influxdb.converter.PointConverter;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableConfigurationProperties({InfluxDBProperties.class})
@SpringBootApplication
public class InfluxdbDemoApplication implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(InfluxdbDemoApplication.class);

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    public static void main(String[] args) {
        SpringApplication.run(InfluxdbDemoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        this.write();

        this.query();
    }

    /**
     * 写数据
     */
    private void write() {
        // Create database...
        // influxDBTemplate.createDatabase();

        // Create some data...
        final Point p1 = Point.measurement("m_web_browse")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("type", "2")
                .tag("id", "2")
                .addField("ip", "192.168.20.4")
                .addField("url", "/api/test1")
                .addField("sessionId", "sessionId1")
                .addField("etag", "etag2")
                .build();
        final Point p2 = Point.measurement("m_user_action")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("type", "2")
                .tag("id", "2")
                .addField("userId", "abc1")
                .build();
        influxDBTemplate.write(p1, p2);
    }

    /**
     * 读数据
     */
    private void query() {
        final Query q = new Query("SELECT * FROM m_web_browse", influxDBTemplate.getDatabase());
        QueryResult queryResult = influxDBTemplate.query(q);

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<WebBrowse> webBrowseList = resultMapper.toPOJO(queryResult, WebBrowse.class);

        for (WebBrowse webBrowse : webBrowseList) {
            System.out.println(webBrowse.toString());
        }
    }
}
