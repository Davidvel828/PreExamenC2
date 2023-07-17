package com.example.preexamencorte;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Modelo.UsuarioDb;

public class RegistroActivity extends AppCompatActivity   {

    private EditText txtNombreUsuario, txtCorreo, txtContrasena, txtReContrasena;
    private Button btnRegistrar, btnIngresar;
    private UsuarioDb usuarioDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inicializarComponentes();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegistrar();
            }
        });
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnIngresar();
            }
        });
    }

    private void inicializarComponentes() {
        txtNombreUsuario = findViewById(R.id.txtNombreUsuario);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasena = findViewById(R.id.txtContrasena);
        txtReContrasena = findViewById(R.id.txtReContrasena);
        btnRegistrar = findViewById(R.id.btnRegistrarse);
        btnIngresar = findViewById(R.id.btnIngresar);
        usuarioDb = new UsuarioDb(getApplicationContext());
    }

    private void btnRegistrar() {
        String nombreUsuario = txtNombreUsuario.getText().toString();
        String correo = txtCorreo.getText().toString();
        String contrasena = txtContrasena.getText().toString();
        String reContrasena = txtReContrasena.getText().toString();

        // Validar que los campos no estén vacíos
        if (nombreUsuario.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || reContrasena.isEmpty()) {
            Toast.makeText(RegistroActivity.this, "No deje campos vacios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que las contraseñas coincidan
        if (!contrasena.equals(reContrasena)) {
            Toast.makeText(RegistroActivity.this, "Las contraseñas no son correctas", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar el formato del correo electrónico
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(RegistroActivity.this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si el correo ya está registrado
        Usuario usuarioExistente = usuarioDb.getUsuario(correo);
        if (usuarioExistente != null) {
            Toast.makeText(RegistroActivity.this, "Este correo ya esta registrado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario(nombreUsuario, correo, contrasena);

        // Insertar el nuevo usuario en la base de datos
        long resultado = usuarioDb.insertUsuario(nuevoUsuario);
        if (resultado != -1) {
            Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(RegistroActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }

        Aplicacion app = (Aplicacion) getApplication();
        app.getUsuarios().add(nuevoUsuario);
    }

    private void btnIngresar(){
        finish();
    }
}