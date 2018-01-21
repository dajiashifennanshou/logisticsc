
package com.wrt.xinsilu.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.view.View;

import com.wrt.xinsilu.dialog.DialogDefault;

import java.lang.reflect.Method;



/** <p>描述(Describe)</p>
 * 网络工具类,此工具类有小bug,主要是延时问题。
 * @author wangsong
 * @version Aug 14, 2014 4:36:57 PM 
 */
public class NetUtil {
	/**网络可以使用*/
	public final static int CONNECTABLE = 230;
	/**网络网络没有连接好*/
	public final static int UNKONWCONNECT = 220;
	/**网络不可用状态*/
	public final static int DISCONNECT = 210;
	/**设备未打开*/
	public final static int NOTOPEN = 200;
	/**没有设备*/
	public final static int NODEVICE = 190;

	/**Wifi已打开*/
	public final static int ENABLED = 100;
	/**Wifi正在*/
	public final static int ENABLING = 90;
	/**Wifi正在关闭*/
	public final static int DISABLING = 80;
	/**Wifi已关闭*/
	public final static int DISABLED = 70;
	/**Wifi状态未知*/
	public final static int UNKNOWN = 60;

	private NetUtil() {
	};

	/**
	 * 获取ConnectivityManager对象
	 * @param context Context
	 * @return ConnectivityManager
	 */
	public static ConnectivityManager getConnectivityManager(Context context) {
		return (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	/**
	 * 获取网络状态
	 * 权限：android.Manifest.permission.ACCESS_NETWORK_STATE.
	 * @param context Context
	 * @return NOTOPEN(没有打开网络，或者没有网络模块),CONNECTABLE,UNKONWCONNECT
	 */
	public static int getNetState(Context context) {
		NetworkInfo networkInfo = getConnectivityManager(context)
				.getActiveNetworkInfo();
		if (null == networkInfo) {
			//网络没有打开、wifi没有连接、飞行模式、没有设备驱动
			int moblie = getMobileConnectionState(context);
			if (moblie == DISCONNECT) {
				return moblie;
			} else {
				int wifi = getWiFiConnectionState(context);
				if (wifi != NOTOPEN && wifi != NODEVICE) {
					return wifi;
				}
			}
			return NOTOPEN;
		} else {
			if (networkInfo.isAvailable()
					&& networkInfo.isConnected()) {
				return CONNECTABLE;
			} else {
				return UNKONWCONNECT;

			}
		}
	}

	/**
	 * 获取WiFi状态
	 * @param context Context
	 * @return ENABLED,ENABLING,DISABLING,DISABLED,UNKNOWN
	 */
	public static int getWiFiState(Context context) {
		WifiManager wm = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		switch (wm.getWifiState()) {
			case WifiManager.WIFI_STATE_ENABLED:
				return ENABLED;
			case WifiManager.WIFI_STATE_ENABLING:
				return ENABLING;
			case WifiManager.WIFI_STATE_DISABLING:
				return DISABLING;
			case WifiManager.WIFI_STATE_DISABLED:
				return DISABLED;
			case WifiManager.WIFI_STATE_UNKNOWN:
				return UNKNOWN;
		}
		return UNKNOWN;
	}

	/**
	 * 获取WiFi连接状态
	 * @param context Context
	 * @return CONNECTABLE,UNKONWCONNECT,NOTOPEN,NODEVICE
	 */
	public static int getWiFiConnectionState(Context context) {
		ConnectivityManager connectivityManager = getConnectivityManager(context);
		NetworkInfo wifiInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (null != wifiInfo) {
			if (wifiInfo.isAvailable()) {//检测wifi是否开启
				if (wifiInfo.isConnected()) {
					return CONNECTABLE;
				} else {
					//没有选择网络和没有登录成功数据一样
					return UNKONWCONNECT;
				}

			} else {
				return NOTOPEN;
			}
		} else {
			return NODEVICE;
		}
	}

	/**
	 * 获取移动网络状态()
	 * @param context Context
	 * @return DISCONNECT(飞行模式测试),UNKONWCONNECT(可能是没有打开，亦有可能是wifi占用，其他)，CONNECTABLE,NODEVICE(没有设备模块)
	 */
	public static int getMobileConnectionState(Context context) {
		ConnectivityManager connectivityManager = getConnectivityManager(context);
		NetworkInfo mobileInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		//		connectivityManager.get
		if (null != mobileInfo) {
			if (!mobileInfo.isConnected()) {
				if ("UNKNOWN".equals(mobileInfo.getSubtypeName())) {
					//飞行模式下测试，
					return DISCONNECT;
				} else {
					//这儿不能确定是没有连接好，还是wifi占用，还是无信号
					return UNKONWCONNECT;
				}
			} else {
				return CONNECTABLE;
			}
		} else {
			return NODEVICE;
		}
	}

	/**
	 * 打开Wifi
	 * 权限：android.permission.CHANGE_WIFI_STATE,android.permission.ACCESS_NETWORK_STATE
	 * @param context Context
	 */
	public static void openWifi(Context context) {
		setWifiState(context, true);
	}

	/**
	 * 关闭Wifi
	 * 权限：android.permission.CHANGE_WIFI_STATE,android.permission.ACCESS_NETWORK_STATE
	 * @param context Context
	 */
	public static void closeWifi(Context context) {
		setWifiState(context, false);
	}

	/**
	 * 设置Wifi状态
	 * 权限：android.permission.CHANGE_WIFI_STATE,android.permission.ACCESS_NETWORK_STATE
	 * @param context
	 * @param state
	 */
	public static void setWifiState(Context context, boolean state) {
		WifiManager wm = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		wm.setWifiEnabled(state);
	}

	/**
	 * 打开移动网络
	 * 权限：android.permission.CHANGE_NETWORK_STATE,android.permission.ACCESS_NETWORK_STATE
	 * @param context Context
	 */
	public static void openMobile(Context context) {
		setMobileState(context, true);

	}

	/**
	 * 关闭移动网络
	 * 权限：android.permission.CHANGE_NETWORK_STATE,android.permission.ACCESS_NETWORK_STATE
	 * @param context Context
	 */
	public static void closeMobile(Context context) {
		setMobileState(context, false);
	}

	/**
	 * 设置手机移动网络状态
	 * 权限：android.permission.CHANGE_NETWORK_STATE,android.permission.ACCESS_NETWORK_STATE
	 * @param context Context
	 * @param state
	 */
	public static void setMobileState(Context context, boolean state) {
		try {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			Class ownerClass = mConnectivityManager.getClass();

			Class[] argsClass = new Class[1];
			argsClass[0] = boolean.class;

			Method method = ownerClass.getMethod("setMobileDataEnabled",
					argsClass);

			method.invoke(mConnectivityManager, state);
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
			System.out.println("移动数据设置错误: " + e.toString());
		}
	}

	//打开网络
	public void setDialog(Context context){
		final DialogDefault setNetDialog=new DialogDefault(context);
		setNetDialog.setMsg("检测到你的网络没有打开，是否打开网络?");
		setNetDialog.setCancleOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setNetDialog.dismiss();
			}
		});
		setNetDialog.setEnterOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


