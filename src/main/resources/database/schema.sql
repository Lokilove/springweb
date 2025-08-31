-- 1) お知らせ情報管理テーブル
DROP TABLE IF EXISTS NTC_MST;
CREATE TABLE NTC_MST (
                         NOTICE_ID    BIGINT       NOT NULL,         -- 16桁の数字
                         LEVEL        SMALLINT     NOT NULL,         -- 0 or 1
                         DISP_LIMIT   TIMESTAMP    NOT NULL,         -- 表示期限
                         REG_USER     VARCHAR(100) NOT NULL,         -- 登録者
                         REG_DATE     TIMESTAMP    NOT NULL,         -- 登録日
                         DEL_FLG      SMALLINT     DEFAULT 0,        -- 0 or 1
                         DEL_USER     VARCHAR(100),                 -- 削除者
                         DEL_DATE     TIMESTAMP,                    -- 削除日
                         UPD_USER     VARCHAR(100) NOT NULL,        -- 最終更新者
                         UPD_DATE     TIMESTAMP    NOT NULL,        -- 最終更新日
                         CONSTRAINT PK_NTC_MST PRIMARY KEY (NOTICE_ID)
);

-- 2) お知らせ情報詳細テーブル
DROP TABLE IF EXISTS NTC_DTL;
CREATE TABLE NTC_DTL (
                         NOTICE_ID    BIGINT       NOT NULL,        -- 16桁の数字
                         LEVEL        SMALLINT     NOT NULL,        -- 0 or 1
                         TITLE        VARCHAR(255) NOT NULL,        -- タイトル
                         CONTENT      CLOB,                         -- 詳細内容
                         UPD_USER     VARCHAR(100) NOT NULL,        -- 最終更新者
                         UPD_DATE     TIMESTAMP    NOT NULL,        -- 最終更新日
                         CONSTRAINT PK_NTC_DTL PRIMARY KEY (NOTICE_ID),
                         CONSTRAINT FK_NTC_DTL FOREIGN KEY (NOTICE_ID) REFERENCES NTC_MST(NOTICE_ID)
);

-- 3) 利用者のお知らせ表示状況
DROP TABLE IF EXISTS USR_NTC;
CREATE TABLE USR_NTC (
                         USERID       VARCHAR(100) NOT NULL,        -- ユーザーID
                         NOTICE_ID    BIGINT       NOT NULL,        -- 16桁の数字
                         READ_FLG     SMALLINT     DEFAULT 0,       -- 既読フラグ（0 or 1）
                         DEL_FLG      SMALLINT     DEFAULT 0,        -- 0 or 1
                         UPD_DATE     TIMESTAMP    NOT NULL,        -- 最終更新日
                         CONSTRAINT PK_USR_NTC PRIMARY KEY (USERID, NOTICE_ID),
                         CONSTRAINT FK_USR_NTC FOREIGN KEY (NOTICE_ID) REFERENCES NTC_MST(NOTICE_ID)
);
