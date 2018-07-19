package com.wealthbook.android.screen.myallocation.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wealthbook.android.R;
import com.wealthbook.android.screen.myallocation.model.InvestmentItemType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestmentAdapter extends RecyclerView.Adapter<InvestmentAdapter.ViewHolder> {
    private List<InvestmentItemType> mList;

    public InvestmentAdapter(List<InvestmentItemType> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.investment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InvestmentItemType item = mList.get(position);
        if (item.getName() != null) {
            holder.mTxvFundName.setVisibility(View.VISIBLE);
            holder.mTxvFundName.setText(item.getName());
        } else {
            holder.mTxvFundName.setVisibility(View.GONE);
        }
        if (item.getY() != null) {
            holder.mTxvFundPercent.setVisibility(View.VISIBLE);
            holder.mTxvFundPercent.setText(String.valueOf(item.getY()) + "%");
        } else {
            holder.mTxvFundPercent.setVisibility(View.GONE);
        }
        GradientDrawable bgShape = (GradientDrawable) holder.mTxvCircle.getBackground();
        if (item.getColor() != null) {
            bgShape.setColor(Color.parseColor(item.getColor()));
        } else {
            bgShape.setColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<InvestmentItemType> investmentList) {
        this.mList = investmentList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txv_circle)
        TextView mTxvCircle;

        @BindView(R.id.txv_fund_name)
        TextView mTxvFundName;

        @BindView(R.id.txv_fund_percent)
        TextView mTxvFundPercent;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
