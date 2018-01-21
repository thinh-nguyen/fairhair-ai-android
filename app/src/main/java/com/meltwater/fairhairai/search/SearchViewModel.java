package com.meltwater.fairhairai.search;

import com.meltwater.fairhairai.base.ViewModel;
import com.meltwater.fairhairai.persistence.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinhnguyen on 1/4/18.
 */

public class SearchViewModel implements ViewModel {

    private String name;
    private List<String> keywords;
    private Operation operation;
    private List<SearchViewModel> searchViewModels = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public List<SearchViewModel> getSearchViewModels() {
        return searchViewModels;
    }

    public void setSearchViewModels(List<SearchViewModel> searchViewModels) {
        this.searchViewModels = searchViewModels;
    }

    public enum Operation {
        AND,
        OR,
        NOT,
        NONE
    }

    public static SearchViewModel instance(Search search) {
        SearchViewModel model = new SearchViewModel();
        model.setName(search.getName());
        model.setKeywords(search.getKeywords());
        model.setOperation(Operation.AND);// TODO hard code for now
        return model;
    }
}
