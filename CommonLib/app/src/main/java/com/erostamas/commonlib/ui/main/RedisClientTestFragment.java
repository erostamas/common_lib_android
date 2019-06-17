package com.erostamas.commonlib.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.erostamas.commonlib.R;
import com.erostamas.common.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class RedisClientTestFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private View _rootView;
    private TextView _resultTextView;

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

        _resultTextView = (TextView)root.findViewById(R.id.redis_result);
        Button button= (Button)root.findViewById(R.id.refreshbutton);
        button.setOnClickListener(this);
        _rootView = root;
        return root;
    }

    @Override
    public void onClick(View v) {
        RedisClient redis = new RedisClient("192.168.1.247", "ledcontrol_process_data", _resultTextView);
        redis.execute();
    }
}