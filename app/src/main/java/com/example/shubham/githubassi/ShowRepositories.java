package com.example.shubham.githubassi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowRepositories extends AppCompatActivity {
ArrayList<Repositories> repos=new ArrayList<>();
ListView listView;
ProgressBar bar;
ArrayList<String> repoTitles=new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_repositories);
        Intent intent=getIntent();
        listView=findViewById(R.id.ListView2);
        bar=findViewById(R.id.progressBar2);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,repoTitles);
        listView.setAdapter(adapter);
        String string=intent.getStringExtra(MainActivity.login);
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/").build();
        GithubService service=retrofit.create(GithubService.class);
        Call<ArrayList<Repositories>> call=service.repoUser(string);
        call.enqueue(new Callback<ArrayList<Repositories>>() {
            @Override
            public void onResponse(Call<ArrayList<Repositories>> call, Response<ArrayList<Repositories>> response) {
                repos.clear();
                repoTitles.clear();
                repos.addAll(response.body());
                for(int i=0;i<repos.size();i++)
                {
                    repoTitles.add(repos.get(i).name);
                }
                adapter.notifyDataSetChanged();
                listView.setVisibility(View.VISIBLE);
                bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<Repositories>> call, Throwable t) {

            }
        });
    }
}
