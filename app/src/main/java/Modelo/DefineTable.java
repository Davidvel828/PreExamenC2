package Modelo;

public class DefineTable {
    public DefineTable() {}
    public static abstract class Usuarios {
        public static final String TABLE_NAME = "usuarios";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NOMBRE_USUARIO = "nombre_usuario";
        public static final String COLUMN_NAME_CORREO = "correo";
        public static final String COLUMN_NAME_CONTRASENA = "contrasena";

        public static String[] REGISTRO = new String[]{
                Usuarios.COLUMN_NAME_ID,
                Usuarios.COLUMN_NAME_NOMBRE_USUARIO,
                Usuarios.COLUMN_NAME_CORREO,
                Usuarios.COLUMN_NAME_CONTRASENA
        };
    }
}
