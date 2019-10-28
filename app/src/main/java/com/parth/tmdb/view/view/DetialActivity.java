package com.parth.tmdb.view.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.parth.tmdb.R;
import com.parth.tmdb.databinding.ActivityDetialBinding;
import com.parth.tmdb.view.model.entity.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetialActivity extends AppCompatActivity {

    private Movie movie;

    private ActivityDetialBinding activityDetialBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityDetialBinding = DataBindingUtil.setContentView(this,R.layout.activity_detial);

        Intent intent = getIntent();

        if (intent.hasExtra("movie")) {

            movie = getIntent().getParcelableExtra("movie");

            activityDetialBinding.setMovie(movie);

            getSupportActionBar().setTitle(movie.getTitle());

        }

    }


}
