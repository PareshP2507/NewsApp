package com.psquare.newsapp.domain.usecases;

import com.psquare.newsapp.domain.models.NewsResult;
import com.psquare.newsapp.domain.sources.NewsDataSource;

public class GetNewsUseCase {

    private final NewsDataSource newsDataSource;

    public GetNewsUseCase(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    public NewsResult execute(String query, String apiKey) throws Exception {
        return newsDataSource.getNews(query, apiKey);
    }
}
