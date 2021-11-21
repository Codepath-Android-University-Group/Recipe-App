package com.example.recipeapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recipeapp.R;
import com.example.recipeapp.adapters.RecipeAdapter;
import com.example.recipeapp.models.Recipe;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class RecipeBookFragment extends Fragment {
    private RecyclerView rvBookRecipes;
    protected RecipeAdapter bookAdapter;
    private List<Recipe> bookRecipes;
    private Recipe recipe;

    public RecipeBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bookRecipes = new ArrayList<>();

        return inflater.inflate(R.layout.fragment_recipe_book, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null) {
            recipe = (Recipe) Parcels.unwrap(getArguments().getParcelable("Add Recipe"));
            bookRecipes.add(recipe);
        }
         rvBookRecipes = view.findViewById(R.id.rvBookRecipes);
        bookAdapter = new RecipeAdapter(getContext(), bookRecipes);
        rvBookRecipes.setAdapter(bookAdapter);
        rvBookRecipes.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}