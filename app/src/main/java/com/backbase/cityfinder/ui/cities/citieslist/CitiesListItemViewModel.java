package com.backbase.cityfinder.ui.cities.citieslist;

import android.databinding.ObservableField;

import com.backbase.cityfinder.data.model.local.City;

import static com.backbase.cityfinder.utils.AppConstants.COMMA;

public class CitiesListItemViewModel {

    public final ObservableField<String> cityTitle;
    public CitiesListAdapter.CityAdapterListener listener;

    public CitiesListItemViewModel(City city, CitiesListAdapter.CityAdapterListener listener) {
        this.cityTitle = new ObservableField<>(city.getName()+COMMA+city.getCountry());
        this.listener = listener;
    }

    public void onCityClicked(City city) {
        this.listener.onCitySelected(city);
    }
}
