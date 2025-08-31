package com.mvc.web.service;

import com.mvc.web.bean.CommonNoticeBean;
import com.mvc.web.bean.entity.*;
import com.mvc.web.constants.Constants;
import com.mvc.web.mapper.NtcDtlMapper;
import com.mvc.web.mapper.NtcMstMapper;
import com.mvc.web.mapper.UsrNtcMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoticeService {

    /**
     * お知らせ情報管理テーブル用 Mapper
     */
    private final NtcMstMapper ntcMstMapper;
    /**
     * お知らせ情報詳細テーブル用 Mapper
     */
    private final NtcDtlMapper ntcDtlMapper;
    /**
     * 利用者のお知らせ表示状況テーブル用 Mapper
     */
    private final UsrNtcMapper usrNtcMapper;

    /**
     * コンストラクター
     *
     * @param ntcMstMapper お知らせ情報管理テーブル用 Mapper
     * @param ntcDtlMapper お知らせ情報詳細テーブル用 Mapper
     * @param usrNtcMapper 利用者のお知らせ表示状況テーブル用 Mapper
     */
    public NoticeService(NtcMstMapper ntcMstMapper, NtcDtlMapper ntcDtlMapper, UsrNtcMapper usrNtcMapper) {
        this.ntcMstMapper = ntcMstMapper;
        this.ntcDtlMapper = ntcDtlMapper;
        this.usrNtcMapper = usrNtcMapper;
    }

    /**
     * システムお知らせ取得
     *
     * @return システムお知らせリスト
     */
    public List<CommonNoticeBean> getSystemNotices() {
        return getNotices(Constants.NOTICE_LEVEL_SYSTEM_0);
    }

    /**
     * 管理人お知らせ取得
     *
     * @return 管理人お知らせリスト
     */
    public List<CommonNoticeBean> getAdminNotices() {
        return getNotices(Constants.NOTICE_LEVEL_ADMIN_1);
    }

    /**
     * お知らせ詳細内容取得
     *
     * @param id お知らせID
     * @return お知らせ詳細内容
     */
    public String getNoticeContentById(long id) {
        System.out.println("ID=[" + id + "]");
        // お知らせ情報詳細情報取得
        NtcDtl ntcDtl = ntcDtlMapper.selectByPrimaryKey(id);
        return new String(ntcDtl.getContent());
    }

    /**
     * 既読状態更新
     *
     * @param id お知らせID
     */
    public boolean markAsRead(long id) {
        // 検索条件設定
        UsrNtcKey usrNtcKey = new UsrNtcKey();
        usrNtcKey.setUserid(getCurrentUsername().toUpperCase()); // ユーザーID
        usrNtcKey.setNoticeId(id); // お知らせID
        // 検索実施
        UsrNtc usrNtc = usrNtcMapper.selectByPrimaryKey(usrNtcKey);
        // 更新内容設定
        usrNtc.setReadFlg(Constants.READ_FLAG_READED); // 既読フラグ
        // 既読状態更新
        int cnt = usrNtcMapper.updateByPrimaryKey(usrNtc);
        // 更新成功の場合、true返す
        return cnt == 1;
    }

    /**
     * お知らせ共通Beanリスト取得
     *
     * @param level お知らせレベル
     * @return お知らせ共通Beanリスト
     */
    private List<CommonNoticeBean> getNotices(int level) {
        // お知らせ共通Beanリスト
        List<CommonNoticeBean> systemNoticeList = new ArrayList<CommonNoticeBean>();
        // お知らせ情報管理検索条件設定
        NtcMstExample ntcMstExample = new NtcMstExample();
        ntcMstExample.createCriteria()
                .andLevelEqualTo(level) // レベル指定
                .andDelFlgEqualTo(Constants.DEL_FLAG_NOT_DEL_0); // 削除フラグ＝未削除
        // お知らせ情報管理取得
        List<NtcMst> ntcMstList = ntcMstMapper.selectByExample(ntcMstExample);
        // 結果なしの場合
        if (ntcMstList == null || ntcMstList.isEmpty()) {
            // 空の結果返す
            return systemNoticeList;
        }
        // お知らせ情報管理リストからお知らせID取得
        List<Long> noticeIds = Optional.of(ntcMstList)
                .orElse(Collections.emptyList())
                .stream()
                .map(NtcMst::getNoticeId)
                .collect(Collectors.toList());
        // お知らせ情報詳細検索条件設定
        NtcDtlExample ntcDtlExample = new NtcDtlExample();
        ntcDtlExample.createCriteria()
                .andNoticeIdIn(noticeIds) // お知らせID
                .andLevelEqualTo(level); // レベル - システムお知らせ
        // お知らせ情報詳細取得
        List<NtcDtl> ntcDtlList = ntcDtlMapper.selectByExample(ntcDtlExample);
        // 利用者のお知らせ表示状況検索条件設定
        UsrNtcExample usrNtcExample = new UsrNtcExample();
        usrNtcExample.createCriteria()
                .andUseridEqualTo(getCurrentUsername().toUpperCase()) // ユーザーID
                .andNoticeIdIn(noticeIds) // お知らせID
                .andDelFlgEqualTo(Constants.DEL_FLAG_NOT_DEL_0); // 削除フラグ＝未削除
        // 利用者のお知らせ表示状況取得
        List<UsrNtc> usrNtcList = usrNtcMapper.selectByExample(usrNtcExample);
        // データ変換して返す
        systemNoticeList = convertBeanToCommonNoticeBean(ntcMstList, ntcDtlList, usrNtcList);
        return systemNoticeList;
    }

    /**
     * お知らせ共通Beanリスト作成
     *
     * @param ntcMstList お知らせ情報管理情報リスト
     * @param ntcDtlList お知らせ情報詳細情報リスト
     * @param usrNtcList 利用者のお知らせ表示状況情報リスト
     * @return お知らせ共通Beanリスト
     */
    private List<CommonNoticeBean> convertBeanToCommonNoticeBean(List<NtcMst> ntcMstList, List<NtcDtl> ntcDtlList, List<UsrNtc> usrNtcList) {
        // お知らせ共通Beanリスト定義
        List<CommonNoticeBean> commonNoticeList = null;
        // NtcDtl を noticeId で Map に変換
        Map<Long, String> ntcDtlMap = ntcDtlList.stream()
                .filter(ntcDtl -> ntcDtl.getTitle() != null && !ntcDtl.getTitle().isBlank())
                .collect(Collectors.toMap(NtcDtl::getNoticeId, NtcDtl::getTitle));

        // UsrNtc を noticeId で Map に変換
        Map<Long, Integer> usrNtcMap = usrNtcList.stream()
                .collect(Collectors.toMap(UsrNtc::getNoticeId, UsrNtc::getReadFlg));

        // Mst リストから NoticeSummary にマージ
        commonNoticeList = ntcMstList.stream()
                .map(ntcMst -> {
                    // お知らせ共通Beanクラス生成
                    CommonNoticeBean commonNoticeBean = new CommonNoticeBean();
                    commonNoticeBean.setId(ntcMst.getNoticeId()); // お知らせID
                    commonNoticeBean.setTitle(ntcDtlMap.get(ntcMst.getNoticeId())); // タイトル
                    commonNoticeBean.setReadFlag(usrNtcMap.get(ntcMst.getNoticeId())); // 既読フラグ
                    return commonNoticeBean;
                })
                .collect(Collectors.toList());
        return commonNoticeList;
    }

    /**
     * ログイン中のユーザーID取得
     *
     * @return ログイン中のユーザーID
     */
    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName();
        }
        return Constants.BLANK;
    }
}
