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

import com.example.gas_app.R;
import com.example.gas_app.adapter.PedidoAdapter;
import com.example.gas_app.dao.AppDatabase;
import com.example.gas_app.dao.PedidoDAO;
import com.example.gas_app.model.Pedido;

import java.util.List;

public class HistoricoFragment extends Fragment {
    private  RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_historico, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);

        new AsyncTask<Void,Void, List<Pedido>>() {

            @Override
            protected List<Pedido> doInBackground(Void... voids) {
                PedidoDAO pedidoDAO = AppDatabase.getInstance(getContext()).createPedidoDAO();
                return pedidoDAO.getAllPedidos();
            }

            @Override
            protected void onPostExecute(List<Pedido> pedidos) {
                super.onPostExecute(pedidos);
                inicializaRecycler(pedidos);
            }

        }.execute();
        return root;
    }
    private void inicializaRecycler(List<Pedido> pedidos){
        PedidoAdapter pedidoAdapter = new PedidoAdapter(pedidos);
        recyclerView.setAdapter(pedidoAdapter);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
    }
}