package com.mvc.web.bean;

public class SystemNoticeBean {

    private int id;
    private String title;
    /**
     * 既読フラグ（0：未読, 1：既読）
     */
    private int readFlag;

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

    public int getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(int readFlag) {
        this.readFlag = readFlag;
    }
}
