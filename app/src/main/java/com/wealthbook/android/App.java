package com.wealthbook.android;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.utils.Config;

public class App extends MultiDexApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        // Init config
        Config.init(this);
        SessionManager.getNewInstance().initialize(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
