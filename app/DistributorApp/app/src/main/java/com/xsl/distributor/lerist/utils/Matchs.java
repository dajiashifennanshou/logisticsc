package com.xsl.distributor.lerist.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/7/19 0019.
 * 正则表达式
 */
public class Matchs {
    /**
     * 判断身份证号的正则表达式
     *
     * @return
     */
    public static boolean getIDCard(String idCard) {
        if (idCard == null || idCard.length() != 18) {
            return false;
        }
        Pattern p = Pattern
                .compile("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)");
        Matcher m = p.matcher(idCard);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    /**
     * 判断电话号码的正则表达式
     *
     * @param phoneNumber
     * @return
     */
    public static boolean getPhoneNumber(String phoneNumber) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$");

        Matcher m = p.matcher(phoneNumber);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
}
