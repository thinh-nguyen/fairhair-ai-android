package com.meltwater.fairhairai.search.di;

import com.meltwater.fairhairai.di.ActivityScope;
import com.meltwater.fairhairai.persistence.AppDatabase;
import com.meltwater.fairhairai.search.SearchInteractor;
import com.meltwater.fairhairai.search.SearchPresenter;
import com.meltwater.fairhairai.search.SearchRouter;
import com.meltwater.fairhairai.search.view.SearchActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thinhnguyen on 1/20/18.
 */

@Module
public class SearchActivityModule {

    private SearchActivity searchActivity;

    public SearchActivityModule(SearchActivity activity) {
        this.searchActivity = activity;
    }

    @Provides
    @ActivityScope
    SearchPresenter providePresenter(SearchInteractor interactor, SearchRouter router) {
        SearchPresenter pr = new SearchPresenter(interactor, router);
        interactor.setPresenter(pr);
        return pr;
    }

    @Provides
    @ActivityScope
    SearchInteractor provideInteractor(AppDatabase appDatabase) {
        return new SearchInteractor(appDatabase);
    }

    @Provides
    @ActivityScope
    SearchRouter provideRouter() {
        return new SearchRouter(this.searchActivity);
    }
}
