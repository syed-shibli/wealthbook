package com.example.daffolapmac.wealthbook;

import android.app.Application;

import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.utils.Config;

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // Init config
        Config.init(this);
        SessionManager.getNewInstance().initialize(getApplicationContext());
    }
}
