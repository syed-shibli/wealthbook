package com.example.daffolapmac.wealthbook.screen.detailportfolio.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.adapter.PortfolioDetailAdapter;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.manager.PortfolioDetailManager;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.PortfolioDataItem;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.PortfolioDetailRes;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.SectionHeader;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.presenter.IPortfolioDetailScreenPresenter;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.presenter.PortfolioDetailPresenter;
import com.example.daffolapmac.wealthbook.screen.stockeod.view.StockEodActivity;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PortfolioDetailActivity extends BaseActivityImpl implements IPortfolioDetailView, PortfolioDetailAdapter.AdapterListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String VIEW_STATE_KEY = "view_state_key";
    public static final String ID = "id";
    public static final String TICKER_SYMBOL = "ticker_symbol";
    public static final String TICKER_NAME = "ticker_name";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.header)
    View mHeaderView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh_view)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<SectionHeader> mSectionItemList = new ArrayList<>();
    private String mTitle;
    private IPortfolioDetailScreenPresenter mPresenter;
    private int mID;
    private PortfolioDetailAdapter mAdapter;
    private PortfolioDetailRes resData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getIntentData();
        setListener();
        setToolbarTitle();
        initializeHeaderView(resData);
        setUpRecyclerView();
        mPresenter = new PortfolioDetailPresenter(this, new PortfolioDetailManager());
        mPresenter.reqToGetPortfolioDetail(mID, true);
    }

    /**
     * Set title
     */
    private void setToolbarTitle() {
        UserSessionData data = SessionManager.getNewInstance().readSession();
        if (data == null) {
            return;
        }
        if (data.getmCompanyName() != null) {
            setTitle(data.getmCompanyName());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disconnect();
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
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * Get data from parent activity
     */
    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mTitle = bundle.getString(Intent.EXTRA_TITLE);
            mID = bundle.getInt(Intent.EXTRA_UID);
        }
    }

    /**
     * Initialize header view of portfolio
     * @param data Data model of all portfolio
     */
    private void initializeHeaderView(PortfolioDetailRes data) {
        ((TextView) mHeaderView.findViewById(R.id.txv_portfolio_title)).setText(mTitle);
        if (data != null && data.getFormatedTotalPrice() != null) {
            ((TextView) mHeaderView.findViewById(R.id.txv_eod_value)).setText(getString(R.string.txt_eod_value, data.getFormatedTotalPrice()));
        } else {
            ((TextView) mHeaderView.findViewById(R.id.txv_eod_value)).setText(getString(R.string.txt_eod_value, ""));
        }
        if (data != null && data.getFormatedGainLossPercent() != null) {
            ((TextView) mHeaderView.findViewById(R.id.txv_ytd_value)).setText(getString(R.string.txt_ytd_value, data.getFormatedGainLossPercent()));
        } else {
            ((TextView) mHeaderView.findViewById(R.id.txv_ytd_value)).setText(getString(R.string.txt_ytd_value, ""));
        }
    }

    /**
     * To setup recycler view for news list
     */
    private void setUpRecyclerView() {
        mAdapter = new PortfolioDetailAdapter(this, this, mSectionItemList);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(ActivityCompat.getColor(this, R.color.colorNewsItemDivider))
                .sizeResId(R.dimen.divider)
                .showLastDivider()
                .build());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
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
    public void bindDataToView(PortfolioDetailRes data) {
        mSwipeRefreshLayout.setRefreshing(false);
        initializeHeaderView(data);
        refreshList(data);
    }

    /**
     * Refresh list
     * @param data Data model
     */
    private void refreshList(PortfolioDetailRes data) {
        if (data != null && data.getData() != null && data.getData().size() != 0) {
            this.resData = data;
            mSectionItemList.clear();
            for (PortfolioDataItem item : data.getData()) {
                SectionHeader sections = new SectionHeader(item.getHolds(), item.getCategoryName());
                mSectionItemList.add(sections);
            }
            mAdapter.notifyDataChanged(mSectionItemList);
        }
    }

    @Override
    public void onError(int error) {
        mSwipeRefreshLayout.setRefreshing(false);
        showSnackBar(error, this);
    }

    @Override
    public void onItemClick(Integer id, String tickerName, String tickerSymbol) {
        Intent intent = new Intent(this, StockEodActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(TICKER_NAME, tickerName);
        intent.putExtra(TICKER_SYMBOL, tickerSymbol);
        launchActivity(intent);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.reqToGetPortfolioDetail(mID, false);
    }
}
