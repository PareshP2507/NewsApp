package com.psquare.newsapp.ui.home;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.google.common.util.concurrent.MoreExecutors;
import com.psquare.newsapp.domain.models.Article;
import com.psquare.newsapp.domain.models.NewsResult;
import com.psquare.newsapp.domain.usecases.GetNewsUseCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@RunWith(JUnit4.class)
public class HomeViewModelTest {

    @Mock
    GetNewsUseCase getNewsUseCase;

    private final ExecutorService directExecutorService = MoreExecutors.newDirectExecutorService();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private HomeViewModel homeViewModel;

    @Before
    public void setUp() {
        homeViewModel = new HomeViewModel(getNewsUseCase, directExecutorService);
    }

    @Test
    public void fetchNewsShouldPostArticlesWhenCalledSuccessfully() throws Exception {
        NewsResult result = new NewsResult();
        result.setStatus("ok");
        result.setArticles(buildArticleList());

        // Given
        when(getNewsUseCase.execute(any(), any())).thenReturn(result);

        // When
        homeViewModel.fetchNews();

        // Then
        assertEquals(homeViewModel.articles.getValue(), result.getArticles());
    }

    @Test
    public void fetchNewsShouldPostErrorEventWhenExceptionIsThrown() throws Exception {
        // Given
        when(getNewsUseCase.execute(any(), any())).thenThrow(new Exception("Test"));

        // When
        homeViewModel.fetchNews();

        // Then
        String message = homeViewModel.showError.getValue().peekContent();
        assertNotNull(message);
        assertEquals(message, "Test");
    }

    private List<Article> buildArticleList() {
        List<Article> articles = new ArrayList<>();
        Article article1 = new Article();
        article1.setTitle("Title");
        article1.setUrlToImage("https://www.url.com");
        article1.setPublishedAt("Published at");
        article1.setContent("This is content");
        articles.add(article1);
        return articles;
    }
}