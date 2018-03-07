package com.example.daffolapmac.wealthbook.api;

import com.example.daffolapmac.wealthbook.utils.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit mRetrofit;
    private static ApiService mApiService;

    /**
     * Get api retrofit object
     *
     * @return Retrofit object
     */
    public static ApiService getApiService() {
        if (mApiService == null) {
            mApiService = getRetrofit().create(ApiService.class);
        }
        return mApiService;
    }

    /**
     * Create client and instance of retrofit
     *
     * @return Return single instance of retrofit
     */
    private static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            final String baseUrl = Config.getInstance().getApiBaseUrl();
            final OkHttpClient client = new OkHttpClient.Builder()
                    .followRedirects(true)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new ApiInterceptor())
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
