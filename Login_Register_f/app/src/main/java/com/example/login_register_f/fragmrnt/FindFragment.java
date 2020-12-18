package com.example.login_register_f.fragmrnt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.login_register_f.DownloaderService;
import com.example.login_register_f.MainActivity;
import com.example.login_register_f.R;
import com.example.login_register_f.bean.Sister;
import com.example.login_register_f.http.PictureLoader;
import com.example.login_register_f.http.SisterApi;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {
    private Button showBtn,saveBtn;
    private ImageView showImg;
    private ArrayList<String> urls;
    private int curPos = 0;
    private PictureLoader loader;
    Context mContext;

    CounterReceiver cr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_find,container,false);

//        sisterApi = new SisterApi();
//        loader = new PictureLoader();
        loader = new PictureLoader();
        initData();
        inits(view);
        loader.load(showImg, urls.get(curPos));
        SaveThread st=new SaveThread();
        st.start();
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curPos > 9) {
                    curPos = 0;
                }
                loader.load(showImg, urls.get(curPos));
                curPos++;
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String path=urls.get(curPos);
//
//                Message msg=new Message();
//                Log.i("wanda","msg:"+msg);
//                //msg.what=0x123;
//                Bundle bundle=new Bundle();
//                bundle.putString("URL",urls.get(curPos));
////                bundle.putInt("msg",msg.what);
//                //msg.setData(bundle);
//                Log.i("wanda","msg2:"+msg);
//                Log.i("wanda",st.mHandler.toString());
//                Log.i("wanda","msg3:"+msg);
               // st.mHandler.sendMessage(msg);
                Intent intent=new Intent(getActivity(), DownloaderService.class);
                if(curPos>0){
                    intent.putExtra("URL",urls.get(curPos-1));
                }
                else{
                    intent.putExtra("URL",urls.get(curPos));
                }

                getActivity().startService(intent);
                IntentFilter intentFiltet = new IntentFilter();
                //设置广播的名字（设置Action，可以添加多个要监听的动作）
                intentFiltet.addAction("myBroadCastAction");
                //注册广播,传入两个参数， 实例化的广播接受者对象，intent 动作筛选对象
                mContext.registerReceiver(cr,intentFiltet);



//                try{
//
//                    URL url = new URL(path);
//                    InputStream is = url.openStream();
//                    //截取最后的文件名
//                    String end = path.substring(path.lastIndexOf("."));
//                    //打开手机对应的输出流,输出到文件中
//                    OutputStream os = mContext.openFileOutput("Cache_"+System.currentTimeMillis()+end, Context.MODE_PRIVATE);
//                    byte[] buffer = new byte[1024];
//                    int len = 0;
//                    //从输入六中读取数据,读到缓冲区中
//                    while((len = is.read(buffer)) > 0)
//                    {
//                        os.write(buffer,0,len);
//                    }
//                    //关闭输入输出流
//                    is.close();
//                    os.close();
//                }
//                catch(Exception ex){
//
//                }



            }
        });
//        refreshBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sisterTask = new SisterTask();
//                sisterTask.execute();
//                curPos = 0;
//            }
//        });
        return view;
    }

    public class SaveThread extends Thread{
        public Handler mHandler;
        public void run(){
            Looper.prepare();
            mHandler=new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    if(msg.what==0x123){
                        String path=msg.getData().getString("URL");
                        try{
                            URL url = new URL(path);
                            InputStream is = url.openStream();
                            //截取最后的文件名
                            String end = path.substring(path.lastIndexOf("."));
                            //打开手机对应的输出流,输出到文件中
                            OutputStream os = mContext.openFileOutput("Cache_"+System.currentTimeMillis()+end, Context.MODE_PRIVATE);
                            byte[] buffer = new byte[1024];
                            int len = 0;
                            //从输入六中读取数据,读到缓冲区中
                            while((len = is.read(buffer)) > 0)
                            {
                                os.write(buffer,0,len);
                            }
                            //关闭输入输出流
                            is.close();
                            os.close();
                        }
                        catch (Exception ex){

                        }


                    }
                }
            };
            Looper.loop();
        }
    }

    class CounterReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            Toast.makeText(mContext,"图片保存成功,已保存到："+intent.getStringExtra("path")+"目录下",Toast.LENGTH_LONG).show();
        }
    }
    private void initData() {
        mContext=getActivity();
        cr=new CounterReceiver();
        urls = new ArrayList<>();
        urls.add("http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f6gcxc1t7vj20hs0hsgo1.jpg");
        urls.add("http://ww4.sinaimg.cn/large/610dc034jw1f6f5ktcyk0j20u011hacg.jpg");
        urls.add("http://ww1.sinaimg.cn/large/610dc034jw1f6e1f1qmg3j20u00u0djp.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f6aipo68yvj20qo0qoaee.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f69c9e22xjj20u011hjuu.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f689lmaf7qj20u00u00v7.jpg");
        urls.add("http://ww3.sinaimg.cn/large/c85e4a5cjw1f671i8gt1rj20vy0vydsz.jpg");
        urls.add("http://ww2.sinaimg.cn/large/610dc034jw1f65f0oqodoj20qo0hntc9.jpg");
        urls.add("http://ww2.sinaimg.cn/large/c85e4a5cgw1f62hzfvzwwj20hs0qogpo.jpg");
    }

    public void inits(View view){
        showBtn = view.findViewById(R.id.btn_show);
        saveBtn=view.findViewById(R.id.btn_save);
        //refreshBtn = view.findViewById(R.id.btn_refresh);
        showImg = view.findViewById(R.id.img_show);

    }

//    private class SisterTask extends AsyncTask<Void,Void,ArrayList<Sister>> {
//
//        public SisterTask() { }
//
//        @Override
//        protected ArrayList<Sister> doInBackground(Void... params) {
//            return sisterApi.fetchSister(10,page);
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Sister> sisters) {
//            super.onPostExecute(sisters);
//            data.clear();
//            data.addAll(sisters);
//            page++;
//        }
//
//        @Override
//        protected void onCancelled() {
//            super.onCancelled();
//            sisterTask = null;
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        sisterTask.cancel(true);
//    }


    @Override
    public void onDestroyView() {


        Intent intent=new Intent();
        intent.setAction("myBroadCastAction");
        PackageManager pm=mContext.getPackageManager();
        List<ResolveInfo> resolveInfos=pm.queryBroadcastReceivers(intent,0);
        if(resolveInfos!=null&&!resolveInfos.isEmpty()){
            mContext.unregisterReceiver(cr);
        }

        super.onDestroyView();
    }

//    @Override
//    public void onDestroy() {
//        mContext.unregisterReceiver(cr);
//        super.onDestroy();
//    }
}
