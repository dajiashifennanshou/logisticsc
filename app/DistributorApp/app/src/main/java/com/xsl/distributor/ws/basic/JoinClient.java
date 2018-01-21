package com.xsl.distributor.ws.basic;

import android.content.Context;

import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.ws.onResponse.OnResponse;
import com.xsl.lerist.llibrarys.client.LClient;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/12 0012.
 * 请求网络类，使用的时候只需new一个对象，然后调用regest方法，
 */
public class JoinClient extends LClient{
    private Context context;
    public JoinClient(Context context){
        this.context = context;
    }

    /**
     * 参数
     * @param response 接口方法
     * @param params 传递的参数对象
     * @param clazz 解析的类 具体详见params里的请求方式
     * @param <T>   哪个class
     */
    public <T>void regist(final OnResponse response, RequestParams params, final Class<T>clazz) {

                        x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) {
                            return;
                        }
                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LToast.show(context, "请求成功");
                                //注册成功后立即登录
                                response.onReponse(resultInfo,clazz);
                                break;
                            default:
                                LToast.show(context, "请求失败, 请稍后再试");
                                break;
                        }
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        LToast.show(context,"错误");
                        ex.printStackTrace();
                    }
                    @Override
                    public void onCancelled(CancelledException cex) {

                    }
                    @Override
                    public void onFinished() {
                        response.onFinish();
            }
        });
    }
}
