package com.xsl.distributor.ws.bean;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class BankOfBranch {
    private String branchName;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public String toString() {
        return branchName;
    }
}
