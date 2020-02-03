package com.example.sciwizprojectmvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.sciwizprojectmvvm.Viewmodel.FilmListViewModel;
import com.example.sciwizprojectmvvm.adapters.FilimsAdapter;
import com.example.sciwizprojectmvvm.models.Result;
import com.example.sciwizprojectmvvm.util.Resource;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
//    FilmsDatabase filmsDatabase;

    private FilmListViewModel mFilimListViewmodel;
    private static final String TAG = "vw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.recyclerView);
        mFilimListViewmodel = ViewModelProviders.of(this).get(FilmListViewModel.class);

        subscribeObservers();
        testretrofit();



    }

    private void subscribeObservers(){

        mFilimListViewmodel.getRecipes().observe(this, new Observer<Resource<List<Result>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Result>> films) {

                if (films!=null){

                    Toast.makeText(MainActivity.this, "not null", Toast.LENGTH_SHORT).show();

//                    List<Result> resultlist = films;

                    /*for (int i = 0; i < resultlist.size(); i++) {
                        Result result = resultlist.get(i);
                        Log.d("vw", "onResponse: "+result.getTitle());
*/

                   /* for (Result result : films){

                        Log.d(TAG, "onChanged: "+result.getTitle());
                    }*/

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(new FilimsAdapter(films.data));
                }


                else {
                    Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void searchFilmsApi(){
        mFilimListViewmodel.searchRecipesApi();
    }

    private void testretrofit()
    {

        searchFilmsApi();
       /* FilimApiClient filimApiClient = ServiceGenerator.getFilimApiClient();
        Call<FilimsResponse> responseCall = filimApiClient.getfilims();
        responseCall.enqueue(new Callback<FilimsResponse>() {
            @Override
            public void onResponse(Call<FilimsResponse> call, Response<FilimsResponse> response) {
                int code=response.code();
                if (code==200){

                }

                Toast.makeText(MainActivity.this, "successfull", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onResponse: Server Response: " + response.toString());

                if (response.code() == 200) {
                    List<Result> resultList = response.body().getResults();

                    for (Result result:resultList){
                        Log.d(TAG, "onResponse:for each "+result.getTitle());
                    }

                   *//* for (int i = 5; i < resultList.size(); i++) {
                        Result resultt = resultList.get(i);
                        Log.d(TAG, "onResponse: for loop "+ resultt.getTitle());
                    }*//*


//                    Log.d(TAG, "onResponse: " + result.toString());
                } else {
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            @Override
            public void onFailure(Call<FilimsResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: ERROR: " + t.getMessage());

            }
        });*/


    }
}
