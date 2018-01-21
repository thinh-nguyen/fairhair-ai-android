package com.meltwater.fairhairai.search;

import com.meltwater.fairhairai.persistence.AppDatabase;
import com.meltwater.fairhairai.persistence.Search;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by thinhnguyen on 1/4/18.
 */

public class SearchInteractor implements SearchProtocol.InteractorInput, SearchProtocol.DataManagerInput {

    private SearchProtocol.InteractorOutput mPresenter;

    @Override // InteractorInput
    public void submitSearch(SearchQuery query) {

    }

    AppDatabase mAppDatabase;

    public SearchInteractor(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
    }

    public void setPresenter(SearchProtocol.InteractorOutput presenter) {
        mPresenter = presenter;
    }
    @Override
    public void saveSearch(Search search) {
        if (search.getId() == 0) {
            mAppDatabase.searchDao().addSearch(search);
        } else {
            mAppDatabase.searchDao().updateSearch(search);
        }
    }

    @Override
    public void retrieveSearches() {
        List<Search> searchList = mAppDatabase.searchDao().findSearches();
        mPresenter.didRetrieveSearches(searchList);
    }
}
