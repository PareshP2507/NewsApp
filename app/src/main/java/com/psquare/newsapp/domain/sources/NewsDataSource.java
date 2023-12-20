package com.psquare.newsapp.domain.sources;

import com.psquare.newsapp.domain.models.NewsResult;

public interface NewsDataSource {

    NewsResult getNews(String query, String apiKey) throws Exception;
}
