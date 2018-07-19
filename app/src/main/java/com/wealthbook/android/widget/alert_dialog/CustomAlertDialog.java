package com.wealthbook.android.widget.alert_dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wealthbook.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlertDialogListener} interface
 * to handle interaction events.
 * Use the {@link CustomAlertDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomAlertDialog extends DialogFragment {

    ListView mListView;

    private static final String TAG_LIST = "list";
    private AlertDialogListener mListener;
    private ArrayList<String> mList;
    private ArrayAdapter<String> mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CustomAlertDialog.
     */
    public static CustomAlertDialog newInstance(@NonNull ArrayList<String> list) {
        CustomAlertDialog fragment = new CustomAlertDialog();
        Bundle args = new Bundle();
        args.putStringArrayList(TAG_LIST, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mList = getArguments().getStringArrayList(TAG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aler_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.list_view);
        initializeView(mList);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if(window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = heightInDpToPx();
        window.setAttributes(params);
    }

    /**
     * View initialize
     * @param list List of portfolio
     */
    private void initializeView(ArrayList<String> list) {
        mAdapter = new ArrayAdapter<>(getContext(), R.layout.list_item, list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                mListener.onItemSelect(pos);
            }
        });
    }

    public void setListener(AlertDialogListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    private int heightInDpToPx() {
        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        return metrics.heightPixels-(metrics.heightPixels / 4);
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
    public interface AlertDialogListener {
        void onItemSelect(int pos);
    }
}
