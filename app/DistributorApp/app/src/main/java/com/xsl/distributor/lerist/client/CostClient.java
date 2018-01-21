package com.xsl.distributor.lerist.client;

import com.socks.library.KLog;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.utils.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class CostClient extends LClient {


    /**
     * 安装费用流水 根据时间查找
     * @param dealerId 经销商id
     * @param page 哪一页
     * @param rows 行数
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @param callback
     * @return
     */
    public Callback.Cancelable getInstallList(long dealerId, int page, int rows, long startTime,long endTime,Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.RUNNING_MONEY);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("dealerId", DES.encrypt(dealerId+""));
        params.addParameter("startTime", startTime);
        params.addParameter("endTime",endTime);
        params.addParameter("page",page);
        params.addParameter("rows", rows);
        KLog.i(params.toString());
        return x.http().post(params, callback);
    }


    /**
     * 专线费用流水
     * @param dealerId 经销商id
     * @param page 哪一页
     * @param rows 每页显示行数
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @param callback
     * @return
     */
    public Callback.Cancelable getSpecialLineList(long dealerId, int page, int rows,long startTime,long endTime, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.DEDICATED_MONEY);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("dealerId", DES.encrypt(dealerId+""));
        params.addParameter("page", page);
        params.addParameter("rows", rows);
        params.addParameter("startTime", startTime);
        params.addParameter("endTime",endTime);
        KLog.i(params.toString());
        return x.http().post(params, callback);
    }
}
