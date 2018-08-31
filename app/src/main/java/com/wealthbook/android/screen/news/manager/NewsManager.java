package com.wealthbook.android.screen.news.manager;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.api.ResponseCallback;
import com.wealthbook.android.api.ResponseWrapper;
import com.wealthbook.android.api.RetrofitClient;
import com.wealthbook.android.screen.news.model.NewsRes;
import com.wealthbook.android.screen.news.presenter.INewsResponseReceiver;
import com.wealthbook.android.usersession.SessionManager;

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
        String token = SessionManager.getNewInstance().readSession().getToken();
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
