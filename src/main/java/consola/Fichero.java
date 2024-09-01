package consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase abstracta permite crear un fichero, que puede ser de tipo
 * directorio o archivo.
 *
 * @author
 * @since version 2
 */
public abstract class Fichero {

    //Atributos
    private String nombre;
    private String permisos;
    private String duenio;
    private String tipo; //Directorio o Archivo textual
    private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String fecha = LocalDateTime.now().format(formatoFecha);

    //Constructor
    public Fichero(String nombre, String permisos, String duenio, String tipo) {
        this.nombre = nombre;
        this.permisos = permisos;
        this.duenio = duenio;
        this.tipo = tipo;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPermisos() {
        return permisos;
    }

    public String getDuenio() {
        return duenio;
    }
    
    public String getTipo() {
        return tipo;
    }
    

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
     /**
     * Metodo que transforma objeto a String.
     *
     * @return fichero en formato String
     */
    @Override
    public String toString() {
        return permisos + " " + duenio + " " + fecha + " " + nombre; //Informacion mostrada al realizar un ls
    }
    
    // Métodos abstractos para obtener contenido
    public abstract String obtenerContenido();
    //public abstract List<Fichero> obtenerContenidoFicheros(); //Suponiendo que fuera escalable, unica forma si mantenemos herencia.
}
