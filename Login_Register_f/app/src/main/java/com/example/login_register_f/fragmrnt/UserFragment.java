package com.example.login_register_f.fragmrnt;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.login_register_f.EssayDetailActivity;
import com.example.login_register_f.FavEssayActivity;
import com.example.login_register_f.FavEssayItemActivity;
import com.example.login_register_f.LoginActivity;
import com.example.login_register_f.R;
import com.example.login_register_f.data.SharedHelper;
import com.example.login_register_f.UserMsgActivity;

import java.util.Iterator;
import java.util.Map;

public class UserFragment extends Fragment {
    TextView text_user_name,text_user_msg,text_user_fav,text_setting;
    Button btn_exit_login;

    SharedHelper sh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_user,container,false);
        Log.i("TAG", "onCreateView: ");
     //   Toast.makeText(getActivity(),"test",Toast.LENGTH_LONG).show();
        inits(view);
//        Intent intent_name=new Intent(getActivity(), FavEssayActivity.class);
//        intent_name.putExtra("username",text_user_name.getText().toString());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("TAG", "onActivityCreated: ");
        sh=new SharedHelper(getActivity().getApplicationContext());
        Log.i("test", "onActivityCreated: " + sh.read_name().get("_username"));
        if(!("".equals(sh.read_name().get("_username")))){
            text_user_name.setText(sh.read_name().get("_username"));
            Log.i("cgx",sh.read_name().get("_username"));
//            Intent name_intent=new Intent(getActivity(), EssayDetailActivity.class);
//            name_intent.putExtra("user_name_fav",sh.read_name().get("_username"));


        }
        else{
            text_user_name.setText("登录");
            text_user_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        btn_exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteKeyOfMap(sh.read_name());
                text_user_name.setText("登录");
                if(text_user_name.getText().toString()=="登录"){
                    text_user_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
        text_user_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), UserMsgActivity.class);
                startActivity(intent);
            }
        });
        text_user_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FavEssayItemActivity.class);
                startActivity(intent);
            }
        });
        }


    private static void deleteKeyOfMap(Map<String,String> paramsMap){
        Iterator<String> iter = paramsMap.keySet().iterator();
        while(iter.hasNext()){
            String key = iter.next();
            if(key.startsWith("_user")){
                iter.remove();
            }
        }
    }
    public String trans_rev(String str){
        Bundle bundle = getArguments();
        String result = bundle.getString(str);
        return result;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        btn_exit_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = getArguments();
//
////                if(bundle.containsKey("username")){
//                    //String user=bundle.getString("username");
//                    text_user_name.setText(bundle.getString("username"));
////                }
//            }
//        });
//
//    }

    public void inits(View view){
        text_user_name=view.findViewById(R.id.name);
        text_user_msg=view.findViewById(R.id.msg);
        text_user_fav=view.findViewById(R.id.fav);
        text_setting=view.findViewById(R.id.setting);
        btn_exit_login=view.findViewById(R.id.btn_exit);
    }


}
