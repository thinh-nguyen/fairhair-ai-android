package com.meltwater.fairhairai.search.view;

import android.content.Context;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.meltwater.fairhairai.R;
import com.meltwater.fairhairai.persistence.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchDetailFragment extends Fragment {

    private static final String ARG_SEARCH = "search";

    private Search search;

    private EditText name;

    private EditText keyword1;

    private EditText keyword2;

    private OnFragmentInteractionListener listener;

    public SearchDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param search Search
     * @return A new instance of fragment SearchDetailFragment.
     */
    public static SearchDetailFragment newInstance(Search search) {
        SearchDetailFragment fragment = new SearchDetailFragment();
        Bundle args = new Bundle();
        // Serialize SearchViewModel
        args.putSerializable(ARG_SEARCH, search);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            search = (Search) getArguments().getSerializable(ARG_SEARCH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button saveButton = view.findViewById(R.id.save_button);
        Button searchButton = view.findViewById(R.id.search_button);

        name = view.findViewById(R.id.search_name);
        keyword1 = view.findViewById(R.id.keyword1);
        keyword2 = view.findViewById(R.id.keyword2);

        if (search.getName() != null) {
            name.setText(search.getName());
        }
        List<String> kws = search.getKeywords();
        if (kws != null) {
            if (kws.size() > 0) {
                keyword1.setText(kws.get(0));
            }
            if (kws.size() > 1) {
                keyword2.setText(kws.get(1));
            }
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save search
                ArrayList<String> kws = new ArrayList<>();
                kws.add(keyword1.getText().toString());
                kws.add(keyword2.getText().toString());
                search.setName(name.getText().toString());
                search.setKeywords(kws);
                hideKeyboard();
                if (listener != null) {
                    listener.onCloseDetailFragment(search);
                }
            }
        });

        searchButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (listener != null) {
            listener.onCloseDetailFragment(null);
        }
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onCloseDetailFragment(Search searchViewModel);
    }

    private void hideKeyboard() {
        View v = getActivity().getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
