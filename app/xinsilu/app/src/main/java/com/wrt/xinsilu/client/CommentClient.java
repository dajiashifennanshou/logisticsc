package com.wrt.xinsilu.client;

import com.socks.library.KLog;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/9 0009.
 * 评价
 */
public class CommentClient extends LClient {
    /**
     * 评论
     *
     * @param url
     * @param order_number     订单号
     * @param evaluate_content 评价内容
     * @param evaluate_level   评价星级
     * @param user_id          用户id
     * @param callback
     * @return
     */
    public Callback.Cancelable comment(String url,
                                       String order_number,
                                       String evaluate_content,
                                       int evaluate_level,
                                       int user_id,
                                       Callback.CommonCallback<String> callback) {
        KLog.i("\n" + "order_number = " + order_number +
                "\n" + "evaluate_content = " + evaluate_content +
                "\n" + "evaluate_level = " + evaluate_level +
                "\n" + "user_id = " + user_id);
        RequestParams params = new RequestParams(url);
        params.addParameter("orderNumber", DES.encrypt(order_number));
        params.addParameter("evaluate_content", DES.encrypt(evaluate_content));
        params.addParameter("evaluate_level", evaluate_level);
        params.addParameter("userId", user_id);
//        params.addParameter("state","");
//        params.addParameter("status","");
        params.addParameter("sign", Authorization.SIGN);
        KLog.i("1", params.toString());
        return x.http().post(params, callback);
    }

    /**
     * 查看评价 需要传递 路线id和用户id
     *
     * @param url      地址
     * @param linId    路线id
     * @param userId   用户id
     * @param callback
     * @return
     */
    public Callback.Cancelable getComment(int linId,
                                          int userId,
                                          int page, int rows,
                                          Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.COMMENT_DETAIL);
        params.addParameter("linId", DES.encrypt(linId + ""));
        params.addParameter("userId", DES.encrypt(userId + ""));
        params.addParameter("page", page);
        params.addParameter("rows", rows);
        params.addParameter("sign", Authorization.SIGN);
        return x.http().post(params, callback);
    }
}
