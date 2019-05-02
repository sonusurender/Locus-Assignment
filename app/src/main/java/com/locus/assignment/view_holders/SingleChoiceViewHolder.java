package com.locus.assignment.view_holders;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.locus.assignment.R;
import com.locus.assignment.models.ItemModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sonu on 14:29, 2019-05-01
 * Copyright (c) 2019 . All rights reserved.
 */
public class SingleChoiceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.single_choice_title_label)
    TextView titleLabel;
    @BindView(R.id.single_choice_item_radio_group)
    RadioGroup radioGroup;

    private Context context;

    public SingleChoiceViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final ItemModel itemModel) {
        titleLabel.setText(itemModel.getTitle());

        List<String> optionsList = itemModel.getDataMapModel().getOptionsList();

        //add radio group on runtime
        if (optionsList != null && optionsList.size() > 0) {
            radioGroup.removeAllViews();
            radioGroup.setOnCheckedChangeListener(null);
            for (int i = 0; i < optionsList.size(); i++) {
                RadioButton button = new RadioButton(context);
                button.setId(i);
                button.setText(optionsList.get(i));
                button.setChecked(i == itemModel.getSelectedIndex());
                radioGroup.addView(button);
            }

            radioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
                int pos = radioGroup.indexOfChild(radioGroup.findViewById(checkedId));
                itemModel.setSelectedIndex(pos);//update the selected index when radio button is selected
            });
        }

    }
}
