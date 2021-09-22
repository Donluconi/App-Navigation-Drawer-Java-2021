package com.example.navigationdrawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ///configura a barra de navegação
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                enviarEmail();

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

            }
        });

        //cria referencia para toda a área do navigationDrawer
        DrawerLayout drawer = binding.drawerLayout;

        //cria referencia para a área de navegação.
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //define as configurações do NavigationDrawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_servico,
                R.id.nav_clientes, R.id.nav_contato, R.id.nav_sobre)
                .setOpenableLayout(drawer)
                .build();

        //configura area que irá carregar os fragments
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        //ele configura menu superior da navegação (os 3 tracinhos superiores)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        //configura a navegação para o navigation view (os itens de menu que são carregados)
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void enviarEmail(){

        //String imagem ="httpp....
        //Uri.parse(imagem)  ACTION_VIEW (imagem, endereço, etc).
       // Intent intent = new Intent (Intent.ACTION_DIAL, Uri.parse("tel:02113991636096")); //CALL precisa de permissão pra telefonar, DIAL não.

        //compartilhando algumas informações
        Intent intent = new Intent (Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"atendimento@atmconsultoria.com.br", "gabriel_luconi@hotmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App");
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem automática");

        //tipo de intent é um email
        intent.setType("message/rfc822");

        //intent.setType("text/plain"); Pesquisar sobre MINE TYPE
        //                image/* qualquer tipo de imagem

        //createChooser é ele que a partir de uma intent ele escolhe alguns apps que pode atender aquela intent.

        startActivity(Intent.createChooser(intent, "Compartilhar"));

       // startActivity(intent);

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}