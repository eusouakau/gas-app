package com.example.gas_app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gas_app.R;
import com.example.gas_app.mock.Produtos;

import java.util.ArrayList;
import java.util.List;

public class ProdutosAdapter extends RecyclerView.Adapter<LineHolder> {

    private final List<Produtos> mProdutos;

    public ProdutosAdapter(ArrayList produtos) {
        mProdutos = produtos;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gas_card, parent, false));
    }

    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position) {
        holder.title.setText(String.format(Locale.getDefault(), "%s, %d - %s",
                mProdutos.get(position).getName(),
                mProdutos.get(position).getAge(),
                mProdutos.get(position).getCity()
        ));

        holder.moreButton.setOnClickListener(view -> updateItem(position));
        holder.deleteButton.setOnClickListener(view -> removerItem(position));
    }

    @Override
    public int getItemCount() {
        return mProdutos != null ? mProdutos.size() : 0;
    }

}
