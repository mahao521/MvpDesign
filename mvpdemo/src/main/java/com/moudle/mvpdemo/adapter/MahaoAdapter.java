package com.moudle.mvpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moudle.mvpdemo.R;
import com.moudle.mvpdemo.bean.MaHao;

import java.util.List;

/**
 * Created by Administrator on 2018/8/21.
 */

public class MahaoAdapter  extends BaseAdapter{

    private LayoutInflater mLayoutInflater ;
    private List<MaHao> mMaHaos;

    public MahaoAdapter(Context context,List<MaHao> maHaos){
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mMaHaos = maHaos;
    }

    @Override
    public int getCount() {
        return mMaHaos.size();
    }

    @Override
    public Object getItem(int position) {
        return mMaHaos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = mLayoutInflater.inflate(R.layout.item,parent,false);
        MaHao maHao = mMaHaos.get(position);
        TextView textView = view.findViewById(R.id.txt_show_name);
        textView.setText(maHao.getName());
        return view;
    }
}
