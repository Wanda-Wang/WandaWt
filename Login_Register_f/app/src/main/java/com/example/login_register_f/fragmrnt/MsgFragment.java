package com.example.login_register_f.fragmrnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.example.login_register_f.R;

public class MsgFragment extends Fragment {
    TextView text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_message,container,false);
        inits(view);
        return view;
    }



    public void inits(View view){
        text=view.findViewById(R.id.msg);

    }

}
