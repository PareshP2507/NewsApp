package com.psquare.newsapp.data;

import com.psquare.newsapp.domain.models.NewsResult;
import com.psquare.newsapp.domain.sources.NewsDataSource;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsDataSourceImpl implements NewsDataSource {

    private static final String BASE_URL = "https://newsapi.org/v2/";

    private final NewsApiWebService service = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiWebService.class);


    @Override
    public NewsResult getNews(String query, String apiKey) throws Exception {
        Response<NewsResult> response = service.getNews(query, apiKey).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception(response.message());
        }
    }
}
