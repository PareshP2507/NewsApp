package com.psquare.newsapp.ui;

import com.psquare.newsapp.domain.sources.NewsDataSource;
import com.psquare.newsapp.domain.usecases.GetNewsUseCase;

import java.util.concurrent.ExecutorService;

public interface ResourceProvider {

    NewsDataSource getNewsDataSource();

    GetNewsUseCase getNewUseCase();

    ExecutorService getNetworkExecutor();
}
