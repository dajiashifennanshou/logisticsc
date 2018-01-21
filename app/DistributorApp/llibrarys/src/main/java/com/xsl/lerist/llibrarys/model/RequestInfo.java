package com.xsl.lerist.llibrarys.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Lerist on 2015/8/28, 0028.
 */
public class RequestInfo {
    public static final String TAG = RequestInfo.class.getSimpleName();
    private String type; //请求类型
    private JSONObject args; //附带参数(json)

    public RequestInfo() {
        this.args = new JSONObject();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getArgs() {
        return args;
    }

    public void setArgs(JSONObject args) {
        this.args = args;
    }

    public void putArg(String argKey,Object argValue){
        args.put(argKey,argValue);
    }

    public void putArg(Object obj){
        args.put(obj.getClass().getSimpleName(),obj);
    }

    public Object getArg(String argKey){
        return args.get(argKey);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static RequestInfo toRequestInfo(String requestInfoJson){
        return JSON.parseObject(requestInfoJson,RequestInfo.class);
    }
}
