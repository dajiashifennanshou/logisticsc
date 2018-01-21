package com.wrt.xinsilu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class ProvinceBean implements Serializable {


    /**
     * id : 110000
     * name : 北京市
     * pid : 100000
     * list : [{"id":"110100","name":"市辖区","pid":"110000","list":[{"id":"110101","name":"东城区","pid":"110100"},{"id":"110102","name":"西城区","pid":"110100"},{"id":"110105","name":"朝阳区","pid":"110100"},{"id":"110106","name":"丰台区","pid":"110100"},{"id":"110107","name":"石景山区","pid":"110100"},{"id":"110108","name":"海淀区","pid":"110100"},{"id":"110109","name":"门头沟区","pid":"110100"},{"id":"110111","name":"房山区","pid":"110100"},{"id":"110112","name":"通州区","pid":"110100"},{"id":"110113","name":"顺义区","pid":"110100"},{"id":"110114","name":"昌平区","pid":"110100"},{"id":"110115","name":"大兴区","pid":"110100"},{"id":"110116","name":"怀柔区","pid":"110100"},{"id":"110117","name":"平谷区","pid":"110100"}]},{"id":"110200","name":"县","pid":"110000","list":[{"id":"110228","name":"密云县","pid":"110200"},{"id":"110229","name":"延庆县","pid":"110200"}]}]
     */

    private String id;
    private String name;
    private String pid;
    /**
     * id : 110100
     * name : 市辖区
     * pid : 110000
     * list : [{"id":"110101","name":"东城区","pid":"110100"},{"id":"110102","name":"西城区","pid":"110100"},{"id":"110105","name":"朝阳区","pid":"110100"},{"id":"110106","name":"丰台区","pid":"110100"},{"id":"110107","name":"石景山区","pid":"110100"},{"id":"110108","name":"海淀区","pid":"110100"},{"id":"110109","name":"门头沟区","pid":"110100"},{"id":"110111","name":"房山区","pid":"110100"},{"id":"110112","name":"通州区","pid":"110100"},{"id":"110113","name":"顺义区","pid":"110100"},{"id":"110114","name":"昌平区","pid":"110100"},{"id":"110115","name":"大兴区","pid":"110100"},{"id":"110116","name":"怀柔区","pid":"110100"},{"id":"110117","name":"平谷区","pid":"110100"}]
     */
    private List<CityBean> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<CityBean> getList() {
        return list;
    }

    public void setList(List<CityBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static class CityBean implements Serializable {
        private String id;
        private String name;
        private String pid;
        /**
         * id : 110101
         * name : 东城区
         * pid : 110100
         */

        private List<CountyBean> list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public List<CountyBean> getList() {
            return list;
        }

        public void setList(List<CountyBean> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return getName();
        }

        public static class CountyBean implements Serializable {
            private String id;
            private String name;
            private String pid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            @Override
            public String toString() {
                return getName();
            }
        }
    }
}
