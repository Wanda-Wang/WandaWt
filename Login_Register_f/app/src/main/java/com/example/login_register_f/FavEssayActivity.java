package com.example.login_register_f;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.login_register_f.data.MyDBOpenHelper;
import com.example.login_register_f.data.SharedHelper;

public class FavEssayActivity extends AppCompatActivity {

    TextView title,author,content;

    String id,str_title,str_author,str_content,username;
    private MyDBOpenHelper myDBHelper;
    private SQLiteDatabase db;
    SharedHelper sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_essay);

        myDBHelper = new MyDBOpenHelper(FavEssayActivity.this, "my.db", null, 1);
         sh=new SharedHelper(getApplicationContext());
        db = myDBHelper.getWritableDatabase();
        init();
        initdata();
//        username=getIntent().getStringExtra("username");

//        Cursor cursor =  db.query("user_"+sh.read_name().get("_username"),null,"USERNAME=?",new String[]{sh.read_name().get("_username")},null,null,null);
//        //存在数据才返回true
//        if(cursor.moveToFirst())
//        {
////            values.put("USERNAME",username);
////            values.put("ID",id);
////            values.put("TITLE",str_title);
////            values.put("CONTENT",str_content);
////            values.put("AUTHOR",str_author);
//            id = cursor.getString(cursor.getColumnIndex("ID"));
//            str_title = cursor.getString(cursor.getColumnIndex("TITLE"));
//            str_content= cursor.getString(cursor.getColumnIndex("CONTENT"));
//            str_author= cursor.getString(cursor.getColumnIndex("AUTHOR"));
//
//            Log.i("TAG", "onClick: values = " + id+str_title);
//        }
//
//        Log.i("TAG", "onClick: values = " + id+str_title+str_content+str_author);


//        if(!"".equals(str_title+str_content+str_author)){
//            Log.i("TAG", "onClick: values = " + id+str_title);
//        Log.i("TAG", "onClick: values id = " +title );
//        title.setText(str_title);
//        author.setText(str_author);
//        content.setText(str_content);
//        }




    }
    @Override
    protected void onStart() {
        super.onStart();
//        if(!"".equals(str_title+str_content+str_author)){
//            Log.i("TAG", "onClick: values = " + id+str_title);
//            title.setText(str_title);
//            author.setText(str_author);
//            content.setText(str_content);
//        }

    }
    public void init(){

        title=findViewById(R.id.fav_title);
        author=findViewById(R.id.fav_author);
        content=findViewById(R.id.fav_content);



//


    }
    public void initdata(){

        //        bundle.putString("_id",mData.get(position).get_id());
//        bundle.putString("author",mData.get(position).getAuthor());
//        bundle.putString("desc",mData.get(position).getDesc());
//        bundle.putString("date",mData.get(position).getPublishedAt());
//        bundle.putString("title",mData.get(position).getTitle());
//        bundle.putString("url",mData.get(position).getUrl());

        Bundle bundle=getIntent().getExtras();

        str_author=bundle.getString("author");
        str_content=bundle.getString("desc");
        id=bundle.getString("_id");
        str_title=bundle.getString("title");

        title.setText(str_title);
        author.setText(str_author);
        content.setText(str_content);
        username= sh.read_name().get("_username");
    }

}