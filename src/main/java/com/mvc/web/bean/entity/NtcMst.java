package com.mvc.web.bean.entity;

import java.util.Date;

public class NtcMst {
    /** お知らせID */
    private Long noticeId;

    /** レベル */
    private Integer level;

    /** 表示期限 */
    private Date dispLimit;

    /** 登録者 */
    private String regUser;

    /** 登録日 */
    private Date regDate;

    /** 削除フラグ */
    private Integer delFlg;

    /** 削除者 */
    private String delUser;

    /** 削除日 */
    private Date delDate;

    /** 最終更新者 */
    private String updUser;

    /** 最終更新日 */
    private Date updDate;

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
     * 表示期限取得
     */
    public Date getDispLimit() {
        return dispLimit;
    }

    /**
     * 表示期限設定
     */
    public void setDispLimit(Date dispLimit) {
        this.dispLimit = dispLimit;
    }

    /**
     * 登録者取得
     */
    public String getRegUser() {
        return regUser;
    }

    /**
     * 登録者設定
     */
    public void setRegUser(String regUser) {
        this.regUser = regUser;
    }

    /**
     * 登録日取得
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * 登録日設定
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * 削除フラグ取得
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * 削除フラグ設定
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 削除者取得
     */
    public String getDelUser() {
        return delUser;
    }

    /**
     * 削除者設定
     */
    public void setDelUser(String delUser) {
        this.delUser = delUser;
    }

    /**
     * 削除日取得
     */
    public Date getDelDate() {
        return delDate;
    }

    /**
     * 削除日設定
     */
    public void setDelDate(Date delDate) {
        this.delDate = delDate;
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
}