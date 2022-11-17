package com.example.gas_app.dao;

import androidx.room.*;
import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.List;

import com.example.gas_app.model.Pedido;

@Dao
public interface PedidoDAO {

    @Query("SELECT * FROM Pedido")
    public List<Pedido> getAllPedidos();

    @Query("SELECT * FROM Pedido WHERE nome = :name")
    public List<Pedido> getUserByName(String name);

    @Insert(onConflict = REPLACE)
    public void insert(Pedido pedido);

    @Update
    public void update(Pedido pedido);

    @Delete
    public void delete(Pedido pedido);

}