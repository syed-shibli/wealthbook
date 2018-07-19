package com.wealthbook.android.screen.updates.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wealthbook.android.R;
import com.wealthbook.android.screen.updates.model.UpdateRes;
import com.wealthbook.android.utils.AppConstant;
import com.wealthbook.android.utils.BitmapTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.ViewHolder> {
    private List<UpdateRes> mList;
    private Context mContext;

    public UpdateAdapter(List<UpdateRes> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UpdateRes itemUpdate = mList.get(position);
        Picasso.with(mContext)
                .load(itemUpdate.getFeaturedPage())
                .placeholder(R.drawable.ic_placeholder)
                .transform(new BitmapTransform(AppConstant.MAX_WIDTH, AppConstant.MAX_HEIGHT))
                .resize(AppConstant.size, AppConstant.size)
                .centerInside()
                .error(R.drawable.ic_placeholder)
                .into(holder.mImgUpdate);
        holder.mTxvUpdateTitle.setText(itemUpdate.getTitle());
        holder.mTxvUpdateContent.setText(itemUpdate.getDatePublished());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<UpdateRes> list){
        this.mList.addAll(list);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txv_update_title)
        TextView mTxvUpdateTitle;

        @BindView(R.id.txv_update_content)
        TextView mTxvUpdateContent;

        @BindView(R.id.img_update)
        ImageView mImgUpdate;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
