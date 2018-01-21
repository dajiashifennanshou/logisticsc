package com.xsl.distributor.ws.basic;

import android.content.Context;

/**
 * Created by Administrator on 2016/7/12 0012.
 * 网络请求参数传递基本类，使用时只需继承该类，
 */
public abstract class BasicParams {
    /**网络请求类
     * 继承者只需调用
     * client.regist()方法即可
     */
    public JoinClient client;
    public Context context;
    /**传递的地址*/
    public String url;
    /**传递的参数bean类*/
    public Object obj;
    public BasicParams(Context context,String url,Object obj){
        this.context = context;
        client = new JoinClient(context);
        this.url = url;
        this.obj = obj;
    }
    public BasicParams(){}
}
