package com.example.daffolapmac.wealthbook.screen.home.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.AlertDialogModel;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.myallocation.view.MyAllocationFragment;
import com.example.daffolapmac.wealthbook.screen.profile.view.ProfileFragment;
import com.example.daffolapmac.wealthbook.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivityImpl
        implements NavigationView.OnNavigationItemSelectedListener, MyAllocationFragment.OnFragmentInteractionListener, ProfileFragment.IProfileFragmentListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mToolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_check));
        setSupportActionBar(mToolbar);
        setUpSideNav();
        // Set default view my allocation frag in home activity
        onNavigationItemSelected(mNavigationView.getMenu().getItem(0));
        updateProfileView();
    }

    /**
     * Update user name and email on side menu
     */
    private void updateProfileView() {
        View headerLayout = mNavigationView.getHeaderView(0);
        TextView mTxvName = headerLayout.findViewById(R.id.txv_name);
        TextView mTxvEmail = headerLayout.findViewById(R.id.txv_email);
        mTxvName.setText("Name");
        mTxvEmail.setText("abc@gmail.com");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_my_allocation:
                replaceFragment(MyAllocationFragment.newInstance(menuItem.getTitle().toString()));
                break;
            case R.id.nav_my_portfoilios:
                replaceFragment(MyAllocationFragment.newInstance(menuItem.getTitle().toString()));
                break;
            case R.id.nav_updates:
                replaceFragment(MyAllocationFragment.newInstance(menuItem.getTitle().toString()));
                break;
            case R.id.nav_news:
                replaceFragment(MyAllocationFragment.newInstance(menuItem.getTitle().toString()));
                break;
            case R.id.nav_profile:
                replaceFragment(ProfileFragment.newInstance());
                break;
            case R.id.nav_logout:
                AlertDialogModel alert = Utility.prepareDialogObj(getString(R.string.alert), getString(R.string.txt_logout_message), getString(R.string.txt_logout_yes), getString(R.string.txt_logout_cancel), R.string.action_done, false);
                Utility.showDialog(this, this, alert);
                break;
            default:
                replaceFragment(MyAllocationFragment.newInstance(menuItem.getTitle().toString()));
        }
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
        return true;
    }

    /**
     * Replace fragment with selected nav view from side menu
     *
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
