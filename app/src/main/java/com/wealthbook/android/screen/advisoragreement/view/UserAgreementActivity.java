package com.wealthbook.android.screen.advisoragreement.view;

import android.os.Bundle;
import android.widget.TextView;

import com.wealthbook.android.R;
import com.wealthbook.android.common.BaseActivityImpl;
import com.wealthbook.android.screen.advisoragreement.manager.UserAgreementManager;
import com.wealthbook.android.screen.advisoragreement.presenter.IUserAgreementScreenPresenter;
import com.wealthbook.android.screen.advisoragreement.presenter.UserAgreementPresenter;
import com.wealthbook.android.screen.home.view.HomeActivity;
import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.usersession.UserSessionData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAgreementActivity extends BaseActivityImpl implements IUserAgreementView {

    @BindView(R.id.txv_agreement)
    TextView mTxvAgreementText;

    private IUserAgreementScreenPresenter mPresenter;
    private boolean isAccept;
    private UserSessionData session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_agreement);
        ButterKnife.bind(this);
        session = SessionManager.getNewInstance().readSession();
        viewInitialize();
        mPresenter = new UserAgreementPresenter(this, new UserAgreementManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disconnect();
    }

    /**
     * Initialize default view
     */
    private void viewInitialize() {
        if (session != null && session.getUserAgreement() != null && session.getUserAgreement().getAgreementText() != null) {
            mTxvAgreementText.setText(session.getUserAgreement().getAgreementText());
        }
    }

    @OnClick(R.id.btn_accept)
    public void agreementAccept() {
        isAccept = true;
        mPresenter.acceptDeclineAgreement(session.getmUserId(), 1);
    }

    @OnClick(R.id.btn_decline)
    public void agreementDecline() {
        isAccept = false;
        mPresenter.acceptDeclineAgreement(session.getmUserId(), 0);
    }

    @Override
    public void showLoader() {
        showProgress();
    }

    @Override
    public void hideLoader() {
        hideProgress();
    }

    @Override
    public void agreementStatus() {
        if (isAccept) {
            launchActivity(this, HomeActivity.class);
        }
        finish();
    }

    @Override
    public void onError(int error) {
        showSnackBar(error, this);
    }
}
