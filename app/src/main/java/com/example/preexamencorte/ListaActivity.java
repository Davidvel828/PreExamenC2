package com.example.preexamencorte;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Modelo.UsuarioDb;

public class ListaActivity extends AppCompatActivity    {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnRegresar;
    private Aplicacion app;
    private Usuario usuario;
    private int posicion;
    private UsuarioDb usuarioDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        this.inicializarComponentes();
        this.btnRegresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { btnRegresar(); }
        });
        app.getAdaptador().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { recyclerView(view); }
        });
    }

    private void inicializarComponentes(){
        this.usuario = new Usuario();
        this.posicion = -1;
        this.app = (Aplicacion) getApplication();
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView = findViewById(R.id.listaUsuarios);
        this.recyclerView.setAdapter(app.getAdaptador());
        this.recyclerView.setLayoutManager(this.layoutManager);

        this.btnRegresar = findViewById(R.id.btnRegresar);
        this.usuarioDb = new UsuarioDb(getApplicationContext());

    }

    private void btnRegresar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que quieres regresar?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // Regresar al MainActivity
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void recyclerView(View view) {
        posicion = recyclerView.getChildAdapterPosition(view);
        usuario = app.getUsuarios().get(posicion);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            app.getUsuarios().clear();
            app.getUsuarios().addAll(usuarioDb.allUsuarios());
            app.getAdaptador().setUsuarioList(app.getUsuarios());
            recreate();
            app.getAdaptador().setUsuarioList(app.getUsuarios());
        }

        this.posicion = -1;
        this.usuario = null;
    }
}
