package com.psquare.newsapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.psquare.newsapp.domain.models.Article;
import com.psquare.newsapp.domain.models.NewsResult;
import com.psquare.newsapp.domain.usecases.GetNewsUseCase;
import com.psquare.newsapp.ui.common.Event;

import java.util.List;
import java.util.concurrent.ExecutorService;

import timber.log.Timber;

public class HomeViewModel extends ViewModel {

    private final GetNewsUseCase getNewsUseCase;
    private final ExecutorService networkExecutor;

    // Used to store/update articles
    private final MutableLiveData<List<Article>> _articles = new MutableLiveData<>();
    LiveData<List<Article>> articles = _articles;

    // Used to show/hide loader
    private final MutableLiveData<Boolean> _showLoader = new MutableLiveData<>();
    LiveData<Boolean> showLoader = _showLoader;

    // Used to show/hide loader
    private final MutableLiveData<Event<String>> _showError = new MutableLiveData<>();
    LiveData<Event<String>> showError = _showError;

    private static final String QUERY = "bitcoin";
    private static final String API_KEY = "485ded9e818b43f8907e57bcf6a1505b";
    private static final String STATUS_OK = "ok";

    public HomeViewModel(GetNewsUseCase getNewsUseCase, ExecutorService networkExecutor) {
        this.getNewsUseCase = getNewsUseCase;
        this.networkExecutor = networkExecutor;
    }

    public void fetchNews() {
        networkExecutor.execute(() -> {
            try {
                _showLoader.postValue(true);
                NewsResult result = getNewsUseCase.execute(QUERY, API_KEY);
                if (result.getStatus().equals(STATUS_OK)) {
                    _articles.postValue(result.getArticles());
                }
                _showLoader.postValue(false);
            } catch (Exception e) {
                _showLoader.postValue(false);
                Timber.e(e, "Unable to retrieve news");
                _showError.postValue(new Event<>(e.getMessage()));
            }
        });
    }
}
