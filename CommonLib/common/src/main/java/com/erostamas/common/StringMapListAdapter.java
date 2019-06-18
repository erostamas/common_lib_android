package com.erostamas.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Map;

public class StringMapListAdapter extends BaseAdapter{

    Map<String, String> _mapData;

    public StringMapListAdapter(Map<String, String> mapData) {
        _mapData = mapData;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return _mapData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_value_list_item, parent, false);
        } else {
            result = convertView;
        }
        TextView name_text = (TextView) result.findViewById(R.id.data_name);
        TextView value_text = (TextView) result.findViewById(R.id.data_value);

        int index = 0;
        for (Map.Entry<String, String> entry : _mapData.entrySet()) {
            if (index == position) {
                name_text.setText(entry.getKey());
                value_text.setText(entry.getValue());
                break;
            } else {
                index++;
            }
        }

        return result;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
