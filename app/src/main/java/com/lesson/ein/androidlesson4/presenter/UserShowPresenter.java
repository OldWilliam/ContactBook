package com.lesson.ein.androidlesson4.presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.lesson.ein.androidlesson4.R;
import com.lesson.ein.androidlesson4.bean.UserBean;
import com.lesson.ein.androidlesson4.model.IUserModel;
import com.lesson.ein.androidlesson4.model.UserModel;
import com.lesson.ein.androidlesson4.view.IUserShowView;
import com.lesson.ein.androidlesson4.view.adpter.MyArrayAdapter;

import java.util.List;

/**
 * Created by ein on 2016/6/15.
 */
public class UserShowPresenter {
    private IUserModel mUserModel;
    private IUserShowView mUserShowView;

    private Context context;
    private ArrayAdapter arrayAdapter;

    public UserShowPresenter(IUserShowView view, Context context) {
        this.context = context;
        mUserShowView = view;
        mUserModel = UserModel.getInstanse(context);
    }

    public List<UserBean> getAllUser(){
        return mUserModel.getCacheList();
    }

    public ArrayAdapter getArrayAdapter() {
        arrayAdapter = new MyArrayAdapter(context, R.layout.lv_item,getAllUser());
        return arrayAdapter;
    }

    public void updateUserInfo() {
        arrayAdapter.notifyDataSetChanged();
    }

    public void del(int position) {
        mUserModel.deleteUser(position);
    }

}
