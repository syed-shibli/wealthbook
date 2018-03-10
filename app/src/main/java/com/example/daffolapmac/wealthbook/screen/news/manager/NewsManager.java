package com.example.daffolapmac.wealthbook.screen.news.manager;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.api.ResponseCallback;
import com.example.daffolapmac.wealthbook.api.ResponseWrapper;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.screen.news.model.NewsRes;
import com.example.daffolapmac.wealthbook.screen.news.presenter.INewsResponseReceiver;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;

import retrofit2.Call;

public class NewsManager {

    private INewsResponseReceiver mNewsResponseReceiver;
    private Call<NewsRes> mNewsReqCall;

    /**
     * To send request for fetching list of news
     *
     * @param receiver Response receiver
     */
    public void getNewsList(INewsResponseReceiver receiver) {
        this.mNewsResponseReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mNewsReqCall = RetrofitClient.getApiService().getNews(token);
        mNewsReqCall.enqueue(new ResponseWrapper<>(mNewsReqCallBack));
    }

    private ResponseCallback<NewsRes> mNewsReqCallBack = new ResponseCallback<NewsRes>() {
        @Override
        public void onSuccess(@NonNull NewsRes data) {
            mNewsResponseReceiver.onSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mNewsResponseReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Cancel all executing request
     */
    public void cancel() {
        if (mNewsReqCall != null && mNewsReqCall.isExecuted()) {
            mNewsReqCall.cancel();
        }
    }
}
