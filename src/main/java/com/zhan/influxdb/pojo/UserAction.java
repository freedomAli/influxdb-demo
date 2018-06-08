package com.zhan.influxdb.pojo;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * Created by zhanyanjun on 2018/6/8.
 */
@Measurement(name = "m_user_action")
public class UserAction {

    @Column(name = "time")
    private Instant time;

    @Column(name = "type", tag = true)
    private String type;

    @Column(name = "id", tag = true)
    private String id;

    @Column(name = "userId")
    private String userId;

    public Instant getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
