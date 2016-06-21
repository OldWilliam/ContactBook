package com.lesson.ein.androidlesson4.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lesson.ein.androidlesson4.bean.UserBean;
import com.lesson.ein.androidlesson4.db.DbOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ein on 2016/6/14.
 */
public class UserModel implements IUserModel {

    private static UserModel instanse;

    private DbOpenHelper helper;
    private List<UserBean> userInfos;

    public static UserModel getInstanse(Context context) {
        if (instanse == null) {
            instanse = new UserModel(context);
        }
        return instanse;
    }

    public static UserModel getInstanse() {
        return instanse;
    }

    private UserModel(Context context) {
        helper = new DbOpenHelper(context);
        userInfos = getAll();
    }

    @Override
    public void insertUser(String name, String phone, String group) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into contact (name, phone, groupName) values (?,?,?)", new String[]{name, phone, group});
        db.close();
        userInfos.add(new UserBean(name, phone, group));
    }

    @Override
    public void deleteUser(int position) {
        UserBean user = userInfos.get(position);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from contact where phone = ?", new String[]{user.getPhone()});
        db.close();
        userInfos.remove(position);
    }

    @Override
    public void updateUser(int position, UserBean bean) {
        UserBean user = userInfos.get(position);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update contact set name = ?, phone = ?, groupName = ? where phone = ?",
                new String[]{bean.getName(), bean.getPhone(), bean.getGroup(), user.getPhone()});
        db.close();
        userInfos.set(position, bean);
    }

    @Override
    public UserBean get(int position) {
        return userInfos.get(position);
    }

    @Override
    public List<UserBean> getAll() {
        List<UserBean> users = new ArrayList<>();
        UserBean userInfo = null;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from contact", null);
        while (true) {
            if (!cursor.moveToNext()) {
                break;
            }
            userInfo = new UserBean(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            users.add(userInfo);
        }
        return users;
    }

    @Override
    public List<UserBean> getCacheList() {
        return userInfos;
    }


}
