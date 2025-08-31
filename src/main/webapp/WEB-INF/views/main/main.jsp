<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/com-taglibs.jspf" %>
<%@ include file="/WEB-INF/views/common/com-stylesheet.jspf" %>
<%@ include file="/WEB-INF/views/common/com-script.jspf" %>

<!-- ページタイトル用の非表示要素 -->
<div id="pageTitle" style="display:none;">トップページ</div>

<!-- メインエリア -->
<!-- システムお知らせ -->
<div class="card mb-3">
    <div class="card-header"><b>★　システムからのお知らせ</b></div>
    <div id="systemNoticeArea" class="card-body p-3 bg-light" style="max-height: 250px; overflow-y: auto;">
        <!-- お知らせはここに動的に表示 -->
    </div>
</div>
<!-- 管理人お知らせ -->
<div class="card mb-3">
    <div class="card-header"><b>◎　管理人からのお知らせ</b></div>
    <div id="adminNoticeArea" class="card-body p-3 bg-light" style="max-height: 250px; overflow-y: auto;">
        <!-- お知らせはここに動的に表示 -->
    </div>
</div>

<script>
    $(document).ready(function () {
        // システムお知らせ
        loadSystemNotices();
        // 管理人からのお知らせ
        loadAdminNotices();

        /**
         * システムのお知らせを取得して表示
         */
        function loadSystemNotices() {
            $.getJSON(contextPath + "/getSystemNotices", function (data) {
                const container = $("#systemNoticeArea");
                container.empty();
                setData(container, data);
            });
        }

        /**
         * 管理人のお知らせを取得して表示
         */
        function loadAdminNotices() {
            $.getJSON(contextPath + "/getAdminNotices", function (data) {
                const container = $("#adminNoticeArea");
                container.empty();
                setData(container, data);
            });
        }

        /**
         * お知らせ内容を指定コンテナーに設定
         * @param container 指定コンテナー
         * @param data お知らせデータ
         */
        function setData(container, data) {
            // システムお知らせがない場合
            if (data.length === 0) {
                container.append('<p class="card-text text-muted">現在、お知らせがありません</p>');
                return;
            }
            // システムのお知らせ表示
            data.forEach(function (notice) {
                const isNew = notice.readFlag === 0;
                const displayText = (isNew ? '<span class="card-text" style="color:red;">(New)</span> ' : '') + notice.title;
                const link = $('<a href="#" class="card-text d-block text-decoration-none mb-2"></a>')
                    .html(displayText)
                    .click(function (e) {
                        e.preventDefault();
                        // ポップアップウィンド開く
                        openPopup(notice, $(this));
                    });
                container.append(link);
            });
        }

        /**
         * ポップアップウィンドでお知らせ内容表示
         * @param notice クリックしたお知らせ情報
         * @param linkElement 現在の要素
         */
        function openPopup(notice, linkElement) {
            const popup = window.open(
                contextPath + "/getNoticeContent?id=" + notice.id,      // 開くURL
                notice.title,               // 新ウィンドウの識別子（ターゲット名）
                "width=800,height=600," +   // サイズ
                "toolbar=no,menubar=no," +  // ツールバー・メニューバー非表示
                "location=no,status=no," +  // アドレスバー・ステータスバー非表示（※Chrome等では無効の場合あり）
                "resizable=no,scrollbars=yes" // サイズ変更不可、スクロールバー表示
            );
            // タイトル変更
            popup.onload = function () {
                popup.document.title = notice.title;
            };
            // 未読の場合、既読フラグ更新
            if (notice.readFlag === 0) {
                markAsRead(notice.id, linkElement);
            }
            // // ポップアップ閉じる場合
            // if (popup.closed) {
            //     // システムお知らせをリフレッシュ
            //     loadSystemNotices();
            //     // 管理人からのお知らせをリフレッシュ
            //     loadAdminNotices();
            // }
        }

        /**
         * 既読状態変更
         * @param id クリックしたお知らせID
         * @param linkElement お知らせタイトル内容
         */
        function markAsRead(id, linkElement) {
            $.ajax({
                url: contextPath + "/markAsRead",
                method: "POST",
                dataType: "text", //"json",
                data: {id: id},
                headers: {[csrfHeader]: csrfToken},
                success: function (res) {
                    if (res === "success") {
                        linkElement.html(linkElement.text().replace('(New) ', ''));
                    } else if (res === "fail") {
                        alert("既読にする処理途中、エラー発生しました！");
                    }
                },
                error: function (error) {
                    alert("既読にする送信途中、エラー発生しました！エラー：" + error);
                }
            })
        }
    });
</script>
