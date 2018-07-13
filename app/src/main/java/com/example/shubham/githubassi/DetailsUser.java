package com.example.shubham.githubassi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsUser extends AppCompatActivity {
Users user;
TextView tvUserName,tvName,tvCompany,tvEmail,tvNoOfRepositories;
ImageView imageView;
Button repo,follower,following;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_user);
        tvName=findViewById(R.id.textView2);
        tvUserName=findViewById(R.id.textView3);
        tvCompany=findViewById(R.id.textView4);
        tvEmail=findViewById(R.id.textView5);
        tvNoOfRepositories=findViewById(R.id.textView10);
        imageView =findViewById(R.id.imageView2);
        repo=findViewById(R.id.button2);
        following=findViewById(R.id.button3);
        follower=findViewById(R.id.button4);
        final Intent intent=getIntent();
        final String string=intent.getStringExtra(MainActivity.login);
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        GithubService service=retrofit.create(GithubService.class);
        Call<Users> call=service.User(string);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                user= response.body();
                Picasso.get().load(user.avatarUrl).into(imageView);
                tvName.setText(tvName.getText().toString()+user.name);
                tvUserName.setText(tvUserName.getText().toString()+user.login);
                tvCompany.setText(tvCompany.getText().toString()+user.company);
                tvEmail.setText(tvEmail.getText().toString()+user.email);
                tvNoOfRepositories.setText(tvNoOfRepositories.getText().toString()+user.noOfRepos);
                repo.setText(repo.getText().toString()+":"+user.noOfRepos);
                following.setText(following.getText().toString()+":"+user.following);
                follower.setText(follower.getText().toString()+":"+user.followers);
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
        repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DetailsUser.this,ShowRepositories.class);
                intent1.putExtra(MainActivity.login,string);
                startActivity(intent1);
            }
        });
        follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DetailsUser.this,ShowFollowers.class);
                intent1.putExtra(MainActivity.login,string);
                startActivity(intent1);
            }
        });
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DetailsUser.this,ShowFollowing.class);
                intent1.putExtra(MainActivity.login,string);
                startActivity(intent1);
            }
        });

    }
}
