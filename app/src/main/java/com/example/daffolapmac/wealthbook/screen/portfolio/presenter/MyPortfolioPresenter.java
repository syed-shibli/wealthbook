package com.example.daffolapmac.wealthbook.screen.portfolio.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.portfolio.manager.MyPortfolioManager;
import com.example.daffolapmac.wealthbook.screen.portfolio.model.AllPortfolioRes;
import com.example.daffolapmac.wealthbook.screen.portfolio.view.IPortfolioFragmentView;

public class MyPortfolioPresenter implements IMyPortfolioScreenPresenter, IMyPortfolioResponseReceiver {
    private IPortfolioFragmentView mViewListener;
    private MyPortfolioManager mMyPortfolioManager;

    public MyPortfolioPresenter(IPortfolioFragmentView fragmentView, MyPortfolioManager manager) {
        this.mViewListener = fragmentView;
        mMyPortfolioManager = manager;
    }

    @Override
    public void reqAllPortfolio(boolean isAppLoader) {
        if (isAppLoader) {
            mViewListener.showLoader();
        }
        mMyPortfolioManager.getAllPortfolioList(this);
    }

    @Override
    public void disconnect() {
        mMyPortfolioManager.cancel();
    }

    @Override
    public void onSuccess(AllPortfolioRes data) {
        mViewListener.hideLoader();
        mViewListener.bindDataToView(data);
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mViewListener.hideLoader();
        mViewListener.onError(errorResponse.getErrorMessage());
    }
}
