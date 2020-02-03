package com.example.sciwizprojectmvvm.Requests;





import com.example.sciwizprojectmvvm.util.LiveDataCallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static FilimApiClient filimApiClient = retrofit.create(FilimApiClient.class);

    public static FilimApiClient getFilimApiClient()
    {
        return filimApiClient;
    }
}
