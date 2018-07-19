package com.wealthbook.android.screen.forgotpassword.manager;

import android.support.annotation.NonNull;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.api.ResponseCallback;
import com.wealthbook.android.api.ResponseWrapper;
import com.wealthbook.android.api.RetrofitClient;
import com.wealthbook.android.common.ConformationRes;
import com.wealthbook.android.screen.forgotpassword.model.ForgotPasswordReq;
import com.wealthbook.android.screen.forgotpassword.presenter.IForgotPasswordResponseReceiver;

import retrofit2.Call;

public class ForgotPasswordManager {

    private IForgotPasswordResponseReceiver mResponseReceiver;
    private Call<ConformationRes> mEmailOTPReqCall;
    private Call<ConformationRes> mPasswordChangeReqCall;

    /**
     * Cancel all ongoing request
     */
    public void cancel() {
        if(mEmailOTPReqCall != null && mEmailOTPReqCall.isExecuted()){
            mEmailOTPReqCall.cancel();
        }

        if(mPasswordChangeReqCall != null && mPasswordChangeReqCall.isExecuted()){
            mPasswordChangeReqCall.cancel();
        }
    }

    /**
     * Send OTP to provided email
     * @param receiver Response receiver
     * @param email    Email where want to send OTP
     */
    public void reqEmailOTP(IForgotPasswordResponseReceiver receiver, String email) {
        this.mResponseReceiver = receiver;
        mEmailOTPReqCall = RetrofitClient.getApiService().emailOTP(email);
        mEmailOTPReqCall.enqueue(new ResponseWrapper<ConformationRes>(mEmailOTPReqCallback));
    }

    private ResponseCallback<ConformationRes> mEmailOTPReqCallback = new ResponseCallback<ConformationRes>() {
        @Override
        public void onSuccess(@NonNull ConformationRes data) {
            mResponseReceiver.onOTPSendSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mResponseReceiver.onFailure(errorResponse);
        }
    };

    /**
     * Send req for change password
     * @param receiver Response receiver
     * @param req      Change password request
     */
    public void reqPasswordChange(IForgotPasswordResponseReceiver receiver, ForgotPasswordReq req) {
        this.mResponseReceiver = receiver;
        mPasswordChangeReqCall = RetrofitClient.getApiService().forgotPassword(req);
        mPasswordChangeReqCall.enqueue(new ResponseWrapper<ConformationRes>(mPasswordChangeReqCallback));
    }

    private ResponseCallback<ConformationRes> mPasswordChangeReqCallback = new ResponseCallback<ConformationRes>() {
        @Override
        public void onSuccess(@NonNull ConformationRes data) {
            mResponseReceiver.onChangePasswordSuccess(data);
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            mResponseReceiver.onFailure(errorResponse);
        }
    };
}
