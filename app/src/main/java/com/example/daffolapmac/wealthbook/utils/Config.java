package com.example.daffolapmac.wealthbook.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;

public class Config {

    private static Config mInstance;

    @SerializedName("apiBaseUrl")
    private String apiBaseUrl;

    public static Config getInstance() {
        if(mInstance == null){
            throw new IllegalStateException("Config not initialized, call init() once to initialize.");
        }
        return mInstance;
    }

    public static void init(Context context){
        Gson gson = new Gson();
        String json = null;
        try {
            final InputStream stream = context.getAssets().open("config.json");
            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            stream.close();
            json = new String(buffer);
            mInstance = gson.fromJson(json, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }
}
