package com.wealthbook.android.usersession;

import android.content.Context;

/**
 * Interface to be implemented by any session managing class.
 */
public interface ISessionManager {

    /**
     * Initialize session's shared preference.
     * @param context context.
     */
    void initialize(Context context);

    /**
     * Creates session with user data provider details.
     * @param userSessionData provider for user data to be persisted.
     * @throws IllegalStateException if initialize has not be called before.
     */
    void saveSession(UserSessionData userSessionData);

    void saveKeyValue(String key, String value);

    String getKeyValue(String key);

    /**
     * Destroys the current session.
     * @throws IllegalStateException if initialize has not be called before.
     */
    void destroySession() throws IllegalStateException;

    /**
     * @return provider for user details if initialized, else null.
     * @throws IllegalStateException if initialize has not be called before.
     */
    UserSessionData readSession() throws IllegalStateException;

    /**
     * @return true if user is not in guest mode else false
     */
    boolean isCurrentUserLoggedIn();

}
