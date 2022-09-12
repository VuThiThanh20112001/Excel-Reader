package com.vuthanh.exclereader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LanguageAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<LanguageData> LanguageList;

    public LanguageAdapter(Context context, int layout, List<LanguageData> languageList) {
        this.context = context;
        this.layout = layout;
        LanguageList = languageList;
    }

    @Override
    public int getCount() {
        return LanguageList.size();
    }

    @Override
    public Object getItem(int i) {
        return LanguageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        ImageView imageView = (ImageView) view.findViewById(R.id.tmgV_image);
        TextView txt_name = (TextView) view.findViewById(R.id.txt_name);

        LanguageData languageData = LanguageList.get(i);
        imageView.setImageResource(languageData.getImage());
        txt_name.setText(languageData.getName());

        return view;
    }
}
