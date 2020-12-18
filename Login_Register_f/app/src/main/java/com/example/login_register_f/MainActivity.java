package com.example.login_register_f;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.login_register_f.adapter.MyFragmentPagerAdapter;
import com.example.login_register_f.fragmrnt.FindFragment;
import com.example.login_register_f.fragmrnt.HomeFragment;
import com.example.login_register_f.fragmrnt.MsgFragment;
import com.example.login_register_f.fragmrnt.UserFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{
    //UI
   // private View find,home,message,user;

    private RadioGroup rg_tab_bar;
    private RadioButton rb_essay;
    private RadioButton rb_image;
    private RadioButton rb_user;
    private ViewPager vpager;
    private MyFragmentPagerAdapter mAdapter;

    //Fragment

    private FragmentManager fManager;

    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager=getSupportFragmentManager();
        mAdapter = new MyFragmentPagerAdapter(fManager);
        bindViews();
        rb_essay.setChecked(true);


    }







    private void bindViews() {
//        txt_home = (TextView) findViewById(R.id.txt_home);
//        txt_message = (TextView) findViewById(R.id.txt_message);
//        txt_find = (TextView) findViewById(R.id.txt_find);
//        txt_user = (TextView) findViewById(R.id.txt_user);
        rg_tab_bar = findViewById(R.id.rg_tab_bar);
        rb_essay=findViewById(R.id.rb_essay);
        rb_image =findViewById(R.id.rb_image);
        rb_user =  findViewById(R.id.rb_user);
        rg_tab_bar.setOnCheckedChangeListener(this);
       // ly_content = (FrameLayout) findViewById(R.id.ly_content);

        vpager = findViewById(R.id.ly_content);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
//        txt_home.setOnClickListener(this);
//        txt_message.setOnClickListener(this);
//        txt_find.setOnClickListener(this);
//        txt_user.setOnClickListener(this);

    }


    //隐藏所有Fragment



    //设置跳转回来选择哪个界面
//    public void back(int id) {
//        FragmentTransaction fTransaction = fManager.beginTransaction();
//        hideAllFragment(fTransaction);
//        switch (id){
//            case R.id.txt_home:
//                setSelected();
//                txt_home.setSelected(true);
//                if(hf == null){
//                    hf=new HomeFragment();
//                    fTransaction.add(R.id.ly_content,hf);
//
//                }else{
//                    fTransaction.show(hf);
//                }
//
//                break;
//            case R.id.txt_message:
//                setSelected();
//                txt_message.setSelected(true);
//                if(mf == null){
//                    mf=new MsgFragment();
//                    fTransaction.add(R.id.ly_content,mf);
//
//                }else{
//                    fTransaction.show(mf);
//                }
//
//                break;
//            case R.id.txt_find:
//                setSelected();
//                txt_find.setSelected(true);
//                if(ff == null){
//                    ff=new FindFragment();
//                    fTransaction.add(R.id.ly_content,ff);
//
//                }else{
//                    fTransaction.show(ff);
//                }
//                break;
//            case R.id.txt_user:
//                setSelected();
//                txt_user.setSelected(true);
//               // trans(uf,getIntent().getStringExtra("username"),"username");
//                if(uf == null){
//                    uf=new UserFragment();
//                    fTransaction.add(R.id.ly_content,uf);
//
//                }else{
//                    fTransaction.show(uf);
//                }
//
//                break;
//        }
//        fTransaction.commit();
//    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.rb_essay:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_image:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_user:
                vpager.setCurrentItem(PAGE_THREE);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_essay.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_image.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_user.setChecked(true);
                    break;

            }
        }
    }


}