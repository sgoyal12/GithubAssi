package com.example.shubham.githubassi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowFollowing extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<Users> users=new ArrayList<>();
    ListView listView;
    ProgressBar bar;
    ArrayList<ShowUsers> showUsers=new ArrayList<>();
    SearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_following);
        Intent intent=getIntent();
        listView=findViewById(R.id.ListView4);
        bar=findViewById(R.id.progressBar4);
        adapter=new SearchAdapter(this,showUsers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        String string=intent.getStringExtra(MainActivity.login);
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/").build();
        GithubService service=retrofit.create(GithubService.class);
        Call<ArrayList<Users>> call=service.followingUser(string);
        call.enqueue(new Callback<ArrayList<Users>>() {
            @Override
            public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                users.clear();
                showUsers.clear();
                users.addAll(response.body());
                for(int i=0;i<users.size();i++)
                {
                    ShowUsers user=new ShowUsers(users.get(i).login,users.get(i).avatarUrl);
                    showUsers.add(user);
                }
                adapter.notifyDataSetChanged();
                listView.setVisibility(View.VISIBLE);
                bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<Users>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,DetailsUser.class);
        intent.putExtra(MainActivity.login,showUsers.get(position).getTitle());
        startActivity(intent);
    }
}

