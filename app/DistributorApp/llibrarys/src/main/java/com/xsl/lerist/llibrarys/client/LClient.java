package com.xsl.lerist.llibrarys.client;


import com.xsl.lerist.llibrarys.model.RequestInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by Lerist on 2016/3/13, 0013.
 */
public class LClient {
    public Callback.Cancelable httpPost(String uri, String type, Object o, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(uri);
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setType(type);
        requestInfo.putArg(o);
        params.addParameter(RequestInfo.TAG, requestInfo);
        String oStr = o.toString();
        if (oStr.contains("file://")) {
            try {
                int startIndex = oStr.indexOf("file://");
                while (startIndex != -1) {
                    int endIndex = oStr.indexOf("\"", startIndex);
                    String filePath = oStr.substring(startIndex, endIndex);
                    params.addBodyParameter("file", new File(filePath.replace("file://","")));
                    startIndex = oStr.indexOf("file://", endIndex);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return x.http().post(params, callback);
    }

    public Callback.Cancelable httpGet(String uri, String type, Object o, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(uri);
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setType(type);
        requestInfo.putArg(o.getClass().getSimpleName(), o);
        params.addParameter(RequestInfo.TAG, requestInfo);
        return x.http().get(params, callback);
    }
}
