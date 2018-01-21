package com.wrt.xinsilu.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4 0004.
 * 附近物流商收搜索显示
 */
public class NearByLineBean {

    /**
     * pageIndex : 1
     * pageSize : 20
     * objectList : [{"cId":1,"companyName":"中工储运物流","outletsId":1,"province":"四川省","city":"成都市","county":"武侯区","distance":7152184,"list":[{"id":1,"server_type":"90","start_outlets":1,"start_outlets_name":"成都","end_outlets":2,"end_outlets_name":"重庆"},{"id":4,"server_type":"90","start_outlets":1,"start_outlets_name":"成都","end_outlets":3,"end_outlets_name":"北京"}]},{"cId":3,"companyName":"顺风物流服务有限公司","outletsId":7,"province":"四川省","city":"成都市","county":"武侯区","distance":7152184,"list":[{"id":3,"server_type":"90","start_outlets":7,"start_outlets_name":"远征物流","end_outlets":6,"end_outlets_name":"金鑫物流"}]},{"cId":6,"companyName":"四川鑫万里货运有限公司","outletsId":20,"province":"四川省","city":"成都市","county":"武侯区","distance":7152184,"list":[{"id":8,"server_type":"91","start_outlets":20,"start_outlets_name":"鑫万里成都网点","end_outlets":21,"end_outlets_name":"鑫万里嘉峪关网点"}]}]
     * limitMin : 0
     * limitMax : 20
     */

    private int pageIndex;
    private int pageSize;
    private int limitMin;
    private int limitMax;
    /**
     * cId : 1
     * companyName : 中工储运物流
     * outletsId : 1
     * province : 四川省
     * city : 成都市
     * county : 武侯区
     * distance : 7152184
     * list : [{"id":1,"server_type":"90","start_outlets":1,"start_outlets_name":"成都","end_outlets":2,"end_outlets_name":"重庆"},{"id":4,"server_type":"90","start_outlets":1,"start_outlets_name":"成都","end_outlets":3,"end_outlets_name":"北京"}]
     */

    public List<ObjectListBean> objectList;

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
        private int cId;
        private String companyName;
        private int outletsId;
        private String province;
        private String city;
        private String county;
        private int distance;
        /**
         * id : 1
         * server_type : 90
         * start_outlets : 1
         * start_outlets_name : 成都
         * end_outlets : 2
         * end_outlets_name : 重庆
         */

        private List<ListBean> list;

        public int getCId() {
            return cId;
        }

        public void setCId(int cId) {
            this.cId = cId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getOutletsId() {
            return outletsId;
        }

        public void setOutletsId(int outletsId) {
            this.outletsId = outletsId;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String server_type;
            private int start_outlets;
            private String start_outlets_name;
            private int end_outlets;
            private String end_outlets_name;

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
        }
    }
}
