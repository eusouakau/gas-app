package com.example.gas_app.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.gas_app.dao.AppDatabase;
import com.example.gas_app.dao.UserDAO;
import com.example.gas_app.model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


public class CadastroFragment extends Fragment {
    private TextInputEditText txtNome;
    private TextInputEditText txtEndereco;
    private Button btnCadastrar;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cadastrar, container, false);
        context = getContext();
        txtNome = root.findViewById(R.id.txtNome);
        txtEndereco = root.findViewById(R.id.txtEnd);
        btnCadastrar = root.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncTask<Void,Integer, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... voids) {
                        UserDAO userDao = AppDatabase.getInstance(context).createUserDAO();
                        User user = new User();
                        user.setNome(txtNome.getText().toString());
                        user.setEndereco(txtEndereco.getText().toString());

                        userDao.insert(user);
                        return user.getId();
                    }

                    @Override
                    protected void onPostExecute(Integer id) {
                        if(id==null)
                            Snackbar.make(view, "Erro ao inserir registro", Snackbar.LENGTH_LONG).show();
                        else {
                            Snackbar.make(view, "Nome informado = "+txtNome.getText().toString(), Snackbar.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_nav_cadastrarFragment_to_nav_home);        }
                    }
                }.execute();
            }
        });
        return root;
    }
}