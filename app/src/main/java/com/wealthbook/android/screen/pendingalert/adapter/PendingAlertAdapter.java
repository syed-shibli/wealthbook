package com.wealthbook.android.screen.pendingalert.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wealthbook.android.R;
import com.wealthbook.android.screen.pendingalert.model.PendingAlertViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingAlertAdapter extends RecyclerView.Adapter<PendingAlertAdapter.ViewHolder> {
    private List<PendingAlertViewModel> mPendingAlertList;
    private Context mContext;

    public PendingAlertAdapter(List<PendingAlertViewModel> list) {
        this.mPendingAlertList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_alert_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PendingAlertViewModel alertModel = mPendingAlertList.get(position);
        if (alertModel.getTitle() != null) {
            holder.mTxvAccountTitle.setText(String.format("%s%s", mContext.getString(R.string.txt_account_title), alertModel.getTitle()));
        } else {
            holder.mTxvAccountTitle.setText(mContext.getString(R.string.txt_account_title));
        }
        if (alertModel.getAccountNumber() != null) {
            holder.mTxvAccountNo.setText(String.format("%s%s", mContext.getString(R.string.txt_account_no), alertModel.getAccountNumber()));
        } else {
            holder.mTxvAccountNo.setText(mContext.getString(R.string.txt_account_no));
        }
        if (alertModel.getAdviserName() != null) {
            holder.mTxvAdviserName.setText(String.format("%s%s", mContext.getString(R.string.txt_adviser_name), alertModel.getAdviserName()));
        } else {
            holder.mTxvAdviserName.setText(mContext.getString(R.string.txt_adviser_name));
        }
        if (alertModel.getAdviserContact() != null) {
            holder.mTxvAdviserContact.setText(String.format("%s%s", mContext.getString(R.string.txt_adviser_contact), alertModel.getAdviserContact()));
        } else {
            holder.mTxvAdviserContact.setText(mContext.getString(R.string.txt_adviser_contact));
        }
        if (alertModel.getAlert() != null) {
            holder.mTxvAlert.setText(String.format("%s%s", mContext.getString(R.string.txt_alert), alertModel.getAlert()));
        } else {
            holder.mTxvAlert.setText(mContext.getString(R.string.txt_alert));
        }
    }

    @Override
    public int getItemCount() {
        return this.mPendingAlertList.size();
    }

    /**
     * Add news data to list
     * @param data List of news data
     */
    public void addAll(List<PendingAlertViewModel> data) {
        this.mPendingAlertList.addAll(data);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txv_account_title)
        TextView mTxvAccountTitle;

        @BindView(R.id.txv_account_no)
        TextView mTxvAccountNo;

        @BindView(R.id.txv_adviser_name)
        TextView mTxvAdviserName;

        @BindView(R.id.txv_adviser_contact)
        TextView mTxvAdviserContact;

        @BindView(R.id.txv_alert)
        TextView mTxvAlert;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

