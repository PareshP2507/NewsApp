package com.psquare.newsapp.ui.common;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.psquare.newsapp.ui.ResourceProvider;
import com.psquare.newsapp.ui.home.HomeViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ResourceProvider resourceProvider;

    public ViewModelFactory(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(
                    resourceProvider.getNewUseCase(),
                    resourceProvider.getNetworkExecutor()
            );
        }
        throw new IllegalArgumentException("No such ViewModel available for " + modelClass);
    }
}
