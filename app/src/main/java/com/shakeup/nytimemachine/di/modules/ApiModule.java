package com.shakeup.nytimemachine.di.modules;

import com.shakeup.nytimemachine.commons.api.NytSearchApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jayson on 9/21/2017.
 * <p>
 * Provides Api objects through Dagger 2 dependency injection
 */

@Module
public class ApiModule {

    private static final String BASE_URL = "https://api.nytimes.com/svc/search/v2/";
    private static final String API_KEY = "aa082d6fd81f4f46a05d49462cd7b105";

    @Provides
    @Singleton
    NytSearchApi provideNytSearchApi() {
        return new NytSearchApi(BASE_URL, API_KEY);
    }
}
