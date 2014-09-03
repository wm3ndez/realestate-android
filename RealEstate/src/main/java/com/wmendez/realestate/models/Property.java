package com.wmendez.realestate.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Property implements Parcelable {
    public int id;
    public String title;
    public String absolute_url;
    public String description;
    public String address;
    public String type;
    public String offer;
    public String created_at;
    public int baths;
    public int beds;
    public float price;
    public List<Image> images;

    public Property() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(absolute_url);
        dest.writeString(description);
        dest.writeString(address);
        dest.writeString(type);
        dest.writeString(offer);
        dest.writeString(created_at);
        dest.writeInt(baths);
        dest.writeInt(beds);
        dest.writeFloat(price);
        dest.writeTypedList(images);
    }

    public static final Parcelable.Creator<Property> CREATOR = new Parcelable.Creator<Property>() {
        public Property createFromParcel(Parcel in) {
            return new Property(in);
        }

        public Property[] newArray(int size) {
            return new Property[size];
        }
    };

    private Property(Parcel in) {
        id = in.readInt();
        title = in.readString();
        absolute_url = in.readString();
        description = in.readString();
        address = in.readString();
        type = in.readString();
        offer = in.readString();
        created_at = in.readString();
        baths = in.readInt();
        beds = in.readInt();
        price = in.readFloat();
        images = new ArrayList<Image>();
        in.readTypedList(images, Image.CREATOR);
    }

}
