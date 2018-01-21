package com.meltwater.fairhairai.search.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.meltwater.fairhairai.FhaiApplication;
import com.meltwater.fairhairai.R;
import com.meltwater.fairhairai.persistence.AppDatabase;
import com.meltwater.fairhairai.persistence.Search;
import com.meltwater.fairhairai.search.SearchPresenter;
import com.meltwater.fairhairai.search.di.DaggerSearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityModule;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchListFragment.OnFragmentInteractionListener,
        SearchDetailFragment.OnFragmentInteractionListener {

    private SearchListFragment mSearchListFragment;

    @Inject
    SearchPresenter presenter;

    final static private String SEARCH_DETAIL_FRAGMENT = "SearchDetailFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        mSearchListFragment = (SearchListFragment) getSupportFragmentManager().findFragmentById(R.id.search_list_fragment);
    }

    @Override
    public void onAddSearchClicked() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SearchDetailFragment searchDetailFragment = SearchDetailFragment.newInstance(new Search());
        ft.add(R.id.search_fragment_placeholder, searchDetailFragment, SEARCH_DETAIL_FRAGMENT);
        ft.hide(mSearchListFragment);

        ft.addToBackStack("search detail tag");// Go back to searchList if going back from details
        ft.commit();
    }

    @Override
    public void onSearchSelected(Search search) {

    }

    @Override
    public void onCloseDetailFragment(Search search) {

        if (search != null) {
            presenter.saveSearch(search);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SearchDetailFragment searchDetailFragment = (SearchDetailFragment) getSupportFragmentManager().findFragmentByTag(SEARCH_DETAIL_FRAGMENT);
        if (searchDetailFragment != null) {
            ft.remove(searchDetailFragment);
        }
        ft.show(mSearchListFragment);
        ft.commit();
    }
}
