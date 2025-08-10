// メイン表示領域ID
const MAIN_AREA_ID = "#main-content";

/**
 * 右クリックを禁止
 */
$(document).on("contextmenu", function (e) {
    return false;
});

/**
 * Alt+←、Alt+→、BrowserBack/Forward、F5、Ctrl+R を禁止
 */
window.addEventListener("keydown", function (e) {
    const key = e.key;
    if (
        (e.altKey && (key === "ArrowLeft" || key === "ArrowRight")) || // Alt+←／→
        (key === "Backspace" && !$(e.target).is("input, textarea")) ||       // Backspace outside input
        key === "BrowserBack" || key === "BrowserForward" ||           // 専用キー
        key === "F5" ||                                                // F5
        (e.ctrlKey && key === "r")                                     // Ctrl+R
    ) {
        e.preventDefault();
        alert("該当操作は無効です。");
    }
});

// // ページリロード禁止（F5対策の補完）
// window.onbeforeunload = function () {
//     // ページ離脱を警告するかどうか
//     if (!location.pathname.endsWith("/login") && !location.pathname.endsWith("/logout")) {
//         return "ページをリロードしないでください。";
//     }
// };

/**
 * ローディングスピナー表示
 */
function showLoading() {
    $(MAIN_AREA_ID).html(
        '<div class="d-flex justify-content-center"><div class="spinner-border" role="status"><span class="visually-hidden">読込中...</span></div></div>'
    );
}

// popstate イベントで履歴の状態復元
window.addEventListener("popstate", function (event) {
    // const state = event.state;
    // if (state && state.url) {
    //     sendAjaxRequest("GET", state.url, null, null, false, false);
    // }
    alert("履歴操作は禁止されています。");
    // historyを1ページ先に戻して強制的に元に戻す
    history.go(1);
});

// 履歴固定：現在のページを履歴に登録し続ける（戻れないようにする）
// window.addEventListener("load", function () {
//     history.pushState(null, "", window.location.href);
// });

/**
 * ページタイトルの更新
 */
function updatePageTitle() {
    const newTitle = $("#pageTitle").text();
    console.log("タイトル：", newTitle);
    if (newTitle) {
        document.title = newTitle;
    }
}

// function loadPage(url, isPost = false, data = null, push = true) {
//     $.ajax({
//         url: url,
//         type: isPost ? 'POST' : 'GET',
//         data: data,
//         beforeSend: function (xhr) {
//             xhr.setRequestHeader(csrfHeader, csrfToken);
//         },
//         success: function (html) {
//             console.log("loadPage html=", html);
//             $(MAIN_AREA_ID).html(html);
//             if (push) history.pushState(null, null, url);
//         },
//         error: function (err) {
//             console.error("画面読み込みエラー:", err);
//         }
//     });
// }

function loadPage(url, push = true) {
    console.log("loadPage URL=", url);
    if (url.endsWith("/index") || url.endsWith("/logout")) {
        window.location.href = url;
    } else {
        document.getElementById("main-content").src = url;
    }
    updatePageTitle();
    if (push) history.pushState(null, null, url);
}

/**
 * Ajax送信共通関数
 * @param {string} method - HTTPメソッド
 * @param {string} url - リクエストURL（contextPathからの相対）
 * @param {Object|string|FormData|null} data - 送信データ
 * @param {function|null} callback - コールバック
 * @param {boolean} updateTitle - タイトルを更新するか（デフォルト true）
 * @param {boolean} updateUrl - URL欄を更新するか（デフォルト true）
 */
// function sendAjaxRequest(method, url, data, callback = null, updateTitle = true, updateUrl = true) {
//     // URLチェック
//     if (!url) {
//         return;
//     }
//     // ローディング表示
//     showLoading();
//     // Ajax送信処理
//     $.ajax({
//         type: method,
//         url: contextPath + url,
//         data: data,
//         contentType: data instanceof FormData ? false : "application/x-www-form-urlencoded; charset=UTF-8",
//         processData: !(data instanceof FormData),
//         beforeSend: function (xhr) {
//             xhr.setRequestHeader(csrfHeader, csrfToken);
//         },
//         success: function (response) {
//             console.log("response=", response);
//             $(MAIN_AREA_ID).html(response);
//             // タイトル更新
//             if (updateTitle) {
//                 updatePageTitle();
//             }
//             // URL更新
//             if (updateUrl) {
//                 const newUrl = contextPath + url;
//                 history.replaceState(null, "", newUrl);
//             }
//             // コールバック処理
//             if (callback) {
//                 callback(response);
//             }
//         },
//         error: function (xhr) {
//             const msg = xhr.responseText || "サーバーエラーが発生しました。";
//             console.error(msg);
//             $(MAIN_AREA_ID).html(`<div class="alert alert-danger">${msg}</div>`);
//         }
//     });
// }

/**
 * 初期ロード時、main.jspを表示（index.jsp読み込み後）
 */
// $(document).ready(function () {
//     // URLパスが/indexの場合ロード
//     if (window.location.pathname.endsWith("/index")) {
//         sendAjaxRequest("GET", "/main", null, null, true, false);
//     }
// });

/**
 * nav-button や dropdown-item のクリック時にAjax処理
 */
// $(document).on("click", ".nav-button, .dropdown-item", function (e) {
//     e.preventDefault();
//
//     const url = $(this).data("url");
//     const method = $(this).data("method") || "GET";
//     const params = $(this).data("params") || null;
//
//     console.log("ボタン処理起動");
//     console.log("URL: ", url);
//     console.log("method: ", method);
//     console.log("params: " + params);
//
//     // URLチェック
//     if (!url) {
//         return;
//     }
//     // ログアウト処理
//     if (url.endsWith("/logout")) {
//         // Ajax禁止、フルリダイレクト
//         window.location.href = contextPath + url;
//         // POSTでログアウトする
//         $.post(contextPath + url, {
//             _csrf: csrfToken  // CSRFトークンを送る必要あり
//         });
//     }
//     // Ajax送信処理
//     sendAjaxRequest(method, url, params);
// });

/**
 * form.ajax-form の submit をAjax化
 */
// $(document).on("submit", "form.ajax-form", function (e) {
//     e.preventDefault();
//
//     const $form = $(this);
//     const method = $form.attr("method") || "POST";
//     const url = $form.attr("action");
//
//     console.log("form処理起動");
//     console.log("url", url);
//     console.log("method", method);
//
//     let formData;
//     if ($form.attr("enctype") === "multipart/form-data") {
//         formData = new FormData(this);
//     } else {
//         formData = $form.serialize();
//     }
//     // Ajax送信処理
//     sendAjaxRequest(method, url, formData);
// });
