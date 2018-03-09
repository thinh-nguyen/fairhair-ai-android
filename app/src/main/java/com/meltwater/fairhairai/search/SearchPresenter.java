package com.meltwater.fairhairai.search;

import android.arch.lifecycle.LiveData;

import com.meltwater.fairhairai.base.BasePresenter;
import com.meltwater.fairhairai.persistence.Search;

import java.util.List;

/**
 * Created by thinhnguyen on 1/4/18.
 */

public class SearchPresenter implements BasePresenter, SearchProtocol.Presenter, SearchProtocol.InteractorOutput {

    // No longer need to hold a view -- private WeakReference<SearchProtocol.View> mViewReference;
    // private SearchProtocol.View mView;
    private SearchProtocol.InteractorInput interactor;
    private SearchProtocol.Router router;

    public SearchPresenter(SearchInteractor interactor, SearchRouter router) {
        this.interactor = interactor;
        this.router = router;
    }

    @Override // Base
    public void onCreate() {
    }

    @Override
    public void onSearchSelected(Search search) {
        router.showSearchDetail(search);
    }

    @Override
    public void createNewSearch() {
        router.showSearchDetail(new Search());
    }

    @Override
    public void saveSearch(Search search) {
        interactor.saveSearch(search);
        router.showSearchList();
    }

    @Override
    public LiveData<List<Search>> getSearches() {
        LiveData<List<Search>> searchLiveDataList = interactor.retrieveSearches();
        return searchLiveDataList;
    }

//    @Override
//    public void didRetrieveSearches(List<Search> searchList) {
//        ArrayList<SearchViewModel> models = new ArrayList<>();
//        for (Search s : searchList) {
//            models.add(SearchViewModel.instance(s));
//        }
//        mViewReference.get().handleSearchList(models);
//        //mView.handleSearchList((models);
//    }

//    @Override //SearchProtocol.InteractorOutput
//    public void didRetrieveResult(SearchResult result) {
//
//    }
}
