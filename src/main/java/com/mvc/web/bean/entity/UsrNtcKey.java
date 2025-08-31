package com.mvc.web.bean.entity;

public class UsrNtcKey {
    /** ユーザーID */
    private String userid;

    /** お知らせID */
    private Long noticeId;

    /**
     * ユーザーID取得
     */
    public String getUserid() {
        return userid;
    }

    /**
     * ユーザーID設定
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * お知らせID取得
     */
    public Long getNoticeId() {
        return noticeId;
    }

    /**
     * お知らせID設定
     */
    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }
}