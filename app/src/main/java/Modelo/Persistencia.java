package Modelo;


import com.example.preexamencorte.Usuario;

public interface Persistencia {
    public void openDataBase();
    public void closeDataBase();
    public long insertUsuario(Usuario usuario);
}
