package com.backbase.cityfinder.ui.cities.citieslist;

import com.backbase.cityfinder.data.DataManager;
import com.backbase.cityfinder.ui.base.BaseViewModel;
import com.backbase.cityfinder.utils.rx.SchedulerProvider;
import com.backbase.cityfinder.utils.testing.SimpleIdlingResource;

public class CitiesListViewModel extends BaseViewModel<CitiesListNavigator> {

    public CitiesListViewModel(SchedulerProvider mSchedulerProvider, DataManager mDataManager) {
        super(mSchedulerProvider, mDataManager);
        mIdlingResource = new SimpleIdlingResource();
    }

    public void readTheCitiesData() {
        setIsLoading(true);
        mIdlingResource.setIdleState(false);
        getCompositeDisposable().add(getDataManager().processCitiesData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    if (response != null) {
                        getNavigator().addCitiesToList(response);
                    }
                    mIdlingResource.setIdleState(true);
                }, throwable -> {
                    setIsLoading(false);
                    mIdlingResource.setIdleState(true);
                }));
    }

}
