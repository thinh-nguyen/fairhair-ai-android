package com.meltwater.fairhairai.search.view;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.meltwater.fairhairai.R;
import com.meltwater.fairhairai.persistence.Search;
import com.meltwater.fairhairai.search.SearchProtocol;

import java.util.List;

/**
 * A fragment containing the list of searches.
 */
public class SearchListFragment extends Fragment implements SearchProtocol.View {

    private RecyclerView searchListView;
    private FloatingActionButton addButton;
    private OnFragmentInteractionListener listener;

    public SearchListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
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

        //subscribeViewModel();
        initViews(view);
        observeViewModelLiveData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // initViews();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void handleSearchList(List<Search> searchList) {
        searchListView.setVisibility(View.VISIBLE);
        SearchListAdapter adapter = new SearchListAdapter(searchList);
        searchListView.setAdapter(adapter);
    }

    private void initViews(View view) {
        searchListView = (RecyclerView) view.findViewById(R.id.search_list);
        searchListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addButton = view.findViewById(R.id.add_floatingButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Switch to add a new search
                listener.onAddSearchClicked();
            }
        });
    }

    private void observeViewModelLiveData() {
        LiveData<List<Search>> searchLiveDataList = ((SearchActivity)getActivity()).presenter.getSearches();
        searchLiveDataList.observe(this, list -> {
            handleSearchList(list);// Call everytime the search list change in the database
        });

    }

    // Custom listener to communicate with parent Activity
    public interface OnFragmentInteractionListener {
        public void onAddSearchClicked();
        public void onSearchClicked();
    }

}

