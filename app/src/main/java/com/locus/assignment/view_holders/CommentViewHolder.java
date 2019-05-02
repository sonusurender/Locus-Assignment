package com.locus.assignment.view_holders;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.locus.assignment.R;
import com.locus.assignment.models.ItemModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sonu on 14:29, 2019-05-01
 * Copyright (c) 2019 . All rights reserved.
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.comment_title_label)
    TextView titleLabel;
    @BindView(R.id.comment_switch)
    SwitchCompat switchCompat;
    @BindView(R.id.type_comment_input)
    EditText typeComment;

    private Context context;

    public CommentViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final ItemModel itemModel) {
        titleLabel.setText(itemModel.getTitle());

        switchCompat.setOnCheckedChangeListener((compoundButton, b) -> typeComment.setVisibility(b ? View.VISIBLE : View.GONE));

        typeComment.setText(itemModel.getComment());

        typeComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                itemModel.setComment(input);//update comment
            }
        });
    }
}
