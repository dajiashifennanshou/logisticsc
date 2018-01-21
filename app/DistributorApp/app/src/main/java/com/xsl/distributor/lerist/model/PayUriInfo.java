package com.xsl.distributor.lerist.model;

/**
 * Created by Lerist on 2016/7/22, 0022.
 */

public class PayUriInfo {
    private String data;
    private String encryptkey;
    private String merchantaccount;
    private String url;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEncryptkey() {
        return encryptkey;
    }

    public void setEncryptkey(String encryptkey) {
        this.encryptkey = encryptkey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMerchantaccount() {
        return merchantaccount;
    }

    public void setMerchantaccount(String merchantaccount) {
        this.merchantaccount = merchantaccount;
    }

    @Override
    public String toString() {
        return url + "?data=" + data
                + "&encryptkey=" + encryptkey
                + "&merchantaccount=" + merchantaccount;
    }
}
