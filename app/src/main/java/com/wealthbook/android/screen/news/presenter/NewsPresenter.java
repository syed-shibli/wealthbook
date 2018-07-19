package com.wealthbook.android.screen.news.presenter;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.screen.news.manager.NewsManager;
import com.wealthbook.android.screen.news.model.NewsRes;
import com.wealthbook.android.screen.news.view.INewsViewListener;

public class NewsPresenter implements INewsScreenPresenter, INewsResponseReceiver {

    private final INewsViewListener mViewListener;
    private final NewsManager mNewsManager;

    public NewsPresenter(INewsViewListener viewListener, NewsManager newsManager) {
        this.mViewListener = viewListener;
        this.mNewsManager = newsManager;
    }

    @Override
    public void fetchNewsReq(boolean isShowAppProgress) {
        if (isShowAppProgress) {
            mViewListener.showLoader();
        }
        mNewsManager.getNewsList(this);
    }

    @Override
    public void disconnect() {
        mNewsManager.cancel();
    }

    @Override
    public void onSuccess(NewsRes data) {
        mViewListener.hideLoader();
        mViewListener.bindNewsListToView(data);
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mViewListener.hideLoader();
        mViewListener.showError(errorResponse.getErrorMessage());
    }
}
