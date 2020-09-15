package com.profess.util;

public class Meta {
    // 状态码
    private int status;
    // 信息
    private String msg;

    // 请求成功
    // public final static Meta SUCCESS = new Meta(200, "请求成功");
    // 创建成功
    public final static Meta CREATED = new Meta(201, "创建成功");
    // 缺少参数
    // public final static Meta ERROR = new Meta(202);
    // 删除成功
    public final static Meta DELETED = new Meta(204, "删除成功");
    // 请求的地址不存在或者包含不支持的参数
    public final static Meta BAD_REQUEST = new Meta(400, "请求的地址不存在或者包含不支持的参数");
    // 未授权
    public final static Meta UNAUTHORIZED = new Meta(401, "未授权");
    // 被禁止访问
    public final static Meta FORBIDDEN = new Meta(403, "被禁止访问");
    // 请求的资源不存在
    public final static Meta NOT_OUND = new Meta(404, "请求的资源不存在");
    // [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误
    public final static Meta Unprocesable_ENTITY = new Meta(422, "[POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误");
    // 内部错误
    public final static Meta INTERNAL_SERVER_ERROR = new Meta(500, "内部错误");

    public Meta() {
    }

    public Meta(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
