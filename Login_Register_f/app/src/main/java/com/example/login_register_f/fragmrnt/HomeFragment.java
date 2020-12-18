package com.example.login_register_f.fragmrnt;


import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.login_register_f.EssayDetailActivity;
import com.example.login_register_f.LoginActivity;
import com.example.login_register_f.MainActivity;
import com.example.login_register_f.R;
import com.example.login_register_f.adapter.EssayAdapter;
import com.example.login_register_f.bean.Essay;
import com.example.login_register_f.http.EssayHttp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomeFragment extends Fragment {
    TextView text;
    private List<Essay> mData;
    private Context mContext;
    private EssayAdapter mAdapter;
    private ListView list_essay;
    private EssayHttp essayHttp;
    CalThread ct;

    private static final String TAG = "Network";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_home,container,false);
        mContext=getActivity();
        inits(view);

        essayHttp=new EssayHttp();
        mData = new ArrayList<>();
        //mData.addAll(essayHttp.fetchEssay(10));
       new EssayTask(10).execute();
       //mData.add(new Essay("author","title","date"));
//        ct=new CalThread();
//
//        ct.start();
//        ct.mHandler.sendEmptyMessage(0x123);
        mAdapter = new EssayAdapter((ArrayList<Essay>) mData, mContext);
        list_essay.setAdapter(mAdapter);

        list_essay.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
                bundle.putString("date",mData.get(position).getPublishedAt());
                bundle.putString("title",mData.get(position).getTitle());
                bundle.putString("url",mData.get(position).getUrl());
                Intent intent=new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), EssayDetailActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



    class CalThread extends Thread{
        public Handler mHandler;
        public void run(){
            Looper.prepare();
            mHandler=new Handler(){

                @Override
                public void handleMessage(Message msg)
                {
                    if(msg.what == 0x123)
                    {
                        mData.addAll(essayHttp.fetchEssay(10));
                    }
                }
            };
            Looper.loop();
        }
    }
    public void inits(View view){
        text=view.findViewById(R.id.home);
        list_essay=view.findViewById(R.id.list_essay);
    }
    public void initdata(ArrayList<Essay> mData){

    }
    private class EssayTask extends AsyncTask<Void,Void, ArrayList<Essay>> {

        private int count;

        public EssayTask(int count) {
            this.count = count;
        }

        @Override
        protected ArrayList<Essay> doInBackground(Void... params) {
            return essayHttp.fetchEssay(count);
        }

        @Override
        protected void onPostExecute(ArrayList<Essay> essays) {
            super.onPostExecute(essays);
            mData.clear();
            mData.addAll(essays);
            mAdapter.notifyDataSetChanged();
            Log.e(TAG,"请求" );
        }
    }
}
