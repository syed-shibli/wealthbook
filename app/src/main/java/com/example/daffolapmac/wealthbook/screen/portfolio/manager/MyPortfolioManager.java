package com.example.daffolapmac.wealthbook.screen.portfolio.manager;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.api.ResponseCallback;
import com.example.daffolapmac.wealthbook.api.ResponseWrapper;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.screen.portfolio.model.AllPortfolioRes;
import com.example.daffolapmac.wealthbook.screen.portfolio.presenter.IMyPortfolioResponseReceiver;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;

import retrofit2.Call;

public class MyPortfolioManager {

    private Call<AllPortfolioRes> mAllPortfolioCall;
    private IMyPortfolioResponseReceiver mResponseReceiver;

    /**
     * Send request to get all portfolio data
     * @param receiver Response receiver
     */
    public void getAllPortfolioList(IMyPortfolioResponseReceiver receiver) {
        this.mResponseReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mAllPortfolioCall = RetrofitClient.getApiService().getAllPortfolio(token);
        mAllPortfolioCall.enqueue(new ResponseWrapper<AllPortfolioRes>(mAllPortfolioCallback));
    }

    private ResponseCallback<AllPortfolioRes> mAllPortfolioCallback = new ResponseCallback<AllPortfolioRes>() {
        @Override
        public void onSuccess(@NonNull AllPortfolioRes data) {
            mResponseReceiver.onSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mResponseReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Cancel all on going request
     */
    public void cancel() {
        if (mAllPortfolioCall != null && mAllPortfolioCall.isExecuted()) {
            mAllPortfolioCall.cancel();
        }
    }
}
