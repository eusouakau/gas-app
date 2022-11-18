package com.example.gas_app.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.gas_app.R;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_pedidoFragment, R.id.nav_historicoFragment)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Segurança");

                builder.setMessage("1. Manusear botijões de gás com cuidado, evitando que caiam ou sofram pancadas;\n" +
                                "2. Os botijões devem ser guardados em locais limpos, ventilados, livres de óleo e graxa, protegidos contra chuva, sol, e outras fontes de calor;\n" +
                                "3. Botijões de gás domésticos não devem ficar juntos do fogão. Precisam estar fora da casa e conectados com tubulações metálicas;\n" +
                                "4. Caso o gás esteja instalado dentro de casa e ele vier a vazar, não risque fósforo e não acenda ou apague luzes. Chame os bombeiros (193) e se possível retire o botijão da sua casa. Abra as portas e janelas, corte a energia no relógio e fique longe do local onde o gás está vazando;\n" +
                                "5. Ao instalar um novo botijão use espuma de sabão para testar se há vazamentos;\n" +
                                "6. Jamais use fogo para tal propósito, mas lembre-se: o sabão não deve ser usado para vedar vazamentos;\n" +
                                "7. Ao acender um forno de fogão, riscar primeiro o fósforo e abrir o gás depois;\n" +
                                "Se a casa ficar desocupada por um período prolongado, feche o registro de gás.")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {}
                        });
                builder.create().show();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}