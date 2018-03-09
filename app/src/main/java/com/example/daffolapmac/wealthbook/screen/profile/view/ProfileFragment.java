package com.example.daffolapmac.wealthbook.screen.profile.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;

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

    @BindView(R.id.first_name)
    View mFirstNameView;

    @BindView(R.id.last_name)
    View mLastNameView;

    @BindView(R.id.mobile_no)
    View mMobileNoView;

    @BindView(R.id.e_mail)
    View mEmailView;

    @BindView(R.id.country)
    View mCountryCodeView;

    private IProfileFragmentListener mListener;
    private TextView mFirstName;
    private TextView mLastName;
    private TextView mMobileNo;
    private TextView mEmail;
    private TextView mCountry;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
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
        findId();
        viewInitialize();
    }

    /**
     * Initialize view
     */
    private void viewInitialize() {
        // set value
        UserSessionData data = SessionManager.getNewInstance().readSession();
        if (data == null) {
            return;
        }
        if (data.getmFirstName() != null) {
            mFirstName.setText(data.getmFirstName());
        }
        if (data.getmLastName() != null) {
            mLastName.setText(data.getmLastName());
        }
        if (data.getmPhone() != null) {
            mMobileNo.setText(data.getmPhone());
        }
        if (data.getmEmail() != null) {
            mEmail.setText(data.getmEmail());
        }
        if (data.getmCountry() != null) {
            mCountry.setText(data.getmCountry());
        }
    }

    /**
     * Find id for every view
     */
    private void findId() {
        // Get id for label
        ((TextView) mFirstNameView.findViewById(R.id.txv_label)).setText(getString(R.string.lbl_first_name));
        ((TextView) mLastNameView.findViewById(R.id.txv_label)).setText(getString(R.string.lbl_last_name));
        ((TextView) mMobileNoView.findViewById(R.id.txv_label)).setText(getString(R.string.lbl_mobile_no));
        ((TextView) mEmailView.findViewById(R.id.txv_label)).setText(getString(R.string.lbl_email));
        ((TextView) mCountryCodeView.findViewById(R.id.txv_label)).setText(getString(R.string.lbl_country));
        // Get id for value
        mFirstName = (TextView) mFirstNameView.findViewById(R.id.txv_value);
        mLastName = (TextView) mLastNameView.findViewById(R.id.txv_value);
        mMobileNo = (TextView) mMobileNoView.findViewById(R.id.txv_value);
        mEmail = (TextView) mEmailView.findViewById(R.id.txv_value);
        mCountry = (TextView) mCountryCodeView.findViewById(R.id.txv_value);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
}
