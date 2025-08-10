<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/com-taglibs.jspf" %>
<%@ include file="/WEB-INF/views/common/com-stylesheet.jspf" %>
<%@ include file="/WEB-INF/views/common/com-script.jspf" %>

<!-- ページタイトル用の非表示要素 -->
<div id="pageTitle" style="display:none;">トップページ</div>

<!-- メインエリア -->
<div>
    <h1>ようこそ <sec:authentication property="name"/> さん</h1>
    <p>★　システムからのお知らせ</p>
</div>

<div id="noticeArea" class="p-3 border bg-light" style="height: 250px; overflow-y: auto;">
    <!-- お知らせはここに動的に表示 -->
</div>

<!-- モーダル（ポップアップ） -->
<div class="modal fade" id="noticeModal" tabindex="-1" aria-hidden="true" data-bs-backdrop="static"
     data-bs-keyboard="false">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">お知らせ詳細</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="閉じる"></button>
            </div>
            <div class="modal-body" id="noticeContent">
                <!-- 内容がここに入る -->
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        loadNotices();

        function loadNotices() {
            $.getJSON(contextPath + "/getNotices", function (data) {
                const container = $("#noticeArea");
                container.empty();

                if (data.length === 0) {
                    container.append('<p class="text-muted">現在、お知らせがありません</p>');
                    return;
                }

                data.forEach(function (notice) {
                    console.log("notice", notice);
                    const isNew = notice.readFlag === 0;
                    const noticeId = notice.id;
                    const displayText = (isNew ? '<span style="color:red;">(New)</span> ' : '') + notice.title;

                    const link = $('<a href="#" class="d-block text-decoration-none mb-2"></a>')
                        .html(displayText)
                        .click(function (e) {
                            e.preventDefault();
                            openNoticeModal(noticeId);
                            if (isNew) {
                                markAsRead(noticeId, $(this));
                            }
                        });

                    container.append(link);
                });
            });
        }

        function openNoticeModal(noticeId) {
            $.get(contextPath + "/getNoticeContent?id=" + noticeId, function (content) {
                $("#noticeContent").html(content);
                const modal = new bootstrap.Modal(document.getElementById('noticeModal'));
                modal.show();
            });
        }

        function markAsRead(id, linkElement) {
            $.post(contextPath + "/markAsRead", {id: id}, function () {
                linkElement.html(linkElement.text().replace('(New) ', ''));
            });
        }
    });
</script>
