package com.psquare.newsapp.ui;

import com.psquare.newsapp.data.NewsDataSourceImpl;
import com.psquare.newsapp.domain.sources.NewsDataSource;
import com.psquare.newsapp.domain.usecases.GetNewsUseCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResourceProvideImpl implements ResourceProvider {

    private static ResourceProvider provider;
    private static ExecutorService networkExecutor;
    private static NewsDataSource newsDataSource;
    private static GetNewsUseCase getNewsUseCase;

    public static ResourceProvider getInstance() {
        if (provider == null) {
            provider = new ResourceProvideImpl();
        }
        return provider;
    }
    @Override
    public NewsDataSource getNewsDataSource() {
        if (newsDataSource == null) {
            newsDataSource = new NewsDataSourceImpl();
        }
        return newsDataSource;
    }

    @Override
    public GetNewsUseCase getNewUseCase() {
        if (getNewsUseCase == null) {
            getNewsUseCase = new GetNewsUseCase(getNewsDataSource());
        }
        return getNewsUseCase;
    }

    @Override
    public ExecutorService getNetworkExecutor() {
        if (networkExecutor == null) {
            networkExecutor = Executors.newCachedThreadPool();
        }
        return networkExecutor;
    }
}
