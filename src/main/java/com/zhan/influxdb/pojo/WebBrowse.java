package com.zhan.influxdb.pojo;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * Created by zhanyanjun on 2018/6/8.
 */
@Measurement(name = "m_web_browse")
public class WebBrowse {

    @Column(name = "time")
    private Instant time;

    @Column(name = "type", tag = true)
    private String type;

    @Column(name = "id", tag = true)
    private String id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "url")
    private String url;

    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "etag")
    private String etag;

    public Instant getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getUrl() {
        return url;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getEtag() {
        return etag;
    }
}
