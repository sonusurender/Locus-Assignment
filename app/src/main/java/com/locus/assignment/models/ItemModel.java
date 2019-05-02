package com.locus.assignment.models;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sonu on 14:19, 2019-05-01
 * Copyright (c) 2019 . All rights reserved.
 */
public class ItemModel {

    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("dataMap")
    private DataMapModel dataMapModel;

    private Uri imageUri;
    private int selectedIndex = -1;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DataMapModel getDataMapModel() {
        return dataMapModel;
    }

    public void setDataMapModel(DataMapModel dataMapModel) {
        this.dataMapModel = dataMapModel;
    }


    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public class DataMapModel {
        @SerializedName("options")
        private List<String> optionsList;

        public List<String> getOptionsList() {
            return optionsList;
        }

        public void setOptionsList(List<String> optionsList) {
            this.optionsList = optionsList;
        }

        @Override
        public String toString() {
            return "DataMapModel{" +
                    "optionsList=" + optionsList +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", dataMapModel=" + dataMapModel +
                ", imageUri=" + imageUri +
                ", selectedIndex=" + selectedIndex +
                ", comment='" + comment + '\'' +
                '}';
    }
}
