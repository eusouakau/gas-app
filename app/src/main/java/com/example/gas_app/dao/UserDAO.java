package com.example.gas_app.dao;

import androidx.room.*;
import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.List;
import com.example.gas_app.model.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User")
    public List<User> getAllUsers();

    @Query("SELECT * FROM User WHERE nome = :name")
    public List<User> getUserByName(String name);

    @Insert(onConflict = REPLACE)
    public void insert(User user);

    @Update
    public void update(User user);

    @Delete
    public void delete(User user);

}