package com.example.daffolapmac.wealthbook.screen.myallocation.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.screen.home.view.HomeActivity;
import com.example.daffolapmac.wealthbook.screen.myallocation.adapter.InvestmentAdapter;
import com.example.daffolapmac.wealthbook.screen.myallocation.manager.MyAllocationManager;
import com.example.daffolapmac.wealthbook.screen.myallocation.model.AccountItem;
import com.example.daffolapmac.wealthbook.screen.myallocation.model.InvestmentItemType;
import com.example.daffolapmac.wealthbook.screen.myallocation.model.MyAllocationRes;
import com.example.daffolapmac.wealthbook.screen.myallocation.presenter.IMyAllocationScreenPresenter;
import com.example.daffolapmac.wealthbook.screen.myallocation.presenter.MyAllocationPresenter;
import com.example.daffolapmac.wealthbook.utils.Utility;
import com.example.daffolapmac.wealthbook.widget.alert_dialog.CustomAlertDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
 * Use the {@link MyAllocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAllocationFragment extends DialogFragment implements IMyAllocationView, CustomAlertDialog.AlertDialogListener {

    @BindView(R.id.txv_header1)
    TextView mTxvHeader;

    @BindView(R.id.view_account_holder)
    View mAccountHolder;

    @BindView(R.id.view_account_title)
    View mAccountTitle;

    @BindView(R.id.view_account_no)
    View mAccountNo;

    @BindView(R.id.view_adviser_name)
    View mAdviserName;

    @BindView(R.id.view_adviser_contact)
    View mAdviserContact;

    @BindView(R.id.view_account_portfolio)
    View mAccountPortfolio;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.webView)
    WebView mWebView;

    private HomeActivity mActivity;
    private IMyAllocationScreenPresenter mAllocationPresenter;
    private List<AccountItem> mAllAllocationList;
    private ArrayList<String> mPortfolioList;
    private CustomAlertDialog mDialog;
    private int mSelectedPortfolio = 0;
    private InvestmentAdapter mAdapter;
    private List<InvestmentItemType> investmentList = new ArrayList<>();

    public static MyAllocationFragment newInstance() {
        MyAllocationFragment fragment = new MyAllocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAllocationPresenter = new MyAllocationPresenter(this, new MyAllocationManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_allocation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setUpRecyclerView();
        mAllocationPresenter.reqAllAllocation();
    }

    /**
     * Set up recycler view
     */
    private void setUpRecyclerView() {
        mAdapter = new InvestmentAdapter(investmentList);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(ActivityCompat.getColor(getActivity(), R.color.colorHint))
                .sizeResId(R.dimen.divider)
                .build());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
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
        mAllocationPresenter.disconnect();
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
    public void bindView(MyAllocationRes data) {
        if (data != null && data.getAccounts() != null && data.getAccounts().size() != 0) {
            mAllAllocationList = data.getAccounts();
            mPortfolioList = createPortfolioList(mAllAllocationList);
            viewInitialize(mSelectedPortfolio);
        }
    }

    @Override
    public void onError(int error) {
        mActivity.showSnackBar(error, mActivity);
    }

    @OnClick(R.id.select_speaker_spinner)
    public void openPortfolioSelectionList() {
        if (mPortfolioList != null && mPortfolioList.size() != 0) {
            mDialog = CustomAlertDialog.newInstance(mPortfolioList);
            mDialog.setListener(this);
            displayAlert();
        }
    }

    /**
     * Create alert for portfolio list selection
     */
    private void displayAlert() {
        if (mDialog != null && !mDialog.isVisible() && !mDialog.isAdded()) {
            mDialog.show(mActivity.getSupportFragmentManager(), "");
        }
    }

    /**
     * Initialize view here
     * @param pos Selected position
     */
    private void viewInitialize(int pos) {
        AccountItem data = mAllAllocationList.get(pos);
        if (data == null) {
            return;
        }
        displayChartTableView(data);
        displayPieChartView(data);
        mTxvHeader.setText(data.getDisplayName());
        // Set label name
        ((TextView) mAccountHolder.findViewById(R.id.txv_label)).setText(R.string.txt_acc_holder);
        ((TextView) mAccountTitle.findViewById(R.id.txv_label)).setText(R.string.txt_acc_title);
        ((TextView) mAccountNo.findViewById(R.id.txv_label)).setText(R.string.txt_acc_no);
        ((TextView) mAdviserName.findViewById(R.id.txv_label)).setText(R.string.txt_adv_name);
        ((TextView) mAdviserContact.findViewById(R.id.txv_label)).setText(R.string.txt_adv_contact);
        ((TextView) mAccountPortfolio.findViewById(R.id.txv_label)).setText(R.string.txt_portfolio_as_of);

        // Set value name
        ((TextView) mAccountHolder.findViewById(R.id.txv_value)).setText(data.getAccountHolder());
        ((TextView) mAccountTitle.findViewById(R.id.txv_value)).setText(data.getAccountTitle());
        ((TextView) mAccountNo.findViewById(R.id.txv_value)).setText(data.getAccountNumber());
        ((TextView) mAdviserName.findViewById(R.id.txv_value)).setText(data.getAdvisorName());
        ((TextView) mAdviserContact.findViewById(R.id.txv_value)).setText(data.getAdvisorContactNo());
        ((TextView) mAccountPortfolio.findViewById(R.id.txv_value)).setText(data.getPortfolioAsOf());
    }

    /**
     * Display pi char
     * @param data Pi char data model
     */
    private void displayPieChartView(AccountItem data) {
        if (data.getAccountAllocations() != null && data.getAccountAllocations().getCurrentAllocation() != null) {
            String chartContent = Utility.createChartContent(data.getAccountAllocations().getCurrentAllocation());
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mWebView.requestFocusFromTouch();
            mWebView.loadDataWithBaseURL("file:///android_asset/", chartContent, "text/html", "utf-8", null);
        }
    }

    /**
     * Display char view
     * @param data Selected portfolio model
     */
    private void displayChartTableView(AccountItem data) {
        if (data.getAccountAllocations() != null) {
            investmentList = createInvestmentTypeList(data.getAccountAllocations().getCurrentAllocation());
        }
        if (investmentList == null || investmentList.size() == 0) {
            mRecyclerView.setVisibility(View.GONE);
        }
        mAdapter.addAll(investmentList);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Create investment type list from selected model
     * @param currentAllocation Current allocation string
     */
    private List<InvestmentItemType> createInvestmentTypeList(String currentAllocation) {
        return new Gson().fromJson(currentAllocation, new TypeToken<List<InvestmentItemType>>() {
        }.getType());
    }

    /**
     * create list for no of portfolio for selection
     * @param data Allocation array
     */
    private ArrayList<String> createPortfolioList(List<AccountItem> data) {
        ArrayList<String> portFolioList = new ArrayList<>();
        for (AccountItem item : data) {
            portFolioList.add(item.getDisplayName());
        }
        return portFolioList;
    }

    @Override
    public void onItemSelect(int pos) {
        mSelectedPortfolio = pos;
        viewInitialize(pos);
        if (mDialog != null && !mDialog.isHidden()) {
            mDialog.dismiss();
        }
    }
}
