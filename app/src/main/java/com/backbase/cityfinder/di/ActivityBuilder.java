package com.backbase.cityfinder.di;

import com.backbase.cityfinder.ui.cities.CitiesActivity;
import com.backbase.cityfinder.ui.cities.CitiesModule;
import com.backbase.cityfinder.ui.cities.citieslist.CitiesListProvider;
import com.backbase.cityfinder.ui.cities.citydetails.CityDetailsProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            CitiesModule.class,
            CitiesListProvider.class,
            CityDetailsProvider.class})
    abstract CitiesActivity bindCityActivity();

}
