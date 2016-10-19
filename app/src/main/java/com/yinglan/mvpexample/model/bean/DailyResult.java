package com.yinglan.mvpexample.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @function ${desc}
 * @auther: Created by sufei
 * @time: 16/10/18
 */

public class DailyResult implements Parcelable {
    public int date;
    public List<Daily> stories;

    public List<Daily> getStories() {
        return stories;
    }

    public void setStories(List<Daily> stories) {
        this.stories = stories;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.date);
        dest.writeTypedList(this.stories);
    }

    public DailyResult() {
    }

    protected DailyResult(Parcel in) {
        this.date = in.readInt();
        this.stories = in.createTypedArrayList(Daily.CREATOR);
    }

    public static final Creator<DailyResult> CREATOR = new Creator<DailyResult>() {
        @Override
        public DailyResult createFromParcel(Parcel source) {
            return new DailyResult(source);
        }

        @Override
        public DailyResult[] newArray(int size) {
            return new DailyResult[size];
        }
    };
}
