package com.example.daffolapmac.wealthbook.screen.updates.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.daffolapmac.wealthbook.R;
import com.example.daffolapmac.wealthbook.screen.updates.model.UpdateDetailsImage;
import com.example.daffolapmac.wealthbook.utils.AppConstant;
import com.example.daffolapmac.wealthbook.utils.BitmapTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class UpdateDetailsAdapter extends RecyclerView.Adapter<UpdateDetailsAdapter.ViewHolder> {
    private List<UpdateDetailsImage> mList;
    private Context mContext;

    public UpdateDetailsAdapter(List<UpdateDetailsImage> list) {
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UpdateDetailsImage itemUpdate = mList.get(position);
        Picasso.with(mContext)
                .load(itemUpdate.getPath())
                .transform(new BitmapTransform(AppConstant.MAX_WIDTH, AppConstant.MAX_HEIGHT))
                .resize(AppConstant.size, AppConstant.size)
                .centerInside()
                .error(R.mipmap.news_image_placeholder)
                .into(holder.mImgUpdate);
        PhotoViewAttacher pAttacher;
        pAttacher = new PhotoViewAttacher(holder.mImgUpdate);
        pAttacher.update();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<UpdateDetailsImage> list) {
        this.mList.addAll(list);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_update_details)
        ImageView mImgUpdate;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

