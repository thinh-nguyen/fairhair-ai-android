package com.meltwater.fairhairai.search;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.meltwater.fairhairai.FhaiApplication;
import com.meltwater.fairhairai.R;
import com.meltwater.fairhairai.base.BaseActivity;
import com.meltwater.fairhairai.persistence.Search;
import com.meltwater.fairhairai.search.di.DaggerSearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityModule;
import com.meltwater.fairhairai.search.view.SearchActivity;
import com.meltwater.fairhairai.search.view.SearchDetailFragment;
import com.meltwater.fairhairai.search.view.SearchListFragment;

/**
 * Created by thinhnguyen on 1/4/18.
 *
 */

public class SearchRouter implements SearchProtocol.Router {

    final static private String SEARCH_DETAIL_FRAGMENT = "SearchDetailFragment";

    private BaseActivity activity;

    public SearchRouter(BaseActivity activity) {
        this.activity = activity;
    }

    public void showToast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId) {
        Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show();
    }

    public void showSearchList() {

        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        SearchDetailFragment searchDetailFragment = (SearchDetailFragment) activity.getSupportFragmentManager().findFragmentByTag(SEARCH_DETAIL_FRAGMENT);
        if (searchDetailFragment != null) {
            ft.remove(searchDetailFragment);
        }
        SearchListFragment searchListFragment = (SearchListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.search_list_fragment);
        ft.show(searchListFragment);
        ft.commit();
    }

    public void showSearchDetail(Search search) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        SearchDetailFragment searchDetailFragment = SearchDetailFragment.newInstance(search);
        ft.add(R.id.search_fragment_placeholder, searchDetailFragment, SEARCH_DETAIL_FRAGMENT);
        SearchListFragment searchListFragment = (SearchListFragment) activity.getSupportFragmentManager().findFragmentById(R.id.search_list_fragment);
        ft.hide(searchListFragment);

        ft.addToBackStack("search detail tag");// Go back to searchList if going back from details
        ft.commit();
    }

    public void navigateToHome() {
//        Intent intent = new Intent(activity, HomeActivity.class);
//        activity.startActivity(intent);
    }

    public void finish() {
        activity.finish();
    }

    public void finishWithResultOK() {
        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }

    public void showProgressDialog(String message) {
        activity.showProgressDialog(message);
    }

    public void showProgressDialog(int resId) {
        activity.showProgressDialog(resId);
    }

    public void dismissProgressDialog() {
        activity.dismissProgressDialog();
    }

    // WE CAN PUT THE CODE TO BUILD THE MODULE HERE ALTHOUGH NOT NECESSARY
    static SearchActivityComponent createModule(SearchActivity activity) {
        SearchActivityComponent component = DaggerSearchActivityComponent.builder()
                .searchActivityModule(new SearchActivityModule(activity))
                .applicationComponent(((FhaiApplication)activity.getApplication()).getAppComponent())
                .build();
        component.inject(activity);
        return component;
    }
}

