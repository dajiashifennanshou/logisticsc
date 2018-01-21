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
public class LogisticsClient extends LClient {
    /**
     * 获取常用物流商
     * @param userId
     * @param page
     * @param rows
     * @param callback
     * @return
     */
    public Callback.Cancelable getList(long userId, int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.GET_COMMON_COMPANY);
        params.addParameter("sign", Authorization.SIGN);
//        params.addParameter("token", LocalData.getToken());
        params.addParameter("userId", DES.encrypt(userId + ""));
        params.addParameter("page", page);
        params.addParameter("rows", rows);
        return x.http().post(params, callback);
    }

    /**
     * 删除收藏物流商(从个人中心删除的收藏物流商)
     * @param id 物流商id
     * @param callback
     * @return
     */
    public Callback.Cancelable deleteLogistic(int id, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.DELETE_COMPANY_COLLECT);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("id", DES.encrypt(id + ""));
        return x.http().post(params, callback);
    }

    /**
     * 批量删除收藏物流商
     * @param ids 便利所有的物流商id，并用“,”隔开
     * @param callback
     * @return
     */
    public Callback.Cancelable deleteAllLogistic(String ids, Callback.CommonCallback<String> callback) {
        KLog.i(ids);
        RequestParams params = new RequestParams(Common.MULTI_DEL_COMPANY_COLLECT);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("ids", DES.encrypt(ids));
        return x.http().post(params, callback);
    }

    /**
     * 从路线查询里面删除收藏物流商
     * @param userId
     * @param comId
     * @param callback
     * @return
     */
    public Callback.Cancelable deleteLogistic(int userId,int comId, Callback.CommonCallback<String> callback) {
        KLog.i("\n" + "userId = " + userId + "\n" + "comId = " + comId);
        RequestParams params = new RequestParams(Common.DELETE_COMPANY_COLLECT);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("userId", DES.encrypt(userId + ""));
        params.addParameter("comId", DES.encrypt(comId + ""));
        return x.http().post(params, callback);
    }

    /**
     *添加收藏物流商
     * @param userId
     * @param comId
     * @param callback
     * @return
     */
    public Callback.Cancelable addCollectCompany(long userId, int comId, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.ADD_COMPANY_COLLECT);
        KLog.i("\n" + "userId = " + userId + "\n" + "comId" + comId);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("userId", DES.encrypt(userId + ""));
        params.addParameter("comId", DES.encrypt(comId + ""));
        return x.http().post(params, callback);
    }

    /**
     * 获取物流商详情
     * @param userId
     * @param id
     * @param callback
     * @return
     */
    public Callback.Cancelable getDetails(long userId, long id, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.GET_COMPANY_DETAIL);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("userId", DES.encrypt(userId + ""));
        params.addParameter("id", DES.encrypt(id + ""));
        return x.http().post(params, callback);
    }
}
