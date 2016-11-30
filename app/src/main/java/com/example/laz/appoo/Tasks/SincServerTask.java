package com.example.laz.appoo.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.laz.appoo.Student;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by laz on 30/11/16.
 */

public class SincServerTask extends AsyncTask<String, String, String> {

    private String URL;
    private ArrayList<Student> students;

    public SincServerTask(String url, ArrayList<Student> students) {
        this.URL = url;
        this.students = students;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        try {

            Gson gson = new Gson();
            String jsonStudents = gson.toJson(students);

            OkHttpClient okHttp = new OkHttpClient();
            Request.Builder builder = new Request.Builder();

            builder.url(this.URL);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(mediaType, jsonStudents);

            builder.post(body);

            Request request = builder.build();

            Response response = okHttp.newCall(request).execute();

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return e.getMessage();

        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        //Update the UI
    }
}

