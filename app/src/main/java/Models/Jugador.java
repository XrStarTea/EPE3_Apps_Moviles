package Models;
// clase de "molde" de un jugador: ficha con sus datos
public class Jugador {

    // Los datos de la fichas privados (id, nombre, posicion, numero de camisetas)
    private int id;
    private String nombre;
    private String posicion;
    private int numeroCamiseta;
//constructor sin parametros
    public Jugador(String nombre, String posicion, int numeroCamiseta) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
    }

    public Jugador(int id, String nombre, String posicion, int numeroCamiseta) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public int getNumeroCamiseta() { return numeroCamiseta; }
    public void setNumeroCamiseta(int numeroCamiseta) { this.numeroCamiseta = numeroCamiseta; }
}