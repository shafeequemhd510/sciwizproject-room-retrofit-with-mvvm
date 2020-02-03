package com.example.sciwizprojectmvvm.Requests;

import androidx.lifecycle.LiveData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilimApiClient {

 // SEARCH
 /*@GET("api/search")
 LiveData<ApiResponse<RecipeSearchResponse>> searchRecipe(
         @Query("key") String key,
         @Query("q") String query,
         @Query("page") String page
 );*/

 @GET("films")
 LiveData<ApiResponse<FilimsResponse>> getfilims();

 // GET RECIPE REQUEST
/* @GET("api/get")
 LiveData<ApiResponse<RecipeResponse>> getRecipe(
         @Query("key") String key,
         @Query("rId") String recipe_id
 );*/
}
