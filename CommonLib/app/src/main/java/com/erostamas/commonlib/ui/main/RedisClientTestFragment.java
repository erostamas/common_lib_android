package com.erostamas.commonlib.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.erostamas.commonlib.R;
import com.erostamas.common.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class RedisClientTestFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private Map<String, String> _redisContent = new HashMap<String, String>() {{}};
    private EditText ipAddressText;
    private EditText mapNameText;
    private StringMapListAdapter _redisDataListAdapter = new StringMapListAdapter(_redisContent);

    public static RedisClientTestFragment newInstance(int index) {
        RedisClientTestFragment fragment = new RedisClientTestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_redis, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        ipAddressText = (EditText)root.findViewById(R.id.ip_address);
        mapNameText = (EditText)root.findViewById(R.id.map_name);

        ListView redisDataList = (ListView) root.findViewById(R.id.redis_data_list);
        redisDataList.setAdapter(_redisDataListAdapter);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new RedisClient(ipAddressText.getText().toString(), mapNameText.getText().toString(), _redisContent).execute();
                        _redisDataListAdapter.notifyDataSetChanged();
                    }
                });
            }
        }, 0, 2000);//put here time 1000 milliseconds=1 second
        return root;
    }

}