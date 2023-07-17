package com.example.preexamencorte;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import Modelo.UsuarioDb;

public class Aplicacion extends Application {

    public static ArrayList<Usuario> usuarios;
    private UsuarioAdapter usuarioAdapter;
    private UsuarioDb usuarioDb;

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public UsuarioAdapter getAdaptador() {
        return usuarioAdapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        usuarioDb = new UsuarioDb(getApplicationContext());
        usuarios = usuarioDb.allUsuarios();
        usuarioDb.openDataBase();

        Log.d("", "onCreate: Tamaño array list: " + this.usuarios.size());
        this.usuarioAdapter = new UsuarioAdapter(this.usuarios, this);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarioDb.openDataBase();
        long resultado = usuarioDb.insertUsuario(usuario);
        if (resultado != -1) {
            usuario.setId((int) resultado);
            usuarios.add(usuario);
            usuarioAdapter.notifyDataSetChanged();
        }
    }
}
