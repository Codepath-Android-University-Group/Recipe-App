Original App Design Project - README Template


# UltimateFoodMaker - Cook, Eat, Repeat.

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Be the ultimate chef by finding popular recipes matched to your specifications. Find out what most people are cooking, or find something unique to fit your taste. Create your own cookbook by storing recipes or writing your own!

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Food
- **Mobile:** Upload photos and recipes of your foods in real time. Implements camera functionality and real time interaction by being able to like other people's recipes.
- **Story:** The app aims to not only improve people's cooking skills, but also allow them to share with others. Enjoyable foods can be shared with each other to produce new recipes or master existing ones.
- **Market:** The audience is very large. It provides functionality for not only the masses of people who like to cook, but also small niche groups by implementing the ability of specified searches.
- **Habit:** An average user would open and use this app whenever they want to cook or get inspired, probably a few times a week. Users have the ability to both consume the information on the app as well as create new recipes to share with others.
- **Scope:** The cscope for this project is fairly defined. Data needs to be parsed in and displayed, as well as created. It also needs to implement a camera functionality and allow the liking of other users posts.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

- [X] Users can see a list of popular recipes
- [X] Users can click on a recipe title to display the full recipe
- [ ] Users can upload their own recipe with photos
- [X] Users can swipe to refresh a new listing of recipes
- [ ] Users can store their own recipes on device
- [ ] Users can search for a recipe by typing the meal name into a search box

**Optional Nice-to-have Stories**

- [ ] Users can see the location of posts
- [ ] Nearby restaurants which have the recipe you are looking for are displayed
- [ ] Users can watch the video version recipes
- [ ] Other basic information like rating, price and something else about such restaurants will be displayed

### App Walkthough GIF
Week 2:
<img src= "https://i.imgur.com/8G9lQW2.gif" width=250><br>

Week 1:
<img src= "https://i.imgur.com/JPXf9Ht.gif" width=250><br>



### 2. Screen Archetypes

* Stream
   * Users can see a list of popular recipes
* Detail
   * Users can click on a recipe title to display the full recipe
* Login
   * Users can login if they wish to post or like a recipe
* Create
    * User can capture the photo and post it
    * User can record the time they spend on this meal
    * User can rate the overall difficulty and record the most difficult part of this recipe
    * User can choose whether including the original image as a contrast
    * User can check their friends' feedback here if using the social media with *comments*.
* Search
    *  User can search for a recipe by typing the meal name into a search box
    *  User can search for a recipe by typing the meal main ingredient into a search box
    *  Log the recent search



### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home Feed
* Post a Recipe
* View Details of a recipe
* Search for recipe
* View and create personalized cookbooks

**Flow Navigation** (Screen to Screen)

* Stream Screen
   * Navigation to details of a recipe
   * Navigate to Search Screen when type in the search bar
* Login Screen
   * Navigate back to creation screen if trying to post a recipe
   * Navigate back to stream screen if trying to log in for general purposes
* Creation Screen
   * Navigation to personal cookbooks page
   * Views and allows the creation and editing of your own personalized cookbooks
   * Allow users share their own recipes by multi-choice quickly
* Create Screen
    * Here is a timeline of the User's past sharing.
    * Navigate to create a new one.
    * Navigate to Login Screen if they are not logged in
    * Navigate to detail page of their sharing. 
* Search Screen
    * Navigate to details of a recipe

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="https://i.imgur.com/3Ri1Bc2.png" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 

### Models

| Property     | Type            | Description                                 |
| ------------ | --------------- |:------------------------------------------- |
| objectId     | String          | unique id for the user post (default field) |
| author       | Pointer to User | Recipe Author                               |
| image        | File            | image that user posts                       |
| recipe  name | String          | recipe's name                               |
| description  | String          | recipe description                          |
| post date    | date-time       | the time recipe created                     |
| update date  | date-time       | the last time recipe updated                |
| rating       | Int             |                                             |
| ingredients  | String          | ingredients for recipe                      |
| minutes      | float           | Cook time in minutes                        |
| steps        | Array           | The recipe (steps are tab-separated).       |


### Networking
- 

| CRUD    | HTTP Verb | Example                          |
|:------- |:--------- |:-------------------------------- |
| Create  | POST      | Creating a new post              |
| Read    | GET       | Fetching posts for a user's feed |
| Update  | PUT       | Changing a user's profile image  |
| Delete  | DELETE    | Deleting a comment               |
| Login   | Post      | User login                       |
| Sign up | Post      | User sign up                     |
| Logout  | GET       | User logout                      |
|         |           |                                  |


- Home Feed Screen
    - (Read/GET) Query all posts with popular recipes
   
`ParseQuery<ParseObject> query =  ParseQuery.getQuery("Recipe");
query.whereEqualTo("rating", 10);
query.findInBackground(new FindCallback<ParseObject>() {
    public void done(List<ParseObject> repcipeList, ParseException e) {
        if (e == null) {
            Log.d("recipe", "Retrieved " + recipeList.size() + " recipes");
        } else {
            Log.d("recipe", "Error: " + e.getMessage());
        }
    }
});`

    - (Create/POST) Create a new like on a post
    - (Delete) Delete existing like
- Create Post Screen
    - (Create/POST) Create a new post object
    
   `ParseObject recipe = new ParseObject("Recipe");`
    `recipe.put("recipeName", "Margherita Pizza");`
    `recipe.put("description", "A Neapolitan pizza, made           with  San Marzano tomatoes, mozzarella cheese, fresh basil, salt, and extra-virgin olive oil.");`
    `recipe.put("ingredients", "4 cloves garlic,
    1 (14.5 ounce) can diced tomatoes,
    2 tablespoons olive oil,
    1 teaspoon granulated sugar,
    1 teaspoon balsamic vinegar,
    3/4 teaspoon salt,
    1/8 teaspoon freshly ground black pepper");`
    `recipe.put("steps", ArrayList<String> steps);`
    `recipe.saveInBackground();`
    
    - (Delete) Delete existing recipe
    
    `// After this, the recipeName field will be empty
    myObject.remove("recipeName");`
    `// Saves the field deletion to your Parse Server
    myObject.saveInBackground();`
    
- Profile Screen
    - (Read/GET) Query logged in user recipes
- Recipe Books Screen
    - (Read/GET) Query all recipes that user added
