package com.locus.assignment.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;
import com.locus.assignment.glide.GlideApp;

/**
 * Created by sonu on 14:33, 2019-05-01
 * Copyright (c) 2019 . All rights reserved.
 */
public class ImageUtils {
    //display uri over imageview using glide
    public static void loadImageUrl(Context context, ImageView imageView, Uri imageUri) {
        GlideApp.with(context).load(imageUri)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .signature(new ObjectKey(System.currentTimeMillis()))
                .into(imageView);
    }

}
