package com.meltwater.fairhairai.search.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meltwater.fairhairai.FhaiApplication;
import com.meltwater.fairhairai.R;
import com.meltwater.fairhairai.persistence.Search;
import com.meltwater.fairhairai.search.SearchPresenter;
import com.meltwater.fairhairai.search.SearchProtocol;
import com.meltwater.fairhairai.search.SearchViewModel;
import com.meltwater.fairhairai.search.UISearchResult;
import com.meltwater.fairhairai.search.di.DaggerSearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityComponent;
import com.meltwater.fairhairai.search.di.SearchActivityModule;

import java.util.List;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchListFragment extends Fragment implements SearchProtocol.View {

    private RecyclerView mSearchListView;
    private FloatingActionButton mAddButton;
    private OnFragmentInteractionListener mListener;

    public SearchListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        } else {
            throw new ClassCastException(context.toString() + " must implement SeachListFragment.OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the dagger component

        subscribeViewModel();
        initViews(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // initViews();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override // SearchProtocol.View
    public void handleResult(UISearchResult result)  {

    }

    @Override
    public void handleSearchList(List<SearchViewModel> searchList) {
        mSearchListView.setVisibility(View.VISIBLE);
        SearchListAdapter adapter = new SearchListAdapter(searchList);
        mSearchListView.setAdapter(adapter);
    }

    private void initViews(View view) {
        mSearchListView = (RecyclerView) view.findViewById(R.id.search_list);
        mSearchListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((SearchActivity)getActivity()).presenter.getSearches();

        mAddButton = view.findViewById(R.id.add_floatingButton);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Switch to add a new search
                mListener.onAddSearchClicked();
            }
        });
    }

    private void subscribeViewModel() {
        ((SearchActivity)getActivity()).presenter.setViewReference(this);// TODO Remove this in favor of next line
       // presenter.getObservableViewModel().subscribe(model -> bindViewModel(model));
    }


    private void bindViewModel(SearchViewModel viewModel) {
        mSearchListView.setVisibility(View.VISIBLE);
        SearchListAdapter adapter = new SearchListAdapter(viewModel.getSearchViewModels());
        mSearchListView.setAdapter(adapter);
    }

    // Custom listener to communicate with parent Activity
    public interface OnFragmentInteractionListener {
        public void onAddSearchClicked();
        public void onSearchSelected(Search search);
    }
}
