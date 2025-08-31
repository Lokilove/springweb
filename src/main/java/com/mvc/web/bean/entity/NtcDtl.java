package com.mvc.web.bean.entity;

import java.util.Date;

public class NtcDtl {
    /** お知らせID */
    private Long noticeId;

    /** レベル */
    private Integer level;

    /** お知らせタイトル */
    private String title;

    /** 最終更新者 */
    private String updUser;

    /** 最終更新日 */
    private Date updDate;

    /** 詳細内容 */
    private byte[] content;

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

    /**
     * レベル取得
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * レベル設定
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * お知らせタイトル取得
     */
    public String getTitle() {
        return title;
    }

    /**
     * お知らせタイトル設定
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 最終更新者取得
     */
    public String getUpdUser() {
        return updUser;
    }

    /**
     * 最終更新者設定
     */
    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    /**
     * 最終更新日取得
     */
    public Date getUpdDate() {
        return updDate;
    }

    /**
     * 最終更新日設定
     */
    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    /**
     * 詳細内容取得
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * 詳細内容設定
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
}