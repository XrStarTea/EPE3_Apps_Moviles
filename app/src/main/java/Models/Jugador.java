package Models;
// clase de "molde" de un jugador: ficha con sus datos


//constructor si parametros
//se crea una ficha en blanco; los datos se llenan despues con setters
public class Jugador {

    // Los datos de la fichas privados (id, nombre, posicion, numero de camisetas) esto es un getters
    // y se cambia con setters
    private int id;
    private String nombre;
    private String posicion;
    private int numeroCamiseta;
//constructor con parametros, sin id.
    // crea la ficha ya llena. se usa con jugadores nuevos,
    //porque la base de datos les asigna el id al guardarlos.
    public Jugador(String nombre, String posicion, int numeroCamiseta) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
    }

    // Constructor con parametros, con id.
    // crea la ficha completa. se usa al leer jugadores ya guadados en la base datos
    public Jugador(int id, String nombre, String posicion, int numeroCamiseta) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
    }
    //accesador (getter): permite mirar el dato.
    //Mutador (setter): permite cambiar el dato.
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public int getNumeroCamiseta() { return numeroCamiseta; }
    public void setNumeroCamiseta(int numeroCamiseta) { this.numeroCamiseta = numeroCamiseta; }
}