package com.mvc.web.bean;

import java.util.List;

public class MenuBean {

    /**
     * メニューID
     */
    private String menuId;
    /**
     * メニュー名
     */
    private String menuName;
    /**
     * メニューURL
     */
    private String menuUrl;
    /**
     * サブメニュー
     */
    private List<MenuBean> subMenu;

    /**
     * メニューID取得
     *
     * @return メニューID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * メニューID設定
     *
     * @param menuId メニューID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public List<MenuBean> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<MenuBean> subMenu) {
        this.subMenu = subMenu;
    }
}
