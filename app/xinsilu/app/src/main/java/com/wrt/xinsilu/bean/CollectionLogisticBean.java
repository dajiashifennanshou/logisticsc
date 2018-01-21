package com.wrt.xinsilu.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 * 收藏物流商，常用物流上bean
 */
public class CollectionLogisticBean {

    /**
     * id : 69
     * userId : 26
     * comId : 11
     * status : 1
     * createTime : 2016-08-15 15:58:28.0
     * com : {"id":11,"company_name":"四川省湘汉运输服务有限公司","logo":"upload/company/login/12/14/628d4915-fbe5-4ed1-849a-bba0abf74b93.jpg","list":[{"id":21,"start_outlets_name":"成都网点","end_outlets_name":"毕节网点"}]}
     */

    private int id;
    private int userId;
    private int comId;
    private int status;
    private String createTime;
    /**
     * id : 11
     * company_name : 四川省湘汉运输服务有限公司
     * logo : upload/company/login/12/14/628d4915-fbe5-4ed1-849a-bba0abf74b93.jpg
     * list : [{"id":21,"start_outlets_name":"成都网点","end_outlets_name":"毕节网点"}]
     */

    private ComBean com;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public ComBean getCom() {
        return com;
    }

    public void setCom(ComBean com) {
        this.com = com;
    }

    public static class ComBean {
        private int id;
        private String company_name;
        private String logo;
        /**
         * id : 21
         * start_outlets_name : 成都网点
         * end_outlets_name : 毕节网点
         */

        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String start_outlets_name;
            private String end_outlets_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStart_outlets_name() {
                return start_outlets_name;
            }

            public void setStart_outlets_name(String start_outlets_name) {
                this.start_outlets_name = start_outlets_name;
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
