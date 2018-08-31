package com.wealthbook.android.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;
import com.squareup.picasso.Picasso;
import com.wealthbook.android.R;
import com.wealthbook.android.api.ErrorResponse;
import com.wealthbook.android.deviceregistration.manager.DeviceRegistrationManager;
import com.wealthbook.android.eventbus.Events;
import com.wealthbook.android.screen.login.model.RepDetails;
import com.wealthbook.android.screen.login.view.LoginActivity;
import com.wealthbook.android.screen.notificationalert.view.NotificationAlertFragment;
import com.wealthbook.android.screen.pendingalert.manager.IPendingAlertResponseReceiver;
import com.wealthbook.android.screen.pendingalert.manager.PendingAlertManager;
import com.wealthbook.android.screen.pendingalert.model.PendingAlertViewModel;
import com.wealthbook.android.screen.pendingalert.view.PendingAlertFragment;
import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.usersession.UserSessionData;
import com.wealthbook.android.utils.AppConstant;
import com.wealthbook.android.utils.BadgeDrawable;
import com.wealthbook.android.utils.BitmapTransform;
import com.wealthbook.android.utils.Utility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class BaseActivityImpl extends AppCompatActivity implements UIBase, IDialogClickListener, IPendingAlertResponseReceiver {

    private WBLoader mLoader;
    private AlertDialogModel alert;
    protected UserSessionData data;
    private TextView mTxvCount;
    private int count = 0;
    protected LayerDrawable icon;
    private NotificationAlertFragment pendingNotification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = SessionManager.getNewInstance().readSession();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showProgress() {
        if (mLoader == null) {
            mLoader = new WBLoader(this);
            mLoader.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mLoader.setCancelable(false);
        }

        if (mLoader != null && !mLoader.isShowing()) {
            mLoader.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mLoader != null && mLoader.isShowing()) {
            mLoader.dismiss();
            mLoader = null;
        }
    }

    @Override
    public void showSnackBar(int message, AppCompatActivity context) {
        if (message != 0) {
            Snackbar.with(getApplicationContext()).type(SnackbarType.MULTI_LINE).text(message).show(context);
        }
    }

    @Override
    public void showSnackBar(String message, AppCompatActivity context) {
        if (message != null) {
            Snackbar.with(getApplicationContext()).type(SnackbarType.MULTI_LINE).text(message).show(context);
        }
    }

    @Override
    public <T> void launchActivity(Activity _context, Class<T> cls) {
        if (_context != null) {
            Intent intent = new Intent(_context, cls);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
            startActivity(intent);
        }
    }

    @Override
    public <T> void launchActivity(Intent _intent) {
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
        startActivity(_intent);
    }

    @Override
    public void onDialogClick(int val) {
        switch (val) {
            case R.string.action_logout:
                SessionManager.getNewInstance().destroySession();
                DeviceRegistrationManager manager = new DeviceRegistrationManager();
                manager.reqDeviceDeRegister();
                launchActivity(BaseActivityImpl.this, LoginActivity.class);
                finish();
                break;
            case R.string.action_empty_pending_alert:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem itemCart = menu.findItem(R.id.action_alert);
        if (data.getUserType() == AppConstant.USER_TYPE_ADVISER) {
            itemCart.setVisible(false);
        } else {
            itemCart.setVisible(true);
        }
        icon = (LayerDrawable) itemCart.getIcon();
        setBadgeCount(this, icon, count);
        return true;
    }

    /**
     * Set badge count
     * @param context Context of activity
     * @param icon    Icon
     * @param count   Count
     */
    public static void setBadgeCount(Context context, LayerDrawable icon, int count) {

        if (context == null || icon == null) {
            return;
        }
        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }
        badge.setCount(String.valueOf(count + badge.getCount()));
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_alert:
                showProgress();
                count = 0;
                setBadgeCount(this, icon, count);
                PendingAlertManager manager = new PendingAlertManager();
                manager.getPendingAlertList(this);
                return true;
            case R.id.action_pending_notification:
                showPendingAlert(-1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showPendingAlert(int id) {
        if (pendingNotification == null) {
            pendingNotification = NotificationAlertFragment.newInstance(id);
            pendingNotification.show(getSupportFragmentManager(), "");
        } else if (pendingNotification.isVisible()) {
            pendingNotification.setId(id);
            pendingNotification.refresh();
        } else {
            pendingNotification.show(getSupportFragmentManager(), "");
            pendingNotification.setId(id);
            pendingNotification.refresh();
        }
    }

    @Override
    public void onSuccess(ArrayList<PendingAlertViewModel> data) {
        hideProgress();
        setBadgeCount(this, icon, count);
        if (data == null || data.size() == 0) {
            alert = Utility.prepareDialogObj(getString(R.string.txt_empty_pending_alert), null, getString(R.string.btn_ok), "", R.string.action_empty_pending_alert, false);
            Utility.showDialog(getSupportFragmentManager(), this, alert);
            return;
        }
        PendingAlertFragment pendingAlert = PendingAlertFragment.newInstance(data);
        pendingAlert.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        hideProgress();
        setBadgeCount(this, icon, count);
        alert = Utility.prepareDialogObj("", getString(R.string.txt_empty_pending_alert), getString(R.string.btn_ok), "", R.string.action_empty_pending_alert, false);
        Utility.showDialog(getSupportFragmentManager(), this, alert);
    }

    /**
     * Init adviser view
     * @param mLLAdviserLogo Adviser view container view
     */
    public void initAdviserView(View mLLAdviserLogo) {
        if (data == null) {
            return;
        }
        ImageView advisorLogo = mLLAdviserLogo.findViewById(R.id.img_adviser_logo);
        TextView advisorNAme = mLLAdviserLogo.findViewById(R.id.txv_adviser_name);
        if (data.getUserType() == AppConstant.USER_TYPE_ADVISER) {
            Picasso.with(this).load(data.getLogo())
                    .transform(new BitmapTransform(AppConstant.MAX_WIDTH, AppConstant.MAX_HEIGHT))
                    .resize(AppConstant.size, AppConstant.size)
                    .error(R.mipmap.advisor_placeholder)
                    .into(advisorLogo);
            if (data.getFirstName() != null) {
                String name = data.getFirstName() + " " + (data.getLastName() != null ? data.getLastName() : "");
                advisorNAme.setText(name);
            }
        } else if (data.getRepDetails() != null) {
            RepDetails adviserDetails = data.getRepDetails();
            Picasso.with(this).load(adviserDetails.getLogo())
                    .transform(new BitmapTransform(AppConstant.MAX_WIDTH, AppConstant.MAX_HEIGHT))
                    .resize(AppConstant.size, AppConstant.size)
                    .error(R.mipmap.advisor_placeholder)
                    .into(advisorLogo);
            if (adviserDetails.getFirstName() != null) {
                String name = adviserDetails.getFirstName() + " " + (adviserDetails.getLastName() != null ? adviserDetails.getLastName() : "");
                advisorNAme.setText(name);
            }
        }
    }

    @Subscribe
    public void pendingAlertEvent(final Events.PendingAlertType pendingAlertType) {
        if (pendingAlertType != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (icon != null) {
                        setBadgeCount(BaseActivityImpl.this, icon, 1);
                        showPendingAlert(pendingAlertType.getId());
                    }
                }
            });
        }
    }
}
