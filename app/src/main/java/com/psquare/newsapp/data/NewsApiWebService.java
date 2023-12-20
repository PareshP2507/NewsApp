package com.psquare.newsapp.data;

import com.psquare.newsapp.domain.models.NewsResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiWebService {

    @GET("everything")
    Call<NewsResult> getNews(@Query("q") String query, @Query("apiKey") String apiKey);
}
