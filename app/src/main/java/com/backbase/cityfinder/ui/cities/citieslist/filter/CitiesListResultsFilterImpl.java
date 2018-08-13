package com.backbase.cityfinder.ui.cities.citieslist.filter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.backbase.cityfinder.data.model.local.City;

import java.util.List;

public class CitiesListResultsFilterImpl implements CitiesListResultsFilter {

    @Override
    public List<City> filterCitiesByKeyword(List<City> cities, CharSequence charSequence) {
        return Stream.of(cities).filter(f ->
                (f.getCityKeyword().startsWith(charSequence.toString().toLowerCase()) ||
                        f.getCountryKeyword().startsWith(charSequence.toString().toLowerCase())))
                .collect(Collectors.toList());
    }

}
