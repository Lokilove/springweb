package com.mvc.web.bean.entity;

import java.util.Date;

public class UsrNtc extends UsrNtcKey {
    /** 既読フラグ */
    private Integer readFlg;

    private Integer delFlg;

    /** 最終更新日 */
    private Date updDate;

    /**
     * 既読フラグ取得
     */
    public Integer getReadFlg() {
        return readFlg;
    }

    /**
     * 既読フラグ設定
     */
    public void setReadFlg(Integer readFlg) {
        this.readFlg = readFlg;
    }

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
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