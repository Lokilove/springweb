<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<div id="left-nav" class="bg-light border-end">--%>
<div id="sidebar" class="bg-light border-end">
    <ul id="menu-container" class="list-unstyled p-2 m-0">
        <!-- メニューがここに動的に描画されます -->
    </ul>
</div>

<script>
    $(document).ready(function () {
        // メニュー取得
        $.ajax({
            url: contextPath + '/getMenu',
            method: "GET",
            dataType: "json",
            headers: {
                [csrfHeader]: csrfToken,
                "Accept": "application/json"
            },
            success: function (menuData) {
                // メニュー構成
                renderMenu(menuData);
            },
            error: function () {
                $('#menu-container').html('<li class="text-danger">メニューの取得に失敗しました。</li>');
            }
        });

        /**
         * メニュー構成
         * @param menuMap メニューデータ
         */
        function renderMenu(menuMap) {
            // コンテナーをクリア
            const $container = $("#menu-container").empty();
            // メニューデータを設定
            Object.values(menuMap).forEach(menu => {
                const $mainLi = $('<li>').addClass('has-submenu').css({marginBottom: '8px'});
                const $mainBtn = $('<button>')
                    .addClass('menu-button btn btn-outline-secondary w-100 text-start fw-bold')
                    .text(menu.menuName);
                $mainLi.append($mainBtn);
                if (menu.subMenu?.length) {
                    const $subUl = $('<ul>')
                        .addClass('submenu')
                        .css({
                            listStyle: 'none',
                            paddingLeft: '15px',
                            display: 'none',
                            marginTop: '4px'
                        });
                    menu.subMenu.forEach(sub => {
                        const $subLi = $('<li>').css({marginBottom: '4px'});
                        if (sub.subMenu?.length) {
                            const $subBtn = $('<button>')
                                .addClass('menu-button btn btn-light w-100 text-start')
                                .text(sub.menuName);

                            $subLi.addClass('has-submenu').append($subBtn);
                            const $subSubUl = $('<ul>')
                                .addClass('submenu')
                                .css({
                                    listStyle: 'none',
                                    paddingLeft: '15px',
                                    display: 'none',
                                    marginTop: '3px'
                                });
                            sub.subMenu.forEach(subsub => {
                                const $leafBtn = $('<button>')
                                    .addClass('menu-button-leaf btn btn-sm btn-link w-100 text-start')
                                    .text(subsub.menuName)
                                    .css({
                                        color: '#0d6efd',
                                        textDecoration: 'none'
                                    })
                                    .on('click', () => {
                                        loadContent(subsub.menuUrl);
                                    });
                                $subSubUl.append($('<li>').append($leafBtn));
                            });
                            $subLi.append($subSubUl);
                        } else {
                            const $leafBtn = $('<button>')
                                .addClass('menu-button-leaf btn btn-sm btn-link w-100 text-start')
                                .text(sub.menuName)
                                .css({
                                    color: '#0d6efd',
                                    textDecoration: 'none'
                                })
                                .on('click', () => {
                                    loadContent(sub.menuUrl);
                                });
                            $subLi.append($leafBtn);
                        }
                        $subUl.append($subLi);
                    });
                    $mainLi.append($subUl);
                }
                $container.append($mainLi);
            });
            setupMenuToggle();
        }

        function setupMenuToggle() {
            // 親メニューの展開・折りたたみ
            $("#menu-container").on("click", ".has-submenu > button.menu-button", function () {
                $(this).siblings("ul.submenu").slideToggle(200);
            });
        }

        function loadContent(relativeUrl) {
            const fullUrl = window.appContext.contextPath + relativeUrl;

            $.ajax({
                url: fullUrl,
                type: 'GET',
                headers: {
                    [window.appContext.csrfHeader]: window.appContext.csrfToken
                },
                success: function (data) {
                    $('#mainContent').html(data);

                    const newTitle = $(data).filter('title').text();
                    if (newTitle) {
                        document.title = newTitle + ' - 管理システム';
                    }
                },
                error: function () {
                    alert('ページの読み込みに失敗しました。');
                }
            });
        }
    });
</script>