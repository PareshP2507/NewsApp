package com.psquare.newsapp.domain.usecases;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.psquare.newsapp.domain.models.Article;
import com.psquare.newsapp.domain.models.NewsResult;
import com.psquare.newsapp.domain.sources.NewsDataSource;

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

@RunWith(JUnit4.class)
public class GetNewsUseCaseTest {

    private GetNewsUseCase getNewsUseCase;

    @Mock
    NewsDataSource newsDataSource;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        getNewsUseCase = new GetNewsUseCase(newsDataSource);
    }

    @Test
    public void testExecute() throws Exception {
        // Given
        NewsResult result = buildNewsResult();
        when(newsDataSource.getNews(any(), any())).thenReturn(result);

        // When
        getNewsUseCase.execute("", "");

        // Then
        assertEquals(result, getNewsUseCase.execute("", ""));
    }

    private NewsResult buildNewsResult() {
        NewsResult result = new NewsResult();
        result.setStatus("ok");
        result.setArticles(buildArticleList());
        return result;
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