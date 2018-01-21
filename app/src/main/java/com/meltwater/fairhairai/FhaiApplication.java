package com.meltwater.fairhairai;

import android.app.Application;

import com.meltwater.fairhairai.di.ApplicationComponent;
import com.meltwater.fairhairai.di.ApplicationModule;
import com.meltwater.fairhairai.di.ContextModule;
import com.meltwater.fairhairai.di.DaggerApplicationComponent;

/**
 * Created by thinhnguyen on 1/20/18.
 */

public class FhaiApplication extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getAppComponent() {
        return mComponent;
    }
}


