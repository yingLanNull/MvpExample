package com.yinglan.mvpexample.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @function ${desc}
 * @auther: Created by sufei
 * @time: 16/10/18
 */

public class Daily implements Parcelable {

    public List<String> images;
    public int type;
    public int id;
    public int ga_prefix;
    public String title;
    public boolean multipic;

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(int ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.images);
        dest.writeInt(this.type);
        dest.writeInt(this.id);
        dest.writeInt(this.ga_prefix);
        dest.writeString(this.title);
        dest.writeByte(this.multipic ? (byte) 1 : (byte) 0);
    }

    public Daily() {
    }

    protected Daily(Parcel in) {
        this.images = in.createStringArrayList();
        this.type = in.readInt();
        this.id = in.readInt();
        this.ga_prefix = in.readInt();
        this.title = in.readString();
        this.multipic = in.readByte() != 0;
    }

    public static final Creator<Daily> CREATOR = new Creator<Daily>() {
        @Override
        public Daily createFromParcel(Parcel source) {
            return new Daily(source);
        }

        @Override
        public Daily[] newArray(int size) {
            return new Daily[size];
        }
    };
}
