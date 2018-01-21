package com.meltwater.fairhairai.search.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meltwater.fairhairai.R;
import com.meltwater.fairhairai.search.SearchViewModel;

import java.util.List;

/**
 * Created by thinhnguyen on 1/10/18.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchViewHolder> {

    private List<SearchViewModel> mViewItems;

    public SearchListAdapter(List<SearchViewModel> mViewItems) {
        this.mViewItems = mViewItems;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        SearchViewModel item = mViewItems.get(position);
        holder.titleView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mViewItems.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.search_name);
        }
    }
}