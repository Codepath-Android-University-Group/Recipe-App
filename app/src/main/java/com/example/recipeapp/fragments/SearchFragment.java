package com.example.recipeapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.recipeapp.R;
import com.example.recipeapp.adapters.RecipeAdapter;
import com.example.recipeapp.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import okhttp3.Headers;


public class SearchFragment extends Fragment {

    public static final String TAG = "RecipesFragment";

    public static final String  NOW_PLAYING_URL = "";
    private RecyclerView rvSearchRecipes;
    protected RecipeAdapter searchAdapter;
    private List<Recipe> searchRecipes;
    private String url;
    private String query;
    private Button btnSearch;
    private EditText etSearch;


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSearchRecipes = view.findViewById(R.id.rvSearchRecipes);
        etSearch = view.findViewById(R.id.etSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        searchRecipes = new ArrayList<>();
        searchAdapter = new RecipeAdapter(getContext(), searchRecipes);
        rvSearchRecipes.setAdapter(searchAdapter);
        rvSearchRecipes.setLayoutManager(new LinearLayoutManager(getContext()));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 query = etSearch.getText().toString();
                if(query.isEmpty()){
                    Toast.makeText(getContext(), "Search cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                url = String.format("https://api.spoonacular.com/recipes/random?number=10&tags=%s&apiKey=c0b115df33704a4abfe201575c615944",query);
                queryPosts();
            }
        });

    }

    protected void queryPosts() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results =  jsonObject.getJSONArray("recipes");
                    Log.i(TAG, "Results" + results.toString());
                    searchRecipes.addAll(Recipe.fromJsonArray(results));
                    searchAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Recipes" + searchRecipes.size());
                } catch (JSONException e) {
                    Log.e(TAG, url);
                    Log.e(TAG, "Hit json exception", e);
                    e.printStackTrace();
                }
                query = "";
                url = "";
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    protected void populateHomeTimeline() {
        searchAdapter.clear();
        queryPosts();
    }
}