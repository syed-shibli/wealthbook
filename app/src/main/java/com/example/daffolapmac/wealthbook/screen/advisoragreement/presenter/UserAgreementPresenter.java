package com.example.daffolapmac.wealthbook.screen.advisoragreement.presenter;

import android.support.annotation.NonNull;

import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.manager.UserAgreementManager;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.mode.UserAgreementReq;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.mode.UserAgreementRes;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.view.IUserAgreementView;

public class UserAgreementPresenter implements IUserAgreementScreenPresenter, IUserAgreementResponseReceiver {
    private IUserAgreementView mAgreementView;
    private UserAgreementManager mAgreementManager;

    public UserAgreementPresenter(IUserAgreementView mAgreementView, UserAgreementManager mAgreementManager) {
        this.mAgreementView = mAgreementView;
        this.mAgreementManager = mAgreementManager;
    }

    @Override
    public void acceptDeclineAgreement(String id, int status) {
        mAgreementView.showLoader();
        UserAgreementReq req = new UserAgreementReq();
        req.setAgreementStatus(status);
        req.setUserID(id);
        mAgreementManager.sendReqToUpdateAgreement(this, req);
    }

    @Override
    public void disconnect() {
        mAgreementManager.cancel();
    }

    @Override
    public void onSuccess(UserAgreementRes data) {
        mAgreementView.hideLoader();
        mAgreementView.agreementStatus();
    }

    @Override
    public void onFailure(@NonNull ErrorResponse errorResponse) {
        mAgreementView.hideLoader();
        mAgreementView.onError(errorResponse.getErrorMessage());
    }
}
