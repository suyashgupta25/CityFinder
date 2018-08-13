package com.backbase.cityfinder.ui.cities.citydetails;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CityDetailsProvider {

    @ContributesAndroidInjector(modules = CityDetailsModule.class)
    abstract CityDetailsFragment provideCityDetailsFragmentFactory();
}
