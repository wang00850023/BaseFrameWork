package com.baseapp.framework.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

;

/**
 * Created by wby on 2016/5/18.
 */
public class RetrofitUtils {
    private static  Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(ApiConfig.BASE_URL)
                            .client(OkHttpUtils.getInstance())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(FastJsonConverterFactory.create()).build();
                }
            }
        }
        return retrofit;
    }
}
