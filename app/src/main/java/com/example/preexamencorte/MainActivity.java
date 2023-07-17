package com.example.preexamencorte;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import Modelo.UsuarioDb;

public class MainActivity extends AppCompatActivity   {

    private EditText txtCorreo, txtContrasena;
    private Usuario usuario;
    private int posicion;
    private Button btnIngresar, btnRegistrarse;
    private UsuarioDb usuarioDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnIngresar();
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegistrarse();
            }
        });

    }

    private void inicializarComponentes() {
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasena = findViewById(R.id.txtContrasena);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        usuarioDb = new UsuarioDb(getApplicationContext());
    }

    private void btnIngresar() {
        String correo = txtCorreo.getText().toString();
        String contrasena = txtContrasena.getText().toString();

        Usuario usuario = usuarioDb.getUsuario(correo);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            Intent intent = new Intent(MainActivity.this, ListaActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("usuario", this.usuario);
            bundle.putInt("posicion", this.posicion);
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
        } else {
            Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

    private void btnRegistrarse() {
        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onResume() {
        super.onResume();
        txtCorreo.setText("");
        txtContrasena.setText("");
    }
}