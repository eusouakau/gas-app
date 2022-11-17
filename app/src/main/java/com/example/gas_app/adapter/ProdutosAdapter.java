package com.example.gas_app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gas_app.R;
import com.example.gas_app.dao.AppDatabase;
import com.example.gas_app.dao.PedidoDAO;
import com.example.gas_app.mock.Produtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.MyViewHolder> {

    List<Produtos> listaProdutos = new ArrayList<>();
    Context context;
    public ProdutosAdapter(List<Produtos> produtos) {
        this.listaProdutos = produtos;
    }

    @NonNull
    @Override
    public ProdutosAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_icones, viewGroup, false);

        this.context = viewGroup.getContext();
        return new ProdutosAdapter.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoAdapter.MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        Produtos p = listaProdutos.get(position);
        myViewHolder.nome.setText(p.nome);
        myViewHolder.preco.setText(p.preco);
        myViewHolder.img.setText(p.img);

        Bundle bundle = new Bundle();
        bundle.putInt("IMG", listaProdutos.get(position).img);
        bundle.putString("NOME", listaProdutos.get(position).nome);
        bundle.putString("PRECO", listaProdutos.get(position).preco);
        myViewHolder.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_homeFragment, bundle));
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        TextView endereco;
        ImageButton btnDelete;
        ImageButton btnEdit;
        public MyViewHolder(View itemView){
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNome);
            endereco = itemView.findViewById(R.id.textViewEndereco);
            btnDelete = itemView.findViewById(R.id.btnExcluir);
            btnEdit= itemView.findViewById(R.id.btnEditar);
        }
    }
}

