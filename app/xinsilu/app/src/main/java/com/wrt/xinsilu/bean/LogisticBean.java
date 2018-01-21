package com.wrt.xinsilu.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class LogisticBean {


    /**
     * pageIndex : 1
     * pageSize : 20
     * objectList : [{"id":21,"server_type":"普通汽运","start_outlets":"34","start_outlets_name":"成都网点","end_outlets":"35","end_outlets_name":"毕节网点","transport_mode":"零担运输","time_long":10,"heavy_cargo_price_low":550,"bulky_cargo_price_low":150,"heavy_cargo_price_mid":450,"bulky_cargo_price_mid":110,"heavy_cargo_price_high":400,"bulky_cargo_price_high":100,"lowest_price":10,"status":1,"release_state":1,"create_time":"2016-06-28 08:39:48.0","create_person_id":40,"outlets_id":34,"recommended":1,"isAddServer":0,"conf":{"id":2,"isReceipt":1,"companyId":11,"isImmediatePay":0,"isArrivePay":1,"isAdvancePay":0,"isCommonReceipt":0,"isAddTaxReceipt":0,"isNoReceipt":1,"outletsId":34},"startProvince":"四川省","startCity":"成都市","startCounty":"双流县","endProvince":"贵州省","endCity":"毕节市","endCounty":"市辖区","companyName":"湘汉运输","companyId":11,"qq":"","contactsMobile":"13568985182","countOrder":0,"countOrderEvaluation":0,"distance":11395,"isCollect":1}]
     * limitMin : 0
     * limitMax : 20
     */

    private int pageIndex;
    private int pageSize;
    private int limitMin;
    private int limitMax;
    /**
     * id : 21
     * server_type : 普通汽运
     * start_outlets : 34
     * start_outlets_name : 成都网点
     * end_outlets : 35
     * end_outlets_name : 毕节网点
     * transport_mode : 零担运输
     * time_long : 10.0
     * heavy_cargo_price_low : 550.0
     * bulky_cargo_price_low : 150.0
     * heavy_cargo_price_mid : 450.0
     * bulky_cargo_price_mid : 110.0
     * heavy_cargo_price_high : 400.0
     * bulky_cargo_price_high : 100.0
     * lowest_price : 10.0
     * status : 1
     * release_state : 1
     * create_time : 2016-06-28 08:39:48.0
     * create_person_id : 40
     * outlets_id : 34
     * recommended : 1
     * isAddServer : 0
     * conf : {"id":2,"isReceipt":1,"companyId":11,"isImmediatePay":0,"isArrivePay":1,"isAdvancePay":0,"isCommonReceipt":0,"isAddTaxReceipt":0,"isNoReceipt":1,"outletsId":34}
     * startProvince : 四川省
     * startCity : 成都市
     * startCounty : 双流县
     * endProvince : 贵州省
     * endCity : 毕节市
     * endCounty : 市辖区
     * companyName : 湘汉运输
     * companyId : 11
     * qq :
     * contactsMobile : 13568985182
     * countOrder : 0
     * countOrderEvaluation : 0
     * distance : 11395
     * isCollect : 1
     */

    private List<ObjectListBean> objectList;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getLimitMin() {
        return limitMin;
    }

    public void setLimitMin(int limitMin) {
        this.limitMin = limitMin;
    }

    public int getLimitMax() {
        return limitMax;
    }

    public void setLimitMax(int limitMax) {
        this.limitMax = limitMax;
    }

    public List<ObjectListBean> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<ObjectListBean> objectList) {
        this.objectList = objectList;
    }

    public static class ObjectListBean {
        private int id;
        private String server_type;
        private String start_outlets;
        private String start_outlets_name;
        private String end_outlets;
        private String end_outlets_name;
        private String transport_mode;
        private double time_long;
        private double heavy_cargo_price_low;
        private double bulky_cargo_price_low;
        private double heavy_cargo_price_mid;
        private double bulky_cargo_price_mid;
        private double heavy_cargo_price_high;
        private double bulky_cargo_price_high;
        private double lowest_price;
        private int status;
        private int release_state;
        private String create_time;
        private int create_person_id;
        private int outlets_id;
        private int recommended;
        private int isAddServer;
        /**
         * id : 2
         * isReceipt : 1
         * companyId : 11
         * isImmediatePay : 0
         * isArrivePay : 1
         * isAdvancePay : 0
         * isCommonReceipt : 0
         * isAddTaxReceipt : 0
         * isNoReceipt : 1
         * outletsId : 34
         */

        private ConfBean conf;
        private String startProvince;
        private String startCity;
        private String startCounty;
        private String endProvince;
        private String endCity;
        private String endCounty;
        private String companyName;
        private int companyId;
        private String qq;
        private String contactsMobile;
        private int countOrder;
        private int countOrderEvaluation;
        private int distance;
        private int isCollect;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServer_type() {
            return server_type;
        }

        public void setServer_type(String server_type) {
            this.server_type = server_type;
        }

        public String getStart_outlets() {
            return start_outlets;
        }

        public void setStart_outlets(String start_outlets) {
            this.start_outlets = start_outlets;
        }

        public String getStart_outlets_name() {
            return start_outlets_name;
        }

        public void setStart_outlets_name(String start_outlets_name) {
            this.start_outlets_name = start_outlets_name;
        }

        public String getEnd_outlets() {
            return end_outlets;
        }

        public void setEnd_outlets(String end_outlets) {
            this.end_outlets = end_outlets;
        }

        public String getEnd_outlets_name() {
            return end_outlets_name;
        }

        public void setEnd_outlets_name(String end_outlets_name) {
            this.end_outlets_name = end_outlets_name;
        }

        public String getTransport_mode() {
            return transport_mode;
        }

        public void setTransport_mode(String transport_mode) {
            this.transport_mode = transport_mode;
        }

        public double getTime_long() {
            return time_long;
        }

        public void setTime_long(double time_long) {
            this.time_long = time_long;
        }

        public double getHeavy_cargo_price_low() {
            return heavy_cargo_price_low;
        }

        public void setHeavy_cargo_price_low(double heavy_cargo_price_low) {
            this.heavy_cargo_price_low = heavy_cargo_price_low;
        }

        public double getBulky_cargo_price_low() {
            return bulky_cargo_price_low;
        }

        public void setBulky_cargo_price_low(double bulky_cargo_price_low) {
            this.bulky_cargo_price_low = bulky_cargo_price_low;
        }

        public double getHeavy_cargo_price_mid() {
            return heavy_cargo_price_mid;
        }

        public void setHeavy_cargo_price_mid(double heavy_cargo_price_mid) {
            this.heavy_cargo_price_mid = heavy_cargo_price_mid;
        }

        public double getBulky_cargo_price_mid() {
            return bulky_cargo_price_mid;
        }

        public void setBulky_cargo_price_mid(double bulky_cargo_price_mid) {
            this.bulky_cargo_price_mid = bulky_cargo_price_mid;
        }

        public double getHeavy_cargo_price_high() {
            return heavy_cargo_price_high;
        }

        public void setHeavy_cargo_price_high(double heavy_cargo_price_high) {
            this.heavy_cargo_price_high = heavy_cargo_price_high;
        }

        public double getBulky_cargo_price_high() {
            return bulky_cargo_price_high;
        }

        public void setBulky_cargo_price_high(double bulky_cargo_price_high) {
            this.bulky_cargo_price_high = bulky_cargo_price_high;
        }

        public double getLowest_price() {
            return lowest_price;
        }

        public void setLowest_price(double lowest_price) {
            this.lowest_price = lowest_price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRelease_state() {
            return release_state;
        }

        public void setRelease_state(int release_state) {
            this.release_state = release_state;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getCreate_person_id() {
            return create_person_id;
        }

        public void setCreate_person_id(int create_person_id) {
            this.create_person_id = create_person_id;
        }

        public int getOutlets_id() {
            return outlets_id;
        }

        public void setOutlets_id(int outlets_id) {
            this.outlets_id = outlets_id;
        }

        public int getRecommended() {
            return recommended;
        }

        public void setRecommended(int recommended) {
            this.recommended = recommended;
        }

        public int getIsAddServer() {
            return isAddServer;
        }

        public void setIsAddServer(int isAddServer) {
            this.isAddServer = isAddServer;
        }

        public ConfBean getConf() {
            return conf;
        }

        public void setConf(ConfBean conf) {
            this.conf = conf;
        }

        public String getStartProvince() {
            return startProvince;
        }

        public void setStartProvince(String startProvince) {
            this.startProvince = startProvince;
        }

        public String getStartCity() {
            return startCity;
        }

        public void setStartCity(String startCity) {
            this.startCity = startCity;
        }

        public String getStartCounty() {
            return startCounty;
        }

        public void setStartCounty(String startCounty) {
            this.startCounty = startCounty;
        }

        public String getEndProvince() {
            return endProvince;
        }

        public void setEndProvince(String endProvince) {
            this.endProvince = endProvince;
        }

        public String getEndCity() {
            return endCity;
        }

        public void setEndCity(String endCity) {
            this.endCity = endCity;
        }

        public String getEndCounty() {
            return endCounty;
        }

        public void setEndCounty(String endCounty) {
            this.endCounty = endCounty;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getContactsMobile() {
            return contactsMobile;
        }

        public void setContactsMobile(String contactsMobile) {
            this.contactsMobile = contactsMobile;
        }

        public int getCountOrder() {
            return countOrder;
        }

        public void setCountOrder(int countOrder) {
            this.countOrder = countOrder;
        }

        public int getCountOrderEvaluation() {
            return countOrderEvaluation;
        }

        public void setCountOrderEvaluation(int countOrderEvaluation) {
            this.countOrderEvaluation = countOrderEvaluation;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getIsCollect() {
            return isCollect;
        }

        public void setIsCollect(int isCollect) {
            this.isCollect = isCollect;
        }

        public static class ConfBean {
            private int id;
            private int isReceipt;
            private int companyId;
            private int isImmediatePay;
            private int isArrivePay;
            private int isAdvancePay;
            private int isCommonReceipt;
            private int isAddTaxReceipt;
            private int isNoReceipt;
            private int outletsId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsReceipt() {
                return isReceipt;
            }

            public void setIsReceipt(int isReceipt) {
                this.isReceipt = isReceipt;
            }

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }

            public int getIsImmediatePay() {
                return isImmediatePay;
            }

            public void setIsImmediatePay(int isImmediatePay) {
                this.isImmediatePay = isImmediatePay;
            }

            public int getIsArrivePay() {
                return isArrivePay;
            }

            public void setIsArrivePay(int isArrivePay) {
                this.isArrivePay = isArrivePay;
            }

            public int getIsAdvancePay() {
                return isAdvancePay;
            }

            public void setIsAdvancePay(int isAdvancePay) {
                this.isAdvancePay = isAdvancePay;
            }

            public int getIsCommonReceipt() {
                return isCommonReceipt;
            }

            public void setIsCommonReceipt(int isCommonReceipt) {
                this.isCommonReceipt = isCommonReceipt;
            }

            public int getIsAddTaxReceipt() {
                return isAddTaxReceipt;
            }

            public void setIsAddTaxReceipt(int isAddTaxReceipt) {
                this.isAddTaxReceipt = isAddTaxReceipt;
            }

            public int getIsNoReceipt() {
                return isNoReceipt;
            }

            public void setIsNoReceipt(int isNoReceipt) {
                this.isNoReceipt = isNoReceipt;
            }

            public int getOutletsId() {
                return outletsId;
            }

            public void setOutletsId(int outletsId) {
                this.outletsId = outletsId;
            }
        }
    }
}
