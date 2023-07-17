package Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDbHelper extends SQLiteOpenHelper   {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = " ,";
    private static final String SQL_CREATE_USUARIO = "CREATE TABLE " +
            DefineTable.Usuarios.TABLE_NAME + " (" +
            DefineTable.Usuarios.COLUMN_NAME_ID + " INTEGER PRIMARY KEY, " +
            DefineTable.Usuarios.COLUMN_NAME_NOMBRE_USUARIO + TEXT_TYPE + COMMA_SEP +
            DefineTable.Usuarios.COLUMN_NAME_CORREO + TEXT_TYPE + COMMA_SEP +
            DefineTable.Usuarios.COLUMN_NAME_CONTRASENA + TEXT_TYPE + ")";

    private static final String SQL_DELETE_USUARIO = "DROP TABLE IF EXISTS " +
            DefineTable.Usuarios.TABLE_NAME;

    private static final String DATABASE_NAME = "db1";
    private static final int DATABASE_VERSION = 1;

    public UsuarioDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_USUARIO);
        onCreate(sqLiteDatabase);
    }
}
