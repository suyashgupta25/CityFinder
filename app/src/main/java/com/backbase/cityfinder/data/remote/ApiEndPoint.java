package com.backbase.cityfinder.data.remote;

import com.backbase.cityfinder.BuildConfig;

public final class ApiEndPoint {

    public static final String ENDPOINT_CITIES = BuildConfig.BASE_URL + "/v0/p1/cities";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}

