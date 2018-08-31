package com.wealthbook.android.screen.portfolio.manager;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.api.ResponseCallback;
import com.wealthbook.android.api.ResponseWrapper;
import com.wealthbook.android.api.RetrofitClient;
import com.wealthbook.android.screen.portfolio.model.AllPortfolioRes;
import com.wealthbook.android.screen.portfolio.presenter.IMyPortfolioResponseReceiver;
import com.wealthbook.android.usersession.SessionManager;

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
        String token = SessionManager.getNewInstance().readSession().getToken();
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
