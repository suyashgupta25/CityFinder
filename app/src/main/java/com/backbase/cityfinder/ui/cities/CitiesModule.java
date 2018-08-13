package com.backbase.cityfinder.ui.cities;

import com.backbase.cityfinder.data.DataManager;
import com.backbase.cityfinder.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class CitiesModule {

    @Provides
    CitiesViewModel provideCityViewModel(SchedulerProvider schedulerProvider, DataManager dataManager) {
        return new CitiesViewModel(schedulerProvider, dataManager);
    }
}
