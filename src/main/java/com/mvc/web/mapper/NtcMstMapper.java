package com.mvc.web.mapper;

import com.mvc.web.bean.entity.NtcMst;
import com.mvc.web.bean.entity.NtcMstExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NtcMstMapper {
    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する countByExample メソッド
     */
    long countByExample(NtcMstExample example);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する deleteByExample メソッド
     */
    int deleteByExample(NtcMstExample example);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する deleteByPrimaryKey メソッド
     */
    int deleteByPrimaryKey(Long noticeId);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する insert メソッド
     */
    int insert(NtcMst row);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する insertSelective メソッド
     */
    int insertSelective(NtcMst row);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する selectByExample メソッド
     */
    List<NtcMst> selectByExample(NtcMstExample example);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する selectByPrimaryKey メソッド
     */
    NtcMst selectByPrimaryKey(Long noticeId);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する updateByExampleSelective メソッド
     */
    int updateByExampleSelective(@Param("row") NtcMst row, @Param("example") NtcMstExample example);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する updateByExample メソッド
     */
    int updateByExample(@Param("row") NtcMst row, @Param("example") NtcMstExample example);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する updateByPrimaryKeySelective メソッド
     */
    int updateByPrimaryKeySelective(NtcMst row);

    /**
     * お知らせ情報管理テーブル（NTC_MST） に対する updateByPrimaryKey メソッド
     */
    int updateByPrimaryKey(NtcMst row);
}