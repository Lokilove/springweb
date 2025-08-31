package com.mvc.web.controller;

import com.mvc.web.bean.MenuBean;
import com.mvc.web.bean.CommonNoticeBean;
import com.mvc.web.service.MenuService;
import com.mvc.web.service.NoticeService;
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

/**
 * メイン画面処理コントローラー
 */
@Controller
public class MainController {

    /**
     * メニューサービス
     */
    private final MenuService menuService;
    /**
     * システムお知らせサービス
     */
    private final NoticeService noticeService;

    @Autowired
    public MainController(MenuService menuService, NoticeService noticeService) {
        this.menuService = menuService;
        this.noticeService = noticeService;
    }

    /**
     * ログインページ遷移
     *
     * @return 遷移先ページ
     */
    @RequestMapping({"/login", "/"})
    public String login() {
        return "login";
    }

    /**
     * レイアウト画面表示
     *
     * @param model 画面モデル
     * @return 遷移先ページ
     */
    @RequestMapping("/index")
    public String index(Model model) {
        // ログイン時刻を設定（現在時刻を利用）
        String loginTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        model.addAttribute("loginTime", loginTime);
        return "index";
    }

    /**
     * デフォルトメイン画面
     *
     * @return 遷移先ページ
     */
    @RequestMapping("/main")
    public String DefaultIndexMain() {
        return "main/main";
    }

    /**
     * ログアウト処理
     *
     * @return ログアウトにリダイレクト
     */
    @RequestMapping("/logout")
    public String logout() {
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

    /**
     * システムお知らせ取得
     *
     * @return システムお知らせリスト
     */
    @RequestMapping("/getSystemNotices")
    @ResponseBody
    public List<CommonNoticeBean> getSystemNotices() {
        return noticeService.getSystemNotices();
    }

    /**
     * 管理人お知らせ取得
     *
     * @return 管理人お知らせ
     */
    @RequestMapping("/getAdminNotices")
    @ResponseBody
    public List<CommonNoticeBean> getAdminNotices() {
        return noticeService.getAdminNotices();
    }

    /**
     * お知らせ内容詳細取得
     *
     * @param id お知らせID
     * @return お知らせ内容
     */
    @RequestMapping(value = "/getNoticeContent", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String getNoticeContent(@RequestParam("id") long id) {
        // HTML形式で返す
        return noticeService.getNoticeContentById(id);
    }

    /**
     * 既読フラグ設定
     *
     * @param id お知らせID
     * @return 処理結果
     */
    @RequestMapping("/markAsRead")
    @ResponseBody
    public String markAsRead(@RequestParam("id") long id) {
        if (noticeService.markAsRead(id)) {
            return "success";
        }
        return "fail";
    }
}
