package com.profess.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Data<T> {
    // 页数
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalpage;

    // 图片id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String expImgid;

    // 数据列表
    @JsonIgnoreProperties({"expId"})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> expList;

    // 公告
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String noticeContent;

    public Data() {
    }

    /**
     * 数据
     * @param expList
     */
    public Data(List<T> expList) {
        this.expList = expList;
    }

    /**
     * 图片id或公告，根据flag来取
     * @param value
     * @param flag 1 表示 expImgid；2 表示 noticeContent
     */
    public Data(String value, int flag) {
        if(flag == 0) {
            this.expImgid = value;
        } else if(flag == 1) {
            this.noticeContent = value;
        }
    }

    /**
     * 页数和数据
     * @param totalpage
     * @param expList
     */
    public Data(Integer totalpage, List<T> expList) {
        this.totalpage = totalpage;
        this.expList = expList;
    }


    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    public String getExpImgid() {
        return expImgid;
    }

    public void setExpImgid(String expImgid) {
        this.expImgid = expImgid;
    }

    public List<T> getExpList() {
        return expList;
    }

    public void setExpList(List<T> expList) {
        this.expList = expList;
    }
}
