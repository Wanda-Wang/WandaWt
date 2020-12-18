package com.example.login_register_f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_register_f.data.MyDBOpenHelper;
import com.example.login_register_f.data.SharedHelper;

public class EssayDetailActivity extends AppCompatActivity {
    TextView title,author,content;
    ImageButton add;
    String id,str_title,str_author,str_content,username,times;
    int rerow=0;

    private MyDBOpenHelper myDBHelper;
    private SQLiteDatabase db;
    SharedHelper sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essay_detail);


        myDBHelper = new MyDBOpenHelper(EssayDetailActivity.this, "my.db", null, 1);
        sh=new SharedHelper(getApplicationContext());
        db = myDBHelper.getWritableDatabase();

        init();
        initdata();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values= new ContentValues();
                values.put("TIMES", System.currentTimeMillis());
                values.put("USERNAME", username);
                values.put("ID",id);
                values.put("TITLE",str_title);
                values.put("CONTENT",str_content);
                values.put("AUTHOR",str_author);
                Log.i("TAG", "onClick: values = " + values.toString());
                db.execSQL("CREATE TABLE if not exists "+"user_table"+"(TIMES TEXT PRIMARY KEY,USERNAME TEXT,ID TEXT, TITLE TEXT, CONTENT TEXT, AUTHOR TEXT)");
                Cursor cursor =  db.query("user_table",null,"USERNAME=?",new String[]{sh.read_name().get("_username")},null,null,null);
                rerow=0;
                while(cursor.moveToNext())
                {
                    if(values.get("TITLE").equals(cursor.getString(cursor.getColumnIndex("TITLE")))){
                       rerow++;
                        Toast.makeText(EssayDetailActivity.this,"已收藏过",Toast.LENGTH_LONG).show();
                        break;
                    }
//                    else{
//
//                    }
                }
                if(rerow==0){
                    long rowid = db.insert("user_table",null,values);
                    Toast.makeText(EssayDetailActivity.this,"收藏成功",Toast.LENGTH_LONG).show();
                }



//                if(values.get("USERNAME").equals(cursor.getString(cursor.getColumnIndex("USERNAME"))))


               // db.execSQL("create table "+username+" (ID STRING PRIMARY KEY, TITLE STRING, CONTENT STRING, AUTHOR STRING)");
               // db.execSQL("INSERT INTO person(USERNAME,ID,TITLE,CONTENT,AUTHOR) values(username,id,str_title,str_content,str_author)");




            }


        });
       // db.close();
    }
    public void init(){

        title=findViewById(R.id.detail_title);
        author=findViewById(R.id.detail_author);
        content=findViewById(R.id.detail_content);
        add=findViewById(R.id.detail_add);
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
        times=System.currentTimeMillis()+"";
    }


}