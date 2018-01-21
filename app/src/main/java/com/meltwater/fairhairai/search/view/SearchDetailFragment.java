package com.meltwater.fairhairai.search.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.meltwater.fairhairai.R;
import com.meltwater.fairhairai.persistence.AppDatabase;
import com.meltwater.fairhairai.persistence.Search;

import java.util.ArrayList;

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

    private Search mSearch;

    private EditText mName;

    private EditText mKeyword1;

    private EditText mKeyword2;

    private OnFragmentInteractionListener mListener;

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
        // Serialize Search
        args.putSerializable(ARG_SEARCH, search);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearch = (Search)getArguments().getSerializable(ARG_SEARCH);
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
        Button button = view.findViewById(R.id.save_button);
        mName = view.findViewById(R.id.search_name);
        mKeyword1 = view.findViewById(R.id.keyword1);
        mKeyword2 = view.findViewById(R.id.keyword2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save search
                ArrayList<String> kws = new ArrayList<>();
                kws.add(mKeyword1.getText().toString());
                kws.add(mKeyword2.getText().toString());
                mSearch.setName(mName.getText().toString());
                mSearch.setKeywords(kws);
                if (mListener != null) {
                    mListener.onCloseDetailFragment(mSearch);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null) {
            mListener.onCloseDetailFragment(null);
        }
        mListener = null;
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
        // TODO: Update argument type and name
        void onCloseDetailFragment(Search search);
    }
}
