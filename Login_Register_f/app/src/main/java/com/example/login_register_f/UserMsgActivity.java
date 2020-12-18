package com.example.login_register_f;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.login_register_f.data.SharedHelper;

public class UserMsgActivity extends AppCompatActivity {

    SharedHelper sh;
    TextView fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_msg);
        sh=new SharedHelper(getApplicationContext());
        fav=findViewById(R.id.fav_u);
        fav.setText(sh.read(sh.read_name().get("_username")).get("autograph"+sh.read_name().get("_username")));
    }
}