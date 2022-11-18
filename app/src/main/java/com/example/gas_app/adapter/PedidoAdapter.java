package com.example.gas_app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gas_app.R;
import com.example.gas_app.dao.AppDatabase;
import com.example.gas_app.dao.PedidoDAO;
import com.example.gas_app.model.Pedido;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder> {
    List<Pedido> listaPedidos;
    Context context;
    public PedidoAdapter(List<Pedido> pedido) {
        this.listaPedidos = pedido;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card, viewGroup, false);

        this.context = viewGroup.getContext();
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        Pedido p = listaPedidos.get(position);
        myViewHolder.nomeProd.setText(p.getNomeProd());
        myViewHolder.total.setText(p.getTotal());
        myViewHolder.qtd.setText(p.getQtd());
        myViewHolder.btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Exclusão de registro de pedido.")
                .setMessage("Tem certeza que deseja deletar este registro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                PedidoDAO pedidoDAO = AppDatabase.getInstance(context).createPedidoDAO();
                                List<Pedido> pedidos = pedidoDAO.getAllPedidos();
                                pedidoDAO.delete(pedidos.get(position));
                                return null;
                            }
                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                listaPedidos.remove(position);
                                notifyItemRemoved(position);
                            }
                        }.execute();
                    }}).setNegativeButton("Não", null).show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nomeProd;
        TextView total;
        TextView qtd;
        ImageButton btnExcluir;

        public MyViewHolder(View itemView){
            super(itemView);
            nomeProd = itemView.findViewById(R.id.textViewNomeProd);
            total = itemView.findViewById(R.id.textViewTotal);
            qtd = itemView.findViewById(R.id.textViewQtd);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
            
        }
    }
}

