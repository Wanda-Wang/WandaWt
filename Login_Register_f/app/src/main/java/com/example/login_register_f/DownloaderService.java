package com.example.login_register_f;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.login_register_f.data.SDFileHelper;
import com.example.login_register_f.fragmrnt.FindFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class DownloaderService extends Service {
    Context mContext;
    SDFileHelper sdf;
    String fileName;
    public DownloaderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!=null){
        Log.i("wanda","intent:"+intent);
            mContext=getBaseContext();
            String path=intent.getStringExtra("URL");
            sdf=new SDFileHelper();
            new Thread(new Runnable() {
                //            public Handler mHandler;
                @Override
                public void run() {


                    try{

                        URL url = new URL(path);
                        InputStream is = url.openStream();

//                    //截取最后的文件名
//                    String end = path.substring(path.lastIndexOf("."));
//                                    Log.i("wanda", "图片已保存到本地1");
//                    sdf.savaFileToSD("Cache_"+System.currentTimeMillis()+end,readin(is));
                        byte[] data = readin(is);
                        // 得到写入sdcard的文件名
                        fileName ="Cache_"+System.currentTimeMillis()+".jpg";
                        // 判断字节数组中是否有数据
                        if (data != null && data.length != 0) {
                            // 调用写入文件的方法，把字节数组写入文件中
                            boolean flag = SDFileHelper
                                    .writeExternalStorageRoot(fileName, data,
                                            DownloaderService.this);
                            if (flag) {
                                Log.i("wanda", "图片保存到sdcard成功");
                                // 调用发送通知的方法
                                sendNotification();
                            } else {
                                Log.i("wanda", "图片保存到sdcard失败");
                            }
                        } else {
                            Log.i("wanda", "图片下载失败");
                        }
                        //打开手机对应的输出流,输出到文件中
//                    OutputStream os = mContext.openFileOutput("Cache_"+System.currentTimeMillis()+end, Context.MODE_PRIVATE);
//                    byte[] buffer = new byte[1024];
//                    int len = 0;
//                    //从输入六中读取数据,读到缓冲区中
//                    while((len = is.read(buffer)) > 0)
//                    {
//                        os.write(buffer,0,len);
//                    }
//
//                    //关闭输入输出流
//                    is.close();
//                    os.close();
                        Log.i("wanda", "图片已保存到本地");
                        //Toast.makeText(getBaseContext(),"图片保存到sdcard成功",Toast.LENGTH_LONG).show();

                        // 调用发送通知的方法

                    }
                    catch(Exception ex){

                    }
//                Looper.prepare();
//                mHandler=new Handler(){
//                    @Override
//                    public void handleMessage(@NonNull Message msg) {
//                        if(msg.what==0x123){
//                            String path=msg.getData().getString("URL");
//                            try{
//                                URL url = new URL(path);
//                                InputStream is = url.openStream();
//                                //截取最后的文件名
//                                String end = path.substring(path.lastIndexOf("."));
//                                //打开手机对应的输出流,输出到文件中
//                                OutputStream os = mContext.openFileOutput("Cache_"+System.currentTimeMillis()+end, Context.MODE_PRIVATE);
//                                byte[] buffer = new byte[1024];
//                                int len = 0;
//                                //从输入六中读取数据,读到缓冲区中
//                                while((len = is.read(buffer)) > 0)
//                                {
//                                    os.write(buffer,0,len);
//                                }
//                                //关闭输入输出流
//                                is.close();
//                                os.close();
//                            }
//                            catch (Exception ex){
//
//                            }
//
//
//                        }
//                    }
//                };
//                Looper.loop();
                    // 停止服务
                    //stopSelf();
                    sendNotification();
                    Intent counterIntent = new Intent();
                    counterIntent.putExtra("counter", "counter");
                    counterIntent.putExtra("path",mContext.getFilesDir().toString());
                    IntentFilter intentFiltet = new IntentFilter();
                    //设置广播的名字（设置Action，可以添加多个要监听的动作）
                    intentFiltet.addAction("myBroadCastAction");
                    counterIntent.setAction("myBroadCastAction");
                    sendBroadcast(counterIntent);
                }



            }).start();
        }



//        Bundle bundle=new Bundle();
////        String path=intent.getStringExtra("URL");
//        bundle=intent.getExtras();
//        Message msg=new Message();
//        Log.i("wanda","msg:"+msg);
//        msg.what=0x123;
//        //bundle.putString("URL",urls.get(curPos));
////                bundle.putInt("msg",msg.what);
//        msg.setData(bundle);
//        Log.i("wanda","msg2:"+msg);
//        Log.i("wanda",mHandler.toString());
//        Log.i("wanda","msg3:"+msg);
//        st.mHandler.sendMessage(msg);

        return super.onStartCommand(intent, flags, startId);
    }

    public static byte[] readin(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                baos.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            baos.flush();
            return baos.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
    // 定义发送通知的方法

    private void sendNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(

                DownloaderService.this);

        // 设置通知栏标题

        builder.setContentTitle("图片下载");

        // 设置通知栏内容

        builder.setContentText("图片保存成功");

        // 设置图标

        builder.setSmallIcon(R.mipmap.add_fav);

        builder.setWhen(System.currentTimeMillis());

        // 定义一个Intent对象

        Intent intent = new Intent(DownloaderService.this, MainActivity.class);

        intent.putExtra("fileName", "fileName");

        // 定义延迟意图,发出去一点就执行

        PendingIntent pendingIntent = PendingIntent.getActivity(DownloaderService.this,

                1, intent, PendingIntent.FLAG_ONE_SHOT);

        // 将延迟意图给Builder构建器

        builder.setContentIntent(pendingIntent);

        // 获取通知管理器

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // 发送通知

        manager.notify(0, builder.build());

    }


}





