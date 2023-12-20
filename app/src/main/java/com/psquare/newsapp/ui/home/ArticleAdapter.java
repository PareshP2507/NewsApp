package com.psquare.newsapp.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.psquare.newsapp.R;
import com.psquare.newsapp.databinding.RowArticleBinding;
import com.psquare.newsapp.domain.models.Article;

public class ArticleAdapter extends ListAdapter<Article, ArticleAdapter.ArticleViewHolder> {

    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Article article);
    }

    public ArticleAdapter(OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowArticleBinding binding = RowArticleBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ArticleViewHolder(binding, position -> onItemClickListener.onItemClick(getItem(position)));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static final DiffUtil.ItemCallback<Article> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Article>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull Article oldArticle, @NonNull Article newArticle) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldArticle.equals(newArticle);
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull Article oldArticle, @NonNull Article newArticle) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldArticle.equals(newArticle);
                }
            };

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        RowArticleBinding binding;

        public ArticleViewHolder(
                @NonNull RowArticleBinding binding,
                OnArticleClickListener onArticleClickListener
        ) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.container.setOnClickListener(view -> {
                onArticleClickListener.onArticleClick(getBindingAdapterPosition());
            });
        }

        public void bind(Article article) {
            binding.title.setText(article.getTitle());
            binding.description.setText(article.getContent());
            binding.published.setText(
                    binding.published.getContext().getString(
                            R.string.published_at,
                            article.getPublishedAt()
                    )
            );
            Glide.with(binding.image)
                    .load(article.getUrlToImage())
                    .placeholder(R.drawable.image_placeholder)
                    .centerCrop()
                    .into(binding.image);
        }

        interface OnArticleClickListener {
            void onArticleClick(int position);
        }
    }
}
