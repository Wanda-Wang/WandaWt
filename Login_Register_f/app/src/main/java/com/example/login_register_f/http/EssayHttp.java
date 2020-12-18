package com.example.login_register_f.http;

import android.util.Log;

import com.example.login_register_f.bean.Essay;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class EssayHttp {
    private static final String TAG = "Network";
    private static final String BASE_URL = "https://gank.io/api/v2/data/category/GanHuo/type/Android/page/1/count/";

    /**
     * 查询信息
     */
    public  ArrayList<Essay> fetchEssay(int count) {
        String fetchUrl = BASE_URL + count;
        ArrayList<Essay> essays = new ArrayList<>();
        try {
            URL url = new URL(fetchUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            Log.v(TAG, "Server response：" + code);
            if (code == 200) {
                InputStream in = conn.getInputStream();
                byte[] data = readFromStream(in);
                String result = new String(data, "UTF-8");
                Log.v(TAG, "essays!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                essays = parseEssay(result);

            } else {
                Log.e(TAG,"请求失败：" + code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return essays;
    }

    /*
       private String _id;
            private String author;
            private String desc;
            private String publishedAt;
            private String title;
            private String url;
    */


    /**
     * 解析返回Json数据的方法
     */
    public ArrayList<Essay> parseEssay(String content) throws Exception {
        ArrayList<Essay> essays = new ArrayList<>();
        JSONObject object = new JSONObject(content);
        JSONArray array = object.getJSONArray("data");
        Log.v(TAG, "essays!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        for (int i = 0; i < array.length(); i++) {
            JSONObject results = (JSONObject) array.get(i);
            Essay essay = new Essay();
            essay.set_id(results.getString("_id"));
            essay.setAuthor(results.getString("author"));
            essay.setDesc(results.getString("desc"));
            essay.setPublishedAt(results.getString("publishedAt"));
            essay.setTitle(results.getString("title"));
            essay.setUrl(results.getString("url"));
            essays.add(essay);
        }
        Log.v(TAG, "essays"+essays.get(0).toString());
        return essays;
    }

    /**
     * 读取流中数据的方法
     */
    public byte[] readFromStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len ;
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }
}
