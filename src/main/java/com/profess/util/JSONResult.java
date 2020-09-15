package com.profess.util;

/**
 * response的封装类
 * @param <T>
 */
public class JSONResult<T> {
    // 数据
    private Data<T> data;
    // 状态码和信息
    private Meta meta;


    public JSONResult() {
    }

    /**
     * 数据和信息
     * @param data
     * @param meta
     */
    public JSONResult(Data<T> data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    /**
     * 信息
     * @param meta
     */
    public JSONResult(Meta meta) {
        this.data = new Data<>();
        this.meta = meta;
    }


    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
