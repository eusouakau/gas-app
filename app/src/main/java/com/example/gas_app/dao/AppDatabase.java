package com.example.gas_app.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gas_app.model.Gas;
import com.example.gas_app.model.User;


@Database(entities = { User.class, Gas.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract UserDAO createUserDAO();

    public abstract GasDAO createGasDAO();


    public static AppDatabase getInstance(Context context) {
        if(appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "driver_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
}