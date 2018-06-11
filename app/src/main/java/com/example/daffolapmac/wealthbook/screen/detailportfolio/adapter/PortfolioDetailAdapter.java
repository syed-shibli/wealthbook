package com.example.daffolapmac.wealthbook.screen.detailportfolio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.Hold;
import com.example.daffolapmac.wealthbook.screen.detailportfolio.model.SectionHeader;
import com.example.daffolapmac.wealthbook.utils.Utility;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PortfolioDetailAdapter extends SectionRecyclerViewAdapter<SectionHeader, Hold, PortfolioDetailAdapter.HeaderViewHolder, PortfolioDetailAdapter.ItemViewHolder> {

    private Context context;
    private AdapterListener listener;

    public PortfolioDetailAdapter(AdapterListener listener, Context context, List<SectionHeader> sectionItemList) {
        super(context, sectionItemList);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public HeaderViewHolder onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.portfolio_detail_adapter_header, sectionViewGroup, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public ItemViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_portfolio_item, childViewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(HeaderViewHolder sectionViewHolder, int sectionPosition, SectionHeader section) {
        sectionViewHolder.mTxvTitle.setText(section.getSectionText());
    }

    @Override
    public void onBindChildViewHolder(ItemViewHolder childViewHolder, int sectionPosition, int childPosition, final Hold item) {
        if (item.getTickerName() != null) {
            childViewHolder.mTxvPortfolioEquity.setText(item.getTickerName());
        }
        if (item.getQuantity() != null) {
            childViewHolder.mTxvQuantity.setText(String.format(Locale.getDefault(), "%d", item.getQuantity()));
        }
        if (item.getFormatedEodPrice() != null) {
            childViewHolder.mTxvEODPrice.setText(String.format("%s", item.getFormatedEodPrice()));
        }
        if (item.getFormatedPurchasePrice() != null) {
            childViewHolder.mTxvEODValue.setText(item.getFormatedPurchasePrice());
        }
        if (item.getTickerWeightPercent() != null) {
            childViewHolder.mTxvAssets.setText(String.format("%s", Utility.decimalFormator(item.getTickerWeightPercent(), "0.00")));
        }
        if (item.getFormatedTickerWeightPercent() != null) {
            childViewHolder.mTxvGainLoss.setText(String.format("%s", item.getFormatedTickerWeightPercent()));
        }
        if (item.getTickerSymbol() != null) {
            childViewHolder.mTxvSymbol.setText(String.format("%s %s", context.getString(R.string.txt_symbol),item.getTickerSymbol()));
        }
        childViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(item.getId(), item.getTickerName(), item.getTickerSymbol());
            }
        });
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txv_equity)
        TextView mTxvPortfolioEquity;

        @BindView(R.id.txv_quantity)
        TextView mTxvQuantity;

        @BindView(R.id.txv_eod_price)
        TextView mTxvEODPrice;

        @BindView(R.id.txv_eod_value)
        TextView mTxvEODValue;

        @BindView(R.id.txv_percent_assets)
        TextView mTxvAssets;

        @BindView(R.id.txv_gain_loss)
        TextView mTxvGainLoss;

        @BindView(R.id.txv_symbol)
        TextView mTxvSymbol;

        private final View rootView;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rootView = itemView;
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txv_title)
        TextView mTxvTitle;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AdapterListener {
        void onItemClick(Integer id, String tickerName, String tickerSymbol);
    }
}
