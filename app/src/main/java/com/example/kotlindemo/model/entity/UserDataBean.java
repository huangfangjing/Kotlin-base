package com.example.kotlindemo.model.entity;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


import com.example.kotlindemo.BR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hfj on 2018/7/11
 */
public class UserDataBean extends BaseObservable implements Serializable {

    /**
     * list : [{"name":"2019-2020学年 秋季","status":"n","termId":"0qUJ0hl01v0S9r41VD91lINZcXDY2","yearId":"0qS0UPX97NTYYKHQcBEaxeaVmyIS3Z"},{"name":"2018-2019学年 第一学期","status":"n","termId":"0qS6HdIS6webbmfh8IE9VlH6rA9wj0","yearId":"0qS0UwXJ2PCV8Q7qHzGaxOS8Nzy86h"},{"name":"2017-2018学年 下学期","status":"v","termId":"0qSaDDiP3cLAMz9ktHDa9QYMn5mesQ","yearId":"0qS0UuL92ZpzmLFvPQR9We1Urh9VaI"},{"name":"2017-2018学年 上学期","status":"n","termId":"0qS4IbNY10AGFam0Wco7CfwSk1EkW2","yearId":"0qS0UuL92ZpzmLFvPQR9We1Urh9VaI"}]
     * status : S1000
     * userBean : {"createTime":1530580288350,"createUserId":1,"email":"277320930@qq.com","lastLoginTime":1531295071134,"loginIp":"192.168.9.150","mobile":"13928872095","name":"管理员","no":"admin","password":"PU8r8H3BvjiyDNbkaUmhBx+dDj0=","state":"v","type":"a","updateTime":1530674125848,"updateUserId":1,"userId":1}
     */

    private String status;
    private UserBeanBean userBean;
    private List<ListBean> list;
    private UserClassBean userClassBean;
    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserClassBean getUserClassBean() {
        return userClassBean;
    }

    public void setUserClassBean(UserClassBean userClassBean) {
        this.userClassBean = userClassBean;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserBeanBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBeanBean userBean) {
        this.userBean = userBean;
    }

//    public List<ListBean> getList() {
//        return list;
//    }

    public List<ListBean> getList() {
        if (list == null)
            return new ArrayList<>();
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class UserBeanBean extends BaseObservable implements Serializable {
        /**
         * createTime : 1530580288350
         * createUserId : 1
         * email : 277320930@qq.com
         * lastLoginTime : 1531295071134
         * loginIp : 192.168.9.150
         * mobile : 13928872095
         * name : 管理员
         * no : admin
         * password : PU8r8H3BvjiyDNbkaUmhBx+dDj0=
         * state : v
         * type : a
         * updateTime : 1530674125848
         * updateUserId : 1
         * userId : 1
         */

        private long createTime;
        private int createUserId;
        private String email;
        private long lastLoginTime;
        private String loginIp;
        private String mobile;
        private String name;
        private String no;
        private String password;
        private String state;
        public String type;//"o"运营人员，“t”老师
        private long updateTime;
        private int updateUserId;
        private Long userId;
        private ListBean currentSemester;
        private String sex;
        private SchoolInfo schoolInfo;
        public String schoolId;

        public String getSchoolId() {
            return schoolId;
        }

        public SchoolInfo getSchoolInfo() {
            return schoolInfo;
        }

        public void setSchoolInfo(SchoolInfo schoolInfo) {
            this.schoolInfo = schoolInfo;
        }

        @Bindable
        public ListBean getCurrentSemester() {
            return currentSemester;
        }

        public void setCurrentSemester(ListBean currentSemester) {
            this.currentSemester = currentSemester;
            notifyPropertyChanged(BR.currentSemester);
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        @Bindable
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            notifyPropertyChanged(BR.name);
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public Long getUserId() {
            return userId;
        }

        public String getUserIdString() {
            if (userId == 0) return null;
            return String.valueOf(userId);
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    public static class SchoolInfo implements Serializable {

        /**
         * schoolId : 000002
         * areaId : 1
         * parentId : /
         * no : 000002
         * name : KC中学
         * sourceType : w
         * schoolType : 5
         * state : v
         * address : address
         * contact : null
         * contactTel : null
         * detail : {}
         * config : {"logoFileId":"20191107103339708946424","mpQrCode":"20191107103049278128916","website":"www.hao123.com"}
         * remark : KC中学注备注
         * beginTime : null
         * endTime : null
         * createTime : null
         * createUserId : null
         */

        private String schoolId;
        private String areaId;
        private String parentId;
        private String no;
        private String name;
        private String sourceType;
        private String schoolType;
        private String state;
        private String address;
        private Object contact;
        private Object contactTel;
        private String detail;
        private String config;
        private String remark;
        private Object beginTime;
        private Object endTime;
        private Object createTime;
        private Object createUserId;

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getSchoolType() {
            return schoolType;
        }

        public void setSchoolType(String schoolType) {
            this.schoolType = schoolType;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getContact() {
            return contact;
        }

        public void setContact(Object contact) {
            this.contact = contact;
        }

        public Object getContactTel() {
            return contactTel;
        }

        public void setContactTel(Object contactTel) {
            this.contactTel = contactTel;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getConfig() {
            return config;
        }

        public void setConfig(String config) {
            this.config = config;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(Object beginTime) {
            this.beginTime = beginTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(Object createUserId) {
            this.createUserId = createUserId;
        }
    }

    public static class ListBean implements Serializable {
        /**
         * name : 2019-2020学年 秋季
         * status : n
         * termId : 0qUJ0hl01v0S9r41VD91lINZcXDY2
         * yearId : 0qS0UPX97NTYYKHQcBEaxeaVmyIS3Z
         */

        private String name;
        private String status;
        private String termId;
        private String yearId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTermId() {
            return termId;
        }

        public void setTermId(String termId) {
            this.termId = termId;
        }

        public String getYearId() {
            return yearId;
        }

        public void setYearId(String yearId) {
            this.yearId = yearId;
        }

        public boolean isSameWith(ListBean listBean) {
            if (listBean == null) {
                return false;
            }
            return termId.equals(listBean.getTermId()) && yearId.equals(listBean.getYearId());
        }

    }
}
