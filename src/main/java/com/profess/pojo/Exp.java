package com.profess.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "exp")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Exp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expId")
    private int expId;

    @Column(name = "expObj")
    private String expObj;

    @Column(name = "expType")
    private Integer expType;

    @Column(name = "expContent", columnDefinition = "TEXT")
    private String expContent;

    @Column(name = "expImgid")
    private String expImgid;

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public String getExpObj() {
        return expObj;
    }

    public void setExpObj(String expObj) {
        this.expObj = expObj;
    }

    public Integer getExpType() {
        return expType;
    }

    public void setExpType(Integer expType) {
        this.expType = expType;
    }

    public String getExpContent() {
        return expContent;
    }

    public void setExpContent(String expContent) {
        this.expContent = expContent;
    }

    public String getExpImgid() {
        return expImgid;
    }

    public void setExpImgid(String expImgid) {
        this.expImgid = expImgid;
    }

    @Override
    public String toString() {
        return "Exp{" +
                "expId=" + expId +
                ", expObj='" + expObj + '\'' +
                ", expType=" + expType +
                ", expContent='" + expContent + '\'' +
                ", expImgid='" + expImgid + '\'' +
                '}';
    }
}
