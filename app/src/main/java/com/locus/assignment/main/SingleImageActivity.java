package com.locus.assignment.main;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.locus.assignment.R;
import com.locus.assignment.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sonu on 15:25, 2019-05-01
 * Copyright (c) 2019 . All rights reserved.
 */
public class SingleImageActivity extends AppCompatActivity {

    private static final String EXTRA_SINGLE_IMAGE = "image_path";

    public static void openSingleImageActivity(Context context, String image) {
        Intent intent = new Intent(context, SingleImageActivity.class);
        intent.putExtra(EXTRA_SINGLE_IMAGE, image);
        context.startActivity(intent);
    }

    @BindView(R.id.single_image_view)
    ImageView imageView;
    @BindView(R.id.image_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_image);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        loadImage();
    }

    //load image
    private void loadImage() {
        String imagePath = getIntent().getStringExtra(EXTRA_SINGLE_IMAGE);
        if (!TextUtils.isEmpty(imagePath)) {
            ImageUtils.loadImageUrl(this, imageView, Uri.parse(imagePath));
        } else {
            Toast.makeText(this, "Image is not valid.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
