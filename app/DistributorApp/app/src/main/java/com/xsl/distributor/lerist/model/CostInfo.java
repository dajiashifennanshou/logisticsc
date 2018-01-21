package com.xsl.distributor.lerist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lerist on 2016/7/11, 0011.
 */

public class CostInfo implements Parcelable {

    public CostInfo() {
    }

    protected CostInfo(Parcel in) {
    }

    public static final Creator<CostInfo> CREATOR = new Creator<CostInfo>() {
        @Override
        public CostInfo createFromParcel(Parcel in) {
            return new CostInfo(in);
        }

        @Override
        public CostInfo[] newArray(int size) {
            return new CostInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
