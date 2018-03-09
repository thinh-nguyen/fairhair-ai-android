package com.meltwater.fairhairai.search.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.meltwater.fairhairai.FhaiApplication;
import com.meltwater.fairhairai.R;
import com.meltwater.fairhairai.base.BaseActivity;
import com.meltwater.fairhairai.persistence.Search;
import com.meltwater.fairhairai.search.SearchPresenter;
import com.meltwater.fairhairai.search.SearchProtocol;
import com.meltwater.fairhairai.search.di.DaggerSearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityModule;

import javax.inject.Inject;

public class SearchActivity extends BaseActivity implements SearchListFragment.OnFragmentInteractionListener,
        SearchDetailFragment.OnFragmentInteractionListener, SearchProtocol.View {

    //private SearchListFragment mSearchListFragment;

    @Inject
    SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // These 2 lines can be moved to the Router. But do we really need to do that? We only need
        // to build the module in the starting Activity. Router can build modules it's routing to.
        SearchActivityComponent component = DaggerSearchActivityComponent.builder()
                .searchActivityModule(new SearchActivityModule(this))
                .applicationComponent(((FhaiApplication)getApplication()).getAppComponent())
                .build();
        // Proceed with injections. This fragment will get all the injections after this call
        component.inject(this);

        presenter.onCreate();

        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // mSearchListFragment = (SearchListFragment) getSupportFragmentManager().findFragmentById(R.id.search_list_fragment);
    }

    @Override
    public void onAddSearchClicked() {
        presenter.createNewSearch();
    }


    // TODO better this one
    public void onSearchSelected(Search search) {
        presenter.onSearchSelected(search);
    }

    @Override
    public void onCloseDetailFragment(Search search) {

        if (search != null) {
            presenter.saveSearch(search);
        }
    }
}
