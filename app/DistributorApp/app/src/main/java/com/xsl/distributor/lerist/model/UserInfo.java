package com.xsl.distributor.lerist.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class UserInfo {

    private User user;
    private Join join;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Join getJoin() {
        return join;
    }

    public void setJoin(Join join) {
        this.join = join;
    }

    public long getId() {
        return user != null ? user.getId() : 0;
    }


    public static class User {
        private long id;
        private String login_name;
        private String password;
        private String mobile;
        private String email;
        private String address;
        private String true_name;
        private int user_type;
        private String cred_type;
        private String cred_number;
        private String cash_password;
        private int status;
        private double balance;
        private int points;
        private String salesman_no;
        private String the_selesman_no;
        private long company_id;
        private long temporary_company_id;
        private int vehicle_type;
        private String dot_address;
        private int user_status;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getLogin_name() {
            return login_name;
        }

        public void setLogin_name(String login_name) {
            this.login_name = login_name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public String getCred_type() {
            return cred_type;
        }

        public void setCred_type(String cred_type) {
            this.cred_type = cred_type;
        }

        public String getCred_number() {
            return cred_number;
        }

        public void setCred_number(String cred_number) {
            this.cred_number = cred_number;
        }

        public String getCash_password() {
            return cash_password;
        }

        public void setCash_password(String cash_password) {
            this.cash_password = cash_password;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getSalesman_no() {
            return salesman_no;
        }

        public void setSalesman_no(String salesman_no) {
            this.salesman_no = salesman_no;
        }

        public String getThe_selesman_no() {
            return the_selesman_no;
        }

        public void setThe_selesman_no(String the_selesman_no) {
            this.the_selesman_no = the_selesman_no;
        }

        public long getCompany_id() {
            return company_id;
        }

        public void setCompany_id(long company_id) {
            this.company_id = company_id;
        }

        public long getTemporary_company_id() {
            return temporary_company_id;
        }

        public void setTemporary_company_id(long temporary_company_id) {
            this.temporary_company_id = temporary_company_id;
        }

        public int getVehicle_type() {
            return vehicle_type;
        }

        public void setVehicle_type(int vehicle_type) {
            this.vehicle_type = vehicle_type;
        }

        public String getDot_address() {
            return dot_address;
        }

        public void setDot_address(String dot_address) {
            this.dot_address = dot_address;
        }

        public int getUser_status() {
            return user_status;
        }

        public void setUser_status(int user_status) {
            this.user_status = user_status;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    public static class Join {
        public static final int APPLYSTATUS_CHECKING = 0;
        public static final int APPLYSTATUS_PASS = 1;
        public static final int APPLYSTATUS_NOTPASS = 2;

        private long id;
        private long joinerId;
        private String joinName;
        private String branchNo;
        private String branchName;
        private int joinType;
        private String zoneNo;
        private float apolyArea;
        private int applyStatus;
        private int days;
        private String startUseTime;
        private String endUseTime;
        private String dealerMsg;
        private float prepaid;
        private float surplus;
        private String createTime;
        private String createUser;
        private String updateTime;
        private String updateUser;
        private String remark;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getJoinerId() {
            return joinerId;
        }

        public void setJoinerId(long joinerId) {
            this.joinerId = joinerId;
        }

        public String getJoinName() {
            return joinName;
        }

        public void setJoinName(String joinName) {
            this.joinName = joinName;
        }

        public String getBranchNo() {
            return branchNo;
        }

        public void setBranchNo(String branchNo) {
            this.branchNo = branchNo;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public int getJoinType() {
            return joinType;
        }

        public void setJoinType(int joinType) {
            this.joinType = joinType;
        }

        public String getZoneNo() {
            return zoneNo;
        }

        public void setZoneNo(String zoneNo) {
            this.zoneNo = zoneNo;
        }

        public float getApolyArea() {
            return apolyArea;
        }

        public void setApolyArea(float apolyArea) {
            this.apolyArea = apolyArea;
        }

        public int getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(int applyStatus) {
            this.applyStatus = applyStatus;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getStartUseTime() {
            return startUseTime;
        }

        public void setStartUseTime(String startUseTime) {
            this.startUseTime = startUseTime;
        }

        public String getEndUseTime() {
            return endUseTime;
        }

        public void setEndUseTime(String endUseTime) {
            this.endUseTime = endUseTime;
        }

        public String getDealerMsg() {
            return dealerMsg;
        }

        public void setDealerMsg(String dealerMsg) {
            this.dealerMsg = dealerMsg;
        }

        public float getPrepaid() {
            return prepaid;
        }

        public void setPrepaid(float prepaid) {
            this.prepaid = prepaid;
        }

        public float getSurplus() {
            return surplus;
        }

        public void setSurplus(float surplus) {
            this.surplus = surplus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static UserInfo toUserInfo(String userInfoStr) {
        try {
            UserInfo userInfo = JSON.parseObject(userInfoStr, UserInfo.class);
            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
