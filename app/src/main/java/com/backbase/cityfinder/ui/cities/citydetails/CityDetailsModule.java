package com.backbase.cityfinder.ui.cities.citydetails;

import android.arch.lifecycle.ViewModelProvider;

import com.backbase.cityfinder.data.DataManager;
import com.backbase.cityfinder.utils.ViewModelProviderFactory;
import com.backbase.cityfinder.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CityDetailsModule {

    @Provides
    CityDetailsViewModel cityDetailsViewModel(SchedulerProvider schedulerProvider, DataManager dataManager) {
        return new CityDetailsViewModel(schedulerProvider, dataManager);
    }

    @Provides
    ViewModelProvider.Factory provideCityDetailsViewModel(CityDetailsViewModel cityDetailsViewModel) {
        return new ViewModelProviderFactory<>(cityDetailsViewModel);
    }
}
