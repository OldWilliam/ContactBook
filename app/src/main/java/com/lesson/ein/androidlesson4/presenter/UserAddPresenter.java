package com.lesson.ein.androidlesson4.presenter;

import android.text.TextUtils;

import com.lesson.ein.androidlesson4.bean.UserBean;
import com.lesson.ein.androidlesson4.model.IUserModel;
import com.lesson.ein.androidlesson4.model.UserModel;
import com.lesson.ein.androidlesson4.view.IUserAddView;

/**
 * Created by ein on 2016/6/15.
 */
public class UserAddPresenter {

    private IUserAddView mUserAddView;
    private IUserModel mUserModel;
    public UserAddPresenter(IUserAddView view) {
        mUserAddView = view;
        mUserModel = UserModel.getInstanse();
    }

    public void insertUser(){
        mUserModel.insertUser(mUserAddView.getName(),mUserAddView.getPhone(),mUserAddView.getGroup());
    }

    public UserBean getUser(int position) {
        return mUserModel.get(position);
    }

    public void updateUser() {
        UserBean bean = new UserBean();
        bean.setName(mUserAddView.getName());
        bean.setPhone(mUserAddView.getPhone());
        bean.setGroup(mUserAddView.getGroup());
        mUserModel.updateUser(mUserAddView.getUpdatePos(), bean);
    }

    public void showCurrent() {
        UserBean bean = mUserModel.get(mUserAddView.getUpdatePos());
        mUserAddView.setName(bean.getName());
        mUserAddView.setPhone(bean.getPhone());
    }
}
