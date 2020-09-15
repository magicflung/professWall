package com.profess.service;

import com.profess.dao.NoticeDao;
import com.profess.pojo.Notice;
import com.profess.util.Data;
import com.profess.util.JSONResult;
import com.profess.util.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    /**
     * 查询公告
     * @param noticePage 公告页名
     * @return
     */
    public JSONResult<Object> get(String noticePage){
        Notice notice = noticeDao.getNoticeContent(noticePage);
        if(notice == null){
            return new JSONResult<>(Meta.NOT_OUND);
        }
        return new JSONResult<Object>(new Data(notice.getNoticeContent(), 1), new Meta(200, "请求成功"));
    }
}
