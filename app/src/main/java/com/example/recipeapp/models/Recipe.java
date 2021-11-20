package com.example.recipeapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



@Parcel
public class Recipe {

    int recipeId;
    String image;
    String title;
    String instructions;
    int readyInMinutes;
    File file;

    // empty constructor needed by the Parceler library
    public Recipe() {}

    public Recipe(JSONObject jsonObject) throws JSONException {
        //backdropPath = jsonObject.getString("backdrop_path");
        image = jsonObject.getString("image");
        title = jsonObject.getString("title");
        String unformattedInstructions = jsonObject.getString("instructions");
        instructions = unformattedInstructions.replaceAll("<.*?>", "");
        instructions = instructions.replaceAll("&amp", "");
        readyInMinutes = jsonObject.getInt("readyInMinutes");
        recipeId = jsonObject.getInt("id");
    }

    public static List<Recipe> fromJsonArray(JSONArray recipeJsonArray) throws JSONException {
        List<Recipe> recipes = new ArrayList<>();
        for (int i = 0; i < recipeJsonArray.length(); i++){
            recipes.add(new Recipe(recipeJsonArray.getJSONObject(i)));
        }
        return recipes;
    }

    public String getPosterPath() {
        return image;
    }

    //public String getBackdropPath() {
    //   return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    // }


    public File getImage() {
        return file;
    }
    public void setImage(File parseFile) {
        this.file = parseFile;
    }
    public String getTitle() {
        return title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setDescription(String description){
        this.title = description;
    }

    public void setInstructions(String instructions){
        this.instructions = instructions;
    }

    public double getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getRecipeId() {
        return recipeId;
    }
}
