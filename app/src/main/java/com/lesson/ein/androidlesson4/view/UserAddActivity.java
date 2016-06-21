package com.lesson.ein.androidlesson4.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lesson.ein.androidlesson4.R;
import com.lesson.ein.androidlesson4.presenter.UserAddPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAddActivity extends AppCompatActivity implements IUserAddView {

    private UserAddPresenter presenter;
    private AlertDialog.Builder adBuilder;
    private boolean isUpdate;
    private int updatePos;

    @BindView(R.id.et_name)
    EditText nameEt;
    @BindView(R.id.et_phone)
    EditText phoneEt;
    @BindView(R.id.spinner)
    Spinner groupSp;

    @OnClick(R.id.bt_save)
    void saveUser() {
        if (!check()) return;
        if (isUpdate) {
            adBuilder
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.updateUser();
                            finish();
                        }
                    })
                    .show();
        } else {
            adBuilder
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.insertUser();
                            finish();
                        }
                    })
                    .show();
        }

    }

    private boolean check() {
        String name = getName();
        String phone = getPhone();
        if (TextUtils.isEmpty(name)) {
            setNameEtError();
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            setPhoneEtError();
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("添加联系人");
        actionBar.setDisplayHomeAsUpEnabled(true);
        presenter = new UserAddPresenter(this);

        Intent intent = getIntent();
        isUpdate = intent.getBooleanExtra("isUpdate", false);
        if (isUpdate) {
            actionBar.setTitle("修改联系人");
            updatePos = intent.getIntExtra("position", 0);
            presenter.showCurrent();
        }

        createDialog();
    }

    private void createDialog() {
        adBuilder = new AlertDialog.Builder(UserAddActivity.this);
        adBuilder
                .setMessage("确认保存吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getName() {
        return nameEt.getText().toString();
    }


    @Override
    public String getPhone() {
        return phoneEt.getText().toString();
    }

    @Override
    public String getGroup() {
        int pos = groupSp.getSelectedItemPosition();
        String[] arrays = getResources().getStringArray(R.array.relation);
        return arrays[pos];
    }

    @Override
    public void setNameEtError() {
        nameEt.setError("不能为空！");
    }

    @Override
    public void setPhoneEtError() {
        phoneEt.setError("不能为空！");
    }

    @Override
    public void setName(String name) {
        nameEt.setText(name);
    }

    @Override
    public void setPhone(String phone) {
        phoneEt.setText(phone);
    }

    @Override
    public int getUpdatePos() {
        return updatePos;
    }

}
