package com.example.scolerday1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dell on 2017/8/2.
 */

public class MyFragment extends Fragment {

    private TextView textview;
    private View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.myfragment, container, false);
        textview = (TextView) inflate.findViewById(R.id.tv_fragment);
        Bundle arguments = getArguments();
        String text = arguments.getString("text");
        textview.setText(text);
        return inflate;
    }
}
