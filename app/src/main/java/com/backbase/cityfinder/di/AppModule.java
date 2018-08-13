package com.backbase.cityfinder.di;

import android.app.Application;
import android.content.Context;

import com.backbase.cityfinder.data.AppDataManager;
import com.backbase.cityfinder.data.DataManager;
import com.backbase.cityfinder.data.processing.ApiProcesser;
import com.backbase.cityfinder.data.processing.AppApiProcessor;
import com.backbase.cityfinder.data.remote.ApiHelper;
import com.backbase.cityfinder.data.remote.AppApiHelper;
import com.backbase.cityfinder.utils.AppConstants;
import com.backbase.cityfinder.utils.rx.AppSchedulerProvider;
import com.backbase.cityfinder.utils.rx.SchedulerProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiProcesser provideApiProcessor(AppApiProcessor appApiProcessor) {
        return appApiProcessor;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return AppConstants.PARAM_VALUE_CONTENT_TYPE;
    }
}
