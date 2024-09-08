package consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase abstracta que define la estructura general de un fichero. Es la base
 * para especializar en los tipos: directorio o archivo.
 */
public abstract class Fichero {

    /**
     * Nombre del fichero
     */
    private String nombre;

    /**
     * Permisos del fichero. Se espera que sean de la forma:
     * <ul>
     * <li>Tamano fijo de diez caracteres.</li>
     * <li>Cada caracter debe ser uno de los siguientes: -, w, r, x, d.</li>
     * </ul>
     */
    private String permisos;

    /**
     * Almacena el nombre del usuario propietario del fichero.
     */
    private String duenio;

    /**
     * Indica si este fichero abstracto se instancio como Directorio o Archivo.
     */
    private String tipo;

    /**
     * Indica el formato de fecha.
     */
    private DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Indica la fecha en que se creo el fichero.
     */
    private String fecha = LocalDateTime.now().format(formatoFecha);

    /**
     * Constructor.
     *
     * @param nombre para el fichero
     * @param permisos originales del fichero
     * @param duenio
     * @param tipo de fichero
     */
    public Fichero(String nombre, String permisos, String duenio, String tipo) {
        this.nombre = nombre;
        this.permisos = permisos;
        this.duenio = duenio;
        this.tipo = tipo;
    }

    /**
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @return permisos
     */
    public String getPermisos() {
        return permisos;
    }

    /**
     * @return duenio
     */
    public String getDuenio() {
        return duenio;
    }

    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el valor de nombre recibido por
     *
     * @param nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el valor de la fecha recibido por
     *
     * @param fecha.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Establece el valor de los permisos recibido por
     *
     * @param permisos.
     */
    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    /**
     * Establece el valor del duenio recibido por
     *
     * @param duenio.
     */
    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    /**
     * Establece el valor del tipo de fichero recibido por
     *
     * @param tipo.
     */
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
