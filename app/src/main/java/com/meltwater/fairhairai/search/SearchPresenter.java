package com.meltwater.fairhairai.search;

import com.meltwater.fairhairai.base.BasePresenter;
import com.meltwater.fairhairai.base.ViewModel;
import com.meltwater.fairhairai.persistence.Search;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by thinhnguyen on 1/4/18.
 */

public class SearchPresenter implements BasePresenter, SearchProtocol.Presenter, SearchProtocol.InteractorOutput {

    private WeakReference<SearchProtocol.View> mViewReference;
   // private SearchProtocol.View mView;
    private SearchProtocol.InteractorInput mInteractor;

    private BehaviorSubject<SearchViewModel> searchBehaviorSubject = BehaviorSubject.create();

    private SearchViewModel mViewModel;

    public SearchPresenter(SearchInteractor interactor) {
        this.mInteractor = interactor;
    }

    public void setViewReference(SearchProtocol.View view) {
        mViewReference = new WeakReference(view);
    }

    @Override // Base
    public void onCreate() {
        mViewModel = new SearchViewModel();
        searchBehaviorSubject.onNext(mViewModel);
    }

    @Override //SearchProtocol.BasePresenter
    public void doSearch(SearchQuery query) {

    }

    @Override
    public void saveSearch(Search search){
        mInteractor.saveSearch(search);
    }

    @Override
    public void getSearches() {
        mInteractor.retrieveSearches();
    }

    @Override
    public void didRetrieveSearches(List<Search> searchList) {
        ArrayList<SearchViewModel> models = new ArrayList<>();
        for (Search s : searchList) {
            models.add(SearchViewModel.instance(s));
        }
        mViewReference.get().handleSearchList(models);
        //mView.handleSearchList((models);
    }

    @Override //SearchProtocol.InteractorOutput
    public void didRetrieveResult(SearchResult result) {

    }

    @Override
    public Observable<SearchViewModel> getObservableViewModel() {
        return searchBehaviorSubject;
    }
}
