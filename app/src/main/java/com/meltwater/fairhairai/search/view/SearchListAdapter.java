package com.meltwater.fairhairai.search.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meltwater.fairhairai.R;
import com.meltwater.fairhairai.persistence.Search;

import java.util.List;

/**
 * Created by thinhnguyen on 1/10/18.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchViewHolder> {

    private List<Search> viewItems;

    public SearchListAdapter(List<Search> viewItems) {
        this.viewItems = viewItems;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        Search item = viewItems.get(position);
        holder.titleView.setText(item.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchActivity)v.getContext()).onSearchSelected(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    // VIEW HOLDER INNER CLASS
    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.search_name);
        }
    }
}