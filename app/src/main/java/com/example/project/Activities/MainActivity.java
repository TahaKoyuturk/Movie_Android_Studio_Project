package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.R;

public class MainActivity extends Activity {

    private EditText movie_name;
    private Button show_saved_data_btn;
    private Button show_data_btn;

    private String movieName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movie_name=findViewById(R.id.movie_input_text);
        show_data_btn=findViewById(R.id.show_data_btn);
        show_saved_data_btn=findViewById(R.id.show_saved_data_btn);

        show_saved_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateRecordedMovieActivity();
            }
        });
        show_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMovieNameToLocalDataSource(movie_name.getText().toString());
                navigateMovieListActivity();
            }
        });
        movieName=getMovieNameFromLocalDataSource();
        Log.e("moviename: ",movieName);

        if(movieName.length()>=0){
            navigateMovieListActivity();
        }
    }

    private void navigateRecordedMovieActivity(){
        Intent recordedMovieListIntent = new Intent(MainActivity.this,RecordedMovieActivity.class);
        startActivity(recordedMovieListIntent);
    }
    private void saveMovieNameToLocalDataSource(String movieName){
       this.movieName=movieName;

        String CONST_DATA = "MOVIE_NAME";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CONST_DATA,String.valueOf(movieName));
        editor.apply();
    }
    private String getMovieNameFromLocalDataSource()
    {
        String result;
        String CONST_DATA = "MOVIE_NAME";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        result = preferences.getString(CONST_DATA, "");
        Log.e("getmovie shared: ",result);

        return result;
    }
    private void navigateMovieListActivity()
    {
        Intent movieListIntent = new Intent(MainActivity.this,MovieListActivity.class);
        Log.e("intent bundle moviename",movieName);
        movieListIntent.putExtra("movie_name",movieName);
        startActivity(movieListIntent);
    }

}