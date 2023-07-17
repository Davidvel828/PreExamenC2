package Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.preexamencorte.Usuario;

import java.util.ArrayList;

public class UsuarioDb implements Persistencia, Proyeccion{

    private Context context;
    private UsuarioDbHelper helper;
    private SQLiteDatabase db;

    public UsuarioDb(Context context, UsuarioDbHelper helper) {
        this.context = context;
        this.helper = helper;
    }

    public UsuarioDb(Context context) {
        this.context = context;
        this.helper = new UsuarioDbHelper(this.context);
    }


    @Override
    public void openDataBase() {
        db = helper.getWritableDatabase();
    }

    @Override
    public void closeDataBase() {
        helper.close();
    }

    @Override
    public long insertUsuario(Usuario usuario) {

        ContentValues values = new ContentValues();
        values.put(DefineTable.Usuarios.COLUMN_NAME_NOMBRE_USUARIO, usuario.getNombreUsuario());
        values.put(DefineTable.Usuarios.COLUMN_NAME_CORREO, usuario.getCorreo());
        values.put(DefineTable.Usuarios.COLUMN_NAME_CONTRASENA, usuario.getContrasena());

        this.openDataBase();
        long num = db.insert(DefineTable.Usuarios.TABLE_NAME, null, values);
        this.closeDataBase();
        Log.d("agregar", "insertUsuario: " + num);

        return num;
    }

    @Override
    public Usuario getUsuario(String correo) {
        db = helper.getWritableDatabase();

        Cursor cursor = db.query(
                DefineTable.Usuarios.TABLE_NAME,
                DefineTable.Usuarios.REGISTRO,
                DefineTable.Usuarios.COLUMN_NAME_CORREO + " = ?",
                new String[]{correo},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Usuario usuario = readUsuario(cursor);
            cursor.close();
            return usuario;
        }
        return null;
    }
    @Override
    public ArrayList<Usuario> allUsuarios() {
        this.openDataBase(); // Abre la base de datos

        Cursor cursor = db.query(
                DefineTable.Usuarios.TABLE_NAME,
                DefineTable.Usuarios.REGISTRO,
                null, null, null, null, null);
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Usuario usuario = readUsuario(cursor);
            usuarios.add(usuario);
            cursor.moveToNext();
        }

        cursor.close();

        this.closeDataBase();
        return usuarios;
    }


    @Override
    public Usuario readUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getInt(0));
        usuario.setNombreUsuario(cursor.getString(1));
        usuario.setCorreo(cursor.getString(2));
        usuario.setContrasena(cursor.getString(3));
        return usuario;
    }
}
