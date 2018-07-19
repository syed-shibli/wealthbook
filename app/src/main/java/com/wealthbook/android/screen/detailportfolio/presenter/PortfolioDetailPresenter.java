package com.wealthbook.android.screen.detailportfolio.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.detailportfolio.manager.PortfolioDetailManager;
import com.wealthbook.android.screen.detailportfolio.model.PortfolioDetailRes;
import com.wealthbook.android.screen.detailportfolio.view.IPortfolioDetailView;

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
