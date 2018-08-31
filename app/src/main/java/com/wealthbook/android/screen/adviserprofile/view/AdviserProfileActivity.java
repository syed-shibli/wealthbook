package com.wealthbook.android.screen.adviserprofile.view;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wealthbook.android.R;
import com.wealthbook.android.common.BaseActivityImpl;
import com.wealthbook.android.screen.login.model.RepDetails;
import com.wealthbook.android.screen.profile.adapter.ProfileAdapter;
import com.wealthbook.android.usersession.SessionManager;
import com.wealthbook.android.usersession.UserSessionData;
import com.wealthbook.android.utils.AppConstant;
import com.wealthbook.android.utils.BitmapTransform;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdviserProfileActivity extends BaseActivityImpl {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.txv_adviser_company_logo)
    ImageView mCompanyLogo;

    @BindView(R.id.img_adviser_logo)
    ImageView mAdvisorLogo;

    @BindView(R.id.txv_name)
    TextView mTxvName;

    @BindView(R.id.txv_company_name)
    TextView mTxvCompanyName;

    @BindView(R.id.mobile_view_container)
    View mMobileViewContainer;

    @BindView(R.id.work_view_container)
    View mWorkViewContainer;

    @BindView(R.id.email_view_container)
    View mEmailViewContainer;

    @BindView(R.id.office_view_container)
    View mOfficeViewContainer;

    @BindView(R.id.txv_declaimer)
    TextView mTxvDeclaimer;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.scroll_view)
    ScrollView mScrollView;

    @BindView(R.id.txv_header)
    TextView mTxvProfileHeader;

    private UserSessionData data;
    private ProfileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adviser_profile);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setListener();
        data = SessionManager.getNewInstance().readSession();
        if (data.getUserType() == AppConstant.USER_TYPE_CLIENT) {
            viewInitialize();
        } else {
            setUpRecyclerView();
        }
        if (data.getCompanyName() != null) {
            setTitle(data.getCompanyName());
        }
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
    private void viewInitialize() {
        mScrollView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mTxvProfileHeader.setVisibility(View.GONE);
        if (data != null && data.getRepDetails() != null) {
            RepDetails adviserDetails = data.getRepDetails();
            //Advisor logo
            Picasso.with(this)
                    .load(adviserDetails.getLogo())
                    .transform(new BitmapTransform(AppConstant.MAX_WIDTH, AppConstant.MAX_HEIGHT))
                    .resize(AppConstant.size, AppConstant.size)
                    .error(R.mipmap.advisor_placeholder)
                    .into(mAdvisorLogo);
            //company logo
            Picasso.with(this)
                    .load(adviserDetails.getCompanyLogo())
                    .transform(new BitmapTransform(AppConstant.MAX_WIDTH, AppConstant.MAX_HEIGHT))
                    .resize(AppConstant.size, AppConstant.size)
                    .error(R.mipmap.ic_launcher)
                    .into(mCompanyLogo);
            String name = adviserDetails.getFirstName();
            if (adviserDetails.getLastName() != null) {
                name = name + " " + adviserDetails.getLastName();
            }
            mTxvName.setText(name);
            if (adviserDetails.getCompanyName() != null) {
                mTxvCompanyName.setText(adviserDetails.getCompanyName());
            }
            ((TextView) mMobileViewContainer.findViewById(R.id.txv_label)).setText(R.string.lbl_mobile_no);
            if (adviserDetails.getMobileNo() != null) {
                ((TextView) mMobileViewContainer.findViewById(R.id.txv_value)).setText(adviserDetails.getMobileNo());
            }
            ((TextView) mWorkViewContainer.findViewById(R.id.txv_label)).setText(R.string.lbl_work);
            if (adviserDetails.getOfficeMobileNo() != null) {
                ((TextView) mWorkViewContainer.findViewById(R.id.txv_value)).setText(adviserDetails.getOfficeMobileNo());
            }
            ((TextView) mEmailViewContainer.findViewById(R.id.txv_label)).setText(R.string.lbl_email);
            if (adviserDetails.getEmail() != null) {
                ((TextView) mEmailViewContainer.findViewById(R.id.txv_value)).setText(adviserDetails.getEmail());
            }
            ((TextView) mOfficeViewContainer.findViewById(R.id.txv_label)).setText(R.string.lbl_office);
            if (adviserDetails.getStreet1() != null) {
                ((TextView) mOfficeViewContainer.findViewById(R.id.txv_value)).setText(adviserDetails.getStreet1());
            }
            if (adviserDetails.getDiclaimerText() != null) {
                mTxvDeclaimer.setText(adviserDetails.getDiclaimerText());
            }
        }
    }

    /**
     * To setup recycler view for news list
     */
    private void setUpRecyclerView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mTxvProfileHeader.setVisibility(View.VISIBLE);
        mScrollView.setVisibility(View.GONE);
        mAdapter = new ProfileAdapter(data.getCurrentUserAttributes());
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(ActivityCompat.getColor(this, R.color.colorNewsItemDivider))
                .sizeResId(R.dimen.divider)
                .build());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
