package com.example.gas_app.model;

import java.io.Serializable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String endereco;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
