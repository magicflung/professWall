package com.profess.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "notice")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "noticePage")
    private String noticePage;

    @Column(name = "noticeContent")
    private String noticeContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticePage() {
        return noticePage;
    }

    public void setNoticePage(String noticePage) {
        this.noticePage = noticePage;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }
}
