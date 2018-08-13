package com.backbase.cityfinder.ui.cities.citieslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.backbase.cityfinder.BR;
import com.backbase.cityfinder.data.model.local.City;
import com.backbase.cityfinder.databinding.ItemCityListBinding;
import com.backbase.cityfinder.ui.base.BaseViewHolder;
import com.backbase.cityfinder.ui.cities.citieslist.filter.CitiesListResultsFilter;

import java.util.ArrayList;
import java.util.List;

import static com.backbase.cityfinder.utils.AppConstants.EMPTY;

public class CitiesListAdapter extends RecyclerView.Adapter<BaseViewHolder> implements Filterable {

    private List<City> citiesListOriginal;
    private List<City> citiesList;
    private CityAdapterListener listener;
    private CitiesListResultsFilter filter;
    private String currentFilterText = EMPTY;

    public CitiesListAdapter(List<City> citiesList, CityAdapterListener listener, CitiesListResultsFilter filter) {
        this.listener = listener;
        this.citiesListOriginal = citiesList;
        this.citiesList = citiesList;
        this.filter = filter;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCityListBinding itemCityListBinding = ItemCityListBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new CityViewHolder(itemCityListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int pos) {
        baseViewHolder.onBind(pos);
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public class CityViewHolder extends BaseViewHolder {

        private ItemCityListBinding mBinding;

        public CityViewHolder(ItemCityListBinding itemCityListBinding) {
            super(itemCityListBinding.getRoot());
            this.mBinding = itemCityListBinding;
        }

        @Override
        public void onBind(int position) {
            City city = citiesList.get(position);
            CitiesListItemViewModel itemViewModel = new CitiesListItemViewModel(city, listener);
            mBinding.setViewModel(itemViewModel);
            mBinding.setVariable(BR.city, city);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    citiesList = citiesListOriginal;
                } else {
                    //filter the list by keyword
                    if (charString.startsWith(currentFilterText)) {
                        citiesList = filter.filterCitiesByKeyword(citiesList, charSequence);
                    } else {
                        citiesList = filter.filterCitiesByKeyword(citiesListOriginal, charSequence);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = citiesList;
                currentFilterText = charString;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                citiesList = (ArrayList<City>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setData(List<City> cities) {
        this.citiesList = cities;
        this.citiesListOriginal = cities;
        notifyDataSetChanged();
    }

    public interface CityAdapterListener {
        void onCitySelected(City city);
    }
}

