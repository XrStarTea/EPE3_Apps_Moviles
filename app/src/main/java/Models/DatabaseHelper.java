package Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "usuarios.db";
    // define el nombre de archivo fisico donde android almacenara todas las tablas de manera local
    private static final int DB_VERSION = 2; //version de la base de datos

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }//llama al constructor padre pasandole el contexto de la app, el nombre del archivo, factory nulo para usar el cursor estandar, y la version de la base de datos
//cursos es el objeto que almacena y permite recorrer fila por fila los resultados devueltos de una consulta select a la base de datos
    @Override
    // Se ejecuta una única vez al crear la base de datos por primera vez en el dispositivo
    // Crea la tabla de usuarios para validar el inicio de sesión (Login)
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL)");

        // Usuario por defecto para poder ingresar en el Login
        db.execSQL("INSERT INTO users (username, password) VALUES ('admin', '1234')");

        // Tabla de jugadores de la cancha
        db.execSQL("CREATE TABLE jugadores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "posicion TEXT NOT NULL, " +
                "numero_camiseta INTEGER UNIQUE NOT NULL)");
    }

    @Override
    // Se ejecuta automáticamente al incrementar DB_VERSION para actualizar la estructura de las tablas
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS jugadores");
        onCreate(db);
    }
}