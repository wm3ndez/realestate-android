package com.wmendez.realestate.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.wmendez.realestate.network.APIClient;

public class Image implements Parcelable {

    public String url;
    public int order;
    public String title;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeInt(order);
        dest.writeString(title);
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    private Image(Parcel in) {
        url = in.readString();
        order = in.readInt();
        title = in.readString();
    }
}
