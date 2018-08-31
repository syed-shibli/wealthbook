package com.wealthbook.android.screen.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wealthbook.android.R;
import com.wealthbook.android.common.AlertDialogModel;
import com.wealthbook.android.common.BaseActivityImpl;
import com.wealthbook.android.screen.home.manager.HomeManager;
import com.wealthbook.android.screen.home.presenter.HomePresenter;
import com.wealthbook.android.screen.myallocation.view.MyAllocationFragment;
import com.wealthbook.android.screen.news.view.NewsFragment;
import com.wealthbook.android.screen.portfolio.view.PortfolioFragment;
import com.wealthbook.android.screen.profile.view.ProfileFragment;
import com.wealthbook.android.screen.updates.view.UpdateFragment;
import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.usersession.UserSessionData;
import com.wealthbook.android.utils.AppConstant;
import com.wealthbook.android.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivityImpl
        implements ProfileFragment.IProfileFragmentListener, IViewListener {

    private static final String VIEW_STATE_KEY = "view_state_key";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.nav_my_allocation)
    TextView mNavMyAllocation;

    @BindView(R.id.my_allocation_divider)
    View mAllocationDivider;

    private int mSelectedNav = 3;
    private UserSessionData sessionData;
    private HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mHomePresenter = new HomePresenter(this, new HomeManager());
        sessionData = SessionManager.getNewInstance().readSession();
        setSupportActionBar(mToolbar);
        setUpSideNav();
        // Set default view my allocation frag in home activity
        if (sessionData.getUserType() == AppConstant.USER_TYPE_ADVISER) {
            hideItem();
        }
        updateProfileView();
        mHomePresenter.reqAlertCount(); // Get alert count
        replaceFragment(NewsFragment.newInstance());
    }

    private void handlePushTypeDialog(Intent intent) {
        if (intent.hasExtra(AppConstant.ID)) {
            showPendingAlert(getIntent().getIntExtra(AppConstant.ID, -1));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHomePresenter.disconnect();
    }

    /**
     * Hide some navigation item when user login as adviser
     */
    private void hideItem() {
        mNavMyAllocation.setVisibility(View.GONE);
        mAllocationDivider.setVisibility(View.GONE);
    }

    /**
     * Update user name and email on side menu
     */
    private void updateProfileView() {
        if (sessionData == null) {
            return;
        }
        if (sessionData.getCompanyName() != null) {
            setTitle(sessionData.getCompanyName());
        }
    }

    /**
     * Set up navigation menu and add listener
     */
    private void setUpSideNav() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.nav_my_allocation)
    void onMyAllocationSelect() {
        mSelectedNav = 0;
        replaceFragment(MyAllocationFragment.newInstance());
    }

    @OnClick(R.id.nav_my_portfoilios)
    void onMyPortfoliosSelect() {
        mSelectedNav = 1;
        replaceFragment(PortfolioFragment.newInstance());
    }

    @OnClick(R.id.nav_updates)
    void onMyUpdateSelect() {
        mSelectedNav = 2;
        replaceFragment(UpdateFragment.newInstance());
    }

    @OnClick(R.id.nav_news)
    void onMyNewSelect() {
        mSelectedNav = 3;
        replaceFragment(NewsFragment.newInstance());
    }

    @OnClick(R.id.nav_profile)
    void onMyProfileSelect() {
        mSelectedNav = 4;
        replaceFragment(ProfileFragment.newInstance());
    }

    @OnClick(R.id.nav_logout)
    void onLogoutSelect() {
        mSelectedNav = 5;
        AlertDialogModel alert = Utility.prepareDialogObj(getString(R.string.alert), getString(R.string.txt_logout_message), getString(R.string.txt_logout_yes), getString(R.string.txt_logout_cancel), R.string.action_logout, false);
        Utility.showDialog(getSupportFragmentManager(), this, alert);
    }

    /**
     * Replace fragment with selected nav view from side menu
     * @param fragment Selected view
     */
    private void replaceFragment(Fragment fragment) {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawers();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    @Override
    public void onProfileInteraction() {
        // TODO on profile interaction
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
    public void bindPendingAlertCountToView(int count) {
        setBadgeCount(this, icon, count);
        handlePushTypeDialog(getIntent());
    }

    @Override
    public void onError(String error) {
        showSnackBar(error, this);
        handlePushTypeDialog(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handlePushTypeDialog(intent);
    }
}
