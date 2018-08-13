package com.backbase.cityfinder.ui.cities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.backbase.cityfinder.BR;
import com.backbase.cityfinder.R;
import com.backbase.cityfinder.databinding.ActivityCitiesBinding;
import com.backbase.cityfinder.ui.base.BaseActivity;
import com.backbase.cityfinder.ui.cities.citieslist.CitiesListFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class CitiesActivity extends BaseActivity<ActivityCitiesBinding, CitiesViewModel>
        implements HasSupportFragmentInjector, CitiesNavigator {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    CitiesViewModel citiesViewModel;

    private ActivityCitiesBinding activityCitiesBinding;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cities;
    }

    @Override
    public CitiesViewModel getViewModel() {
        return citiesViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCitiesBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        CitiesListFragment citiesListFragment = CitiesListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(activityCitiesBinding.flCityContainer.getId(),
                        citiesListFragment, CitiesListFragment.class.getSimpleName())
                .commitNow();
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        });
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getCitiesListIdlingResource() {
        CitiesListFragment fragmentByTag = (CitiesListFragment) getSupportFragmentManager()
                .findFragmentByTag(CitiesListFragment.class.getSimpleName());
        return fragmentByTag.getIdlingResource();
    }
}
