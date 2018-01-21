package com.meltwater.fairhairai.search.di;

import com.meltwater.fairhairai.di.ActivityScope;
import com.meltwater.fairhairai.persistence.AppDatabase;
import com.meltwater.fairhairai.search.SearchInteractor;
import com.meltwater.fairhairai.search.SearchPresenter;
import com.meltwater.fairhairai.search.view.SearchActivity;
import com.meltwater.fairhairai.search.view.SearchListFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thinhnguyen on 1/20/18.
 */

@Module
public class SearchActivityModule {

    private SearchActivity mSearchActivity;

    public SearchActivityModule(SearchActivity activity) {
        this.mSearchActivity = activity;
    }

    @Provides
    @ActivityScope
    SearchPresenter providePresenter(SearchInteractor interactor) {
        SearchPresenter pr = new SearchPresenter(interactor);
        interactor.setPresenter(pr);
        return pr;
    }

    @Provides
    @ActivityScope
    SearchInteractor provideInteractor(AppDatabase appDatabase) {
        return new SearchInteractor(appDatabase);
    }
}
