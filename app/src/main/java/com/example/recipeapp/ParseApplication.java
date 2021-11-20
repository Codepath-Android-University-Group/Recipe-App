package com.example.recipeapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("gLwt11RN1jjwxigAJM241SML0qp9aJQHNVBrVP7p")
                .clientKey("w4HXQLXUt9OEL8gq4yPH6LhulsnCFTuM9bWbKH5k")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}