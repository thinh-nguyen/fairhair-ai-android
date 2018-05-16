package com.meltwater.fairhairai.document;

import android.arch.lifecycle.LiveData;

import com.meltwater.fairhairai.base.BasePresenter;
import com.meltwater.fairhairai.search.SearchQuery;

import java.util.List;

/**
 * Created by thinhnguyen on 5/15/18.
 */

public class DocumentProtocol {
    interface View {

    }

    interface Presenter extends BasePresenter {
        /* view -> BasePresenter */
        LiveData<List<Document>> getDocuments(SearchQuery query);
    }

    interface Interactor {
        /* ptrsenter -> interactor */
        LiveData<List<Document>> getDocuments(SearchQuery query);
    }

    interface Wireframe {

    }
}
