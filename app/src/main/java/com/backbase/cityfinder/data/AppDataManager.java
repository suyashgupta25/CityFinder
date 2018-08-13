package com.backbase.cityfinder.data;

import android.content.Context;

import com.backbase.cityfinder.data.model.local.City;
import com.backbase.cityfinder.data.processing.ApiProcesser;
import com.backbase.cityfinder.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final ApiProcesser mApiProcesser;

    @Inject
    public AppDataManager(ApiHelper apiHelper, Context context, ApiProcesser apiProcesser) {
        this.mApiHelper = apiHelper;
        this.mContext = context;
        this.mApiProcesser = apiProcesser;
    }

    @Override
    public Single<List<City>> processCitiesData() {
        return mApiProcesser.processCitiesData();
    }
}
