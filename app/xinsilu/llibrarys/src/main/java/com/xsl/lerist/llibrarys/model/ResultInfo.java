package com.xsl.lerist.llibrarys.model;

import com.alibaba.fastjson.JSON;
import com.xsl.lerist.llibrarys.utils.StringUtils;

/**
 * Created by Lerist on 2015/8/28, 0028.
 */
public class ResultInfo {

    public static final String FLAG_SUCCESS = "success";
    private final int FLAGCODE_SUCCESS = 200;
    public static final String FLAG_FAILED = "failed";
    private final int FLAGCODE_FAILED = 201;

    private String flag; //标识
    private int code; //标识码 200
    private String data; //响应结果
    private String msg;

    public boolean isSuccess() {
        return FLAG_SUCCESS.equals(getFlag());
    }

    public boolean isFailed() {
        return FLAG_FAILED.equals(getFlag());
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static ResultInfo toResultInfo(String resultInfoJson) {
        if (StringUtils.isEmpty(resultInfoJson)) return null;

        ResultInfo resultInfo = null;
        try {
            resultInfo = JSON.parseObject(resultInfoJson, ResultInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultInfo;
    }
}
