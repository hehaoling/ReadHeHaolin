package com.example.administrator.readhehaolin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.readhehaolin.Person;
import com.google.gson.Gson;

import javax.net.ssl.SSLEngineResult;

public class MainActivity extends Activity
{
    private Button tojson;
    RequestQueue mQueue;
    StringRequest stringRequest;
    Gson gson;
    String str;
    private static BufferedWriter bw = null;
    private static File cacheFile;
    public static  void storeJsonData(Context context, String jsonData){
        try {
            cacheFile = new File(context.getFilesDir(),"jsonData1");
            bw = new BufferedWriter(new FileWriter(cacheFile));
            bw.newLine();;
            bw.write(jsonData);
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bw = null;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tojson = (Button)findViewById(R.id.tojson);
        gson = new Gson();

        mQueue = Volley.newRequestQueue(MainActivity.this);
        //http://10.19.20.12/upgrade/test.txt是测试使用的json数据
        stringRequest = new StringRequest("http://v.juhe.cn/joke/content/list.php?key=c037fdc5bbb9e640d6b5a701dcb11441 &page=1&pageSize=10&sort=asc&time=1418745237",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        System.out.println("response="+response);
                        Person person = gson.fromJson(response, Person.class);
                        System.out.println("reason ="+person.getReason());
                        System.out.println("-------------------------------------");
                        System.out.println("error_code ="+person.getError_code());
                        System.out.println("-------------------------------------");
                        List<data> dataList = person.getResult().getData();
                        System.out.println("data ="+dataList);
                        for (int i=0;i<dataList.size();i++)
                        {
                            data data = dataList.get(i);
                            String content = data.getContent();
                            System.out.println("content ="+content);
                            String hashid = data.getHashId();
                            System.out.println("hashid ="+hashid);
                            String unixtime = data.getUnixtime();
                            System.out.println("unixtime ="+unixtime);
                            String updatetime = data.getUpdatetime();
                            System.out.println("updatetime ="+updatetime);
                        }

                        storeJsonData(MainActivity.this,person.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("TAG", error.getMessage(), error);
                    }

                });

        tojson.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mQueue.add(stringRequest);
            }
        });
    }
}
