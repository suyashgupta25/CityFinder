package com.backbase.cityfinder.ui.cities.citieslist;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CitiesListProvider {

    @ContributesAndroidInjector(modules = CitiesListModule.class)
    abstract CitiesListFragment provideCityListFragmentFactory();
}
