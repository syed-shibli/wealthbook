package com.wealthbook.android.notification;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.wealthbook.android.eventbus.Events;
import com.wealthbook.android.screen.splash.view.SplashActivity;
import com.wealthbook.android.utils.AppConstant;

import org.greenrobot.eventbus.EventBus;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static String token;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: " + token);
        MyFirebaseMessagingService.token = token;
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            String title = remoteMessage.getNotification() != null ? remoteMessage.getNotification().getTitle() : "";
            handleDataMessage(title, remoteMessage.getData().get(AppConstant.PAYLOAD));
        }
    }

    private void handleDataMessage(String title, String payload) {
        Intent resultIntent;
        Gson gson = new Gson();
        PushDataPayload pushPayload = gson.fromJson(payload, PushDataPayload.class);
        if (!NotificationUtils.isAppInBackground(getApplicationContext())) {
            switch (pushPayload.getNotification().getAlert().getNotificationType()) {
                case "1":
                    Events.PendingAlertType pendingAlertType = new Events.PendingAlertType(-1);
                    EventBus.getDefault().post(pendingAlertType);
                    break;
            }
        } else {
            switch (pushPayload.getNotification().getAlert().getNotificationType()) {
                case "1":
                    resultIntent = new Intent(getApplicationContext(), SplashActivity.class);
                    resultIntent.putExtra(AppConstant.PAYLOAD, payload);
                    NotificationUtils.prepareNotificationMessage(getApplicationContext(), title, pushPayload.getNotification().getAlert().getLocKey(), resultIntent);
                    break;
                default:
                    resultIntent = new Intent();
                    NotificationUtils.prepareNotificationMessage(getApplicationContext(), title, pushPayload.getNotification().getAlert().getLocKey(), resultIntent);
                    break;
            }
        }
    }
}
