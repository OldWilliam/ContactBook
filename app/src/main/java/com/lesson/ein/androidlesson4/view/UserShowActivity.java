package com.lesson.ein.androidlesson4.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lesson.ein.androidlesson4.R;
import com.lesson.ein.androidlesson4.presenter.UserShowPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserShowActivity extends AppCompatActivity implements IUserShowView {

    UserShowPresenter presenter;

    @BindView(R.id.lv_item)
    ListView userinfoLv;

    @OnClick(R.id.ib_add)
    void addUser() {
        toAddActivity();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_show);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setLogo(R.drawable.logo);
            actionBar.setTitle("  联系人列表");
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        presenter = new UserShowPresenter(this, this);
        userinfoLv.setAdapter(presenter.getArrayAdapter());
        userinfoLv.setOnItemClickListener(new MyOnItemClickListener());
    }

    @Override
    public void showUserInfo() {
        presenter.updateUserInfo();
    }

    @Override
    public void toAddActivity() {
        Intent i = new Intent(UserShowActivity.this, UserAddActivity.class);
        startActivity(i);
    }

    @Override
    public void toEditActivity(int position) {
        Intent i = new Intent(UserShowActivity.this, UserAddActivity.class);
        i.putExtra("isUpdate", true);
        i.putExtra("position", position);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showUserInfo();
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder adBuilder = new AlertDialog.Builder(UserShowActivity.this);
            adBuilder
                    .setMessage("请选择操作")
                    .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.del(position);
                            presenter.updateUserInfo();
                        }
                    })
                    .setNegativeButton("编辑", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toEditActivity(position);
                        }
                    })
                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();

        }
    }
}
