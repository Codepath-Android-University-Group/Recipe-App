package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.recipeapp.models.Recipe;


import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends MainActivity {

    TextView tvTitle;
    TextView tvOverview;
    ImageView ivRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        ivRecipe = findViewById(R.id.ivRecipe);

        Recipe recipe = Parcels.unwrap(getIntent().getParcelableExtra("recipe"));
        tvTitle.setText(recipe.getTitle());
        tvOverview.setText(recipe.getInstructions());
        Glide.with(this).load(recipe.getPosterPath()).into(ivRecipe);

    }
}