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

import com.i012215.tallerconsultajson.Adapters.AdapterUser;
import com.i012215.tallerconsultajson.HttpManager.Connection;
import com.i012215.tallerconsultajson.Models.ModelUser;
import com.i012215.tallerconsultajson.Parser.JsonUser;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBarUser;
    RecyclerView recyclerViewUser;
    List<ModelUser> userModelList;
    AdapterUser userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarUser=(ProgressBar) findViewById(R.id.id_pb_item_user);
        recyclerViewUser=(RecyclerView) findViewById(R.id.id_rv_item_user);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewUser.setLayoutManager(linearLayoutManager);

        loadData();
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


    private void loadData() {
        if (isOnLine()){
            TaskUser taskAlbum = new TaskUser();
            taskAlbum.execute("https://jsonplaceholder.typicode.com/users");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData(){
        userAdapter = new AdapterUser(userModelList, getApplicationContext());
        recyclerViewUser.setAdapter(userAdapter);
    }

    public class TaskUser extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarUser.setVisibility(View.VISIBLE);
        }

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

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                userModelList = JsonUser.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBarUser.setVisibility(View.GONE);
        }
        }
}
