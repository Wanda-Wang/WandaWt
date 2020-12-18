package com.example.login_register_f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.login_register_f.R;
import com.example.login_register_f.bean.Essay;

import java.util.ArrayList;
import java.util.LinkedList;

public class EssayAdapter extends BaseAdapter {
    private ArrayList<Essay> mData;
    private Context mContext;

    public EssayAdapter(ArrayList<Essay> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_essay,parent,false);
        //TextView date = convertView.findViewById(R.id.date);
        TextView author = convertView.findViewById(R.id.author);
        TextView title = convertView.findViewById(R.id.essay_name);
       // date.setText(mData.get(position).getPublishedAt().split(" ")[0]);
        author.setText(mData.get(position).getAuthor());
        title.setText(mData.get(position).getTitle());
        return convertView;
    }
}
