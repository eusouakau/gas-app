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
import com.example.gas_app.adapter.ProdutoAdapter;
import com.example.gas_app.mock.MockProduto;
import com.example.gas_app.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);

        int tam = MockProduto.nome.length;

        new AsyncTask<Void,Void, List<Produto>>() {

            @Override
            protected List<Produto> doInBackground(Void... voids) {
                List<Produto> produtos = new ArrayList<>();
                Produto produto = new Produto();

                String nome = "";
                Double preco = 0.0;

                for (int position = 0; position < tam; position ++ ){
                    nome = MockProduto.nome[position];
                    produto.setNome(nome.trim());

                    preco = MockProduto.preco[position];
                    produto.setPreco(preco);

                    produtos.add(produto);
                }

                return produtos;
            }

            @Override
            protected void onPostExecute(List<Produto> produtos) {
                super.onPostExecute(produtos);
                inicializaRecycler(produtos);
            }

        }.execute();
        return root;
    }
    private void inicializaRecycler(List<Produto> produtos){
        ProdutoAdapter produtoAdapter = new ProdutoAdapter(produtos);
        recyclerView.setAdapter(produtoAdapter);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

    }
}