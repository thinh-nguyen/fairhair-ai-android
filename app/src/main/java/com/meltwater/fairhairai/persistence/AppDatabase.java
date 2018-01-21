package com.meltwater.fairhairai.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import dagger.Module;
import dagger.Provides;


/**
 * Created by thinhnguyen on 1/7/18.
 */

@Database(entities = {Search.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract SearchDao searchDao();

    public static AppDatabase provideAppDatabase(Context appContext) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(appContext, AppDatabase.class, "fhaidatabase")
                    //.allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
