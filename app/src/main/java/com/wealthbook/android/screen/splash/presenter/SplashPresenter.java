package com.wealthbook.android.screen.splash.presenter;

import com.wealthbook.android.screen.splash.view.ISplashView;
import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.usersession.UserSessionData;
import com.wealthbook.android.utils.AppConstant;

import java.util.Timer;
import java.util.TimerTask;

public class SplashPresenter {
    private final ISplashView mSplashView;
    private static final int DURATION_OF_SPLASH = 3000;
    private Timer mTimer;
    private TimerTask mTimerTask;

    public SplashPresenter(ISplashView iSplashView) {
        this.mSplashView = iSplashView;
    }

    /**
     * When splash screen visible
     */
    public void onSplashVisible() {
        stopTimer();
        createTimer();
        mTimer.schedule(mTimerTask, DURATION_OF_SPLASH);
    }

    /**
     * When user destroy the app then automatically stop the timer
     */
    public void onSplashInvisible() {
        stopTimer();
    }

    /**
     * Stop an already started Timer and TimerTasks.
     */
    private void stopTimer() {
        // Cancel the existing timer task
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        // Cancel the existing timer
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    /**
     * Creates a new instances of the Timer and TimerTasks.
     */
    private void createTimer() {
        mTimer = new Timer();
        mTimerTask = new SplashTimer();
    }

    private class SplashTimer extends TimerTask {
        @Override
        public void run() {
            stopTimer();
            checkUserLoginOrNot();
        }
    }

    /**
     * Check user logged or not
     */
    public void checkUserLoginOrNot() {
        if (SessionManager.getNewInstance().isCurrentUserLoggedIn()) {
            UserSessionData session = SessionManager.getNewInstance().readSession();
            if (session.getUserType() == AppConstant.USER_TYPE_ADVISER
                    && session.getUserAgreement() != null && !session.getUserAgreement().isAgreementStatus()) {
                mSplashView.redirectToAdviserAgreementScreen();
                return;
            }
            mSplashView.redirectToHomeScreen();
        } else {
            mSplashView.redirectToLoginScreen();
        }
    }
}
