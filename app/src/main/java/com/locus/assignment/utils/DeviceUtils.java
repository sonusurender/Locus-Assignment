package com.locus.assignment.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by sonu on 15:08, 2019-05-01
 * Copyright (c) 2019 . All rights reserved.
 */
public class DeviceUtils {
    //check if device support camera or not
    public static boolean isDeviceSupportCamera(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        }
        return false;
    }

}
