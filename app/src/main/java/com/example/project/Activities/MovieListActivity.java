package com.example.project.Activities;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import com.example.project.Adapter.MovieResultAdapter;
import com.example.project.Entity.MovieResult;
import com.example.project.Entity.Result;
import com.example.project.R;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class MovieListActivity extends AppCompatActivity {

    private String movieName;
    private RecyclerView movie_list_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        movie_list_recyclerview = findViewById(R.id.movie_listt_recyclerview);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            System.out.println(movieName);
            movieName = bundle.getString("movie_name");
        }

        getMovieListFromNetwork(movieName);
    }
    private void getMovieListFromNetwork(String movieName)
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.collectapi.com/imdb/imdbSearchByName?query="+movieName)
                .method("GET", null)
                .addHeader("authorization", "apikey 5J0JozKS0Q5GpZ4Gi9VpLM:7tAZxCY61s818iw7RrxQug")
                .addHeader("content-type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("Fail: " , "Network Fail");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    Log.e("response =  ",response.message());
                    final String responseBody = response.body().string();
                    MovieResult movieResult = new Gson().fromJson(responseBody, MovieResult.class);
                    MovieListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setAdapterRecyclerView(movieResult.getResult());
                        }
                    });
                }
            }
        });
    }
    private void setAdapterRecyclerView(List<Result> resultList)
    {
        MovieResultAdapter adapter = new MovieResultAdapter(resultList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        movie_list_recyclerview.setLayoutManager(mLayoutManager);
        movie_list_recyclerview.setAdapter(adapter);

    }
}