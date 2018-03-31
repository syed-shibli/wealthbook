package com.example.daffolapmac.wealthbook.screen.news.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.common.BaseActivityImpl;
import com.example.daffolapmac.wealthbook.screen.adviserprofile.view.AdviserProfileActivity;
import com.example.daffolapmac.wealthbook.screen.news.model.NewsItem;
import com.example.daffolapmac.wealthbook.usersession.SessionManager;
import com.example.daffolapmac.wealthbook.usersession.UserSessionData;
import com.example.daffolapmac.wealthbook.utils.AppConstant;
import com.example.daffolapmac.wealthbook.utils.BitmapTransform;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailsActivity extends BaseActivityImpl {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.txv_news_title)
    TextView mTxvNewsTitle;

    @BindView(R.id.txv_news_source)
    TextView mTxvNewsSource;

    @BindView(R.id.txv_news_category)
    TextView mTxvNewsCategory;

    @BindView(R.id.img_news)
    ImageView mImgNews;

    @BindView(R.id.txv_news_content)
    TextView mTxvNewsContent;

    @BindView(R.id.txv_adviser_name)
    TextView mTxvAdviserName;

    @BindView(R.id.adviser_logo_container)
    LinearLayout mLLAdviserLogo;

    private NewsItem newsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setListener();
        initAdviserView(mTxvAdviserName, mLLAdviserLogo);
        getIntentData();
        initializeView();
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
     * View initialize
     */
    private void initializeView() {
        if (newsItem != null) {
            mTxvNewsTitle.setText(newsItem.getHeadline());
            if (newsItem.getSource() != null && newsItem.getSource().getName() != null) {
                mTxvNewsSource.setText(getString(R.string.txt_source, newsItem.getSource().getName()));
            }
            if (newsItem.getCategories() != null && newsItem.getCategories().size() != 0 && newsItem.getCategories().get(0).getName() != null) {
                mTxvNewsCategory.setText(getString(R.string.txt_category, newsItem.getCategories().get(0).getName()));
            }
            Picasso.with(this)
                    .load(newsItem.getImage())
                    .placeholder(R.mipmap.news_image_placeholder)
                    .transform(new BitmapTransform(AppConstant.MAX_WIDTH, AppConstant.MAX_HEIGHT))
                    .centerInside()
                    .resize(AppConstant.size, AppConstant.size)
                    .error(R.mipmap.news_image_placeholder)
                    .into(mImgNews);
            mTxvNewsContent.setText(newsItem.getStory());
        }
        if (data == null) {
            return;
        }
        if (data.getmCompanyName() != null) {
            setTitle(data.getmCompanyName());
        }
    }

    /**
     * Get parent data from intent
     */
    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            newsItem = bundle.getParcelable(NewsFragment.TAG_NEWS_ITEM);
        }
    }

    @OnClick(R.id.adviser_container)
    public void openAdviserProfile() {
        launchActivity(this, AdviserProfileActivity.class);
    }
}
