package com.meltwater.fairhairai.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thinhnguyen on 1/20/18.
 */
@Module
public class ContextModule {//TODO may not need this class?

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationContext
    @ApplicationScope
    public Context context() {
        return this.context;
    }
}