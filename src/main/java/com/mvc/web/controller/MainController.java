package com.mvc.web.controller;

import com.mvc.web.bean.MenuBean;
import com.mvc.web.bean.SystemNoticeBean;
import com.mvc.web.service.MenuService;
import com.mvc.web.service.SystemNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    /**
     * メニューサービス
     */
    private final MenuService menuService;
    /**
     * システムお知らせサービス
     */
    private final SystemNoticeService systemNoticeService;

    @Autowired
    public MainController(MenuService menuService, SystemNoticeService systemNoticeService) {
        this.menuService = menuService;
        this.systemNoticeService = systemNoticeService;
    }

    @RequestMapping({"/login", "/"})
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        System.out.println("indexが呼ばれました！modelのloginTime=[" + model.getAttribute("loginTime") + "]");
        // ログイン時刻を設定（現在時刻を利用）
        String loginTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        model.addAttribute("loginTime", loginTime);
        return "index";
    }

    @RequestMapping("/main")
    public String DefaultIndexMain() {
        System.out.println("DefaultIndexMainが呼ばれました！");
        return "main/main";
    }

    @RequestMapping("/logout")
    public String logout() {
        System.out.println("ログアウト処理実施。ログイン画面に遷移。");
        return "redirect:/login";
    }

    /**
     * メニューリスト取得
     *
     * @return メニュー
     */
    @RequestMapping(value = "/getMenu", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, MenuBean> getMenu() {
        // メニュー階層をJSON形式で返す
        return menuService.getMenu();
    }

    @RequestMapping("/getNotices")
    @ResponseBody
    public List<SystemNoticeBean> getNotices() {
        return systemNoticeService.getAllNotices(); // title, id, readFlag
    }

    @RequestMapping(value = "/getNoticeContent", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String getNoticeContent(@RequestParam("id") int id) {
        return systemNoticeService.getNoticeContentById(id); // HTML形式で返す
    }

    @RequestMapping("/markAsRead")
    @ResponseBody
    public void markAsRead(@RequestParam("id") int id) {
        systemNoticeService.markAsRead(id);
    }
}
