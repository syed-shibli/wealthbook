package com.wealthbook.android.screen.stockeod.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;

import java.util.List;

public interface IStockEodResponseReceiver {

    /**
     * On getting success from server send data back to presenter
     * @param data List of stock value
     */
    void onSuccess(List data);

    /**
     * To send error back to presenter
     * @param errorResponse Error response
     */
    void onFailure(@NonNull ErrorResponse errorResponse);
}
