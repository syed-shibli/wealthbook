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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.notificationalert.manager.PendingNotificationManager;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.LatestPortfolioReviewData;
import com.example.daffolapmac.wealthbook.screen.notificationalert.model.LatestPortfolioReviewRes;
import com.example.daffolapmac.wealthbook.screen.notificationalert.presenter.IPendingNotificationScreenPresenter;
import com.example.daffolapmac.wealthbook.screen.notificationalert.presenter.PendingNotificationPresenter;
import com.example.daffolapmac.wealthbook.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationAlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationAlertFragment extends DialogFragment implements INotificationAlertView {

    @BindView(R.id.web_view)
    WebView mWebView;

    @BindView(R.id.txv_customer_name)
    TextView mTxvCustomerName;

    @BindView(R.id.txv_product_name)
    TextView mTxvProductName;

    @BindView(R.id.txv_update_status_date)
    TextView mTxvUpdatedDate;

    @BindView(R.id.btn_container_view)
    LinearLayout mLLViewContainer;

    @BindView(R.id.txv_accepted_succ)
    TextView mTxvAcceptSuccess;

    private int id;
    private IPendingNotificationScreenPresenter mPresenter;
    private BaseActivityImpl mActivity;
    private LatestPortfolioReviewData mPendingAlertData;
    private int isAccept = 0;

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
        return inflater.inflate(R.layout.fragment_notification_alert, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter.reqLatestPortfolioReview(id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivityImpl) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
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
        params.width = widthInDpToPx();
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
        return metrics.heightPixels - (metrics.heightPixels / 10);
    }

    /**
     * Width of dialog fragment
     * @return Return width
     */
    private int widthInDpToPx() {
        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        return metrics.widthPixels - (metrics.widthPixels / 20);
    }

    /**
     * Display pi char
     * @param from From view chart
     * @param to   To view chat
     */
    private void displayPieChartView(String from, String to, String legend) {
        String showLegend = legend.equalsIgnoreCase("1") ? "true" : "false";
        String chartContent = Utility.createContentForNotificationAlert(from, to, showLegend);
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
    public void bindAcceptDeclineViewModel() {
        if (isAccept == 1) {
            // Accepted
            this.mPendingAlertData.setWbStatusId(1);
        } else if (isAccept == 2) {
            // Declined
            this.mPendingAlertData.setWbStatusId(2);
        } else {
            // Nothing happened
            this.mPendingAlertData.setWbStatusId(0);
        }
        viewInitialize(mPendingAlertData);
    }

    @Override
    public void onError(int error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindLatestPortfolioReviewViewModel(LatestPortfolioReviewRes data) {
        if (data != null) {
            this.mPendingAlertData = data.getData();
            viewInitialize(mPendingAlertData);
            displayPieChartView(data.getFrom(), data.getTo(), data.getShowLegend());
        }
    }

    /**
     * Initialize view
     * @param customerDetails Latest review data
     */
    private void viewInitialize(LatestPortfolioReviewData customerDetails) {
        if (customerDetails == null) {
            return;
        }
        if (customerDetails.getWbCustomerName() != null) {
            mTxvCustomerName.setText(customerDetails.getWbCustomerName());
        }
        if (customerDetails.getWbVaProductName() != null) {
            mTxvProductName.setText(customerDetails.getWbVaProductName());
        }
        if (customerDetails.getStatusDate() != null) {
            mTxvUpdatedDate.setText(getString(R.string.txt_portfolio_change, customerDetails.getStatusDate()));
        }
        if (customerDetails.getWbStatusId() == 0) {
            mTxvAcceptSuccess.setVisibility(View.GONE);
            mLLViewContainer.setVisibility(View.VISIBLE);
        } else if (customerDetails.getWbStatusId() == 1) {
            mTxvAcceptSuccess.setVisibility(View.VISIBLE);
            mTxvAcceptSuccess.setText(getString(R.string.txt_accepted_successfully, "Accepted"));
            mLLViewContainer.setVisibility(View.GONE);
        } else if (customerDetails.getWbStatusId() == 2) {
            mTxvAcceptSuccess.setVisibility(View.VISIBLE);
            mTxvAcceptSuccess.setText(getString(R.string.txt_accepted_successfully, "Declined"));
            mLLViewContainer.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_accept)
    public void btnAccept() {
        isAccept = 1;
        mPresenter.reqPendingNotification(mPendingAlertData.getWbVaUserCustomerPortfolioHistoryId(), 1);
    }

    @OnClick(R.id.btn_decline)
    public void btnDecline() {
        isAccept = 2;
        mPresenter.reqPendingNotification(mPendingAlertData.getWbVaUserCustomerPortfolioHistoryId(), 2);
    }

}