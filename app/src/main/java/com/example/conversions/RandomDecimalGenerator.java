package com.example.conversions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RandomDecimalGenerator {

    @GET("api/v1.0/random?min=100&max=1000&count=1")
    Call<List<Post>> getRandom();


}
