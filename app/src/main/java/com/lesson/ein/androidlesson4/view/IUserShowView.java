package com.lesson.ein.androidlesson4.view;

import android.widget.BaseAdapter;

/**
 * Created by ein on 2016/6/15.
 */
public interface IUserShowView {
    void showUserInfo();
    void toAddActivity();
    void toEditActivity(int pos);

}
