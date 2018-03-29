package com.example.daffolapmac.wealthbook.screen.stockeod.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.stockeod.model.StockEodRes;

import java.util.List;

public interface IStockEodResponseReceiver {

    /**
     * On getting success from server send data back to presenter
     * @param data List of stock value
     * @param list
     */
    void onSuccess(List data);

    /**
     * To send error back to presenter
     * @param errorResponse Error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);
}
