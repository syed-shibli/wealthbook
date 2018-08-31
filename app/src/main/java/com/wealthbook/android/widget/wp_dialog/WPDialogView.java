package com.wealthbook.android.widget.wp_dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wealthbook.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WPDialogView extends DialogFragment {

    @BindView(R.id.dialog_title)
    TextView mWPTxvTitle;

    @BindView(R.id.dialog_body)
    TextView mWPTxvBody;

    @BindView(R.id.btn_positive)
    Button mWPBtnPositive;

    @BindView(R.id.btn_negative)
    Button mWPBtnNegative;

    @BindView(R.id.view)
    View mSepView;

    public static final String ARG_TITLE = "title";
    public static final String ARG_BODY = "body";
    public static final String ARG_POSBTN = "posBtn";
    public static final String ARG_NEGBTN = "negBtn";

    private IWPDialogListener mIWPDialogListener;
    private String mTitle;
    private String mBody;
    private String mPosBtn;
    private String mNegBtn = null;

    String mStrBody;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    public static WPDialogView newInstance(String title,
                                           String body,
                                           String posBtn,
                                           String negBtn) {
        WPDialogView f = new WPDialogView();

        Bundle args = new Bundle();

        args.putString(ARG_TITLE, title);
        args.putString(ARG_BODY, body);
        args.putString(ARG_POSBTN, posBtn);
        if (negBtn != null && !negBtn.equals("")) {
            args.putString(ARG_NEGBTN, negBtn);
        }

        f.setArguments(args);
        return f;
    }

    /**
     * Get arguments from bundle
     * @param savedInstanceState Saved bundle state
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mBody = getArguments().getString(ARG_BODY);
            mPosBtn = getArguments().getString(ARG_POSBTN);
            mNegBtn = getArguments().getString(ARG_NEGBTN);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.wp_dialog_view, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        viewInitialize();
    }

    /**
     * Initialize view
     */
    private void viewInitialize() {
        if (!mTitle.isEmpty()) {
            mWPTxvTitle.setVisibility(View.VISIBLE);
            mWPTxvTitle.setText(mTitle);
        } else {
            mWPTxvTitle.setVisibility(View.GONE);
        }
        if (mBody != null) {
            mWPTxvBody.setVisibility(View.VISIBLE);
            mWPTxvBody.setText(mBody);
        } else {
            mWPTxvBody.setVisibility(View.GONE);
        }
        mWPBtnPositive.setText(mPosBtn);
        if (mNegBtn != null) {
            mSepView.setVisibility(View.VISIBLE);
            mWPBtnNegative.setVisibility(View.VISIBLE);
            mWPBtnNegative.setText(mNegBtn);
        } else {
            mSepView.setVisibility(View.GONE);
            mWPBtnNegative.setVisibility(View.GONE);
        }
    }

    /**
     * To set listener for dialog
     * @param listener Listener
     */
    public void setDialogListener(IWPDialogListener listener) {
        mIWPDialogListener = listener;
    }

    /**
     * Invoke when clicked on positive btn
     */
    @OnClick(R.id.btn_positive)
    public void onClickPositiveBtn() {
        mIWPDialogListener.onClickPositiveButton();
    }

    /**
     * Invoke when clicked on negative btn
     */
    @OnClick(R.id.btn_negative)
    public void onClickNegativeBtn() {
        mIWPDialogListener.onClickNegativeButton();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, null);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            getActivity().finish();
        }
    }
}
