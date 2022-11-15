package com.example.gas_app.fragments;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.gas_app.dao.AppDatabase;
import com.example.gas_app.dao.UserDAO;
import com.example.gas_app.model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class EditarFragment extends Fragment {
    private TextInputEditText txtNome;
    private TextInputEditText txtEnd;
    private Button btnEditar;
    private int id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_editar, container, false);
        Bundle bundle = getArguments();
        id = bundle.getInt("ID");
        txtNome = root.findViewById(R.id.txtNome);
        txtNome.setText(bundle.getString("NOME"));
        txtEnd = root.findViewById(R.id.txtEnd);
        txtEnd.setText(bundle.getString("END"));
        btnEditar = root.findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarItem();
            }
        });
        return root;
    }
    private void editarItem() {
        new AlertDialog.Builder(getContext())
                .setTitle("Editando pessoa")
                .setMessage("Tem certeza que deseja editar essa pessoa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        User user = new User();
                        user.setId(id);
                        user.setNome(txtNome.getText().toString().trim());
                        user.setEndereco(txtEnd.getText().toString().trim());
                        Log.d("OBJ", user.toString());
                        AsyncTask<Void, Void, Void> execute = new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                UserDAO userDAO = AppDatabase.getInstance(getContext()).createUserDAO();
                                userDAO.update(user);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                Snackbar.make(getView(), "item editado!!!", Snackbar.LENGTH_LONG).show();
                                Navigation.findNavController(getView()).navigate(R.id.action_nav_editarFragment_to_nav_listarFragment);
                            }
                        }.execute();

                    }
                }).setNegativeButton("Não", null).show();
    }
}
