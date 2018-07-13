package com.example.shubham.githubassi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by shubham on 7/12/2018.
 */

public class SearchAdapter extends ArrayAdapter {
    ArrayList<ShowUsers> userDetails;
    LayoutInflater inflater;
    public SearchAdapter(@NonNull Context context, @NonNull  ArrayList<ShowUsers> userDetails) {
        super(context,0, userDetails);
        this.userDetails=userDetails;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return userDetails.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View output=convertView;
        if(output==null)
        {
            output=inflater.inflate(R.layout.show_user_row,parent,false);
            ImageView imageView;
            TextView textView;
            imageView=output.findViewById(R.id.imageView);
            textView=output.findViewById(R.id.textView);
            SearchViewHolder viewHolder=new SearchViewHolder(imageView,textView);
            output.setTag(viewHolder);
        }
        SearchViewHolder viewHolder= (SearchViewHolder) output.getTag();
        ShowUsers showUsers=userDetails.get(position);
        Picasso.get().load(showUsers.getUrl()).into(viewHolder.iv);
        viewHolder.tv.setText(showUsers.getTitle());
        return output;
    }
}
