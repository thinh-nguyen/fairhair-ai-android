package com.meltwater.fairhairai.di;

import android.content.Context;

import com.meltwater.fairhairai.persistence.AppDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thinhnguyen on 1/20/18.
 */

@Module(includes = NetworkModule.class)
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @Named("application")
    @Singleton
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    @ApplicationScope
    public AppDatabase appDatabase() {
        return AppDatabase.provideAppDatabase(provideApplicationContext());
    }
}

