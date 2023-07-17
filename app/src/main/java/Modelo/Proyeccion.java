package Modelo;

import android.database.Cursor;

import com.example.preexamencorte.Usuario;

import java.util.ArrayList;

public interface Proyeccion {
    public Usuario getUsuario(String correo);
    public ArrayList<Usuario> allUsuarios();
    public Usuario readUsuario(Cursor cursor);
}
