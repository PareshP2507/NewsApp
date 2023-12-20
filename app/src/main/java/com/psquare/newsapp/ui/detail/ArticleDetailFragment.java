package com.psquare.newsapp.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.psquare.newsapp.R;
import com.psquare.newsapp.databinding.FragmentArticleDetailBinding;
import com.psquare.newsapp.domain.models.Article;

import java.util.Objects;

public class ArticleDetailFragment extends Fragment {

    private static final String EXTRA_ARTICLE = "extra_article";

    public static ArticleDetailFragment newInstance(Article article) {

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_ARTICLE, article);
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentArticleDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toolbar.setNavigationOnClickListener(view1 -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });
        bindArticle((Article) Objects.requireNonNull(requireArguments().getParcelable(EXTRA_ARTICLE)));
    }

    private void bindArticle(Article article) {
        binding.content.setText(article.getContent());
        binding.url.setText(article.getUrl());
        Glide.with(binding.image)
                .load(article.getUrlToImage())
                .placeholder(R.drawable.image_placeholder)
                .centerCrop()
                .into(binding.image);
    }
}
