package com.wealthbook.android.api;

import android.support.annotation.NonNull;

import com.wealthbook.android.R;
import com.wealthbook.android.utils.AppConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A wrapper layer over the retrofit callback, written for distinguishing the success
 * and failure responses.
 * @param <T> the class type of the success response expected.
 */
public class ResponseWrapper<T> implements Callback<T> {

    private final ResponseCallback<T> mResponseCallback;
    private final String INTERNAL_ERROR = "Internal server error";

    /**
     * Creates an instance without the error mapper,
     * in case of all errors we would get the default response.
     * @param responseCallback implementation of the response callback.
     */
    public ResponseWrapper(ResponseCallback<T> responseCallback) {
        mResponseCallback = responseCallback;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

        if (response.isSuccessful()) {
            String token = response.headers().get("Authorization");
            if (token != null && !token.equals("undefined")) {
                saveTokenToSession(token);
            }
            mResponseCallback.onSuccess(response.body());
        } else {
            String errorBodyPayload = null;
            try {
                errorBodyPayload = response.errorBody().string();
                if (errorBodyPayload != null) {
                    mResponseCallback.onFailure(parseError(errorBodyPayload));
                } else {
                    mResponseCallback.onFailure(new ErrorResponse(INTERNAL_ERROR, "500"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                mResponseCallback.onFailure(new ErrorResponse(INTERNAL_ERROR, "500"));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable throwable) {

        ErrorResponse errorResponse;

        if (throwable instanceof ConnectException
                || throwable instanceof UnknownHostException) {
            // Network error
            errorResponse = new ErrorResponse("Please check your internet connection.", "012");
        } else {
            // some more complex error occurred like conversion etc.
            errorResponse = new ErrorResponse("Request time out", "500");
        }
        mResponseCallback.onFailure(errorResponse);
    }

    /**
     * Parse error response
     * @param errorBodyPayload Error body
     * @return Return error response
     */
    private ErrorResponse parseError(String errorBodyPayload) {
        try {
            JSONObject jsonObject = new JSONObject(errorBodyPayload);
            String errorMessage = jsonObject.optString("error");
            if (errorMessage == null ||errorMessage == "") {
                errorMessage = INTERNAL_ERROR;
            }
            return new ErrorResponse(errorMessage, "500");
        } catch (JSONException e) {
            return new ErrorResponse(INTERNAL_ERROR, "500"); // Unable to process request at this time.
        }
    }

    /**
     * Save Toke to session
     * @param token User Token
     */
    private void saveTokenToSession(String token) {
//        UserSessionData sessionData = SessionManager.getNewInstance().readSession();
//        sessionData.setAuthToken(token);
//        SessionManager.getNewInstance().saveSession(sessionData);
    }
}
