package com.i012215.tallerconsultajson;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.i012215.tallerconsultajson.Adapters.AdapterPosts;
import com.i012215.tallerconsultajson.HttpManager.Connection;
import com.i012215.tallerconsultajson.Models.ModelPosts;
import com.i012215.tallerconsultajson.Parser.JsonPosts;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivityPost extends AppCompatActivity {

    ProgressBar progressBarPost;
    RecyclerView recyclerViewPost;
    List<ModelPosts> PostModelList;
    AdapterPosts adapterPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_post);

        progressBarPost = (ProgressBar) findViewById(R.id.id_pb_item_posts);
        recyclerViewPost = (RecyclerView) findViewById(R.id.id_rv_item_posts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPost.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("userId")));
    }

    public Boolean isOnLine() {
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }


    private void loadData(String userId) {

        if (isOnLine()) {
            TaskPost taskPhoto = new TaskPost();
            taskPhoto.execute("https://jsonplaceholder.typicode.com/posts=" + userId);
        } else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }


    public void processData() {
        adapterPosts = new AdapterPosts(PostModelList, getApplicationContext());
        recyclerViewPost.setAdapter(adapterPosts);
    }

    public class TaskPost extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarPost.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = Connection.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                PostModelList = JsonPosts.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarPost.setVisibility(View.GONE);
        }
    }
}