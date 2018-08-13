package com.backbase.cityfinder.ui.cities.citieslist.filter;

import com.backbase.cityfinder.data.model.local.City;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CitiesListResultsFilterTest {

    @Mock
    CitiesListResultsFilter mCitiesListResultsFilter;

    private List<City> cities;

    @Before
    public void setup() {
        mCitiesListResultsFilter = new CitiesListResultsFilterImpl();
        prepareTestData();
    }

    private void prepareTestData() {
        cities = new ArrayList<>();
        cities.add(new City("US", "Alabama", 0, null));
        cities.add(new City("US", "Albuquerque", 0, null));
        cities.add(new City("US", "Anaheim", 0, null));
        cities.add(new City("US", "Arizona", 0, null));
        cities.add(new City("AU", "Sydney", 0, null));
    }

    @Test
    public void testCitiesWithInvalidInputs() {
        List<City> citiesOutput = mCitiesListResultsFilter.filterCitiesByKeyword(this.cities, "!");
        Assert.assertEquals(0, citiesOutput.size());

        List<City> citiesOutput2 = mCitiesListResultsFilter.filterCitiesByKeyword(this.cities, "B");
        Assert.assertEquals(0, citiesOutput2.size());

        List<City> citiesOutput3 = mCitiesListResultsFilter.filterCitiesByKeyword(this.cities, "1");
        Assert.assertEquals(0, citiesOutput3.size());
    }

    @Test
    public void testCitiesFilterSuccess() {
        List<City> citiesOutput = mCitiesListResultsFilter.filterCitiesByKeyword(this.cities, "A");
        Assert.assertEquals(5, citiesOutput.size());

        List<City> citiesOutput2 = mCitiesListResultsFilter.filterCitiesByKeyword(this.cities, "Al");
        Assert.assertEquals(2, citiesOutput2.size());

        List<City> citiesOutput3 = mCitiesListResultsFilter.filterCitiesByKeyword(this.cities, "Alb");
        Assert.assertEquals(1, citiesOutput3.size());
    }

    @After
    public void tearDown() {
        mCitiesListResultsFilter = null;
    }

}
