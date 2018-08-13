package com.backbase.cityfinder.ui.cities.citydetails;

import com.backbase.cityfinder.data.DataManager;
import com.backbase.cityfinder.ui.base.BaseViewModel;
import com.backbase.cityfinder.utils.rx.SchedulerProvider;

public class CityDetailsViewModel extends BaseViewModel<CityDetailsNavigator> {
    public CityDetailsViewModel(SchedulerProvider mSchedulerProvider, DataManager mDataManager) {
        super(mSchedulerProvider, mDataManager);
    }
}
