package com.lesson.ein.androidlesson4.view;

/**
 * Created by ein on 2016/6/14.
 */
public interface IUserAddView {
    String getName();
    String getPhone();
    String getGroup();
    void setNameEtError();
    void setPhoneEtError();

    void setName(String name);

    void setPhone(String phone);

    int getUpdatePos();

}
