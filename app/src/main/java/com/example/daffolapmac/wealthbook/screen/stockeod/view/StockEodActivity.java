package com.example.daffolapmac.wealthbook.screen.stockeod.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.view.PortfolioDetailActivity;
import com.example.daffolapmac.wealthbook.screen.stockeod.manager.StockEodManager;
import com.example.daffolapmac.wealthbook.screen.stockeod.presenter.StockEodPresenter;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;
import com.example.daffolapmac.wealthbook.utils.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockEodActivity extends BaseActivityImpl implements IStockEodView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.txv_ticker_name)
    TextView mTxvTickerName;

    @BindView(R.id.web_view)
    WebView mWebView;

    private StockEodPresenter mStockPresenter;
    private String tickerName;
    private String tickerSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_eod);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setToolbarTitle();
        setListener();
        mStockPresenter = new StockEodPresenter(this, new StockEodManager());
        if (getIntent() != null) {
            int id = getIntent().getIntExtra(PortfolioDetailActivity.ID, 0);
            tickerName = getIntent().getStringExtra(PortfolioDetailActivity.TICKER_NAME);
            tickerSymbol = getIntent().getStringExtra(PortfolioDetailActivity.TICKER_SYMBOL);
            mTxvTickerName.setText(tickerName);
            mStockPresenter.getSelectedStockEod(id, tickerSymbol);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStockPresenter.disconnect();
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
    }

    /**
     * Display single line chart view
     * @param data list of data
     */
    private void displaySingleLineChartView(List data) {
        String chartContent = Utility.createContentForSingleLineChart(data);
        chartContent = chartContent.replace("titleText", tickerSymbol);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.requestFocusFromTouch();
        mWebView.loadDataWithBaseURL("file:///android_asset/", chartContent, "text/html", "utf-8", null);
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
    public void bindStockEodValueToView(List data) {
        mTxvTickerName.setVisibility(View.GONE);
        displaySingleLineChartView(data);
    }

    @Override
    public void onError(int error) {
        mTxvTickerName.setVisibility(View.GONE);
        showSnackBar(error, this);
    }
}
