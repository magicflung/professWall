package com.profess.dao;

import com.profess.pojo.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeDao extends JpaRepository<Notice, Integer> {

    @Query(value = "SELECT id, noticePage, noticeContent FROM notice WHERE noticePage = :noticePage", nativeQuery = true)
    Notice getNoticeContent(@Param("noticePage") String noticePage);
}
