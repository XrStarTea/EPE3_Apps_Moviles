package Models;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
// JugadorAdapter actua como puente, entre lista de objetos jugador y las filas dentro de ListView
public class JugadorAdapter extends ArrayAdapter<Jugador> {

    public JugadorAdapter(@NonNull Context context, @NonNull List<Jugador> jugadores) {
        super(context, android.R.layout.simple_list_item_2, jugadores);
        //LLama al constructor de la clase padre, diciendo que cada fila usara el diseño nativo de android, el cual trae dos textos apilados
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) { // verifica si ya existe una fila reciclada, si no existe, "infla" (construye en memoria) el diseño simple_list_item_2
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        Jugador jugador = getItem(position); // recupera el objeto jugador de esa fila, y enlaza los 2 textview internos de la  fila
        TextView text1 = convertView.findViewById(android.R.id.text1);
        TextView text2 = convertView.findViewById(android.R.id.text2);

        if (jugador != null) {
            text1.setText("#" + jugador.getNumeroCamiseta() + " - " + jugador.getNombre());
            text1.setTextColor(Color.parseColor("#1F1F1F"));
            text1.setTextSize(15f); //coloca el numero de camiseta del jugador, y el nombre en color gris oscuro, con tamaño de 15

            text2.setText("Posición: " + jugador.getPosicion() + "  |  ID: " + jugador.getId());
            text2.setTextColor(Color.parseColor("#666666")); //muestra la posicion en cancha, y el id en sql en tono gris mas suave
        }

        return convertView; //devuelve la fila ya armada, para que el listview la dibuje en pantalla
    }
}