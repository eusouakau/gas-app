package com.example.gas_app.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    private Button btnCadastrar;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedido, container, false);
        context = getContext();
        txtNome = root.findViewById(R.id.editTextNome);
        txtEndereco = root.findViewById(R.id.editTextEndereco);
        btnCadastrar = root.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncTask<Void,Integer, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... voids) {
                        PedidoDAO pedidoDao = AppDatabase.getInstance(context).createPedidoDAO();
                        Pedido pedido = new Pedido();
                        pedido.setNome(txtNome.getText().toString());
                        pedido.setEndereco(txtEndereco.getText().toString());

                        pedidoDao.insert(pedido);
                        return pedido.getId();
                    }

                    @Override
                    protected void onPostExecute(Integer id) {
                        if(id==null)
                            Snackbar.make(view, "Erro ao inserir registro", Snackbar.LENGTH_LONG).show();
                        else {
                            Snackbar.make(view, "Nome informado = "+txtNome.getText().toString(), Snackbar.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_nav_pedidoFragment_to_nav_home);        }
                    }
                }.execute();
            }
        });
        return root;
    }
}