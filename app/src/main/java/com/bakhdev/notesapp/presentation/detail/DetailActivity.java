package com.bakhdev.notesapp.presentation.detail;

import android.os.Bundle;
import android.view.View;

import com.bakhdev.notesapp.databinding.ActivityDetailBinding;
import com.bakhdev.notesapp.presentation.base.BaseActivity;

public class DetailActivity extends BaseActivity<ActivityDetailBinding> {

    public static String TITLE_VALUE = "TITLE_VALUE";
    public static String DESC_VALUE = "DESC_VALUE";

    @Override
    protected ActivityDetailBinding setBinding() {
        return ActivityDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpToolbar();
        setData();
    }

    private void setUpToolbar() {
        binding.topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setData() {
        String title = getIntent().getStringExtra(TITLE_VALUE);
        String desc = getIntent().getStringExtra(DESC_VALUE);
        binding.titleTv.setText(title);
        binding.descTv.setText(desc);
    }
}