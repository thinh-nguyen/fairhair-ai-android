package com.meltwater.fairhairai.search;

import com.meltwater.fairhairai.base.ViewModel;
import com.meltwater.fairhairai.persistence.Search;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by thinhnguyen on 12/14/17.
 */

public interface SearchProtocol {

    interface View {

        /* BasePresenter -> View */
        void handleResult(UISearchResult result);
        void handleSearchList(List<SearchViewModel> searchList);
    }

    interface Presenter {

        /* View -> BasePresenter */
        void doSearch(SearchQuery query);
        void saveSearch(Search search);
        void getSearches();
        Observable<SearchViewModel> getObservableViewModel();
    }

    interface InteractorInput {

        /* BasePresenter -> Interactor */
        void submitSearch(SearchQuery query);
        void saveSearch(Search search);
        void retrieveSearches();
    }

    interface InteractorOutput {

        /* Interactor -> BasePresenter */
        void didRetrieveSearches(List<Search> searchList);
        void didRetrieveResult(SearchResult result);
    }

    interface Router {

    }

    interface DataManagerInput {
        /* BasePresenter -> Data */
    }

    interface DataManagerOutput {
        /* Data -> BasePresenter */
    }
}
