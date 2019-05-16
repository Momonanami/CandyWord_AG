package com.example.a19134355_7.candyword_ag;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Myadapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> nombres;

    public Myadapter(Context context,int layout,List<String> nombres){
        this.context= context;
        this.layout = layout;
        this.nombres = nombres;
    }
    @Override
    public int getCount ()
    {
        return this.nombres.size();
    }

    @Override
    public Object getItem(int position) {
        return this.nombres.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if (convertView == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.list_item,null);
            holder = new ViewHolder();
            holder.nametextView = convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        String nombreActual= nombres.get(position);
        holder.nametextView.setText(nombreActual);

        return convertView;

    }
    static class ViewHolder
    {
        public TextView nametextView;
    }

}
