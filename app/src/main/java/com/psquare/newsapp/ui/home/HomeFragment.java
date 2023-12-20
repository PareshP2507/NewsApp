package com.psquare.newsapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.psquare.newsapp.R;
import com.psquare.newsapp.databinding.FragmentHomeBinding;
import com.psquare.newsapp.ui.ResourceProvideImpl;
import com.psquare.newsapp.ui.common.ViewModelFactory;
import com.psquare.newsapp.ui.detail.ArticleDetailFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private final ArticleAdapter adapter = new ArticleAdapter(article -> {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, ArticleDetailFragment.newInstance(article))
                .addToBackStack(null)
                .commit();
    });

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
        setupObservers();
        viewModel.fetchNews();
    }

    private void initialize() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
        binding.recyclerviewNews.setAdapter(adapter);

        ViewModelFactory factory = new ViewModelFactory(ResourceProvideImpl.getInstance());
        viewModel = factory.create(HomeViewModel.class);
    }

    private void setupObservers() {
        viewModel.articles.observe(getViewLifecycleOwner(), adapter::submitList);
        viewModel.showLoader.observe(getViewLifecycleOwner(), show -> {
            if (show) {
                binding.loader.setVisibility(View.VISIBLE);
            } else {
                binding.loader.setVisibility(View.GONE);
            }
        });
    }
}
