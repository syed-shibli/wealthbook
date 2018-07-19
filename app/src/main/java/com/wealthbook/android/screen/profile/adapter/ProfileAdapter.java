package com.wealthbook.android.screen.profile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wealthbook.android.R;
import com.wealthbook.android.screen.login.model.CurrentUserAttribute;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private List<CurrentUserAttribute> mProfileValueList;

    public ProfileAdapter(List<CurrentUserAttribute> list) {
        this.mProfileValueList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CurrentUserAttribute item = mProfileValueList.get(position);
        holder.mTxvLabel.setText(item.getLabel());
        holder.mTxvValue.setText(item.getValue());
    }

    @Override
    public int getItemCount() {
        return this.mProfileValueList.size();
    }

    /**
     * Add news data to list
     *
     * @param data List of news data
     */
    public void addAll(List<CurrentUserAttribute> data) {
        this.mProfileValueList.addAll(data);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txv_label)
        TextView mTxvLabel;

        @BindView(R.id.txv_value)
        TextView mTxvValue;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
