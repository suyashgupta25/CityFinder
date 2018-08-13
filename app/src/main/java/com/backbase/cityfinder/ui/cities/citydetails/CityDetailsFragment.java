package com.backbase.cityfinder.ui.cities.citydetails;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.backbase.cityfinder.BR;
import com.backbase.cityfinder.R;
import com.backbase.cityfinder.data.model.local.City;
import com.backbase.cityfinder.databinding.FragmentCityDetailsBinding;
import com.backbase.cityfinder.ui.base.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

public class CityDetailsFragment extends BaseFragment<FragmentCityDetailsBinding, CityDetailsViewModel>
        implements CityDetailsNavigator, OnMapReadyCallback, View.OnClickListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private SupportMapFragment mapFragment;
    private CityDetailsViewModel cityDetailsViewModel;
    private FragmentCityDetailsBinding cityDetailsBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_city_details;
    }

    @Override
    public CityDetailsViewModel getViewModel() {
        cityDetailsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CityDetailsViewModel.class);
        return cityDetailsViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityDetailsViewModel.setNavigator(this);
    }

    public static CityDetailsFragment newInstance(Context context, City city) {
        CityDetailsFragment fragment = new CityDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(context.getString(R.string.param_city_details), city);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(this);
            // R.id.map is a FrameLayout, not a Fragment
            getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
            ((AppCompatActivity)getActivity()).setSupportActionBar(getViewDataBinding().toolbar);
            getViewDataBinding().toolbar.setNavigationOnClickListener(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        City city = getArguments().getParcelable(getString(R.string.param_city_details));
        LatLng latLng = new LatLng(city.getCoord().getLat(), city.getCoord().getLon());
        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title(city.getName()));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onClick(View view) {
        if (view.getParent() instanceof Toolbar) {
            getActivity().onBackPressed();
        }
    }
}
