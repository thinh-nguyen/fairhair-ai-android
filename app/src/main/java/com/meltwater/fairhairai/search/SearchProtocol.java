package com.meltwater.fairhairai.search;

import android.arch.lifecycle.LiveData;

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
    }

    interface Presenter {
        /* View -> BasePresenter */
        void onSearchSelected(Search search);
        void createNewSearch();
        void saveSearch(Search searchViewModel);
        LiveData<List<Search>> getSearches();
    }

    interface InteractorInput {
        /* BasePresenter -> Interactor */
        void submitSearch(SearchQuery query);
        void saveSearch(Search search);
        LiveData<List<Search>> retrieveSearches();
    }

    interface InteractorOutput {

//        /* Interactor -> BasePresenter */
//        void didRetrieveSearches(List<Search> searchList);
    }

    interface Router {
        /* Presenter -> Router */
        void showSearchList();
        void showSearchDetail(Search search);
    }

    interface DataManagerInput {
        /* BasePresenter -> Data */
    }

    interface DataManagerOutput {
        /* Data -> BasePresenter */
    }
}
