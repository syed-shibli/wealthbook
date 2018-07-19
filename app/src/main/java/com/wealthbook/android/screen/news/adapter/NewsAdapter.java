package com.wealthbook.android.screen.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.wealthbook.android.R;
import com.wealthbook.android.screen.news.model.NewsItem;
import com.wealthbook.android.utils.AppConstant;
import com.wealthbook.android.utils.BitmapTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsItem> mNewsList = new ArrayList<>();
    private Context mContext;

    public NewsAdapter(List<NewsItem> list) {
        this.mNewsList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem newsItem = mNewsList.get(position);
        holder.mTxvNewsTitle.setText(newsItem.getHeadline());
        holder.mTxvNewsContent.setText(newsItem.getStory());
        Picasso.with(mContext)
                .load(newsItem.getImage())
                .placeholder(R.drawable.ic_placeholder)
                .transform(new BitmapTransform(AppConstant.MAX_WIDTH, AppConstant.MAX_HEIGHT))
                .resize(AppConstant.size, AppConstant.size)
                .centerInside()
                .error(R.drawable.ic_placeholder)
                .into(holder.mNewsLogo);
    }

    @Override
    public int getItemCount() {
        return this.mNewsList.size();
    }

    /**
     * Add news data to list
     *
     * @param data List of news data
     */
    public void addAll(List<NewsItem> data) {
        this.mNewsList.addAll(data);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txv_news_title)
        TextView mTxvNewsTitle;

        @BindView(R.id.txv_news_content)
        TextView mTxvNewsContent;

        @BindView(R.id.img_news_logo)
        ImageView mNewsLogo;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
