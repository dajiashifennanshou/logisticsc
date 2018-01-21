package com.xsl.distributor.lerist.client;

import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.utils.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;


/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class BankCardClient extends LClient {

    public static final String SIGN = "sign";


    public Callback.Cancelable bind(
            long user_id
            , String bindmobile
            , String customertype
            , String linkman
            , String idcard
            , String provincename
            , String cityname
            , String bankheadname
            , String bankname
            , String accountname
            , String bankaccounttype
            , String bankprovince
            , String bankcity
            , String bankaccountnumber
            , String id_card_front
            , String id_ccard_back
            , String bank_card_front
            , String persou_photo
            , Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.BANK_BINDING);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("user_id", DES.encrypt(user_id + ""));
        params.addParameter("bindmobile", DES.encrypt(bindmobile));
        params.addParameter("customertype", DES.encrypt(customertype));
        params.addParameter("linkman", DES.encrypt(linkman));
        params.addParameter("idcard", DES.encrypt(idcard));
        params.addParameter("provincename", DES.encrypt(provincename));
        params.addParameter("cityname", DES.encrypt(cityname));
        params.addParameter("bankheadname", DES.encrypt(bankheadname));
        params.addParameter("bankname", DES.encrypt(bankname));
        params.addParameter("accountname", DES.encrypt(accountname));
        params.addParameter("bankaccounttype", DES.encrypt(bankaccounttype));
        params.addParameter("bankprovince", DES.encrypt(bankprovince));
        params.addParameter("bankcity", DES.encrypt(bankcity));
        params.addParameter("bankaccountnumber", DES.encrypt(bankaccountnumber));
        params.addParameter("id_card_front", DES.encrypt(id_card_front));
        params.addParameter("id_ccard_back", DES.encrypt(id_ccard_back));
        params.addParameter("bank_card_front", DES.encrypt(bank_card_front));
        params.addParameter("persou_photo", DES.encrypt(persou_photo));

        return x.http().post(params, callback);
    }


    public Callback.Cancelable uploadImg(
            long userId
            , String filePath
            , Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.BANK_UPLOADIMG);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("userId", DES.encrypt(userId+""));
        try {
            params.addParameter("file", new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return x.http().post(params, callback);
    }
}
