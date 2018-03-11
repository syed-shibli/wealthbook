package com.example.daffolapmac.wealthbook.screen.updates.view;

import android.content.Context;
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

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.screen.adviserprofile.view.AdviserProfileActivity;
import com.example.daffolapmac.wealthbook.screen.home.view.HomeActivity;
import com.example.daffolapmac.wealthbook.screen.updates.adapter.UpdateAdapter;
import com.example.daffolapmac.wealthbook.screen.updates.manager.UpdateManager;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateRes;
import com.example.daffolapmac.wealthbook.screen.updates.presenter.IUpdateScreenPresenter;
import com.example.daffolapmac.wealthbook.screen.updates.presenter.UpdatePresenter;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;
import com.example.daffolapmac.wealthbook.widget.RecyclerItemClickListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link UpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IUpdateView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.txv_adviser_name)
    TextView mTxvAdviserName;

    @BindView(R.id.swipe_refresh_view)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeActivity mActivity;
    private UpdateAdapter mAdapter;
    private List<UpdateRes> mUpdateList = new ArrayList<>();
    private IUpdateScreenPresenter mUpdatePresenter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment UpdateFragment.
     */
    public static UpdateFragment newInstance() {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUpdatePresenter = new UpdatePresenter(this, new UpdateManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setListener();
        viewInitialize();
        setUpRecyclerView();
        mUpdatePresenter.fetchUpdateItemList(true);
    }

    /**
     * Set up recycler view for update list
     */
    private void setUpRecyclerView() {
        mAdapter = new UpdateAdapter(mUpdateList);
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
                /*NewsItem newsItem = mNewsList.get(position);
                Intent newsIntent = new Intent(getActivity(), NewsDetailsActivity.class);
                newsIntent.putExtra(TAG_NEWS_ITEM, newsItem);
                mActivity.launchActivity(newsIntent);*/
            }
        }));
    }

    /**
     * Initialize view here
     */
    private void viewInitialize() {
        UserSessionData data = SessionManager.getNewInstance().readSession();
        if (data.getRepDetails() != null && data.getRepDetails().getFirstName() != null) {
            String name = data.getRepDetails().getFirstName() + " " + (data.getRepDetails().getLastName() != null ? data.getRepDetails().getLastName() : "");
            mTxvAdviserName.setText(name);
        }
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
        mUpdatePresenter.disconnect();
    }

    @OnClick(R.id.adviser_container)
    public void openAdviserProfile() {
        mActivity.launchActivity(mActivity, AdviserProfileActivity.class);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mUpdatePresenter.fetchUpdateItemList(false);
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
    public void refreshUpdateListView(List<UpdateRes> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (data == null || data.size() == 0) {
            return;
        }
        mUpdateList.clear();
        mUpdateList.addAll(data);
        mAdapter.addAll(mUpdateList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(int error) {
        mActivity.showSnackBar(error, mActivity);
    }
}
