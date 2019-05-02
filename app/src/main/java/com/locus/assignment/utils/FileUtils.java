package com.locus.assignment.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sonu on 15:11, 2019-05-01
 * Copyright (c) 2019 . All rights reserved.
 */
public class FileUtils {
    private static final String SHARING_DIRECTORY_NAME = "Locus";

    //create file
    public static File createOutputImageFile(Context context) {
        File mediaStorageDir = getOutputMediaFile(context);
        if (mediaStorageDir == null) {
            return null;
        }
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".jpg");

    }

    public static File getOutputMediaFile(Context context) {
        File mediaStorageDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), SHARING_DIRECTORY_NAME);
        if (mediaStorageDir.exists() || !mediaStorageDir.mkdir()) {
            return mediaStorageDir;
        }
        return mediaStorageDir;
    }
}
