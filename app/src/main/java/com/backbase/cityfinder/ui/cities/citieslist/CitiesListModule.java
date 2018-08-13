package com.backbase.cityfinder.ui.cities.citieslist;


import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.backbase.cityfinder.data.DataManager;
import com.backbase.cityfinder.ui.cities.citieslist.filter.CitiesListResultsFilter;
import com.backbase.cityfinder.ui.cities.citieslist.filter.CitiesListResultsFilterImpl;
import com.backbase.cityfinder.utils.ViewModelProviderFactory;
import com.backbase.cityfinder.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class CitiesListModule {

    @Provides
    CitiesListViewModel cityListViewModel(SchedulerProvider schedulerProvider, DataManager dataManager) {
        return new CitiesListViewModel(schedulerProvider, dataManager);
    }

    @Provides
    ViewModelProvider.Factory provideCityListViewModel(CitiesListViewModel citiesListViewModel) {
        return new ViewModelProviderFactory<>(citiesListViewModel);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CitiesListFragment fragment) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragment.getActivity());
        return linearLayoutManager;
    }

    @Provides
    RecyclerView.ItemDecoration provideItemDecoration(CitiesListFragment fragment, LinearLayoutManager linearLayoutManager) {
        DividerItemDecoration itemDecoration = new DividerItemDecoration(fragment.getContext(),
                linearLayoutManager.getOrientation());
        return itemDecoration;
    }

    @Provides
    RecyclerView.ItemAnimator provideItemAnimator() {
        return new DefaultItemAnimator();
    }

    @Provides
    CitiesListAdapter provideCitiesListAdapter(CitiesListFragment fragment, CitiesListResultsFilter filter) {
        CitiesListAdapter citiesListAdapter = new CitiesListAdapter(new ArrayList<>(), fragment, filter);
        return citiesListAdapter;
    }

    @Provides
    CitiesListResultsFilter provideCitiesListFilter() {
        return new CitiesListResultsFilterImpl();
    }

    @Provides
    SearchManager provideSearchManager(CitiesListFragment fragment) {
        SearchManager searchManager = (SearchManager) fragment.getActivity().getSystemService(Context.SEARCH_SERVICE);
        return searchManager;
    }
}
