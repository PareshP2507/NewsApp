package com.psquare.newsapp.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.psquare.newsapp.databinding.ActivityMainBinding;
import com.psquare.newsapp.ui.home.HomeFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(), HomeFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
