package com.example.daffolapmac.wealthbook.api;

import com.example.daffolapmac.wealthbook.common.ConformationRes;
import com.example.daffolapmac.wealthbook.screen.forgotpassword.model.ForgotPasswordReq;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRequest;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRes;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginTroubleRes;
import com.example.daffolapmac.wealthbook.screen.news.model.NewsRes;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateRes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * Relative login URL of the resource
     * @param request Request body
     * @return Return the converted result of this login if converter is not assign then use ResponseBody class
     */
    @POST("auth/login")
    Call<LoginRes> login(@Body LoginRequest request);

    /**
     * Relative get news list URL of the resource
     * @return Return list of news
     */
    @GET("news/all")
    Call<NewsRes> getNews(@Query("token") String token);

    /**
     * Relative URL to send reset OTP to email
     * @param email Email where send OTP
     * @return Return confirmation message
     */
    @GET("auth/reset_password")
    Call<ConformationRes> emailOTP(@Query("email") String email);

    /**
     * Relative URL to change password
     * @param request Request body
     * @return Return Forgot password success or error response
     */
    @POST("auth/change_password")
    Call<ConformationRes> forgotPassword(@Body ForgotPasswordReq request);

    /**
     * Relative URL to login trouble
     * @return Return Email value for send email
     */
    @GET("wb/tangram_support")
    Call<LoginTroubleRes> loginTrouble();

    /**
     * Relative URL to get all update list
     * @return Return list of update
     */
    @GET("doc/all")
    Call<List<UpdateRes>> updateList(@Query("token") String token);
}
