package com.example.recipeapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeapp.DetailActivity;
import com.example.recipeapp.R;
import com.example.recipeapp.models.Recipe;

import org.parceler.Parcels;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    Context context;
    List<Recipe> recipes;

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("RecipeAdapter", "onCreateViewHolder");
        View recipeView = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(recipeView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("RecipeAdapter", "onBindViewHolder" + position);
        // Get the movie at the passed in position
        Recipe recipe = recipes.get(position);
        // Bind the movie data into the ViewHolder
        holder.bind(recipe);
    }
    // Clean all elements of the recycler
    public void clear() {
        recipes.clear();
        notifyDataSetChanged();
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivImage = itemView.findViewById(R.id.ivImage);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Recipe recipe) {
            tvTitle.setText(recipe.getTitle());
            tvOverview.setText(recipe.getInstructions());
            //String imageUrl;
            // if phone is in landscape
            // if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            // then imageUrl = back drop image
            //   imageUrl = movie.getBackdropPath();
            // } else {
            // else imageUrl = poster image
            //  imageUrl = movie.getPosterPath();
            //  }

            if(recipe.getImage() == null) {
                Glide.with(context).load(recipe.getPosterPath()).into(ivImage);
            }else{
                Glide.with(context).load(recipe.getImage()).into(ivImage);
            }

            // 1. Register click listener on the whole view
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("recipe", Parcels.wrap(recipe));
                    context.startActivity(i);
                }
            });
        }
    }
}

