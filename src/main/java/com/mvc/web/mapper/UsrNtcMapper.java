package com.mvc.web.mapper;

import com.mvc.web.bean.entity.UsrNtc;
import com.mvc.web.bean.entity.UsrNtcExample;
import com.mvc.web.bean.entity.UsrNtcKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsrNtcMapper {
    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する countByExample メソッド
     */
    long countByExample(UsrNtcExample example);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する deleteByExample メソッド
     */
    int deleteByExample(UsrNtcExample example);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する deleteByPrimaryKey メソッド
     */
    int deleteByPrimaryKey(UsrNtcKey key);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する insert メソッド
     */
    int insert(UsrNtc row);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する insertSelective メソッド
     */
    int insertSelective(UsrNtc row);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する selectByExample メソッド
     */
    List<UsrNtc> selectByExample(UsrNtcExample example);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する selectByPrimaryKey メソッド
     */
    UsrNtc selectByPrimaryKey(UsrNtcKey key);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する updateByExampleSelective メソッド
     */
    int updateByExampleSelective(@Param("row") UsrNtc row, @Param("example") UsrNtcExample example);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する updateByExample メソッド
     */
    int updateByExample(@Param("row") UsrNtc row, @Param("example") UsrNtcExample example);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する updateByPrimaryKeySelective メソッド
     */
    int updateByPrimaryKeySelective(UsrNtc row);

    /**
     * 利用者のお知らせ表示状況（USR_NTC） に対する updateByPrimaryKey メソッド
     */
    int updateByPrimaryKey(UsrNtc row);
}