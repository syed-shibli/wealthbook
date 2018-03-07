package com.example.daffolapmac.wealthbook.api;

import com.example.daffolapmac.wealthbook.LoginRequest;
import com.example.daffolapmac.wealthbook.LoginRes;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
