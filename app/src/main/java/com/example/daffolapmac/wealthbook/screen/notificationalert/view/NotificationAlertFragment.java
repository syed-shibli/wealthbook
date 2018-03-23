package com.example.daffolapmac.wealthbook.screen.notificationalert.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.notificationalert.manager.PendingNotificationManager;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.LatestPortfolioReviewRes;
import com.example.daffolapmac.wealthbook.screen.notificationalert.presenter.PendingNotificationPresenter;
import com.example.daffolapmac.wealthbook.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationAlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationAlertFragment extends DialogFragment implements INotificationAlertView {

    @BindView(R.id.web_view)
    WebView mWebView;

    private int id;
    private PendingNotificationPresenter mPresenter;
    private BaseActivityImpl mActivity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment NotificationAlertFragment.
     */
    public static NotificationAlertFragment newInstance(int id) {
        NotificationAlertFragment fragment = new NotificationAlertFragment();
        Bundle args = new Bundle();
        args.putInt(Intent.EXTRA_UID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PendingNotificationPresenter(this, new PendingNotificationManager());
        if (getArguments() != null) {
            id = getArguments().getInt(Intent.EXTRA_UID);
        }
        if (savedInstanceState != null) {
            id = savedInstanceState.getInt(Intent.EXTRA_UID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_alert, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (id == -1) {
            mPresenter.reqLatestPortfolioReview();
        } else {
//            mPresenter.reqPendingNotification(id);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivityImpl) context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.disconnect();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Intent.EXTRA_UID, id);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = heightInDpToPx();
        window.setAttributes(params);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            getActivity().finish();
        }
    }

    /**
     * High of dialog fragment
     * @return Return height
     */
    private int heightInDpToPx() {
        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        return metrics.heightPixels - (metrics.heightPixels / 4);
    }

    /**
     * Display pi char
     * @param from From view chart
     * @param to   To view chat
     */
    private void displayPieChartView(String from, String to) {
        String chartContent = Utility.createContentForNotificationAlert(from, to);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.requestFocusFromTouch();
        mWebView.loadDataWithBaseURL("file:///android_asset/", chartContent, "text/html", "utf-8", null);
    }

    @Override
    public void showLoader() {
        mActivity.showProgress();
    }

    @Override
    public void hideLoader() {
        mActivity.hideProgress();
    }

    @Override
    public void bindViewModel() {

    }

    @Override
    public void onError(int error) {
        mActivity.showSnackBar(error, mActivity);
    }

    @Override
    public void bindLatestPortfolioReviewViewModel(LatestPortfolioReviewRes data) {
        if (data != null) {
            displayPieChartView(data.getFrom(), data.getTo());
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface NotificationAlertDialogListener {
        void onItemSelect(int pos);
    }
}
