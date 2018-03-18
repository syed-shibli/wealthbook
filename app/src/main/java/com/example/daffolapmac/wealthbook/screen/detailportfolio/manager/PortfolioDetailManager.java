package com.example.daffolapmac.wealthbook.screen.detailportfolio.manager;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.api.ResponseCallback;
import com.example.daffolapmac.wealthbook.api.ResponseWrapper;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.PortfolioDetailRes;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.presenter.IPortfolioDetailResponseReceiver;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;

import retrofit2.Call;

public class PortfolioDetailManager {

    private IPortfolioDetailResponseReceiver mPortfolioDetailReceiver;
    private Call<PortfolioDetailRes> mSelectedPortfolioCall;

    public void sendReqForSelectedPortfolio(IPortfolioDetailResponseReceiver receiver, int id) {
        this.mPortfolioDetailReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mSelectedPortfolioCall = RetrofitClient.getApiService().getSelectedPortfolio(id, token);
        mSelectedPortfolioCall.enqueue(new ResponseWrapper<PortfolioDetailRes>(mSelectedPortfolioCallback));
    }

    private ResponseCallback<PortfolioDetailRes> mSelectedPortfolioCallback = new ResponseCallback<PortfolioDetailRes>() {
        @Override
        public void onSuccess(@NonNull PortfolioDetailRes data) {
            mPortfolioDetailReceiver.onSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mPortfolioDetailReceiver.onFailure(errorResponse);
        }
    };

    /**
     * To cancel all req if viw destroy
     */
    public void cancel() {
        if (mSelectedPortfolioCall != null && mSelectedPortfolioCall.isExecuted()) {
            mSelectedPortfolioCall.cancel();
        }
    }
}
