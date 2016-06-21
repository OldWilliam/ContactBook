package com.lesson.ein.androidlesson4.bean;

/**
 * Created by ein on 2016/6/7.
 */
public class UserBean {
    private String name;
    private String phone;
    private String group;

    public UserBean(String name, String phone, String group) {
        this.name = name;
        this.phone = phone;
        this.group = group;
    }

    public UserBean() {

    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGroup() {
        return group;
    }
}
