package com.example.daffolapmac.wealthbook.screen.stockeod.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.stockeod.manager.StockEodManager;
import com.example.daffolapmac.wealthbook.screen.stockeod.model.StockEodRes;
import com.example.daffolapmac.wealthbook.screen.stockeod.view.IStockEodView;

import java.util.List;

public class StockEodPresenter implements IStockEodScreenPresenter, IStockEodResponseReceiver {
    private IStockEodView mStockEodView;
    private StockEodManager mStockEodManager;

    public StockEodPresenter(IStockEodView mStockEodView, StockEodManager mStockEodManager) {
        this.mStockEodView = mStockEodView;
        this.mStockEodManager = mStockEodManager;
    }

    @Override
    public void getSelectedStockEod(int id, String ticker) {
        mStockEodView.showLoader();
        mStockEodManager.sendReqToSelectedStockEod(this, id, ticker);
    }

    @Override
    public void disconnect() {
        mStockEodManager.cancel();
    }

    @Override
    public void onSuccess(List data) {
        mStockEodView.hideLoader();
        mStockEodView.bindStockEodValueToView(data);
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mStockEodView.hideLoader();
        mStockEodView.onError(errorResponse.getErrorMessage());
    }
}
