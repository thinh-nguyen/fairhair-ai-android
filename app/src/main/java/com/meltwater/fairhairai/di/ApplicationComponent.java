package com.meltwater.fairhairai.di;

import com.meltwater.fairhairai.persistence.AppDatabase;

import dagger.Component;
import dagger.Provides;

/**
 * Created by thinhnguyen on 1/20/18.
 */

@Component(modules = ApplicationModule.class)
@ApplicationScope
public interface ApplicationComponent {
    AppDatabase getAppDatabase();
}