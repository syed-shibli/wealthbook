package com.example.daffolapmac.wealthbook.screen.advisoragreement.view;

import android.os.Bundle;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.manager.UserAgreementManager;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.presenter.IUserAgreementScreenPresenter;
import com.example.daffolapmac.wealthbook.screen.advisoragreement.presenter.UserAgreementPresenter;
import com.example.daffolapmac.wealthbook.screen.home.view.HomeActivity;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;

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
