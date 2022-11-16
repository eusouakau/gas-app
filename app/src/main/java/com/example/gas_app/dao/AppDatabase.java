package com.example.gas_app.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gas_app.model.User;


@Database(entities = { User.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract UserDAO createUserDAO();

    public static AppDatabase getInstance(Context context) {
        if(appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "gas-app")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
}