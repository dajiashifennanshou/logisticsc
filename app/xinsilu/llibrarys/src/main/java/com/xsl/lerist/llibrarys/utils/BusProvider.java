package com.xsl.lerist.llibrarys.utils;

import com.squareup.otto.Bus;

/**
 * Created by Lerist on 2016/2/10, 0010.
 */
public class BusProvider {

    private static Bus bus;

    private BusProvider() {
    }

    public static Bus getInstance() {
        if (bus==null)
            bus = new Bus();
        return bus;
    }

    public static class BusEvent {
        private String flag;
        private Object event;

        public BusEvent(String flag, Object... event) {
            this.flag = flag;
            this.event = event;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public Object getEvent() {
            return event;
        }

        public void setEvent(Object event) {
            this.event = event;
        }
    }
}
