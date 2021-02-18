package com.gadgetsfolk.mypreschool.model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Category {
    private String title;
    private String icon_url;
    private String sound_url;
    private String sub_title;
    private String sub_icon;

    public String getSub_icon() {
        return sub_icon;
    }

    public void setSub_icon(String sub_icon) {
        this.sub_icon = sub_icon;
    }

    public Category() {}

    public String getIcon_url() { return icon_url; }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getSound_url() {
        return sound_url;
    }

    public void setSound_url(String sound_url) {
        this.sound_url = sound_url;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
