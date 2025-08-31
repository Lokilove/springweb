package com.mvc.web.bean;

/**
 * お知らせ共通Beanクラス
 */
public class CommonNoticeBean {

    /**
     * お知らせID
     */
    private long id;
    /**
     * お知らせタイトル
     */
    private String title;
    /**
     * 既読フラグ（0：未読, 1：既読）
     */
    private int readFlag;

    /**
     * お知らせID取得
     *
     * @return お知らせID
     */
    public long getId() {
        return id;
    }

    /**
     * お知らせID設定
     *
     * @param id お知らせID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * お知らせタイトル取得
     *
     * @return お知らせタイトル
     */
    public String getTitle() {
        return title;
    }

    /**
     * お知らせタイトル設定
     *
     * @param title お知らせタイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 既読フラグ（0：未読, 1：既読）取得
     *
     * @return 既読フラグ（0：未読, 1：既読）
     */
    public int getReadFlag() {
        return readFlag;
    }

    /**
     * 既読フラグ（0：未読, 1：既読）設定
     *
     * @param readFlag 既読フラグ（0：未読, 1：既読）
     */
    public void setReadFlag(int readFlag) {
        this.readFlag = readFlag;
    }
}
