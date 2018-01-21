package com.wrt.xinsilu.lerist.model;

import java.util.List;

/**
 * Created by Lerist on 2016/8/4, 0004.
 */

public class LogisticsCompanyInfo {


    /**
     * pageSize : 20
     * limitMax : 20
     * limitMin : 0
     * objectList : [{"id":1,"logo":"upload/company/login/3/14/ec693d61-4422-429f-ad4a-41f1407d6fbc.jpg","distance":10162,"list":[{"transport_mode":"5","outlets_id":1,"start_outlets_name":"成都","release_state":0,"heavy_cargo_price_mid":0.01,"recommended":1,"city":"成都市","id":1,"province":"四川省","time_long":8,"start_outlets":1,"status":1,"bulky_cargo_price_high":0.01,"heavy_cargo_price_high":0.01,"end_outlets_name":"重庆","bulky_cargo_price_low":0.01,"server_type":"90","bulky_cargo_price_mid":0.01,"end_outlets":2,"address":"西部鞋都","county":"武侯区","heavy_cargo_price_low":0.01,"evaluate_level":5,"create_person_id":1,"create_time":"2016-06-06 00:03:41.0","lowest_price":0.01},{"start_outlets":1,"status":1,"bulky_cargo_price_high":0.01,"heavy_cargo_price_high":0.01,"transport_mode":"5","end_outlets_name":"北京","outlets_id":1,"start_outlets_name":"成都","release_state":1,"heavy_cargo_price_mid":0.01,"recommended":1,"bulky_cargo_price_low":0.01,"server_type":"90","city":"成都市","bulky_cargo_price_mid":0.01,"end_outlets":3,"id":4,"address":"西部鞋都","county":"武侯区","heavy_cargo_price_low":0.01,"province":"四川省","create_person_id":1,"create_time":"2016-06-07 13:49:32.0","lowest_price":0.01,"time_long":72}],"company_name":"中工储运物流"},{"id":3,"logo":"upload/company/login/6/3/ae319476-0579-44ed-805a-930ef4d3f458.jpg","distance":13126,"list":[{"transport_mode":"5","outlets_id":7,"start_outlets_name":"远征物流","release_state":1,"heavy_cargo_price_mid":0.02,"recommended":1,"city":"成都市","id":3,"province":"四川省","time_long":72,"start_outlets":7,"status":1,"bulky_cargo_price_high":0.04,"heavy_cargo_price_high":0.03,"end_outlets_name":"金鑫物流","bulky_cargo_price_low":0.02,"server_type":"90","bulky_cargo_price_mid":0.03,"end_outlets":6,"address":"中央花园三期","county":"武侯区","heavy_cargo_price_low":0.01,"evaluate_level":4,"create_person_id":9,"create_time":"2016-06-06 17:00:14.0","lowest_price":0.01}],"company_name":"顺风物流服务有限公司"},{"id":7,"logo":"upload/company/login/1/11/cb48b718-826c-400b-9341-2720823c5149.jpg","distance":11389,"list":[{"start_outlets":22,"status":1,"bulky_cargo_price_high":370,"heavy_cargo_price_high":370,"transport_mode":"6","end_outlets_name":"远大榕江网点","outlets_id":22,"start_outlets_name":"远大成都网点","release_state":1,"heavy_cargo_price_mid":400,"recommended":1,"bulky_cargo_price_low":450,"server_type":"91","city":"成都市","bulky_cargo_price_mid":400,"end_outlets":23,"id":11,"address":"成都双流九江","county":"双流县","heavy_cargo_price_low":450,"province":"四川省","create_person_id":24,"create_time":"2016-06-14 16:27:41.0","lowest_price":100,"time_long":9},{"start_outlets":22,"status":1,"bulky_cargo_price_high":530,"heavy_cargo_price_high":550,"transport_mode":"5","end_outlets_name":"远大凯里网点","outlets_id":22,"start_outlets_name":"远大成都网点","release_state":1,"heavy_cargo_price_mid":530,"recommended":1,"bulky_cargo_price_low":400,"server_type":"90","city":"成都市","bulky_cargo_price_mid":480,"end_outlets":24,"id":12,"address":"成都双流九江","county":"双流县","heavy_cargo_price_low":480,"province":"四川省","create_person_id":24,"create_time":"2016-06-17 17:01:57.0","lowest_price":150,"time_long":2}],"company_name":"成都远大物流货运有限公司"},{"id":8,"logo":"upload/company/login/1/11/8712393d-5dc9-4391-8f39-2af1dc20eb71.jpg","distance":31613,"list":[{"start_outlets":25,"status":1,"bulky_cargo_price_high":450,"heavy_cargo_price_high":450,"transport_mode":"6","end_outlets_name":"鑫宏旬阳网点","outlets_id":25,"start_outlets_name":"鑫宏成都网点","release_state":0,"heavy_cargo_price_mid":500,"recommended":1,"bulky_cargo_price_low":550,"server_type":"90","city":"成都市","bulky_cargo_price_mid":500,"end_outlets":26,"id":23,"address":"成都新都","county":"新都区","heavy_cargo_price_low":550,"province":"四川省","create_person_id":25,"create_time":"2016-07-01 15:56:17.0","lowest_price":100,"time_long":24}],"company_name":"四川鑫宏物流有限公司"},{"id":9,"logo":"upload/company/login/1/11/16220b54-d6af-47e7-976a-4bc5adc1b996.jpg","distance":31613,"list":[{"start_outlets":27,"status":1,"bulky_cargo_price_high":300,"heavy_cargo_price_high":300,"transport_mode":"5","end_outlets_name":"黑马从江网点","outlets_id":27,"start_outlets_name":"黑马成都网点","release_state":1,"heavy_cargo_price_mid":320,"recommended":1,"bulky_cargo_price_low":320,"server_type":"91","city":"成都市","bulky_cargo_price_mid":310,"end_outlets":28,"id":10,"address":"成都新都","county":"新都区","heavy_cargo_price_low":340,"province":"四川省","create_person_id":26,"create_time":"2016-06-14 11:11:22.0","lowest_price":100,"time_long":9}],"company_name":"成都黑马物流"}]
     * pageIndex : 1
     */


