package com.blanke.xgank.utils;

/**
 * Created by blanke on 16-5-31.
 */
public class ImgUtils {
    public static boolean isImg(String path) {
        return path.endsWith(".png") || path.endsWith(".jpg");
    }
}
