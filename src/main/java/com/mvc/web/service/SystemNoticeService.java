package com.mvc.web.service;

import com.mvc.web.bean.SystemNoticeBean;
import com.mvc.web.constants.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemNoticeService {

    /**
     * すべてのシステムお知らせ取得
     *
     * @return システムお知らせリスト
     */
    public List<SystemNoticeBean> getAllNotices() {
        List<SystemNoticeBean> systemNoticeList = new ArrayList<>();

        SystemNoticeBean systemNoticeBean = new SystemNoticeBean();
        systemNoticeBean.setId(1);
        systemNoticeBean.setTitle("【重要】システムメンテナンスのお知らせ！");
        systemNoticeBean.setReadFlag(0);
        systemNoticeList.add(systemNoticeBean);

        SystemNoticeBean systemNoticeBean2 = new SystemNoticeBean();
        systemNoticeBean2.setId(2);
        systemNoticeBean2.setTitle("【重要】メールサービス不安定のお知らせ！");
        systemNoticeBean2.setReadFlag(1);
        systemNoticeList.add(systemNoticeBean2);

        return systemNoticeList;
    }

    public String getNoticeContentById(int id) {
        System.out.println("ID=[" + id + "]");
        String content = Constants.BLANK;
        if (id == 1) {
            content = "システムメンテナンスのテスト表示内容";
        } else {
            content = "メールサービス不安定。";
        }
        return content;
    }

    public void markAsRead(int id) {
        System.out.println("ID=[" + id + "], 既読に変更します。");
    }
}
