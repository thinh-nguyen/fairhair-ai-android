package com.meltwater.fairhairai.search;

/**
 * Created by thinhnguyen on 12/14/17.
 */

public interface FHAISearchProtocol {

    public interface View {

        /* Presenter -> View */
        public void handleResult(UISearchResult result);
    }

    public interface Presenter {

        /* View -> Presenter */
        public void doSearch(SearchQuery query);
    }

    public interface InteractorInput {

        /* Presenter -> Interactor */
        public void submitSearch(SearchQuery query);
    }

    public interface InteractorOutput {

        /* Interactor -> Presenter */
        public void didRetrieveResult(FHAISearchProtocol result);
    }

    public interface Router {

    }

    public interface DataManagerInput {
        /* Interactor -> Data */
    }

    public interface DataManagerOutput {
        /* Data -> Interactor */
    }
}
