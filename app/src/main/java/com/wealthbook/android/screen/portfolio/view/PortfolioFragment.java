package com.wealthbook.android.screen.portfolio.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wealthbook.android.R;
import com.wealthbook.android.screen.detailportfolio.view.PortfolioDetailActivity;
import com.wealthbook.android.screen.home.view.HomeActivity;
import com.wealthbook.android.screen.portfolio.adapter.MyPortfolioAdapter;
import com.wealthbook.android.screen.portfolio.manager.MyPortfolioManager;
import com.wealthbook.android.screen.portfolio.model.AllPortfolioRes;
import com.wealthbook.android.screen.portfolio.model.Datum;
import com.wealthbook.android.screen.portfolio.presenter.IMyPortfolioScreenPresenter;
import com.wealthbook.android.screen.portfolio.presenter.MyPortfolioPresenter;
import com.wealthbook.android.widget.RecyclerItemClickListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PortfolioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PortfolioFragment extends Fragment implements IPortfolioFragmentView, SwipeRefreshLayout.OnRefreshListener {

    private static final String VIEW_STATE_KEY = "view_state_key";

    @BindView(R.id.header)
    View mHeaderView;

    @BindView(R.id.table_header)
    View mTableHeader;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh_view)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MyPortfolioAdapter mAdapter;
    private HomeActivity mActivity;
    private List<Datum> mPortfolioItemList = new ArrayList<>();
    private IMyPortfolioScreenPresenter mPortfolioPresenter;
    private AllPortfolioRes resData;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PortfolioFragment.
     */
    public static PortfolioFragment newInstance() {
        PortfolioFragment fragment = new PortfolioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPortfolioPresenter = new MyPortfolioPresenter(this, new MyPortfolioManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPortfolioPresenter.reqAllPortfolio(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setListener();
        setUpRecyclerView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity) {
            mActivity = (HomeActivity) context;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPortfolioPresenter.disconnect();
    }

    /**
     * Set listener
     */
    private void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * Initialize header view of portfolio
     * @param data Data model of all portfolio
     */
    private void initializeHeaderView(AllPortfolioRes data) {
        ((TextView) mHeaderView.findViewById(R.id.txv_portfolio_title)).setText(getString(R.string.nav_my_portfolios));
        if (data != null && data.getFormatedTotalPrice() != null) {
            ((TextView) mHeaderView.findViewById(R.id.txv_eod_value)).setText(getString(R.string.txt_eod_value,  data.getFormatedTotalPrice()));
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
        mAdapter = new MyPortfolioAdapter(mPortfolioItemList);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(ActivityCompat.getColor(getActivity(), R.color.colorNewsItemDivider))
                .sizeResId(R.dimen.divider)
                .showLastDivider()
                .build());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), PortfolioDetailActivity.class);
                intent.putExtra(Intent.EXTRA_TITLE, mPortfolioItemList.get(position).getName());
                intent.putExtra(Intent.EXTRA_UID, mPortfolioItemList.get(position).getId());
                mActivity.startActivity(intent);
            }
        }));
    }

    @Override
    public void showLoader() {
        mActivity.showProgress();
    }

    @Override
    public void hideLoader() {
        mActivity.hideProgress();
    }

    @Override
    public void bindDataToView(AllPortfolioRes data) {
        mSwipeRefreshLayout.setRefreshing(false);
        this.resData = data;
        initializeHeaderView(data);
        if (data != null && data.getData() != null && data.getData().size() != 0) {
            mPortfolioItemList.clear();
            mPortfolioItemList.addAll(data.getData());
        } else {
            if (mPortfolioItemList.size() == 0) {
                mPortfolioItemList.clear();
            }
        }
        mAdapter.addAll(mPortfolioItemList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        mSwipeRefreshLayout.setRefreshing(false);
        mActivity.showSnackBar(error, mActivity);
        if (mPortfolioItemList.size() == 0) {
            mPortfolioItemList.clear();
            mAdapter.addAll(mPortfolioItemList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPortfolioPresenter.reqAllPortfolio(false);
    }
}
