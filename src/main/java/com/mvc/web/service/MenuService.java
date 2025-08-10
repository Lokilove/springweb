package com.mvc.web.service;

import com.mvc.web.bean.MenuBean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MenuService {

    public Map<String, MenuBean> getMenu() {
        Map<String, MenuBean> menuMap = new LinkedHashMap<>();

        // ───── 問題管理 ─────
        MenuBean singleCreate = new MenuBean();
        singleCreate.setMenuId("create-single");
        singleCreate.setMenuName("単一問題作成");
        singleCreate.setMenuUrl("/problem/create/single");

        MenuBean multiCreate = new MenuBean();
        multiCreate.setMenuId("create-multi");
        multiCreate.setMenuName("複数問題作成");
        multiCreate.setMenuUrl("/problem/create/multi");

        MenuBean bulkUpdate = new MenuBean();
        bulkUpdate.setMenuId("bulk-update");
        bulkUpdate.setMenuName("一括アップデート");
        bulkUpdate.setMenuUrl("/problem/create/bulk");

        MenuBean createRoot = new MenuBean();
        createRoot.setMenuId("create");
        createRoot.setMenuName("新規作成");
        createRoot.setMenuUrl("#");
        createRoot.setSubMenu(Arrays.asList(singleCreate, multiCreate, bulkUpdate));

        MenuBean edit = new MenuBean();
        edit.setMenuId("edit");
        edit.setMenuName("問題編集");
        edit.setMenuUrl("/problem/edit");

        MenuBean search = new MenuBean();
        search.setMenuId("search");
        search.setMenuName("問題検索");
        search.setMenuUrl("/problem/search");

        MenuBean delete = new MenuBean();
        delete.setMenuId("delete");
        delete.setMenuName("問題削除");
        delete.setMenuUrl("/problem/delete");

        MenuBean problemMgmt = new MenuBean();
        problemMgmt.setMenuId("problem");
        problemMgmt.setMenuName("問題管理");
        problemMgmt.setMenuUrl("#");
        problemMgmt.setSubMenu(Arrays.asList(createRoot, edit, search, delete));

        // ───── 利用者管理 ─────
        MenuBean userReg = new MenuBean();
        userReg.setMenuId("user-reg");
        userReg.setMenuName("利用者登録");
        userReg.setMenuUrl("/user/register");

        MenuBean userMod = new MenuBean();
        userMod.setMenuId("user-mod");
        userMod.setMenuName("利用者変更");
        userMod.setMenuUrl("/user/modify");

        MenuBean userDel = new MenuBean();
        userDel.setMenuId("user-del");
        userDel.setMenuName("利用者削除");
        userDel.setMenuUrl("/user/delete");

        MenuBean userBulk = new MenuBean();
        userBulk.setMenuId("user-bulk");
        userBulk.setMenuName("利用者一括登録");
        userBulk.setMenuUrl("/user/bulk");

        MenuBean userRole = new MenuBean();
        userRole.setMenuId("user-role");
        userRole.setMenuName("利用者権限管理");
        userRole.setMenuUrl("/user/role");

        MenuBean userMgmt = new MenuBean();
        userMgmt.setMenuId("user");
        userMgmt.setMenuName("利用者管理");
        userMgmt.setMenuUrl("#");
        userMgmt.setSubMenu(Arrays.asList(userReg, userMod, userDel, userBulk, userRole));

        // ───── システム管理 ─────
        MenuBean menuMgmt = new MenuBean();
        menuMgmt.setMenuId("menu-mgmt");
        menuMgmt.setMenuName("メニュー管理");
        menuMgmt.setMenuUrl("/system/menu");

        MenuBean noticeMgmt = new MenuBean();
        noticeMgmt.setMenuId("notice-mgmt");
        noticeMgmt.setMenuName("お知らせ管理");
        noticeMgmt.setMenuUrl("/system/notice");

        MenuBean systemMgmt = new MenuBean();
        systemMgmt.setMenuId("system");
        systemMgmt.setMenuName("システム管理");
        systemMgmt.setMenuUrl("#");
        systemMgmt.setSubMenu(Arrays.asList(menuMgmt, noticeMgmt));

        // --- Mapにまとめて追加 ---
        menuMap.put(problemMgmt.getMenuId(), problemMgmt);
        menuMap.put(userMgmt.getMenuId(), userMgmt);
        menuMap.put(systemMgmt.getMenuId(), systemMgmt);

        return menuMap;
    }
}