    private int code;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


        private List<ObjectListBean> objectList;


        public List<ObjectListBean> getObjectList() {
            return objectList;
        }

        public void setObjectList(List<ObjectListBean> objectList) {
            this.objectList = objectList;
        }

        public static class ObjectListBean {
            private int id;
            private String logo;
            private int distance;
            private String company_name;
            /**
             * transport_mode : 5
             * outlets_id : 1
             * start_outlets_name : 成都
             * release_state : 0
             * heavy_cargo_price_mid : 0.01
             * recommended : 1
             * city : 成都市
             * id : 1
             * province : 四川省
             * time_long : 8
             * start_outlets : 1
             * status : 1
             * bulky_cargo_price_high : 0.01
             * heavy_cargo_price_high : 0.01
             * end_outlets_name : 重庆
             * bulky_cargo_price_low : 0.01
             * server_type : 90
             * bulky_cargo_price_mid : 0.01
             * end_outlets : 2
             * address : 西部鞋都
             * county : 武侯区
             * heavy_cargo_price_low : 0.01
             * evaluate_level : 5
             * create_person_id : 1
             * create_time : 2016-06-06 00:03:41.0
             * lowest_price : 0.01
             */

            private List<ListBean> list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private String transport_mode;
                private int outlets_id;
                private String start_outlets_name;
                private int release_state;
                private double heavy_cargo_price_mid;
                private int recommended;
                private String city;
                private int id;
                private String province;
                private int time_long;
                private int start_outlets;
                private int status;
                private double bulky_cargo_price_high;
                private double heavy_cargo_price_high;
                private String end_outlets_name;
                private double bulky_cargo_price_low;
                private String server_type;
                private double bulky_cargo_price_mid;
                private int end_outlets;
                private String address;
                private String county;
                private double heavy_cargo_price_low;
                private int evaluate_level;
                private int create_person_id;
                private String create_time;
                private double lowest_price;

