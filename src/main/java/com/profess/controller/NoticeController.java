package com.profess.controller;

import com.profess.service.NoticeService;
import com.profess.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    /**
     * 查询公告内容
     * @param noticePage 公告页名
     * @return
     */
    @GetMapping("/getNotice")
    public JSONResult<Object> get(@RequestParam("noticePage") String noticePage) {
        // 去掉左右空字符
        noticePage = noticePage.trim();
        return noticeService.get(noticePage);
    }
}
