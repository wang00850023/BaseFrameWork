package com.baseapp.framework.api;

import com.baseapp.framework.bean.response.ResponseBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by wby on 2016/5/18.
 */
public interface  ResfApi {

    /**
     * @return Observable<GankDaily>
     */
    @GET(ApiConfig.HOME_ADD_LIST)
    Observable<ResponseBean> getDaily();

}
