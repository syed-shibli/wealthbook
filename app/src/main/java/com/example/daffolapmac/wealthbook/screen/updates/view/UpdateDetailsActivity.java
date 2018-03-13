package com.example.daffolapmac.wealthbook.screen.updates.view;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.adviserprofile.view.AdviserProfileActivity;
import com.example.daffolapmac.wealthbook.screen.news.view.NewsFragment;
import com.example.daffolapmac.wealthbook.screen.updates.adapter.UpdateDetailsAdapter;
import com.example.daffolapmac.wealthbook.screen.updates.manager.UpdateManager;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateDetailsImage;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateRes;
import com.example.daffolapmac.wealthbook.screen.updates.presenter.UpdatePresenter;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateDetailsActivity extends BaseActivityImpl implements IUpdateDetailsView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.txv_adviser_name)
    TextView mTxvAdviserName;

    private UpdateDetailsAdapter mAdapter;
    private List<UpdateDetailsImage> mUpdateDetailsImageList = new ArrayList<>();
    private String mUpdateDetailsID;
    private UpdatePresenter mUpdatePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setListener();

        getIntentData();
        setUpRecyclerView();
        initializeView();
        mUpdatePresenter = new UpdatePresenter(this, new UpdateManager());
        mUpdatePresenter.getUpdateDetails(mUpdateDetailsID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUpdatePresenter.disconnect();
    }

    /**
     * Set listener
     */
    private void setListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * View initialize
     */
    private void initializeView(){
        UserSessionData data = SessionManager.getNewInstance().readSession();
        if (data == null) {
            return;
        }
        if (data.getmCompanyName() != null) {
            setTitle(data.getmCompanyName());
        }
        if (data.getRepDetails() != null && data.getRepDetails().getFirstName() != null) {
            String name = data.getRepDetails().getFirstName() + " " + (data.getRepDetails().getLastName() != null ? data.getRepDetails().getLastName() : "");
            mTxvAdviserName.setText(name);
        }
    }
    /**
     * To setup recycler view for news list
     */
    private void setUpRecyclerView() {
        mAdapter = new UpdateDetailsAdapter(mUpdateDetailsImageList);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(ActivityCompat.getColor(this, R.color.colorNewsItemDivider))
                .sizeResId(R.dimen.divider)
                .build());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Get parent data from intent
     */
    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUpdateDetailsID = bundle.getString(UpdateFragment.TAG_UPDATE_ID);
        }
    }

    @OnClick(R.id.adviser_container)
    public void openAdviserProfile() {
        launchActivity(this, AdviserProfileActivity.class);
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
    public void refreshUpdateDetailsListView(UpdateRes data) {
        if (data == null || data.getImages() == null || data.getImages().size() == 0) {
            return;
        }
        mUpdateDetailsImageList.clear();
        mUpdateDetailsImageList.addAll(data.getImages());
        mAdapter.addAll(mUpdateDetailsImageList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(int error) {
        showSnackBar(error, this);
    }
}
