package com.xsl.distributor.lerist.model;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class ResultCode {
    public static final int SUCCEED = 10000;
    public static final int FAILURE = 9999;
    public static final int NOPERMISSION = -1;

    public static String getHint(int code) {
        String hint = "";
        switch (code) {
            case SUCCEED:
                hint = "成功";
                break;
        }
        return hint;
    }
}
