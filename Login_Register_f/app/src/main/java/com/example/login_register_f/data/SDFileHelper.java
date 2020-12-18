package com.example.login_register_f.data;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SDFileHelper {

    private static final String TAG = "SDFileHlelper";
    private Context context;

    public SDFileHelper() {
    }

    public SDFileHelper(Context context) {
        super();
        this.context = context;
    }

    // 判断sdCard是否可用
    public static boolean isSdcardUseful() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    // 存储内容
    public static boolean writeExternalStorageRoot(String name, byte[] data,
                                                   Context context) {
        if (isSdcardUseful()) {
            File sdFile = Environment.getExternalStorageDirectory();
            File file = new File(sdFile, name);
//            if (!file.exists()){
//                try {
//                    file.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }else {
//                Log.e(TAG,"文件不存在");
//            }
            try {
                Log.i("wanda", "图片已保存到本地7:");
                FileOutputStream fos = new FileOutputStream(file);
                Log.i("wanda", "图片已保存到本地6:");
                fos.write(data, 0, data.length);
                fos.flush();
                fos.close();
                // 返回true
                return true;
            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {
            Log.i("wanda", "sdCard不可用");
        }
        return false;
    }

    //往SD卡写入文件的方法
    public void savaFileToSD(String filename, byte[] filecontent) throws Exception {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i("wanda", "图片已保存到本地5:");
            Log.i("wanda", "图片已保存到本地4:");

            // 获取sdCard的根目录
            File sdFile = Environment.getExternalStorageDirectory();
            // 创建文件的抽象路径
            File file = new File(sdFile, filename);
            FileOutputStream output = new FileOutputStream(file);
            Log.i("wanda", "图片已保存到本地3:");
            output.write(filecontent);
            Log.i("wanda", "图片已保存到本地2:");
//            byte[] buffer = new byte[1024];
//            int len = 0;
            //从输入流中读取数据,读到缓冲区中
//            while((len = is.read(buffer)) > 0)
//            {
//                output.write(buffer,0,len);
//            }
            //将String字符串以字节流的形式写入到输出流中
            output.close();
//            is.close();

        } else Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
    }

    //读取SD卡中文件的方法
    //定义读取文件的方法:
    public String readFromSD(String filename) throws IOException {
        StringBuilder sb = new StringBuilder("");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            //打开文件输入流
            FileInputStream input = new FileInputStream(filename);
            byte[] temp = new byte[1024];

            int len = 0;
            //读取文件内容:
            while ((len = input.read(temp)) > 0) {
                sb.append(new String(temp, 0, len));
            }
            //关闭输入流
            input.close();
        }
        return sb.toString();
    }


}