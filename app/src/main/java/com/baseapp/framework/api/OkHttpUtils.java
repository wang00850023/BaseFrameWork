package com.baseapp.framework.api;

import okhttp3.OkHttpClient;

/**
 * Created by wby on 2016/5/18.
 * asd
 */
public class OkHttpUtils {
    private static OkHttpClient singleton;

    public static OkHttpClient getInstance() {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {
                    singleton = new OkHttpClient();

                }
            }
        }
        return singleton;
    }
}
