package com.example.daffolapmac.wealthbook.screen.detailportfolio.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.manager.PortfolioDetailManager;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.PortfolioDetailRes;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.view.IPortfolioDetailView;

public class PortfolioDetailPresenter implements IPortfolioDetailScreenPresenter, IPortfolioDetailResponseReceiver {
    private IPortfolioDetailView mPortfolioDetailView;
    private PortfolioDetailManager mPortfolioDetailManager;

    public PortfolioDetailPresenter(IPortfolioDetailView mPortfolioDetailView, PortfolioDetailManager mPortfolioDetailManager) {
        this.mPortfolioDetailView = mPortfolioDetailView;
        this.mPortfolioDetailManager = mPortfolioDetailManager;
    }

    @Override
    public void reqToGetPortfolioDetail(int id, boolean isAppLoader) {
        if(isAppLoader) {
            mPortfolioDetailView.showLoader();
        }
        mPortfolioDetailManager.sendReqForSelectedPortfolio(this, id);
    }

    @Override
    public void disconnect() {
        mPortfolioDetailManager.cancel();
    }

    @Override
    public void onSuccess(PortfolioDetailRes data) {
        mPortfolioDetailView.hideLoader();
        mPortfolioDetailView.bindDataToView(data);
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mPortfolioDetailView.hideLoader();
    }
}
