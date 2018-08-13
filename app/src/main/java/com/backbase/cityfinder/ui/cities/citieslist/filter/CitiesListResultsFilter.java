package com.backbase.cityfinder.ui.cities.citieslist.filter;

import com.backbase.cityfinder.data.model.local.City;

import java.util.List;

public interface CitiesListResultsFilter {
    List<City> filterCitiesByKeyword(List<City> cities, CharSequence charSequence);
}
