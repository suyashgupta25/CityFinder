package com.backbase.cityfinder.ui.cities.citieslist;


import android.content.Context;

import com.backbase.cityfinder.data.DataManager;
import com.backbase.cityfinder.utils.rx.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CitiesListViewModelTest {

    @Mock
    CitiesListNavigator mCitiesListNavigator;

    @Mock
    DataManager mMockDataManager;

    @Mock
    Context context;

    private TestScheduler mTestScheduler;
    private CitiesListViewModel mCitiesListViewModel;

    @BeforeClass
    public static void onlyOnce() {
    }

    @Before
    public void setUp() {
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mCitiesListViewModel = new CitiesListViewModel(testSchedulerProvider, mMockDataManager);
        mCitiesListViewModel.setNavigator(mCitiesListNavigator);
    }

    @Test
    public void testCitiesListSuccess() {
        doReturn(Single.just(new ArrayList<>()))
                .when(mMockDataManager)
                .processCitiesData();

        mCitiesListViewModel.readTheCitiesData();
        mTestScheduler.triggerActions();

        verify(mCitiesListNavigator).addCitiesToList(new ArrayList<>());
    }

    @After
    public void tearDown() {
        mTestScheduler = null;
        mCitiesListViewModel = null;
        mCitiesListNavigator = null;
    }
}
