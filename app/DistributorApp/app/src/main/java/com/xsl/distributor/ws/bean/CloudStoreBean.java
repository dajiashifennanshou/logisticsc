package com.xsl.distributor.ws.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class CloudStoreBean implements Serializable{

    /**
     * branchNo : YC-00007
     * branchName : 成都网点
     */

    private String branchNo;
    private String branchName;

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
