package com.meltwater.fairhairai.search;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.meltwater.fairhairai.persistence.AppDatabase;
import com.meltwater.fairhairai.persistence.Search;

import java.util.List;

/**
 * Created by thinhnguyen on 1/4/18.
 */

public class SearchInteractor implements SearchProtocol.InteractorInput, SearchProtocol.DataManagerInput {

    private SearchProtocol.InteractorOutput presenter;

    @Override // InteractorInput
    public void submitSearch(SearchQuery query) {

    }

    AppDatabase appDatabase;

    public SearchInteractor(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void setPresenter(SearchProtocol.InteractorOutput presenter) {
        this.presenter = presenter;
    }
    @Override
    public void saveSearch(Search search) {
        if (search.getId() == 0) {
            AsyncTask.execute(() -> appDatabase.searchDao().addSearch(search));
        } else {
            AsyncTask.execute(() -> appDatabase.searchDao().updateSearch(search));
        }
    }

    @Override
    public LiveData<List<Search>> retrieveSearches() {
        LiveData<List<Search>> searchListLiveData = appDatabase.searchDao().findSearches();
        //mPresenter.didRetrieveSearches(searchList);
        return searchListLiveData;
    }
}
