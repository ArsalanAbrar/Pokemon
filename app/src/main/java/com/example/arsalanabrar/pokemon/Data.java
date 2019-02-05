package com.example.arsalanabrar.pokemon;

public class Data
{
    private String title, url, time,avatar_url,comments_url;

    public Data() {
    }

    public Data(String title, String url,String avatar_url) {
        this.title = title;
        this.url = url;

//        this.time = time;
 this.avatar_url=avatar_url;
//        this.comments_url=comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getComments_url() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time1) {
        this.time = time1;
    }

    public String getBody() {
        return url;
    }

    public void setBody(String genre) {
        this.url = genre;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
