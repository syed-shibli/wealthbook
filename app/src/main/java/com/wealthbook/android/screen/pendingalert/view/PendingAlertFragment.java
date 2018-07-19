package com.wealthbook.android.screen.pendingalert.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wealthbook.android.R;
import com.wealthbook.android.screen.notificationalert.view.NotificationAlertFragment;
import com.wealthbook.android.screen.pendingalert.adapter.PendingAlertAdapter;
import com.wealthbook.android.screen.pendingalert.model.PendingAlertViewModel;
import com.wealthbook.android.widget.RecyclerItemClickListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PendingAlertFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PendingAlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PendingAlertFragment extends DialogFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private static final String PENDING_ALERT_LIST = "list";
    private ArrayList<PendingAlertViewModel> mPendingAlertList = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param data View model
     * @return A new instance of fragment PendingAlertFragment.
     */
    public static PendingAlertFragment newInstance(ArrayList<PendingAlertViewModel> data) {
        PendingAlertFragment fragment = new PendingAlertFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(PENDING_ALERT_LIST, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPendingAlertList = getArguments().getParcelableArrayList(PENDING_ALERT_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pending_alert, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setUpRecyclerView();
    }

    /**
     * Set up recycler view
     */
    private void setUpRecyclerView() {
        PendingAlertAdapter mAdapter = new PendingAlertAdapter(mPendingAlertList);
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
                NotificationAlertFragment pendingNotification = NotificationAlertFragment.newInstance(mPendingAlertList.get(position).getId());
                pendingNotification.show(getActivity().getSupportFragmentManager(), "");
                dismiss();
            }
        }));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = heightInDpToPx();
        params.width = widthInDpToPx();
        window.setAttributes(params);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            getActivity().finish();
        }
    }

    /**
     * High of dialog fragment
     * @return Return height
     */
    private int heightInDpToPx() {
        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        return metrics.heightPixels - (metrics.heightPixels / 10);
    }

    /**
     * Width of dialog fragment
     * @return Return width
     */
    private int widthInDpToPx() {
        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
