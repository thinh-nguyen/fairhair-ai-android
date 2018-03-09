package com.meltwater.fairhairai.persistence;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinhnguyen on 1/7/18.
 */

@Entity(tableName = "search",
        indices = { @Index(value = "id"), @Index(value = "name") }
)
public class Search implements Serializable {

    @PrimaryKey(autoGenerate = true)
    long id = 0;

    private String name;
    //private Operation operation;
    private List<String> keywords;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    //    private enum Operation {
//
//    }
}
