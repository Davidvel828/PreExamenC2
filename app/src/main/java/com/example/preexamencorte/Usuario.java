package com.example.preexamencorte;

import java.io.Serializable;

public class Usuario implements Serializable   {
    private int id;
    private String nombreUsuario;
    private String correo;
    private String contrasena;

    public Usuario(){
        this.nombreUsuario = "";
        this.correo = "";
        this.contrasena = "";
    }

    public Usuario(String nombreUsuario, String correo, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
