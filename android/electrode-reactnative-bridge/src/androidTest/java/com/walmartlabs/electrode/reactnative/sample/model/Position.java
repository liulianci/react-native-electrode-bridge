package com.walmartlabs.electrode.reactnative.sample.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.walmartlabs.electrode.reactnative.bridge.Bridgeable;

public class Position implements Parcelable,Bridgeable {

    private static final String KEY_BUNDLE_ID = "className";
    private static final String VALUE_BUNDLE_ID = Person.class.getSimpleName();

    @Nullable
    public static Position fromBundle(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        if (!bundle.containsKey(KEY_BUNDLE_ID)
                || !(VALUE_BUNDLE_ID).equals(bundle.getString(KEY_BUNDLE_ID))) {
            return null;
        }

        return new Builder().lat(bundle.getDouble("lat")).lng(bundle.getDouble("lng")).build();
    }

    private final Double lat;
    private final Double lng;

    private Position(Builder builder) {
        this.lat = builder.lat;
        this.lng = builder.lng;
    }

    private Position(Parcel in) {
        this(in.readBundle());
    }

    private Position(Bundle bundle) {
        lat = bundle.getDouble("lat");
        lng = bundle.getDouble("lng");
    }

    public static final Creator<Position> CREATOR = new Creator<Position>() {
        @Override
        public Position createFromParcel(Parcel in) {
            return new Position(in);
        }

        @Override
        public Position[] newArray(int size) {
            return new Position[size];
        }
    };

    @Nullable
    public Double getLat() {
        return lat;
    }

    @Nullable
    public Double getLng() {
        return lng;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(toBundle());
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (lat != null) {
            bundle.putDouble("lat", lat);
        }

        if (lng != null) {
            bundle.putDouble("lng", lng);
        }
        bundle.putString(KEY_BUNDLE_ID, VALUE_BUNDLE_ID);
        return bundle;
    }

    public static class Builder {
        private Double lat;
        private Double lng;

        public Builder() {
        }

        @NonNull
        public Builder lat(@Nullable Double lat) {
            this.lat = lat;
            return this;
        }

        @NonNull
        public Builder lng(@Nullable Double lng) {
            this.lng = lng;
            return this;
        }

        @NonNull
        public Position build() {
            return new Position(this);
        }
    }
}
