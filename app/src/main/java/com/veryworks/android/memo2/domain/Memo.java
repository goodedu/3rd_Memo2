package com.veryworks.android.memo2.domain;

/**
 * Created by pc on 5/29/2017.
 */
public class Memo {
    private String id;
    private String content;
    private String date;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
