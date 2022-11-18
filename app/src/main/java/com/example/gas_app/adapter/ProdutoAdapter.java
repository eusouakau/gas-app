package com.example.gas_app.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gas_app.R;
import com.example.gas_app.mock.MockProduto;
import com.example.gas_app.model.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    List<Produto> listaProdutos;
    Double sum;
    Context context;
    public ProdutoAdapter(List<Produto> produtos) {
        this.listaProdutos = produtos;
    }

    @NonNull
    @Override
    public ProdutoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.gas_card, viewGroup, false);

        this.context = viewGroup.getContext();
        return new ProdutoAdapter.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoAdapter.MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        Produto p = listaProdutos.get(position);

        myViewHolder.nome.setText(MockProduto.nome[position]);
        myViewHolder.preco.setText(String.valueOf(MockProduto.preco[position]));

        Resources res = context.getResources();
        Drawable drawable = ResourcesCompat.getDrawable(res, MockProduto.img[position], null);
        myViewHolder.img.setImageDrawable(drawable);

        if (p.getQtd() == null){
            myViewHolder.qtdd.setText("0");
        }
        if (p.getTotal() == null){
            myViewHolder.total.setText("0");
        } else {
            myViewHolder.total.setText(String.valueOf(p.getTotal()));
        }

        Bundle bundle = new Bundle();

        bundle.putInt("QTD", 0);

        myViewHolder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtd = Integer.parseInt(String.valueOf(myViewHolder.qtdd.getText()));

                if (qtd <= 0){
                    qtd = 0;
                } else {
                    qtd = qtd - 1;

                }
                double preco = Double.parseDouble(String.valueOf(MockProduto.preco[position]));

                sum = qtd * preco;

                p.setQtd(qtd);
                myViewHolder.qtdd.setText(String.valueOf(qtd));

                p.setTotal(sum);
                myViewHolder.total.setText(String.valueOf(sum));

                bundle.putString("NOME_PROD", MockProduto.nome[position]);
                bundle.putDouble("PRECO", MockProduto.preco[position]);
                bundle.putInt("QTD", p.getQtd());
                bundle.putDouble("TOTAL", p.getTotal());

            }
        });

        myViewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qtd = Integer.parseInt(String.valueOf(myViewHolder.qtdd.getText()));

                qtd = qtd + 1;

                double preco = Double.parseDouble(String.valueOf(MockProduto.preco[position]));

                sum = qtd * preco;

                p.setQtd(qtd);
                myViewHolder.qtdd.setText(String.valueOf(qtd));

                p.setTotal(sum);
                myViewHolder.total.setText(String.valueOf(sum));

                bundle.putString("NOME_PROD", MockProduto.nome[position]);

                bundle.putDouble("PRECO", MockProduto.preco[position]);
                bundle.putInt("QTD", p.getQtd());
                bundle.putDouble("TOTAL", p.getTotal());

            }
        });
        myViewHolder.btnAdicionar.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_home_to_nav_pedidoFragment, bundle));
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView preco;
        ImageView img;
        EditText qtdd;
        TextView total;
        ImageButton sub;
        ImageButton add;
        Button btnAdicionar;

        public MyViewHolder(View itemView){
            super(itemView);
            nome = itemView.findViewById(R.id.textViewNome);
            preco = itemView.findViewById(R.id.textViewPreco);
            img = itemView.findViewById(R.id.imageViewImg);
            qtdd = itemView.findViewById(R.id.editTextQtd);
            total = itemView.findViewById(R.id.textViewTotal);
            sub = itemView.findViewById(R.id.subButton);
            add = itemView.findViewById(R.id.addButton);
            btnAdicionar = itemView.findViewById(R.id.buttonAdicionar);
        }
    }
}

