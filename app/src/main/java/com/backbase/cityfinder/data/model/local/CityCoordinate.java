package com.backbase.cityfinder.data.model.local;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class CityCoordinate implements Parcelable {

    private float lon;
    private float lat;

    @JsonCreator
    public CityCoordinate(@JsonProperty("lon") float lon,
                          @JsonProperty("lat") float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.lon);
        dest.writeFloat(this.lat);
    }

    protected CityCoordinate(Parcel in) {
        this.lon = in.readFloat();
        this.lat = in.readFloat();
    }

    public static final Parcelable.Creator<CityCoordinate> CREATOR = new Parcelable.Creator<CityCoordinate>() {
        @Override
        public CityCoordinate createFromParcel(Parcel source) {
            return new CityCoordinate(source);
        }

        @Override
        public CityCoordinate[] newArray(int size) {
            return new CityCoordinate[size];
        }
    };
}
