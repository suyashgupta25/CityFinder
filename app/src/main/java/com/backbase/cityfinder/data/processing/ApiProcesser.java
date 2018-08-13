package com.backbase.cityfinder.data.processing;

import com.backbase.cityfinder.data.model.local.City;

import java.util.List;

import io.reactivex.Single;

public interface ApiProcesser {

    Single<List<City>> processCitiesData();

}
