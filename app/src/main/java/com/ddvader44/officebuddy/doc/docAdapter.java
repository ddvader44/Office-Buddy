package com.ddvader44.officebuddy.doc;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ddvader44.officebuddy.R;

import java.io.File;
import java.util.ArrayList;

public class docAdapter extends ArrayAdapter<File> {
    Context context;

    ViewHolder viewHolder;
    ArrayList<File> arrayList;

    public docAdapter(@NonNull Context context, ArrayList<File> arrayList) {
        super(context, R.layout.doc_adapter,arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if(arrayList.size()>0)
        {
            return arrayList.size();
        }
        else
            return 1;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.doc_adapter,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.fileName = (TextView)convertView.findViewById(R.id.docName);
            viewHolder.fileName.setTextColor(Color.WHITE);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.fileName.setText(arrayList.get(position).getName());
        viewHolder.fileName.setTextColor(Color.WHITE);
        return convertView;
    }

    public class ViewHolder{
        TextView fileName;

    }
}
