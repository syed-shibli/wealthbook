package com.wealthbook.android.screen.news.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wealthbook.android.R;
import com.wealthbook.android.screen.adviserprofile.view.AdviserProfileActivity;
import com.wealthbook.android.screen.home.view.HomeActivity;
import com.wealthbook.android.screen.news.adapter.NewsAdapter;
import com.wealthbook.android.screen.news.manager.NewsManager;
import com.wealthbook.android.screen.news.model.NewsItem;
import com.wealthbook.android.screen.news.model.NewsRes;
import com.wealthbook.android.screen.news.presenter.INewsScreenPresenter;
import com.wealthbook.android.screen.news.presenter.NewsPresenter;
import com.wealthbook.android.widget.RecyclerItemClickListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment implements INewsViewListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG_NEWS_ITEM = "news_item";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.txv_adviser_name)
    TextView mTxvAdviserName;

    @BindView(R.id.swipe_refresh_view)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.adviser_logo_container)
    LinearLayout mLLAdviserLogo;

    private NewsAdapter mAdapter;
    private HomeActivity mActivity;
    private INewsScreenPresenter mNewsPresenter;
    private List<NewsItem> mNewsList = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment NewsFragment.
     */
    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenter(this, new NewsManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setListener();
        mActivity.initAdviserView(mTxvAdviserName, mLLAdviserLogo);
        setUpRecyclerView();
        mNewsPresenter.fetchNewsReq(true);
    }

    /**
     * Set all listener here
     */
    private void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity) {
            this.mActivity = (HomeActivity) context;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNewsPresenter.disconnect();
    }

    /**
     * To setup recycler view for news list
     */
    private void setUpRecyclerView() {
        mAdapter = new NewsAdapter(mNewsList);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(ActivityCompat.getColor(getActivity(), R.color.colorNewsItemDivider))
                .sizeResId(R.dimen.divider)
                .build());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsItem newsItem = mNewsList.get(position);
                Intent newsIntent = new Intent(getActivity(), NewsDetailsActivity.class);
                newsIntent.putExtra(TAG_NEWS_ITEM, newsItem);
                mActivity.launchActivity(newsIntent);
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
    public void bindNewsListToView(NewsRes data) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (data != null && data.getNewsList() != null && data.getNewsList().size() != 0) {
            mNewsList.clear();
            mNewsList.addAll(data.getNewsList());
            mAdapter.addAll(mNewsList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(@StringRes int error) {
        mSwipeRefreshLayout.setRefreshing(false);
        mActivity.showSnackBar(error, mActivity);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mNewsPresenter.fetchNewsReq(false);
    }

    @OnClick(R.id.adviser_container)
    public void openAdviserProfile() {
        mActivity.launchActivity(mActivity, AdviserProfileActivity.class);
    }
}
