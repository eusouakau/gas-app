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

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gas_app.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    List<User> listaPessoas = new ArrayList<>();
    Context context;
    public MyAdapter(List<Pessoa> pessoas) {
        this.listaPessoas = pessoas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_icones, viewGroup, false);
        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //retorna o itemList que é passado para o construtor da MyViewHolder
        this.context = viewGroup.getContext();
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        //exibe os itens no Recycler
        Pessoa p = listaPessoas.get(position);
        myViewHolder.nome.setText(p.getNome());
        myViewHolder.dataNascimento.setText(p.getDataNascimento());
        myViewHolder.endereco.setText(p.getEndereco());
        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItem(position);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putInt("ID", listaPessoas.get(position).getId());
        bundle.putString("NOME", listaPessoas.get(position).getNome());
        bundle.putString("END", listaPessoas.get(position).getEndereco());
        bundle.putString("DTNASC", listaPessoas.get(position).getDataNascimento());
        myViewHolder.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_editarFragment, bundle));
    }

    @Override
    public int getItemCount() {
        //retorna a quantidade de itens que será exibida
        return listaPessoas.size();
    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando pessoa")
                .setMessage("Tem certeza que deseja deletar essa pessoa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                PessoaDAO pessoaDAO = AppDatabase.getInstance(context).createPessoaDAO();
                                List<Pessoa> pessoas = pessoaDAO.getAllPessoas();
                                pessoaDAO.delete(pessoas.get(position));
                                return null;
                            }
                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                listaPessoas.remove(position);
                                notifyItemRemoved(position);
                            }
                        }.execute();
                    }}).setNegativeButton("Não", null).show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //dados da pessoa que serão exibidos no recycler
        TextView nome;
        TextView dataNascimento;
        TextView endereco;
        ImageButton btnDelete;
        ImageButton btnEdit;
        //se usar adapter_card -> ajustar o ViewHolder para usar Button
        //Button btnDelete;
        //Button btnEdit;
        public MyViewHolder(View itemView){
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            nome = itemView.findViewById(R.id.textViewNome);
            dataNascimento = itemView.findViewById(R.id.textViewDtNasc);
            endereco = itemView.findViewById(R.id.textViewEndereco);
            btnDelete = itemView.findViewById(R.id.btnExcluir);
            btnEdit= itemView.findViewById(R.id.btnEditar);
        }
    }
}

