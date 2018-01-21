package com.wrt.xinsilu.Service;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.xsl.lerist.llibrarys.utils.BusProvider;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/8 0008.
 * 地图服务
 */
public class LocationService extends Service {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private double lontitude;
    private double latitude;
    private String nearProvince;
    private String nearCity;
    private String nearDistrict;

    public static void start(final Activity activity) {
        new TedPermission(activity).setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .setGotoSettingButton(true)
                .setDeniedMessage("未获取到定位权限, 请前往权限设置授权")
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        activity.startService(new Intent(activity, LocationService.class));
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> arrayList) {

                    }
                })
                .check();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        initLocation();
        return super.onStartCommand(intent, flags, startId);
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 2000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (mLocationClient.isStarted()) {
                        mLocationClient.requestLocation();
                        break;
                    }

                }
            }
        }).start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            lontitude = location.getLongitude();
            latitude = location.getLatitude();
            nearProvince = location.getProvince();
            nearCity = location.getCity();
            nearDistrict = location.getDistrict();
            if (nearCity == null) return;

            BusProvider.getInstance().post(location);
            mLocationClient.stop();
            stopSelf();
        }
    }

}
