package com.example.daffolapmac.wealthbook.api;

import com.example.daffolapmac.wealthbook.screen.login.model.LoginRequest;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRes;
import com.example.daffolapmac.wealthbook.screen.news.model.NewsRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * Relative login URL of the resource
     *
     * @param request Request body
     * @return Return the converted result of this login if converter is not assign then use ResponseBody class
     */
    @POST("auth/login")
    Call<LoginRes> login(@Body LoginRequest request);

    /**
     * Relative get news list URL of the resource
     *
     * @return Return list of news
     */
    @GET("news/all")
    Call<NewsRes> getNews(@Query("token") String token);
}
