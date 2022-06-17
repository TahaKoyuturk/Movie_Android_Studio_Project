package com.example.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Activities.MovieDetailActivity;
import com.example.project.Entity.Result;
import com.example.project.R;


import java.util.List;

public class MovieResultAdapter  extends RecyclerView.Adapter<MovieResultAdapter.ViewHolder>{
    private List<Result> movieResultList;
    private Result result;
    public MovieResultAdapter(List<Result> movieResultList){
        Log.e("adapter result",movieResultList.toString());
        this.movieResultList=movieResultList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        result = movieResultList.get(position);
        holder.title_textview.setText(result.getTitle());
        holder.type_textview.setText(result.getType());
        String imageURL=result.getPoster();
        Glide.with(holder.poster_image)
                    .load(imageURL)
                    .fitCenter()
                    .override(500,500)
                    .into(holder.poster_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateDetailActivity(v.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieResultList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster_image;
        private TextView title_textview;
        private TextView type_textview;

        public ViewHolder(View v) {
            super(v);

            title_textview = v.findViewById(R.id.title_textview);
            poster_image = v.findViewById(R.id.image_view);
            type_textview=v.findViewById(R.id.type_textview);
        }
    }

    private void navigateDetailActivity(Context context)
    {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("result",  result);
        context.startActivity(intent);
    }
}
