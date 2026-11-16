package Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
// Esta clase se encarga de guardar, leer y borrar jugadores
// en la base de datos. El resto de la app le pide las cosas a él.

public class JugadorDAO {
    //abre y se crea la base de datos
    private final DatabaseHelper helper;
//crea y prepara el dao
    public JugadorDAO(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    //guarda un jugador nuevo en la BD Y Devuelve el id donde guardado o es -1 si fallo.
    public long insertarJugador(Jugador jugador) {
        //abre la base de datos para escribir
        SQLiteDatabase db = helper.getWritableDatabase();

        //arma los datos a guardar: columna -> valor
        ContentValues cv = new ContentValues();
        cv.put("nombre", jugador.getNombre());
        cv.put("posicion", jugador.getPosicion());
        cv.put("numero_camiseta", jugador.getNumeroCamiseta());

        //los guarda en la tabla "jugadores"
        long resultado = db.insert("jugadores", null, cv);
        db.close();
        return resultado;
    }

    //lee todos los jugadores guardados y los devuelve en una lista, ordenados por numero de camisetas.
    public List<Jugador> listarJugadores() {

        //lista vacia donde se van agregando los jugadores
        List<Jugador> lista = new ArrayList<>();

        //abre la base de datos solo para leer
        SQLiteDatabase db = helper.getReadableDatabase();

        //pide todos los jugadores ordenados por camisetas
        Cursor c = db.rawQuery("SELECT id, nombre, posicion, numero_camiseta FROM jugadores ORDER BY numero_camiseta ASC", null);

        //por cada fila encontrada, crea un jugador y lo agrega a la lista
        while (c.moveToNext()) {
            lista.add(new Jugador(
                    c.getInt(0), //id
                    c.getString(1), //nombre
                    c.getString(2), //posicion
                    c.getInt(3))); //numero de camiseta
        }
        c.close();
        db.close();
        return lista;
    }
    //borra un jugador segun su numero de camiseta y devuelve cuantos borro (0 si no encuentra ninguno)
    public int eliminarPorCamiseta(int numeroCamiseta) {
        SQLiteDatabase db = helper.getWritableDatabase();

        //borra al jugador cuyo numero de camiseta coincida
        int filas = db.delete("jugadores", "numero_camiseta=?", new String[]{String.valueOf(numeroCamiseta)});
        db.close();
        return filas;
    }
}