package com.example.shubham.githubassi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView listView;
ProgressBar progressBar;
ImageButton search;
EditText searchText;
public final static String login="username";
ArrayList<Users> users=new ArrayList<>();
ArrayList<ShowUsers> showUsers=new ArrayList<>();
SearchAdapter adapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.ListView);
        progressBar=findViewById(R.id.progressBar);
        search=findViewById(R.id.imageButton);
        searchText=findViewById(R.id.editText);
        adapter=new SearchAdapter(this,showUsers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String search=searchText.getText().toString();
                if(search!=null)
                {
                    listView.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    Retrofit.Builder builder=new Retrofit.Builder()
                            .baseUrl("https://api.github.com/")
                            .addConverterFactory(GsonConverterFactory.create());
                    Retrofit retrofit=builder.build();
                    GithubService githubService=retrofit.create(GithubService.class);
                    Call<SearchUsers> call=githubService.searchUsers(search);
                    call.enqueue(new Callback<SearchUsers>() {
                        @Override
                        public void onResponse(Call<SearchUsers> call, Response<SearchUsers> response) {
                            SearchUsers searchUsers =response.body();
                            users.clear();
                            showUsers.clear();
                            users.addAll(searchUsers.items);
                            for(int i=0;i<users.size();i++)
                            {
                                ShowUsers user=new ShowUsers(users.get(i).login,users.get(i).avatarUrl);
                                showUsers.add(user);
                            }
                            adapter.notifyDataSetChanged();
                            if(users.size()==0)
                                Toast.makeText(MainActivity.this,"No Result Found",Toast.LENGTH_SHORT).show();
                            listView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onFailure(Call<SearchUsers> call, Throwable t) {

                        }
                    });

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Please Enter Text",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(MainActivity.this,DetailsUser.class);
        intent.putExtra(login,showUsers.get(position).getTitle());
        startActivity(intent);
    }
}
