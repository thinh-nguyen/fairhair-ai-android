package com.meltwater.fairhairai.search.di;

import com.meltwater.fairhairai.di.ApplicationComponent;
import com.meltwater.fairhairai.di.ActivityScope;
import com.meltwater.fairhairai.search.view.SearchActivity;

import dagger.Component;

/**
 * Created by thinhnguyen on 1/20/18.
 */

@Component(modules = SearchActivityModule.class, dependencies = ApplicationComponent.class)
@ActivityScope
public interface SearchActivityComponent {
    void inject(SearchActivity activity);
}
