package com.xsl.distributor.lerist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lerist on 2016/7/14, 0014.
 */

public class SpecialLineCostInfo implements Parcelable {

    /**
     * order_number : 5695949160708001
     * paid_price : 100.0
     * final_price : 200.0
     * order_time : 1467945049000
     * is_payment : 1 (0支付,1未支付)
     */

    private String order_number;//专线订单号
    private String way_bill_number;//运单号
    private float total;//总费用
    private int status;//状态--暂不清楚具体意义
    private long way_bill_order_time;//时间

    private double paid_price;
    private double final_price;
    private String order_time;
    private int is_payment = -1;

    public SpecialLineCostInfo() {
    }

    protected SpecialLineCostInfo(Parcel in) {
        order_number = in.readString();
        way_bill_number = in.readString();
        total = in.readFloat();
        status = in.readInt();
        way_bill_order_time = in.readLong();
        paid_price = in.readDouble();
        final_price = in.readDouble();
        order_time = in.readString();
        is_payment = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(order_number);
        dest.writeString(way_bill_number);
        dest.writeFloat(total);
        dest.writeInt(status);
        dest.writeLong(way_bill_order_time);
        dest.writeDouble(paid_price);
        dest.writeDouble(final_price);
        dest.writeString(order_time);
        dest.writeInt(is_payment);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SpecialLineCostInfo> CREATOR = new Creator<SpecialLineCostInfo>() {
        @Override
        public SpecialLineCostInfo createFromParcel(Parcel in) {
            return new SpecialLineCostInfo(in);
        }

        @Override
        public SpecialLineCostInfo[] newArray(int size) {
            return new SpecialLineCostInfo[size];
        }
    };

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getWay_bill_number() {
        return way_bill_number;
    }

    public void setWay_bill_number(String way_bill_number) {
        this.way_bill_number = way_bill_number;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getWay_bill_order_time() {
        return way_bill_order_time;
    }

    public void setWay_bill_order_time(long way_bill_order_time) {
        this.way_bill_order_time = way_bill_order_time;
    }

    public double getPaid_price() {
        return paid_price;
    }

    public void setPaid_price(double paid_price) {
        this.paid_price = paid_price;
    }

    public double getFinal_price() {
        return final_price;
    }

    public void setFinal_price(double final_price) {
        this.final_price = final_price;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public int getIs_payment() {
        return is_payment;
    }

    public void setIs_payment(int is_payment) {
        this.is_payment = is_payment;
    }
}
