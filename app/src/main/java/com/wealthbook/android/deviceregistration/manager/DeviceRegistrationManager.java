package com.wealthbook.android.deviceregistration.manager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.api.ResponseCallback;
import com.wealthbook.android.api.ResponseWrapper;
import com.wealthbook.android.api.RetrofitClient;
import com.wealthbook.android.deviceregistration.DeviceRegistrationReq;
import com.wealthbook.android.deviceregistration.DeviceRegistrationResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class DeviceRegistrationManager {
    private Call<DeviceRegistrationResponse> mDeviceRegistrationCall;
    private Call<ResponseBody> mDeviceDeRegistrationCall;

    public void reqDeviceRegister(String token, String deviceId) {
        DeviceRegistrationReq registrationReq = new DeviceRegistrationReq(token, deviceId, "android");
        mDeviceRegistrationCall = RetrofitClient.getApiService().deviceRegister(registrationReq);
        mDeviceRegistrationCall.enqueue(new ResponseWrapper<>(mDeviceRegistrationCallback));
    }

    private ResponseCallback<DeviceRegistrationResponse> mDeviceRegistrationCallback = new ResponseCallback<DeviceRegistrationResponse>() {
        @Override
        public void onSuccess(@NonNull DeviceRegistrationResponse data) {
            Log.d("Device Register : ", "Success");
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            Log.d("Device Register : ", "Failure");
        }
    };

    public void reqDeviceDeRegister() {
        mDeviceDeRegistrationCall = RetrofitClient.getApiService().deviceDeRegister();
        mDeviceDeRegistrationCall.enqueue(new ResponseWrapper<ResponseBody>(mDeviceDeRegistrationCallback));
    }

    private ResponseCallback<ResponseBody> mDeviceDeRegistrationCallback = new ResponseCallback<ResponseBody>() {
        @Override
        public void onSuccess(@NonNull ResponseBody data) {
            Log.d("Device De Register : ", "Success");
        }

        @Override
        public void onFailure(@NonNull ErrorResponse errorResponse) {
            Log.d("Device De Register : ", "Success");
        }
    };

    public void cancel() {
        if (mDeviceRegistrationCall != null && mDeviceRegistrationCall.isExecuted()) {
            mDeviceRegistrationCall.cancel();
        }
        if (mDeviceDeRegistrationCall != null && mDeviceDeRegistrationCall.isExecuted()) {
            mDeviceDeRegistrationCall.cancel();
        }
    }

    public interface IDeviceRegistrationCallback {
        void onSuccess();

        void onError();
    }
}
