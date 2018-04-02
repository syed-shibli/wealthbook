package com.example.daffolapmac.wealthbook.screen.portfolio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.screen.portfolio.model.Datum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPortfolioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_1 = 1;
    private static final int VIEW_TYPE_2 = 2;
    private List<Datum> mList;
    private Context mContext;

    public MyPortfolioAdapter(List<Datum> mList) {
        this.mList = mList;
    }

    @Override
    public MyPortfolioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = null;
        switch (viewType) {
            case VIEW_TYPE_1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.txv_no_view, parent, false);
                break;
            case VIEW_TYPE_2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfolio_item, parent, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_1:
                ViewHolderEmpty viewHolderEmpty = (ViewHolderEmpty) holder;
                viewHolderEmpty.mTxvEmptyMessage.setText(R.string.txt_portfolio_empty_message);
                break;
            case VIEW_TYPE_2:
                Datum item = mList.get(position);
                ViewHolder viewHolder = (ViewHolder) holder;
                if (item.getName() != null) {
                    viewHolder.mTxvPortfolioName.setText(item.getName());
                }
                if (item.getFormatedCurrentPrice() != null) {
                    viewHolder.mTxvMarketClose.setText(item.getFormatedCurrentPrice());
                }
                if (item.getFormatedGainLossPercent() != null) {
                    viewHolder.mTxvYTDRate.setText(item.getFormatedGainLossPercent().replace("%", ""));
                }
                if (item.getFormattedNetReturn() != null) {
                    viewHolder.mTxvPrYrNet.setText(item.getFormattedNetReturn().replace("%", ""));
                }
                if (item.getFormattedLifetimeReturn() != null) {
                    viewHolder.mTxvLifetimeRate.setText(item.getFormattedLifetimeReturn().replace("%", ""));
                }
                if (item.getInceptionDate() != null) {
                    viewHolder.mTxvInceptDate.setText(item.getInceptionDate());
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList == null || mList.size() == 0) {
            return VIEW_TYPE_1;
        }
        return VIEW_TYPE_2;
    }

    public void addAll(List<Datum> list) {
        this.mList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txv_portfolio_name)
        TextView mTxvPortfolioName;

        @BindView(R.id.txv_market_close)
        TextView mTxvMarketClose;

        @BindView(R.id.txv_ytd_rate)
        TextView mTxvYTDRate;

        @BindView(R.id.txv_pr_yr_net)
        TextView mTxvPrYrNet;

        @BindView(R.id.txv_lifetime_rate)
        TextView mTxvLifetimeRate;

        @BindView(R.id.txv_incept_date)
        TextView mTxvInceptDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewHolderEmpty extends RecyclerView.ViewHolder {

        @BindView(R.id.txv_empty_item)
        TextView mTxvEmptyMessage;

        public ViewHolderEmpty(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
