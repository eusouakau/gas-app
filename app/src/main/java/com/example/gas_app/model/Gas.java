package com.example.gas_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Gas implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private Double valor;
}
