package com.baseapp.framework.api;

/**
 * Created by wby on 2016/5/18.
 */
public class ApiManger {
    private static ApiManger apiManger;
    private static  ResfApi apiService;
    public static ApiManger getInstance() {
        if (apiManger == null) {
            synchronized (ApiManger.class) {
                if (apiManger == null) {
                    apiManger = new ApiManger();
                }
            }
        }
        return apiManger;
    }


    public ResfApi getResfApi(){

        synchronized (ApiManger.class) {
            if (apiService == null) {
                apiService =   RetrofitUtils.getInstance().create(ResfApi.class);
            }
        }
        return apiService;
    }

}
