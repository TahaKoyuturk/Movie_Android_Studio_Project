package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project.Entity.Result;
import com.example.project.R;

import org.w3c.dom.Text;

public class MovieDetailActivity extends AppCompatActivity {

    private Result result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView poster_textview = findViewById(R.id.poster_textview);
        TextView title_textview = findViewById(R.id.title_textview);
        TextView year_textview = findViewById(R.id.year_textview);
        TextView type_textview = findViewById(R.id.type_textview);
        AppCompatButton saveButton = findViewById(R.id.save_button);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            result = bundle.getParcelable("result");
            String imageURL=result.getPoster();
            Glide.with(poster_textview)
                    .load(imageURL)
                    .centerCrop()
                    .fitCenter()
                    .override(1000,1000)
                    .into(poster_textview);
            title_textview.setText(result.getTitle());
            year_textview.setText(result.getYear());
            type_textview.setText(result.getType());
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }
    private void saveData()
    {

    }
}