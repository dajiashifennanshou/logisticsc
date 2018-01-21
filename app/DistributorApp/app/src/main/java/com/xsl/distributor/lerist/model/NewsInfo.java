package com.xsl.distributor.lerist.model;

/**
 * Created by Lerist on 2016/7/9, 0009.
 */

public class NewsInfo {

    /**
     * id : 5
     * title : 李克强谈互联网+物流：既发展新经济，又提升传统经济
     * url : http://news.ifeng.com/a/20160720/49462095_0.shtml
     * create_time : 2016-07-23 15:39:09.0
     * release_time : 2016-07-23 15:39:10.0
     */

    private int id;
    private String title;
    private String url;
    private String create_time;
    private String release_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }
}
