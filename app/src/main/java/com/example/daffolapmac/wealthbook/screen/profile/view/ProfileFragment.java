package com.example.daffolapmac.wealthbook.screen.profile.view;

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

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.screen.login.model.CurrentUserAttribute;
import com.example.daffolapmac.wealthbook.screen.profile.adapter.ProfileAdapter;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IProfileFragmentListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private IProfileFragmentListener mListener;
    private ProfileAdapter mAdapter;
    private UserSessionData data;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        data = SessionManager.getNewInstance().readSession();
        setUpRecyclerView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IProfileFragmentListener) {
            mListener = (IProfileFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IProfileFragmentListener");
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface IProfileFragmentListener {
        void onProfileInteraction();
    }

    /**
     * To setup recycler view for news list
     */
    private void setUpRecyclerView() {
        List<CurrentUserAttribute> list = createProfileData(data);
        mAdapter = new ProfileAdapter(list);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(ActivityCompat.getColor(getActivity(), R.color.colorNewsItemDivider))
                .sizeResId(R.dimen.divider)
                .build());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Return formatted data for user profile
     * @param data USer session data
     * @return
     */
    private List<CurrentUserAttribute> createProfileData(UserSessionData data) {
        List<CurrentUserAttribute> list = new ArrayList<>();
        if (data.getCurrentUserAttributes() == null) {
            return list;
        }
        for (CurrentUserAttribute item : data.getCurrentUserAttributes()) {
            if (item != null && item.getValue() != null && !item.getValue().isEmpty()) {
                list.add(item);
            }
        }
        return list;
    }
}
