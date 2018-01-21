package com.wrt.xinsilu.bean;

/**
 * Created by Administrator on 2016/6/28 0028.
 * 一个物流商的路线
 */
public class CollectionLineBean {

    /**
     * id : 29
     * line_id : 25
     * user_id : 131
     * state : 1
     * collection_time : 2016-08-22
     * line : {"id":25,"server_type":"定日达","start_outlets":49,"start_outlets_name":"成都网点","end_outlets":50,"end_outlets_name":"南京","transport_mode":"零担运输","time_long":50,"heavy_cargo_price_low":550,"bulky_cargo_price_low":150,"heavy_cargo_price_mid":500,"bulky_cargo_price_mid":120,"heavy_cargo_price_high":500,"bulky_cargo_price_high":120,"lowest_price":1,"status":1,"release_state":1,"create_time":"2016-07-11 22:21:05.0","create_person_id":62,"outlets_id":49,"recommended":1,"isAddServer":0,"conf":{"id":1,"isReceipt":1,"companyId":15,"isImmediatePay":0,"isArrivePay":1,"isAdvancePay":0,"isCommonReceipt":0,"isAddTaxReceipt":0,"isNoReceipt":1,"outletsId":49}}
     * countOrder : 3
     * countOrderEvaluation : 0
     */

    private int id;
    private int line_id;
    private int user_id;
    private int state;
    private String collection_time;
    /**
     * id : 25
     * server_type : 定日达
     * start_outlets : 49
     * start_outlets_name : 成都网点
     * end_outlets : 50
     * end_outlets_name : 南京
     * transport_mode : 零担运输
     * time_long : 50.0
     * heavy_cargo_price_low : 550.0
     * bulky_cargo_price_low : 150.0
     * heavy_cargo_price_mid : 500.0
     * bulky_cargo_price_mid : 120.0
     * heavy_cargo_price_high : 500.0
     * bulky_cargo_price_high : 120.0
     * lowest_price : 1.0
     * status : 1
     * release_state : 1
     * create_time : 2016-07-11 22:21:05.0
     * create_person_id : 62
     * outlets_id : 49
     * recommended : 1
     * isAddServer : 0
     * conf : {"id":1,"isReceipt":1,"companyId":15,"isImmediatePay":0,"isArrivePay":1,"isAdvancePay":0,"isCommonReceipt":0,"isAddTaxReceipt":0,"isNoReceipt":1,"outletsId":49}
     */

    private LineBean line;
    private int countOrder;
    private int countOrderEvaluation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCollection_time() {
        return collection_time;
    }

    public void setCollection_time(String collection_time) {
        this.collection_time = collection_time;
    }

    public LineBean getLine() {
        return line;
    }

    public void setLine(LineBean line) {
        this.line = line;
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

    public static class LineBean {
        private int id;
        private String server_type;
        private int start_outlets;
        private String start_outlets_name;
        private int end_outlets;
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
         * id : 1
         * isReceipt : 1
         * companyId : 15
         * isImmediatePay : 0
         * isArrivePay : 1
         * isAdvancePay : 0
         * isCommonReceipt : 0
         * isAddTaxReceipt : 0
         * isNoReceipt : 1
         * outletsId : 49
         */

        private ConfBean conf;

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

        public int getStart_outlets() {
            return start_outlets;
        }

        public void setStart_outlets(int start_outlets) {
            this.start_outlets = start_outlets;
        }

        public String getStart_outlets_name() {
            return start_outlets_name;
        }

        public void setStart_outlets_name(String start_outlets_name) {
            this.start_outlets_name = start_outlets_name;
        }

        public int getEnd_outlets() {
            return end_outlets;
        }

        public void setEnd_outlets(int end_outlets) {
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
