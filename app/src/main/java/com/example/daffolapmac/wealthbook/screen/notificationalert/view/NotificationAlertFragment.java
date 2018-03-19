package com.example.daffolapmac.wealthbook.screen.notificationalert.view;


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
import com.example.daffolapmac.wealthbook.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationAlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationAlertFragment extends DialogFragment {

    @BindView(R.id.web_view)
    WebView mWebView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment NotificationAlertFragment.
     */
    public static NotificationAlertFragment newInstance() {
        NotificationAlertFragment fragment = new NotificationAlertFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        displayPieChartView();
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
     */
    private void displayPieChartView() {
        String chartContent = Utility.createContentForNotificationAlert("[{\n" +
                "            name: 'Chrome',\n" +
                "            y: 61.41,\n" +
                "            sliced: true,\n" +
                "            selected: true\n" +
                "        }, {\n" +
                "            name: 'Internet Explorer',\n" +
                "            y: 11.84\n" +
                "        }, {\n" +
                "            name: 'Firefox',\n" +
                "            y: 10.85\n" +
                "        }, {\n" +
                "            name: 'Edge',\n" +
                "            y: 4.67\n" +
                "        }, {\n" +
                "            name: 'Safari',\n" +
                "            y: 4.18\n" +
                "        }, {\n" +
                "            name: 'Other',\n" +
                "            y: 7.05\n" +
                "        }]");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.requestFocusFromTouch();
        mWebView.loadDataWithBaseURL("file:///android_asset/", chartContent, "text/html", "utf-8", null);
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
