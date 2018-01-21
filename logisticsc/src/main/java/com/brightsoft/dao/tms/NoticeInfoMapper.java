package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.NoticeInfo;
import com.brightsoft.model.User;
import com.brightsoft.utils.Page;

public interface NoticeInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoticeInfo record);

    int insertSelective(NoticeInfo record);

    NoticeInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NoticeInfo record);

    int updateByPrimaryKey(NoticeInfo record);
    
    //获取总记录数
    int countRows(@Param("noticeInfo")NoticeInfo noticeInfo,@Param("user")User user);
    
    //通过通知类型获取信息
    List<NoticeInfo> selectByNoticeType(@Param("page")Page<?> page,@Param("noticeInfo")NoticeInfo noticeInfo,@Param("user")User user);
}