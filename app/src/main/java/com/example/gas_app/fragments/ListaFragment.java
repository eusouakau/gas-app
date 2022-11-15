package com.example.gas_app.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gas_app.adapter.UserAdapter;
import com.example.gas_app.dao.AppDatabase;
import com.example.gas_app.dao.UserDAO;
import com.example.gas_app.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListaFragment extends Fragment {
    private  RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_listar, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        //configurar o adapter - que formata que o layout de cada item do recycler
        new AsyncTask<Void,Void, List<User>>() {

            @Override
            protected List<User> doInBackground(Void... voids) {
                UserDAO userDAO = AppDatabase.getInstance(getContext()).createUserDAO();
                return userDAO.getAllUsers();
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                inicializaRecycler(users);
            }

        }.execute();
        return root;
    }
    private void inicializaRecycler(List<User> users){
        UserAdapter userAdapter = new UserAdapter(users);//new MyAdapter(Pessoa.inicializaLista());
        recyclerView.setAdapter(userAdapter);
        //linha de código usada para otimizar o recycler
        recyclerView.setHasFixedSize(true);

        //configurar o gerenciador de layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager);


    }
}