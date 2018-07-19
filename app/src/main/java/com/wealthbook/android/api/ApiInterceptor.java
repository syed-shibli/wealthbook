package com.wealthbook.android.api;

import android.support.annotation.NonNull;

import com.wealthbook.android.usersession.SessionManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class ApiInterceptor implements Interceptor {

    private static final String APP_CONTENT_TYPE = "Content-Type";
    private static final String APP_API_KEY = "api_key";
    private static final String APP_AUTH_TOKEN = "token";

    /**
     * Interceptor that modify/add header before outgoing request
     *
     * @param chain Chain request
     * @return Modified header request
     * @throws IOException Throws exceptions
     */
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request originalReq = chain.request();
        final Request reqWithNonAuthHeader = modifyNonAuthHeader(originalReq);
        final Request reqWithAuthHeader = modifyAuthHeader(reqWithNonAuthHeader);
        return chain.proceed(reqWithAuthHeader);
    }

    /**
     * Modify header which want to authorization
     *
     * @param request Request
     * @return Return builder
     */
    private Request modifyAuthHeader(Request request) {
        if (request != null) {
            Request.Builder builder = request.newBuilder();
            if (SessionManager.getNewInstance().isCurrentUserLoggedIn()) {
                builder.addHeader(APP_AUTH_TOKEN, SessionManager.getNewInstance().readSession().getmToken());
            }
            return builder.build();
        }
        return null;
    }

    /**
     * Modify public header
     *
     * @param request Request
     * @return Return builder
     */
    private Request modifyNonAuthHeader(Request request) {
        if (request != null) {
            Request.Builder builder = request.newBuilder();
            // Add content type of response
            builder.addHeader(APP_CONTENT_TYPE, "application/json");
            // Add api key if available
            /*builder.addHeader(APP_API_KEY, "");*/
            return builder.build();
        }
        return null;
    }
}
