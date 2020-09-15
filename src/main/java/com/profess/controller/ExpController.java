package com.profess.controller;

import com.profess.component.RequestTimes;
import com.profess.pojo.Exp;
import com.profess.service.ExpService;
import com.profess.util.JSONResult;
import com.profess.util.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ExpController {
    @Autowired
    private ExpService expService;


    /**
     * 查询列表
     * 出现问题：刚开始使用@RequestParam接收不到json中的pagenum，
     * 现在使用@RequestBody，把前端传递的按键值保存在map中
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/exps", consumes="application/json;charset=UTF-8")
    public JSONResult<Exp> list(@RequestBody Map<String,Object> map) throws Exception {
        int size = 5;
        Integer pagenum = (Integer) map.get("pagenum");
        if(pagenum == null) {
            return new JSONResult<>(new Meta(202, "缺少参数"));
        }
        return expService.list(pagenum, size);
    }

    /**
     * 添加留言
     * @param exp expObj！=null || expType！=null || expContent！=null
     * @return 200：留言成功
     * @throws Exception
     */
    @RequestTimes(count = 2, time = 86400)
    @PostMapping(value = "/add", produces = "application/json;charset=UTF-8")
    public JSONResult<Object> add(@RequestBody Exp exp, HttpServletRequest request) throws Exception {
        if(request.getAttribute("ifovertimes").equals("true")){
            return new JSONResult<>(new Meta(202, "每天只能留言2次"));
        }
        return expService.add(exp);
    }


}
