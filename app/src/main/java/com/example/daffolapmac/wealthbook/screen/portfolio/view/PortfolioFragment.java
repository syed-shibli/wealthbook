package com.example.daffolapmac.wealthbook.screen.portfolio.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.screen.home.view.HomeActivity;
import com.example.daffolapmac.wealthbook.screen.portfolio.adapter.MyPortfolioAdapter;
import com.example.daffolapmac.wealthbook.screen.portfolio.manager.MyPortfolioManager;
import com.example.daffolapmac.wealthbook.screen.portfolio.model.AllPortfolioRes;
import com.example.daffolapmac.wealthbook.screen.portfolio.model.Datum;
import com.example.daffolapmac.wealthbook.screen.portfolio.presenter.IMyPortfolioScreenPresenter;
import com.example.daffolapmac.wealthbook.screen.portfolio.presenter.MyPortfolioPresenter;
import com.example.daffolapmac.wealthbook.widget.RecyclerItemClickListener;
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
public class PortfolioFragment extends Fragment implements IPortfolioFragmentView {

    @BindView(R.id.header)
    View mHeaderView;

    @BindView(R.id.table_header)
    View mTableHeader;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private MyPortfolioAdapter mAdapter;
    private HomeActivity mActivity;
    private List<Datum> mPortfolioItemList = new ArrayList<>();
    private IMyPortfolioScreenPresenter mPortfolioPresenter;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setUpRecyclerView();
        mPortfolioPresenter.reqAllPortfolio();
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
     * Initialize header view of portfolio
     * @param data Data model of all portfolio
     */
    private void initializeHeaderView(AllPortfolioRes data) {
        if (data == null) {
            return;
        }
        if (data.getEod() != null) {
            ((TextView) mHeaderView.findViewById(R.id.txv_eod_value)).setText(getString(R.string.txt_eod_value, getString(R.string.dolor) + data.getEod()));
        } else {
            ((TextView) mHeaderView.findViewById(R.id.txv_eod_value)).setText(getString(R.string.txt_eod_value, ""));
        }
        if (data.getTotalPrice() != null) {
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
                mActivity.showSnackBar("Selected Position: " + position, mActivity);
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
        initializeHeaderView(data);
        if (data != null && data.getData() != null && data.getData().size() != 0) {
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
    public void onError(int error) {
        mActivity.showSnackBar(error, mActivity);
        if (mPortfolioItemList.size() == 0) {
            mPortfolioItemList.clear();
            mAdapter.addAll(mPortfolioItemList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
