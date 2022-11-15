package com.sonderben.news.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sonderben.news.R;
import com.sonderben.news.ReadArticleActivity;
import com.sonderben.news.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    List<Article> articles;
    Activity activity;

    public MainAdapter (Activity activity){
        this.activity=activity;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i("ersen",articles.size()+"");
        holder.title.setText(articles.get(position).getTitle());
        holder.description.setText(articles.get(position).getDescription());
       if (articles.get(position).getUrlImage() != null && articles.get(position).getUrlImage().length()>4){
           Picasso.get()
                   .load(articles.get(position).getUrlImage())
                   .into(holder.img);
           holder.constraintLayout.setOnClickListener(x->{
               Intent intent=new Intent(activity, ReadArticleActivity.class);
               intent.putExtra("URL_IMG",articles.get(position).getUrl());
               activity.startActivity(intent);

           });
       }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, description;
        ImageView img;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            img = itemView.findViewById(R.id.img);
            constraintLayout = itemView.findViewById(R.id.main_layout_item);
        }
    }
}
