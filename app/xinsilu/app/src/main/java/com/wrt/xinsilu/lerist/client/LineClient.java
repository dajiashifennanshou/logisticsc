package com.wrt.xinsilu.lerist.client;

import com.socks.library.KLog;
import com.wrt.xinsilu.client.Authorization;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Lerist on 2016/8/4, 0004.
 */

/**
 * 物流商
 */
public class LineClient extends LClient {
    /**
     * 获取收藏线路
     *
     * @param userId
     * @param page
     * @param rows
     * @param callback
     * @return
     */
    public Callback.Cancelable getCollectList(long userId, int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.MY_COLLECT_INFO);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("user_id", DES.encrypt(userId + ""));
        params.addParameter("page", page);
        params.addParameter("rows", rows);
        return x.http().post(params, callback);
    }

    /**
     * 收藏线路
     *
     * @param userId   用户id
     * @param line_id  路线id
     * @param callback
     * @return
     */
    public Callback.Cancelable addCollectLine(long userId, int line_id, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.ADD_COLLECT);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("user_id", DES.encrypt(userId + ""));
        params.addParameter("line_id", DES.encrypt(line_id + ""));
        return x.http().post(params, callback);
    }

    /**
     * 删除收藏路线
     *
     * @param callback
     * @return
     */
    public Callback.Cancelable deleteCollectLine(int id, Callback.CommonCallback<String> callback) {
        KLog.i("\n" + "Id = " + id);
        RequestParams params = new RequestParams(Common.DELETE_COLLECT);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("id", DES.encrypt(id + ""));
        return x.http().post(params, callback);
    }

    /**
     * 我的物流商收藏详情路线取消
     *
     * @param userId
     * @param line_id
     * @param callback
     * @return
     */
    public Callback.Cancelable deleteCollectLine(long userId, int line_id, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.DELETE_COLLECT);
        KLog.i("\n" + "userId = " + userId + "\n" + "line_id = " + line_id);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("user_id", DES.encrypt(userId + ""));
        params.addParameter("line_id", DES.encrypt(line_id + ""));
        return x.http().post(params, callback);
    }

    /**
     * 获取网点地址
     *
     * @param startId
     * @param endId
     * @param callback
     * @return
     */
    public Callback.Cancelable getOutlesAddress(String startId, String endId, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.GET_OUTLES_ADDRESS);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("startId", DES.encrypt(startId + ""));
        params.addParameter("endId", DES.encrypt(endId + ""));
        return x.http().post(params, callback);
    }
}
