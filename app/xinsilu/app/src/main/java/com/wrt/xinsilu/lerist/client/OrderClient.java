package com.wrt.xinsilu.lerist.client;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.client.Authorization;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class OrderClient extends LClient {
    public Callback.Cancelable getList(long user_Id, String state, int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.GET_USER_ORDER);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("user_id", DES.encrypt(user_Id + ""));
        params.addParameter("state", state);
        params.addParameter("page", page);
        params.addParameter("rows", rows);
        KLog.i(params.toString());
        return x.http().post(params, callback);
    }

    public Callback.Cancelable getDetail(String order_number, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.GET_USER_ORDER_DETAIL);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("order_number", DES.encrypt(order_number));
        return x.http().post(params, callback);
    }

    /**
     * 获取包装类型
     *
     * @param callback
     * @return
     */
    public Callback.Cancelable getPackType(Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.GET_PACKTYPE);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("sign", Authorization.SIGN);
        return x.http().post(params, callback);
    }

    /**
     * 添加订单
     *
     * @param user_id
     * @param paramsHashMap
     * @param callback
     * @return
     */
    public Callback.Cancelable add(long user_id,
                                   HashMap<String, Object> paramsHashMap,
                                   Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.ADD_ORDER);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("user_id", DES.encrypt(user_id + ""));
        for (String key : paramsHashMap.keySet()) {
            Object value = paramsHashMap.get(key);
            if (paramsHashMap.get(key) instanceof List) {
                value = JSON.toJSONString(paramsHashMap.get(key));
            }
            params.addParameter(key, DES.encrypt(value + ""));
        }
        KLog.i(params.toJSONString());
        return x.http().post(params, callback);
    }

    /**
     * 取消订单
     *
     * @param order_number
     * @param callback
     * @return
     */
    public Callback.Cancelable cancel(String order_number,
                                      Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.CANCEL_ORDER);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("order_number", DES.encrypt(order_number + ""));
        params.addParameter("state", DES.encrypt(11 + "")); //11为取消订单

        return x.http().post(params, callback);
    }

    /**
     * 订单支付
     *
     * @param order_number
     * @param callback
     * @return
     */
    public Callback.Cancelable orderPay(
            long user_id,
            int payType,
            double cost,
            String order_number,
            Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.ORDER_PAY);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("order_number", DES.encrypt(order_number + ""));
        params.addParameter("payType", DES.encrypt(payType + ""));
        //payType 0保险  1预约  2运费  3保证金
        if (payType == 1) {
            params.addParameter("take_cargo_cost", DES.encrypt(cost + ""));
        } else {
            params.addParameter("total_price", DES.encrypt(cost + ""));
        }
        params.addParameter("user_id", DES.encrypt(user_id + ""));

        return x.http().post(params, callback);
    }

    /**
     * 获取增值服务
     *
     * @param TmsLineId
     * @param callback
     * @return
     */
    public Callback.Cancelable getAddServer(
            long TmsLineId,
            Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.GET_ADDSERVICE);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("TmsLineId", DES.encrypt(TmsLineId + ""));
        return x.http().post(params, callback);
    }
}
