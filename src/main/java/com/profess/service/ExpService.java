package com.profess.service;

import com.profess.dao.ExpDao;
import com.profess.pojo.Exp;
import com.profess.util.Data;
import com.profess.util.JSONResult;
import com.profess.util.Meta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpService {
    @Autowired
    private ExpDao expDao;

    private Logger logger = LoggerFactory.getLogger(ExpService.class);

    /**
     * 添加
     * @param exp
     * @return
     */
    public JSONResult<Object> add(Exp exp){
        // 检查
        if(exp.getExpObj() == null || exp.getExpObj().equals("")) {
            logger.error("给谁留言字段不能为空");
            return new JSONResult(new Meta(202, "给谁留言不能为空"));
        } else if(exp.getExpType() == null ) {
            logger.error("留言类型的字段不能为空");
            return new JSONResult(new Meta(202, "留言类型不能为空"));
        } else if(exp.getExpContent() == null || exp.getExpContent().equals("")) {
            logger.error("留言内容的字段不能为空");
            return new JSONResult(new Meta(202, "留言内容都不能为空"));
        } else if(exp.getExpType() < 0 || exp.getExpType() > 4) {
            return new JSONResult(Meta.BAD_REQUEST);
        }
        // 保存
        expDao.save(exp);
        return new JSONResult(new Meta(200, "留言成功"));
    }

    /**
     * 获取列表
     * @param pagenum 当前页
     * @param size 每页记录数
     * @return
     */
    public JSONResult<Exp> list(Integer pagenum, int size){
        // 分页
        Pageable pageable = PageRequest.of(pagenum,size,Sort.by(Sort.Direction.DESC, "expId"));
        Page pageFromJPA = expDao.findAll(pageable);
        // 检查
        if(pagenum >  pageFromJPA.getTotalPages()){
            logger.error("查询列表超过总页数");
            return new JSONResult<>(new Meta(202, "超过总页数"));
        }
        // 返回
        List<Exp> exps = pageFromJPA.getContent();
        Data<Exp> data = new Data<>(pageFromJPA.getTotalPages(), exps);
        return new JSONResult<>(data, new Meta(200, "获取列表成功"));
    }


}
