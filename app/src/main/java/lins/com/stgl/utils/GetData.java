package lins.com.stgl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData {
    //封装
    //networkonmainthreadexcepiton
    public static String getJsonData(String path) throws IOException{
        //get  post
        URL url=new URL(path);
        //connention 名词
        HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
        //connect 动词
        httpURLConnection.connect();
        //得到输入流
        InputStream inputStream=httpURLConnection.getInputStream();
        //任务：把流变成字符串
        byte[] b=new byte[300];
        int i=0;
        StringBuffer buffer=new StringBuffer();
        //1000 300  300  300 100

        //read  把流中的数据读到byte数组里  返回值是读的长度  -1：读到了流的最后
        while ((i=inputStream.read(b))!=-1) {
            buffer.append(new String(b, 0, i));
        }
        return buffer.toString();


    }
}
