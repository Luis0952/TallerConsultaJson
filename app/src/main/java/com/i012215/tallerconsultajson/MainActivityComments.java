package com.i012215.tallerconsultajson;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i012215.tallerconsultajson.Adapters.AdapterComments;
import com.i012215.tallerconsultajson.Adapters.AdapterPosts;
import com.i012215.tallerconsultajson.HttpManager.Connection;
import com.i012215.tallerconsultajson.Models.ModelComments;
import com.i012215.tallerconsultajson.Models.ModelPosts;
import com.i012215.tallerconsultajson.Parser.JsonComments;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivityComments extends AppCompatActivity {

    ProgressBar progressBarComments;
    RecyclerView recyclerViewComments;
    List<ModelComments> CommentModelList;
    AdapterComments adapterComments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_comments);

        progressBarComments = (ProgressBar) findViewById(R.id.id_pb_item_comments);
        recyclerViewComments = (RecyclerView) findViewById(R.id.id_rv_item_comments);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewComments.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("postId")));

    }

    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    //
    public void loadData(String postId){
        if (isOnLine()){
            TaskComment taskAlbum = new TaskComment();
            taskAlbum.execute("https://jsonplaceholder.typicode.com/comments="+postId);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }
//

    public void processData(){
        adapterComments = new AdapterComments(CommentModelList, getApplicationContext());
        recyclerViewComments.setAdapter(adapterComments);
    }

    //
    public class TaskComment extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarComments.setVisibility(View.VISIBLE);
        }

        //
        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = Connection.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        //
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        //
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                CommentModelList = JsonComments.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarComments.setVisibility(View.GONE);
        }
    }
    }
