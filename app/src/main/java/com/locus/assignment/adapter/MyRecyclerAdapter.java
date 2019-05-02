package com.locus.assignment.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.locus.assignment.interfaces.OnCaptureImageListener;
import com.locus.assignment.models.ItemModel;
import com.locus.assignment.R;
import com.locus.assignment.view_holders.CommentViewHolder;
import com.locus.assignment.view_holders.PhotoViewHolder;
import com.locus.assignment.view_holders.SingleChoiceViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonu on 14:18, 2019-05-01
 * Copyright (c) 2019 . All rights reserved.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemModel> itemModelList = new ArrayList<>(0);

    /* Different View Types */
    private static final int PHOTO_TYPE = 1;
    private static final int SINGLE_CHOICE_TYPE = 2;
    private static final int COMMENT_TYPE = 3;

    /* Different Types */
    private static final String PHOTO = "PHOTO";
    private static final String SINGLE_CHOICE = "SINGLE_CHOICE";
    private static final String COMMENT = "COMMENT";

    private OnCaptureImageListener onCaptureImageListener;

    public void setOnCaptureImageListener(OnCaptureImageListener onCaptureImageListener) {
        this.onCaptureImageListener = onCaptureImageListener;
    }

    @Override
    public int getItemViewType(int position) {
        ItemModel itemModel = itemModelList.get(position);
        if (itemModel.getType().equalsIgnoreCase(PHOTO)) {
            return PHOTO_TYPE;
        } else if (itemModel.getType().equalsIgnoreCase(SINGLE_CHOICE)) {
            return SINGLE_CHOICE_TYPE;
        } else if (itemModel.getType().equalsIgnoreCase(COMMENT)) {
            return COMMENT_TYPE;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case PHOTO_TYPE:
                View photoView = layoutInflater.inflate(R.layout.photo_item_layout, parent, false);
                return new PhotoViewHolder(parent.getContext(), photoView,onCaptureImageListener);
            case SINGLE_CHOICE_TYPE:
                View singleChoiceView = layoutInflater.inflate(R.layout.single_choice_item_layout, parent, false);
                return new SingleChoiceViewHolder(parent.getContext(), singleChoiceView);
            case COMMENT_TYPE:
                View commentView = layoutInflater.inflate(R.layout.comment_item_layout, parent, false);
                return new CommentViewHolder(parent.getContext(), commentView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemModel itemModel = itemModelList.get(holder.getAdapterPosition());
        if (holder instanceof PhotoViewHolder) {
            ((PhotoViewHolder) holder).bindData(itemModel);
        } else if (holder instanceof SingleChoiceViewHolder) {
            ((SingleChoiceViewHolder) holder).bindData(itemModel);
        } else if (holder instanceof CommentViewHolder) {
            ((CommentViewHolder) holder).bindData(itemModel);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public List<ItemModel> getItemModelList() {
        return itemModelList;
    }

    /**
     * swap item model data
     * @param itemModelList
     */
    public void swapData(List<ItemModel> itemModelList) {
        if (itemModelList != null && itemModelList.size() > 0) {
            this.itemModelList.clear();
            this.itemModelList.addAll(itemModelList);
            notifyDataSetChanged();
        }
    }

    /**
     * update image after captured
     * @param position to be updated
     * @param file uri of the image
     */
    public void updateImage(int position, Uri file) {
        if (position != -1 && file != null) {
            ItemModel itemModel = itemModelList.get(position);
            itemModel.setImageUri(file);
            itemModelList.set(position, itemModel);
            notifyItemChanged(position);
        }
    }
}
