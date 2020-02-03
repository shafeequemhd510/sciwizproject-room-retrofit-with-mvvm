package com.example.sciwizprojectmvvm.Requests;



public class CheckRecipeApiKey {

    protected static boolean isRecipeApiKeyValid(FilimsResponse response){
        return response.getError() == null;
    }

  /*  protected static boolean isRecipeApiKeyValid(RecipeResponse response){
        return response.getError() == null;
    }*/
}
