package com.zhenhui.apps.toptop.data.api;


import com.zhenhui.apps.toptop.data.api.request.Login;
import com.zhenhui.apps.toptop.data.api.request.SocialSignin;
import com.zhenhui.apps.toptop.model.Result;
import com.zhenhui.apps.toptop.model.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface UserService {

    @GET("user/test")
    Observable<Result<Boolean>> isUserExist(@Query("phone") String phone);

    @POST("auth/login")
    Observable<Result<String>> login(@Body Login login);

    @POST("auth/login/social")
    Observable<Result<String>> loginSocial(@Body SocialSignin socialSignin);

    @GET("user/me")
    Observable<Result<User>> getUserInfo();

}
