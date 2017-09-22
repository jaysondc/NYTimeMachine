package com.shakeup.nytimemachine;

import android.app.Application;

import com.shakeup.nytimemachine.di.components.ApiComponent;
import com.shakeup.nytimemachine.di.components.DaggerApiComponent;
import com.shakeup.nytimemachine.di.modules.ApiModule;
import com.shakeup.nytimemachine.di.modules.AppModule;

/**
 * Created by Jayson on 9/21/2017.
 *
 * Subclass of the Application class used to set up our DI Components any any other setup related
 * things that should happen before onCreate()
 */

public class NytApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        mApiComponent = DaggerApiComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .apiModule(new ApiModule())
                .build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
