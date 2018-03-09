package com.meltwater.fairhairai.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by thinhnguyen on 1/7/18.
 */

@Dao
public interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSearch(Search search);

    @Query("SELECT * FROM Search")
    LiveData<List<Search>> findSearches();

    @Query("SELECT * FROM Search WHERE name = :name")
    LiveData<Search> searchByName(String name);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSearch(Search search);

    @Query("delete from Search where id = :id")
    void delete(long id);
}
