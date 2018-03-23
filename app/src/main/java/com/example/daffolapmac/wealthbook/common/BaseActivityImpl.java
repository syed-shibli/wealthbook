package com.example.daffolapmac.wealthbook.common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.api.ErrorResponse;
import com.example.daffolapmac.wealthbook.screen.login.view.LoginActivity;
import com.example.daffolapmac.wealthbook.screen.notificationalert.view.NotificationAlertFragment;
import com.example.daffolapmac.wealthbook.screen.pendingalert.manager.IPendingAlertResponseReceiver;
import com.example.daffolapmac.wealthbook.screen.pendingalert.manager.PendingAlertManager;
import com.example.daffolapmac.wealthbook.screen.pendingalert.model.PendingAlertViewModel;
import com.example.daffolapmac.wealthbook.screen.pendingalert.view.PendingAlertFragment;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.utils.Utility;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BaseActivityImpl extends AppCompatActivity implements UIBase, IDialogClickListener, IPendingAlertResponseReceiver {

    private WBLoader mLoader;
    private AlertDialogModel alert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return true;
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
}
