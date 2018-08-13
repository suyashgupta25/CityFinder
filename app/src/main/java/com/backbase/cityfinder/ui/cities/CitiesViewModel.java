package com.backbase.cityfinder.ui.cities;

import com.backbase.cityfinder.data.DataManager;
import com.backbase.cityfinder.ui.base.BaseViewModel;
import com.backbase.cityfinder.utils.rx.SchedulerProvider;

public class CitiesViewModel extends BaseViewModel<CitiesNavigator> {

    public CitiesViewModel(SchedulerProvider mSchedulerProvider, DataManager mDataManager) {
        super(mSchedulerProvider, mDataManager);
    }
}