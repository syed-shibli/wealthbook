package com.example.daffolapmac.wealthbook.screen.stockeod.manager;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.api.ResponseCallback;
import com.example.daffolapmac.wealthbook.api.ResponseWrapper;
import com.example.daffolapmac.wealthbook.api.RetrofitClient;
import com.example.daffolapmac.wealthbook.screen.stockeod.model.StockEodRes;
import com.example.daffolapmac.wealthbook.screen.stockeod.presenter.IStockEodResponseReceiver;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class StockEodManager {

    private IStockEodResponseReceiver mResponseReceiver;
    private Call<List<StockEodRes>> mStockEodCall;

    /**
     * To send req for getting selected stock eod data
     * @param receiver Response receiver
     * @param id       Stock id
     * @param ticker   Ticker value
     */
    public void sendReqToSelectedStockEod(IStockEodResponseReceiver receiver, int id, String ticker) {
        this.mResponseReceiver = receiver;
        String token = SessionManager.getNewInstance().readSession().getmToken();
        mStockEodCall = RetrofitClient.getApiService().getSpecificStockEOD(token, id, ticker);
        mStockEodCall.enqueue(new ResponseWrapper<List<StockEodRes>>(mStockEodCallback));
    }

    private ResponseCallback<List<StockEodRes>> mStockEodCallback = new ResponseCallback<List<StockEodRes>>() {
        @Override
        public void onSuccess(@NonNull List<StockEodRes> data) {
            List list = mapSingleLineCharViewData(data);
            mResponseReceiver.onSuccess(list);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mResponseReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Create list of data for stock line chart
     * @param data Stock data model
     * @return List of chart data
     */
    private List mapSingleLineCharViewData(List<StockEodRes> data) {
        List lineCharList = new ArrayList();
        for (StockEodRes item : data) {
            List<Double> l1 = new ArrayList<>();
            l1.add(Double.valueOf(Utility.getMillisecondFromDateString(item.getDate())));
            l1.add(item.getPrice());
            lineCharList.add(l1);
        }
        return lineCharList;
    }

    /**
     * Cancel all api req if view destroy
     */
    public void cancel() {
        if (mStockEodCall != null && mStockEodCall.isExecuted()) {
            mStockEodCall.cancel();
        }
    }
}
