package com.example.gas_app.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.gas_app.R;
import com.example.gas_app.dao.AppDatabase;
import com.example.gas_app.dao.PedidoDAO;
import com.example.gas_app.model.Pedido;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


public class PedidoFragment extends Fragment {
    private TextInputEditText txtNome;
    private TextInputEditText txtEndereco;
    private TextView txtNomeProd;
    private TextView txtTotal;
    private String txtPreco;
    private TextView txtQtd;

    private Button btnFinalizar;
    private Button btnCancelar;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedido, container, false);
        context = getContext();

        Bundle bundle = getArguments();

        txtNome = root.findViewById(R.id.editTextNome);
        txtEndereco = root.findViewById(R.id.editTextEndereco);

        txtNomeProd = root.findViewById(R.id.textViewNomeProd);
        txtNomeProd.setText(bundle.getString("NOME_PROD"));
        txtTotal= root.findViewById(R.id.textViewTotal);
        txtTotal.setText(String.valueOf(bundle.getDouble("TOTAL")));
        //txtPreco = root.findViewById(R.id.textViewNomeProd);
        txtPreco = String.valueOf(bundle.getDouble("PRECO"));
        txtQtd = root.findViewById(R.id.textViewQtd);
        txtQtd.setText(String.valueOf(bundle.getInt("QTD")));

        btnCancelar = root.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setMessage("Você tem certeza que deseja cancelar o pedido?")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Snackbar.make(view, "Pedido cancelado!", Snackbar.LENGTH_LONG).show();
                                Navigation.findNavController(view).navigate(R.id.nav_home);
                            }
                        })
                        .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {}
                        });
                builder.create().show();
            }
        });
        btnFinalizar = root.findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncTask<Void,Integer, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... voids) {
                        PedidoDAO pedidoDao = AppDatabase.getInstance(context).createPedidoDAO();
                        Pedido pedido = new Pedido();
                        pedido.setNome(txtNome.getText().toString());
                        pedido.setEndereco(txtEndereco.getText().toString());
                        pedido.setNomeProd(txtNomeProd.getText().toString());
                        pedido.setTotal(txtTotal.getText().toString());
                        pedido.setPreco(txtPreco);
                        pedido.setQtd(txtQtd.getText().toString());

                        pedidoDao.insert(pedido);
                        return pedido.getId();
                    }

                    @Override
                    protected void onPostExecute(Integer id) {
                        if(id==null)
                            Snackbar.make(view, "Erro ao submeter pedido.", Snackbar.LENGTH_LONG).show();
                        else {
                            Snackbar.make(view, "Pedido processado com sucesso!", Snackbar.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_nav_pedidoFragment_to_nav_historicoFragment);        }
                    }
                }.execute();
            }
        });
        return root;
    }
}