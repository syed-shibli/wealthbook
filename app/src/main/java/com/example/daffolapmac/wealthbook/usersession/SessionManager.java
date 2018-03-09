package com.example.daffolapmac.wealthbook.usersession;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Session Manager class to persist and fetch user details.
 */
public final class SessionManager implements ISessionManager {

    private static final String DEFAULT_USER_DATA = null;
    private final String PREFERENCE_FILE_NAME = "user_session_file";
    private final String USER_PREFERENCE_KEY = "USER_PREFERENCE_KEY";
    private SharedPreferences mSharedPreference;
    private static ISessionManager sInstance;

    public static ISessionManager getNewInstance() {
        if (sInstance == null) {

            sInstance = new SessionManager();
        }
        return sInstance;
    }

    @Override
    public void initialize(Context context) {
        mSharedPreference = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveSession(UserSessionData userSessionData) {
        checkForInitialization();

        String userDataJsonObject = new Gson().toJson(userSessionData);
        final SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(USER_PREFERENCE_KEY, userDataJsonObject).apply();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroySession() throws IllegalStateException {
        checkForInitialization();
        mSharedPreference.edit().clear().apply();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public UserSessionData readSession() throws IllegalStateException {
        checkForInitialization();
        String userSessionJsonData = mSharedPreference.getString(USER_PREFERENCE_KEY, DEFAULT_USER_DATA);
        return new Gson().fromJson(userSessionJsonData, UserSessionData.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCurrentUserLoggedIn() {
        return !(mSharedPreference == null || readSession() == null || readSession().getmToken() == null) && mSharedPreference != null && readSession().getmToken() != null;
    }

    /**
     * Throws IllegalStateException if initialization has not been performed.
     */
    private void checkForInitialization() {
        if (mSharedPreference == null) {
            throw new IllegalStateException("Initialization is not performed yet.");
        }
    }
}