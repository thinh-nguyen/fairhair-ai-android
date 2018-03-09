package com.meltwater.fairhairai;

import android.app.Application;

import com.meltwater.fairhairai.di.ApplicationComponent;
import com.meltwater.fairhairai.di.ApplicationModule;
import com.meltwater.fairhairai.di.DaggerApplicationComponent;
import com.meltwater.fairhairai.search.di.DaggerSearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityModule;

/**
 * Created by thinhnguyen on 1/20/18.
 */

public class FhaiApplication extends Application {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }
}


