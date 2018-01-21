package com.xsl.lerist.llibrarys.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.socks.library.KLog;

import java.net.UnknownHostException;

/**
 * Created by Lerist on 2015/5/20, 0020.
 */
public class NetworkUtil {

    /**
     * 网络是否连接
     * android.permission.ACCESS_NETWORK_STATE
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        if (context == null) {
            KLog.e("context == null");
            return false;
        }
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        context = null;
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断连接类型
     *
     * @param context
     * @param type
     * @return
     */
    public static boolean isConnectionType(Context context, int type) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo active = cm.getActiveNetworkInfo();
        return (active != null && active.getType() == type);
    }

    /**
     * 是否WIFI连接
     *
     * @param context
     * @return
     */
    public static boolean isWIFI(Context context) {
        return isConnectionType(context, ConnectivityManager.TYPE_WIFI);
    }

    /**
     * 是否2G网络
     *
     * @param context
     * @return
     */
    public static boolean is2GNetWork(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo net = cm.getActiveNetworkInfo();
            if (net.getState() == NetworkInfo.State.CONNECTED) {
                int type = net.getType();
                int subtype = net.getSubtype();

                return !isConnectionFast(type, subtype);
            }
        }
        return false;
    }

    public static boolean isConnectionFast(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return true; // ~ 400-7000 kbps
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 获取本机WiFi状态下ip地址
     *
     * @param context
     * @return
     * @throws UnknownHostException
     */
    private String getLocalIpAddress(Context context) throws UnknownHostException {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String IP;
        int ipAddress = wifiInfo.getIpAddress();
        IP = intToIp(ipAddress);
        return IP;
    }

    // ip地址转化成字符串
    private String intToIp(int i) {
        return (i & 0xFF) + " . " + ((i >> 8) & 0xFF) + " . " + ((i >> 16) & 0xFF) + " . " + ((i >> 24) & 0xFF);
    }
}
