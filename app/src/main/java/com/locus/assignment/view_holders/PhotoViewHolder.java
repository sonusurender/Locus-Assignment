package com.locus.assignment.view_holders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.locus.assignment.R;
import com.locus.assignment.interfaces.OnCaptureImageListener;
import com.locus.assignment.main.SingleImageActivity;
import com.locus.assignment.models.ItemModel;
import com.locus.assignment.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sonu on 14:29, 2019-05-01
 * Copyright (c) 2019 . All rights reserved.
 */
public class PhotoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.photo_image_view)
    ImageView imageView;
    @BindView(R.id.photo_title_label)
    TextView titleLabel;
    @BindView(R.id.remove_photo_item)
    ImageView removePhoto;

    private Context context;
    private OnCaptureImageListener onCaptureImageListener;

    public PhotoViewHolder(Context context, @NonNull View itemView, OnCaptureImageListener onCaptureImageListener) {
        super(itemView);
        this.context = context;
        this.onCaptureImageListener = onCaptureImageListener;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final ItemModel itemModel) {
        titleLabel.setText(itemModel.getTitle());

        if (itemModel.getImageUri() != null) {

            ImageUtils.loadImageUrl(context, imageView, itemModel.getImageUri());

            removePhoto.setVisibility(View.VISIBLE);
            removePhoto.setOnClickListener(view -> {
                itemModel.setImageUri(null);
                ImageUtils.loadImageUrl(context, imageView, itemModel.getImageUri());
                removePhoto.setVisibility(View.GONE);
            });
        } else {
            removePhoto.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.default_image);
        }

        //click event over imageview
        imageView.setOnClickListener(view -> {
            //if uri is not null open activity else open camera
            if (itemModel.getImageUri() != null) {
                SingleImageActivity.openSingleImageActivity(context, itemModel.getImageUri().toString());
            } else {
                if (onCaptureImageListener != null) {
                    onCaptureImageListener.captureImage(getAdapterPosition());
                }
            }
        });
    }
}