                public String getTransport_mode() {
                    return transport_mode;
                }

                public void setTransport_mode(String transport_mode) {
                    this.transport_mode = transport_mode;
                }

                public int getOutlets_id() {
                    return outlets_id;
                }

                public void setOutlets_id(int outlets_id) {
                    this.outlets_id = outlets_id;
                }

                public String getStart_outlets_name() {
                    return start_outlets_name;
                }

                public void setStart_outlets_name(String start_outlets_name) {
                    this.start_outlets_name = start_outlets_name;
                }

                public int getRelease_state() {
                    return release_state;
                }

                public void setRelease_state(int release_state) {
                    this.release_state = release_state;
                }

                public double getHeavy_cargo_price_mid() {
                    return heavy_cargo_price_mid;
                }

                public void setHeavy_cargo_price_mid(double heavy_cargo_price_mid) {
                    this.heavy_cargo_price_mid = heavy_cargo_price_mid;
                }

                public int getRecommended() {
                    return recommended;
                }

                public void setRecommended(int recommended) {
                    this.recommended = recommended;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public int getTime_long() {
                    return time_long;
                }

                public void setTime_long(int time_long) {
                    this.time_long = time_long;
                }

                public int getStart_outlets() {
                    return start_outlets;
                }

                public void setStart_outlets(int start_outlets) {
                    this.start_outlets = start_outlets;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public double getBulky_cargo_price_high() {
                    return bulky_cargo_price_high;
                }

                public void setBulky_cargo_price_high(double bulky_cargo_price_high) {
                    this.bulky_cargo_price_high = bulky_cargo_price_high;
                }

                public double getHeavy_cargo_price_high() {
                    return heavy_cargo_price_high;
                }

                public void setHeavy_cargo_price_high(double heavy_cargo_price_high) {
                    this.heavy_cargo_price_high = heavy_cargo_price_high;
                }

                public String getEnd_outlets_name() {
                    return end_outlets_name;
                }

                public void setEnd_outlets_name(String end_outlets_name) {
                    this.end_outlets_name = end_outlets_name;
                }

                public double getBulky_cargo_price_low() {
                    return bulky_cargo_price_low;
                }

                public void setBulky_cargo_price_low(double bulky_cargo_price_low) {
                    this.bulky_cargo_price_low = bulky_cargo_price_low;
                }

                public String getServer_type() {
                    return server_type;
                }

                public void setServer_type(String server_type) {
                    this.server_type = server_type;
                }

                public double getBulky_cargo_price_mid() {
                    return bulky_cargo_price_mid;
                }

                public void setBulky_cargo_price_mid(double bulky_cargo_price_mid) {
                    this.bulky_cargo_price_mid = bulky_cargo_price_mid;
                }

                public int getEnd_outlets() {
                    return end_outlets;
                }

                public void setEnd_outlets(int end_outlets) {
                    this.end_outlets = end_outlets;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public double getHeavy_cargo_price_low() {
                    return heavy_cargo_price_low;
                }

                public void setHeavy_cargo_price_low(double heavy_cargo_price_low) {
                    this.heavy_cargo_price_low = heavy_cargo_price_low;
                }

                public int getEvaluate_level() {
                    return evaluate_level;
                }

                public void setEvaluate_level(int evaluate_level) {
                    this.evaluate_level = evaluate_level;
                }

                public int getCreate_person_id() {
                    return create_person_id;
                }

                public void setCreate_person_id(int create_person_id) {
                    this.create_person_id = create_person_id;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public double getLowest_price() {
                    return lowest_price;
                }

                public void setLowest_price(double lowest_price) {
                    this.lowest_price = lowest_price;
                }
            }
        }
    }
