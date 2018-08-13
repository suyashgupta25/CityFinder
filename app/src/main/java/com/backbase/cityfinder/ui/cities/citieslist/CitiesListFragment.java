package com.backbase.cityfinder.ui.cities.citieslist;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.backbase.cityfinder.BR;
import com.backbase.cityfinder.R;
import com.backbase.cityfinder.data.model.local.City;
import com.backbase.cityfinder.databinding.FragmentCitiesListBinding;
import com.backbase.cityfinder.ui.base.BaseFragment;
import com.backbase.cityfinder.ui.cities.citydetails.CityDetailsFragment;

import java.util.List;

import javax.inject.Inject;

public class CitiesListFragment extends BaseFragment<FragmentCitiesListBinding, CitiesListViewModel>
        implements CitiesListNavigator, CitiesListAdapter.CityAdapterListener {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    CitiesListAdapter citiesListAdapter;
    @Inject
    RecyclerView.ItemDecoration itemDecoration;
    @Inject
    RecyclerView.ItemAnimator itemAnimator;
    @Inject
    SearchManager searchManager;

    private CitiesListViewModel mCitiesListViewModel;
    private FragmentCitiesListBinding mFragmentCitiesListBinding;
    private SearchView searchView;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cities_list;
    }

    @Override
    public CitiesListViewModel getViewModel() {
        mCitiesListViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CitiesListViewModel.class);
        return mCitiesListViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCitiesListViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        setUp();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCitiesListViewModel.readTheCitiesData();
    }

    private void setUp() {
        mFragmentCitiesListBinding = getViewDataBinding();
        ((AppCompatActivity) getActivity()).setSupportActionBar(mFragmentCitiesListBinding.toolbar);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentCitiesListBinding.rlCitiesList.setLayoutManager(mLayoutManager);
        mFragmentCitiesListBinding.rlCitiesList.setItemAnimator(itemAnimator);
        mFragmentCitiesListBinding.rlCitiesList.addItemDecoration(itemDecoration);
        mFragmentCitiesListBinding.rlCitiesList.setAdapter(citiesListAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                citiesListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                citiesListAdapter.getFilter().filter(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static CitiesListFragment newInstance() {
        CitiesListFragment fragment = new CitiesListFragment();
        return fragment;
    }

    @Override
    public void addCitiesToList(List<City> cities) {
        citiesListAdapter.setData(cities);
    }

    @Override
    public void onCitySelected(City city) {
        //keyboard dismiss
        searchView.clearFocus();
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        Log.d("onCitySelected","onCitySelected");
        CityDetailsFragment cityDetailsFragment = CityDetailsFragment.newInstance(getActivity(), city);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_city_container, cityDetailsFragment, CityDetailsFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    public IdlingResource getIdlingResource() {
        return mCitiesListViewModel.getIdlingResource();
    }
}
