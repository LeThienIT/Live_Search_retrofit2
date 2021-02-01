package com.android.livesearch.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.livesearch.R;
import com.android.livesearch.adapter.Adapter_LiveSearch;
import com.android.livesearch.api.ApiClient;
import com.android.livesearch.api.ApiInterface;
import com.android.livesearch.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter_LiveSearch adapter;
    private List<Users> users;
    private ApiInterface apiInterface;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
        fetchUsers(""); // without keywords
    }

    private void mapping() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress);
        layoutManager = new LinearLayoutManager(this);
    }

    private void init() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public void fetchUsers(String key){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Users>> call = apiInterface.getUsers(key);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                progressBar.setVisibility(View.GONE);
                users = response.body();
                adapter = new Adapter_LiveSearch(users, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}