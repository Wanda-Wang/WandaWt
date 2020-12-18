package com.example.login_register_f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.login_register_f.adapter.EssayAdapter;
import com.example.login_register_f.bean.Essay;
import com.example.login_register_f.data.MyDBOpenHelper;
import com.example.login_register_f.data.SharedHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FavEssayItemActivity extends AppCompatActivity {

    private List<Essay> mData;
    private Context mContext;
    private EssayAdapter mAdapter;
    private ListView list_fav_essay;
    private static final String TAG = "wanda";
//    ArrayList<Essay> fav_essay;
    private MyDBOpenHelper myDBHelper;
    private SQLiteDatabase db;
    SharedHelper sh;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_essay_item);
        inits();
        myDBHelper = new MyDBOpenHelper(FavEssayItemActivity.this, "my.db", null, 1);
        sh=new SharedHelper(getApplicationContext());
        db = myDBHelper.getWritableDatabase();
        username= sh.read_name().get("_username");
        mContext=FavEssayItemActivity.this;
        mData = new ArrayList<>();
        mData.addAll(parseEssay(db));

        //mData.add(new Essay("author","title","date"));
        //mAdapter.notifyDataSetChanged();
        //Log.i("wanda","收藏列表数据"+parseEssay(db).get(0).getAuthor());
        mAdapter = new EssayAdapter((ArrayList<Essay>) mData, mContext);
        mAdapter.notifyDataSetChanged();
        Log.i("wanda","list_fav_essay"+list_fav_essay+"mAdapter"+mAdapter);
        list_fav_essay.setAdapter(mAdapter);

        list_fav_essay.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
//                private String _id;
//                private String author;
//                private String desc;
//                private String publishedAt;
//                private String title;
//                private String url;

                bundle.putString("_id",mData.get(position).get_id());
                bundle.putString("author",mData.get(position).getAuthor());
                bundle.putString("desc",mData.get(position).getDesc());
                bundle.putString("title",mData.get(position).getTitle());
                Intent intent=new Intent();
                intent.putExtras(bundle);
                intent.setClass(FavEssayItemActivity.this, FavEssayActivity.class);
                startActivity(intent);
            }
        });
    }
    public void inits(){
        list_fav_essay=findViewById(R.id.list_fav_essay);

    }
    public ArrayList<Essay> parseEssay(SQLiteDatabase database) {
        ArrayList<Essay> fav_essays = new ArrayList<>();
        Cursor cursor =  db.query("user_table",null,"USERNAME=?",new String[]{sh.read_name().get("_username")},null,null,null);
        //存在数据才返回true
        if(!cursor.moveToFirst()){
            Essay essay = new Essay();
            essay.set_id("无");
            essay.setAuthor("无");
            essay.setDesc("无");
            essay.setTitle("无");
            fav_essays.add(essay);
        }
        else{
          do
            {
                Essay essay = new Essay();
                essay.set_id(cursor.getString(cursor.getColumnIndex("ID")));
                essay.setAuthor(cursor.getString(cursor.getColumnIndex("AUTHOR")));
                essay.setDesc(cursor.getString(cursor.getColumnIndex("CONTENT")));
                essay.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                fav_essays.add(essay);
            }  while(cursor.moveToNext());
        }
//
//            essay.set_id(cursor.getString(cursor.getColumnIndex("ID")));
//            essay.setAuthor(cursor.getString(cursor.getColumnIndex("AUTHOR")));
//            essay.setDesc(cursor.getString(cursor.getColumnIndex("CONTENT")));
//            essay.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
//            fav_essays.add(essay);


        return fav_essays;
    }
}