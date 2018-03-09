package com.example.daffolapmac.wealthbook.api;

import com.example.daffolapmac.wealthbook.screen.login.model.LoginRequest;
import com.example.daffolapmac.wealthbook.screen.login.model.LoginRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    /**
     * Relative login URL of the resource
     *
     * @param request Request body
     * @return Return the converted result of this login if converter is not assign then use ResponseBody class
     */
    @POST("auth/login")
    Call<LoginRes> login(@Body LoginRequest request);
}
