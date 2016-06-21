package com.lesson.ein.androidlesson4.view.adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lesson.ein.androidlesson4.R;
import com.lesson.ein.androidlesson4.bean.UserBean;

import java.util.List;

/**
 * Created by ein on 2016/6/15.
 */
public class MyArrayAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private Toast toast;

    public MyArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    @Override
    public UserBean getItem(int position) {
        UserBean info = (UserBean) super.getItem(position);
        return info;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        UserBean user = getItem(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.lv_item, null);
            holder.nameTv = (TextView) convertView.findViewById(R.id.tv_name);
            holder.phoneTv = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.groupTv = (TextView) convertView.findViewById(R.id.tv_group);
            convertView.setTag(holder);
            Log.e("TAG", "convertView null");
        } else {
            holder = (ViewHolder) convertView.getTag();
            Log.e("TAG", "convertView not null");
        }

        holder.nameTv.setText(user.getName());
        holder.phoneTv.setText(user.getPhone());
        holder.groupTv.setText(user.getGroup());
        return convertView;
    }

    static class ViewHolder {
        TextView nameTv;
        TextView phoneTv;
        TextView groupTv;
    }
}
