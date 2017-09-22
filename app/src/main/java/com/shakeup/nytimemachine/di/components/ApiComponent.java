package com.shakeup.nytimemachine.di.components;

import com.shakeup.nytimemachine.di.modules.ApiModule;
import com.shakeup.nytimemachine.di.modules.AppModule;
import com.shakeup.nytimemachine.features.search.SearchRepository;
import com.shakeup.nytimemachine.features.search.SearchViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jayson on 9/21/2017.
 *
 * Defines the relationship between our DI Modules and their targets
 */

@Singleton
@Component(modules={AppModule.class, ApiModule.class})
public interface ApiComponent {
        void inject(SearchRepository searchRepo);
        void inject(SearchViewModel searchViewModel);
}