				setNetDialog.dismiss();
			}
		});
		setNetDialog.show();
	}

	/**
	 * 获取蓝牙状态<br>自动打开蓝牙
	 * @param context
	 * @return true:蓝牙打开 false:蓝牙关闭/没有蓝牙
	 */
	public static boolean getBlueTooth(Context context){
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			// 没有蓝牙模块
			return false;
		}
		mBluetoothAdapter.enable();
		return mBluetoothAdapter.isEnabled();
	}

	/**
	 * 获取蓝牙状态<br>不自动打开蓝牙
	 * @param context
	 * @return true:蓝牙打开 false:蓝牙关闭/没有蓝牙
	 */
	public static boolean isBlueTooth(Context context){
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			// 没有蓝牙模块
			return false;
		}
		return mBluetoothAdapter.isEnabled();
	}


	/** 打开蓝牙 */
	public static void openBlueTooth(Context context) {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			// 没有蓝牙模块
			return;
		}
		// 如果本地蓝牙没有开启，则开启
		if (!mBluetoothAdapter.isEnabled()) {
			mBluetoothAdapter.enable();
		}
	}

	/**关闭蓝牙*/
	public static void closeBlueTooth(Context context) {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			// 没有蓝牙模块
			return;
		}
		// 如果本地蓝牙没有开启，则开启
		if (mBluetoothAdapter.isEnabled()) {
			mBluetoothAdapter.disable();
		}
	}
}
