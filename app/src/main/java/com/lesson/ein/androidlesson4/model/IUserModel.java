package com.lesson.ein.androidlesson4.model;

import android.widget.ArrayAdapter;

import com.lesson.ein.androidlesson4.bean.UserBean;

import java.util.List;

/**
 * Created by ein on 2016/6/14.
 */
public interface IUserModel {

    void insertUser(String name, String phone, String group);

    void deleteUser(int position);

    void updateUser(int position,UserBean user);

    UserBean get(int position);

    List<UserBean> getAll();

    List<UserBean> getCacheList();
}
