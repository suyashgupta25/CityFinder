package com.backbase.cityfinder.data.model.local;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class City implements Parcelable {

    private String country;
    private String name;
    private String cityKeyword;
    private String countryKeyword;
    private int _id;
    private CityCoordinate coord;

    @JsonCreator
    public City(@JsonProperty("country") String country,
                @JsonProperty("name")  String name,
                @JsonProperty("_id") int _id,
                @JsonProperty("coord") CityCoordinate coord) {
        this.country = country;
        this.name = name;
        this._id = _id;
        this.coord = coord;
        this.cityKeyword = name.toLowerCase();
        this.countryKeyword = country.toLowerCase();
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getCityKeyword() {
        return cityKeyword;
    }

    public String getCountryKeyword() {
        return countryKeyword;
    }

    public CityCoordinate getCoord() {
        return coord;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.country);
        dest.writeString(this.name);
        dest.writeString(this.cityKeyword);
        dest.writeString(this.countryKeyword);
        dest.writeInt(this._id);
        dest.writeParcelable(this.coord, flags);
    }

    protected City(Parcel in) {
        this.country = in.readString();
        this.name = in.readString();
        this.cityKeyword = in.readString();
        this.countryKeyword = in.readString();
        this._id = in.readInt();
        this.coord = in.readParcelable(CityCoordinate.class.getClassLoader());
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
