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
import android.widget.TextView;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;
import com.wealthbook.android.R;
import com.wealthbook.android.api.ErrorResponse;
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
import com.wealthbook.android.utils.Utility;

import java.util.ArrayList;

public class BaseActivityImpl extends AppCompatActivity implements UIBase, IDialogClickListener, IPendingAlertResponseReceiver {

    private WBLoader mLoader;
    private AlertDialogModel alert;
    protected UserSessionData data;
    private TextView mTxvCount;
    private String count = "0";
    protected LayerDrawable icon;

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
                launchActivity(this, LoginActivity.class);
                break;
            case R.string.action_empty_pending_alert:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem itemCart = menu.findItem(R.id.action_alert);
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
    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_alert:
                showProgress();
                PendingAlertManager manager = new PendingAlertManager();
                manager.getPendingAlertList(this);
                return true;
            case R.id.action_pending_notification:
                NotificationAlertFragment pendingNotification = NotificationAlertFragment.newInstance(-1);
                pendingNotification.show(getSupportFragmentManager(), "");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(ArrayList<PendingAlertViewModel> data) {
        hideProgress();
        if (data == null || data.size() == 0) {
            alert = Utility.prepareDialogObj("", getString(R.string.txt_empty_pending_alert), getString(R.string.btn_ok), "", R.string.action_empty_pending_alert, false);
            Utility.showDialog(this, this, alert);
            return;
        }
        PendingAlertFragment pendingAlert = PendingAlertFragment.newInstance(data);
        pendingAlert.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        hideProgress();
        alert = Utility.prepareDialogObj("", getString(R.string.txt_empty_pending_alert), getString(R.string.btn_ok), "", R.string.action_empty_pending_alert, false);
        Utility.showDialog(this, this, alert);
    }

    /**
     * Init adviser view
     * @param mTxvAdviserName Text view for adviser name
     * @param mLLAdviserLogo  Adviser view container view
     */
    public void initAdviserView(TextView mTxvAdviserName, View mLLAdviserLogo) {
        RepDetails adviserDetails = data.getRepDetails();
        if (adviserDetails == null) {
            return;
        }
        if (data.getUserType() == AppConstant.USER_TYPE_ADVISER) {
            mLLAdviserLogo.setVisibility(View.GONE);
            return;
        }
        if (adviserDetails.getFirstName() != null) {
            String name = adviserDetails.getFirstName() + " " + (adviserDetails.getLastName() != null ? adviserDetails.getLastName() : "");
            mTxvAdviserName.setText(name);
        }
    }
}
