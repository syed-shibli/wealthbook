package com.example.daffolapmac.wealthbook.utils;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.AlertDialogModel;
import com.example.daffolapmac.wealthbook.common.IDialogClickListener;
import com.example.daffolapmac.wealthbook.widget.wp_dialog.IWPDialogListener;
import com.example.daffolapmac.wealthbook.widget.wp_dialog.WPDialogView;

import java.util.regex.Pattern;

public class Utility {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "^(\\+\\d{1,3}[- ]?)?\\d{10,14}$";
    private static WPDialogView mDialog;
    private static IDialogClickListener mListener;
    private static AlertDialogModel mAlertModel;

    /**
     * To verify email is valid or not
     *
     * @param email Email text
     * @return True/false
     */
    public static boolean isEmailValid(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * To verify pass is valid or not
     *
     * @param pass Password text
     * @return True/false
     */
    public static boolean isPasswordValid(String pass) {
        return pass.length() >= 6;
    }

    /**
     * Create alert model object
     *
     * @param icon          Icon
     * @param title         Title of alert
     * @param msg           Message body
     * @param positiveBtn   Positive button
     * @param negBtn        Negative button
     * @param action        Action
     * @param isCancellable is Cancellable
     * @return Return object
     */
    public static AlertDialogModel prepareDialogObj(String title, String msg,
                                                    String positiveBtn, String negBtn,
                                                    @StringRes int action, boolean isCancellable) {
        AlertDialogModel sDialogModel = new AlertDialogModel();
        sDialogModel.setTitle(title);
        sDialogModel.setMsg(msg);
        sDialogModel.setYesBtn(positiveBtn);
        sDialogModel.setNoBtn(negBtn);
        sDialogModel.setAction(action);
        sDialogModel.setCancelable(isCancellable);
        return sDialogModel;
    }

    public static void showDialog(AppCompatActivity activity, IDialogClickListener listener, AlertDialogModel alertModel) {
        mAlertModel = alertModel;
        mListener = listener;
        if (mDialog != null && mDialog.isVisible()) {
            mDialog.dismiss();
            mDialog = null;
        }
        mDialog = WPDialogView.newInstance(alertModel.getTitle(),
                alertModel.getMsg(),
                alertModel.getYesBtn(),
                alertModel.getNoBtn());
        mDialog.setDialogListener(mCallback);
        mDialog.setCancelable(alertModel.isCancelable());
        if (mDialog != null && !mDialog.isVisible() && !mDialog.isAdded()) {
            mDialog.show(activity.getSupportFragmentManager(), "");
        }
    }

    private static IWPDialogListener mCallback = new IWPDialogListener() {
        @Override
        public void onClickPositiveButton() {
            if (mListener != null) {
                if (mDialog != null && mDialog.isVisible()) {
                    mDialog.dismiss();
                    mDialog = null;
                }
                mListener.onDialogClick(mAlertModel.getAction());
            }
        }

        @Override
        public void onClickNegativeButton() {
            if (mListener != null) {
                if (mDialog != null && mDialog.isVisible()) {
                    mDialog.dismiss();
                }
                mListener.onDialogClick(R.string.cancel);
            }
        }
    };
}
