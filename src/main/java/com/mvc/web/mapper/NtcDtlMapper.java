package com.mvc.web.mapper;

import com.mvc.web.bean.entity.NtcDtl;
import com.mvc.web.bean.entity.NtcDtlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NtcDtlMapper {
    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する countByExample メソッド
     */
    long countByExample(NtcDtlExample example);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する deleteByExample メソッド
     */
    int deleteByExample(NtcDtlExample example);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する deleteByPrimaryKey メソッド
     */
    int deleteByPrimaryKey(Long noticeId);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する insert メソッド
     */
    int insert(NtcDtl row);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する insertSelective メソッド
     */
    int insertSelective(NtcDtl row);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する selectByExampleWithBLOBs メソッド
     */
    List<NtcDtl> selectByExampleWithBLOBs(NtcDtlExample example);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する selectByExample メソッド
     */
    List<NtcDtl> selectByExample(NtcDtlExample example);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する selectByPrimaryKey メソッド
     */
    NtcDtl selectByPrimaryKey(Long noticeId);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する updateByExampleSelective メソッド
     */
    int updateByExampleSelective(@Param("row") NtcDtl row, @Param("example") NtcDtlExample example);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する updateByExampleWithBLOBs メソッド
     */
    int updateByExampleWithBLOBs(@Param("row") NtcDtl row, @Param("example") NtcDtlExample example);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する updateByExample メソッド
     */
    int updateByExample(@Param("row") NtcDtl row, @Param("example") NtcDtlExample example);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する updateByPrimaryKeySelective メソッド
     */
    int updateByPrimaryKeySelective(NtcDtl row);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する updateByPrimaryKeyWithBLOBs メソッド
     */
    int updateByPrimaryKeyWithBLOBs(NtcDtl row);

    /**
     * お知らせ情報詳細テーブル（NTC_DTL） に対する updateByPrimaryKey メソッド
     */
    int updateByPrimaryKey(NtcDtl row);
}