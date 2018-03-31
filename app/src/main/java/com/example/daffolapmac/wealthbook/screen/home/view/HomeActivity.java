package com.example.daffolapmac.wealthbook.screen.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.AlertDialogModel;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.myallocation.view.MyAllocationFragment;
import com.example.daffolapmac.wealthbook.screen.news.view.NewsFragment;
import com.example.daffolapmac.wealthbook.screen.portfolio.view.PortfolioFragment;
import com.example.daffolapmac.wealthbook.screen.profile.view.ProfileFragment;
import com.example.daffolapmac.wealthbook.screen.updates.view.UpdateFragment;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;
import com.example.daffolapmac.wealthbook.utils.AppConstant;
import com.example.daffolapmac.wealthbook.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivityImpl
        implements NavigationView.OnNavigationItemSelectedListener, ProfileFragment.IProfileFragmentListener {

    private static final String VIEW_STATE_KEY = "view_state_key";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private int mSelectedNav = 1;
    private UserSessionData sessionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        sessionData = SessionManager.getNewInstance().readSession();
        setSupportActionBar(mToolbar);
        setUpSideNav();
        // Set default view my allocation frag in home activity
        if (sessionData.getUserType() == AppConstant.USER_TYPE_ADVISER) {
            hideItem();
        }
        onNavigationItemSelected(mNavigationView.getMenu().getItem(mSelectedNav));
        updateProfileView();
    }

    /**
     * Hide some navigation item when user login as adviser
     */
    private void hideItem() {
        Menu navMenu = mNavigationView.getMenu();
        navMenu.findItem(R.id.nav_my_allocation).setVisible(false);
    }

    /**
     * Update user name and email on side menu
     */
    private void updateProfileView() {
        View headerLayout = mNavigationView.getHeaderView(0);
        TextView mTxvName = headerLayout.findViewById(R.id.txv_name);
        TextView mTxvEmail = headerLayout.findViewById(R.id.txv_email);
        if (sessionData == null) {
            return;
        }
        Log.d("Token : ", sessionData.getmToken());
        if (sessionData.getmFirstName() != null) {
            mTxvName.setText(sessionData.getmFirstName());
        }
        if (sessionData.getmEmail() != null) {
            mTxvEmail.setText(sessionData.getmEmail());
        }
        if (sessionData.getmCompanyName() != null) {
            setTitle(sessionData.getmCompanyName());
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
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_my_allocation:
                mSelectedNav = 0;
                replaceFragment(MyAllocationFragment.newInstance());
                break;
            case R.id.nav_my_portfoilios:
                mSelectedNav = 1;
                replaceFragment(PortfolioFragment.newInstance());
                break;
            case R.id.nav_updates:
                mSelectedNav = 2;
                replaceFragment(UpdateFragment.newInstance());
                break;
            case R.id.nav_news:
                mSelectedNav = 3;
                replaceFragment(NewsFragment.newInstance());
                break;
            case R.id.nav_profile:
                mSelectedNav = 4;
                replaceFragment(ProfileFragment.newInstance());
                break;
            case R.id.nav_logout:
                mSelectedNav = 5;
                AlertDialogModel alert = Utility.prepareDialogObj(getString(R.string.alert), getString(R.string.txt_logout_message), getString(R.string.txt_logout_yes), getString(R.string.txt_logout_cancel), R.string.action_logout, false);
                Utility.showDialog(this, this, alert);
                break;
            default:
                replaceFragment(MyAllocationFragment.newInstance());
        }
        menuItem.setChecked(true);
        mDrawer.closeDrawers();
        return true;
    }

    /**
     * Replace fragment with selected nav view from side menu
     * @param fragment Selected view
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    @Override
    public void onProfileInteraction() {
        // TODO on profile interaction
    }
}
