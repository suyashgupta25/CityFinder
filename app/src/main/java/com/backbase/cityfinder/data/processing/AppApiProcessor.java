package com.backbase.cityfinder.data.processing;

import android.content.Context;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.backbase.cityfinder.R;
import com.backbase.cityfinder.data.model.local.City;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppApiProcessor implements ApiProcesser {

    private Context context;
    private ObjectMapper objectMapper;

    @Inject
    public AppApiProcessor(Context context, ObjectMapper objectMapper) {
        this.context = context;
        this.objectMapper = objectMapper;
    }

    @Override
    public Single<List<City>> processCitiesData() {
        return Single.fromCallable(new Callable<List<City>>() {
            @Override
            public List<City> call() {
                return getCitiesData();
            }
        });
    }

    /**
     * Parsing and sorting of the initial data-set happens here
     * @return
     */
    private List<City> getCitiesData() {
        List<City> cities = new ArrayList<>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.cities);
        try {
            cities = objectMapper.readValue(inputStream, new TypeReference<List<City>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return cities;
        }
        cities = Stream.of(cities).sorted((p1, p2) ->
                p1.getCityKeyword().compareTo(p2.getCityKeyword())
        ).collect(Collectors.toList());
        return cities;
    }

}
