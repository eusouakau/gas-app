package com.example.gas_app.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.*;

import com.example.gas_app.model.Gas;

import java.util.List;


@Dao
public interface GasDAO {

    @Query("SELECT * FROM Pessoa")
    public List<Gas> getAllGases();

    @Query("SELECT * FROM Pessoa WHERE nome = :name")
    public List<Gas> getGasByName(String name);

    @Insert(onConflict = REPLACE)
    public void insert(Gas gas);

    @Update
    public void update(Gas gas);

    @Delete
    public void delete(Gas gas);

}