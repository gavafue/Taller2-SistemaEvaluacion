package consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase abstracta que define la estructura general de un fichero. Es la base
 * para directorio o archivo.
 *
 * @author Gabriel, Ana, Santiago, Juan y Gonzalo
 */
public abstract class Fichero {

    /**
     * Nombre del fichero
     */
    private String nombre;

    /**
     * Permisos del fichero. Se espera que sean de la forma:
     *
     * Tamano fijo de diez caracteres. Cada caracter debe ser uno de los
     * siguientes: -, w, r, x, d.
     *
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
     * Constructor común que permite crear un fichero.
     *
     * @param nombre para el fichero
     * @param permisos originales del fichero
     * @param duenio del fichero
     * @param tipo de fichero
     */
    public Fichero(String nombre, String permisos, String duenio, String tipo) {
        this.nombre = nombre;
        this.permisos = permisos;
        this.duenio = duenio;
        this.tipo = tipo;
    }

    /**
     * Método que permite obtener el nombre del fichero.
     *
     * @return nombre del fichero.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite obtener la fecha de creación del fichero.
     *
     * @return fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Método que permite obtener los permisos del fichero.
     *
     * @return permisos
     */
    public String getPermisos() {
        return permisos;
    }

    /**
     * Método que permite obtener el propietario del fichero.
     *
     * @return duenio
     */
    public String getDuenio() {
        return duenio;
    }

    /**
     * Método que permite obtener el tipo de fichero.
     *
     * @return tipo de fichero, archivo o directorio.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el nombre del fichero.
     *
     * @param nombre del fichero.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el valor de la fecha.
     *
     * @param fecha dle fichero.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Establece el valor de los permisos.
     *
     * @param permisos del fichero.
     */
    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    /**
     * Establece el valor del propietario.
     *
     * @param duenio del fichero.
     */
    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    /**
     * Establece el valor del tipo de fichero.
     *
     * @param tipo de fichero.
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

    /**
     * Método abstracto para obtener contenido. Su implementacion sera distinta
     * segín la especilizacion de las subclases.
     *
     * @return resumen del contenido del fichero.
     */
    public abstract String obtenerResumenDelContenido();

    /**
     * Método abstracto para obtener contenido. Su implementacion sera distinta
     * segun la especilizacion de las subclases.
     *
     * @param opcion que determina un comportamiento distinto al devolver el
     * resumen.
     * @return resumen del contenido del fichero.
     */
    public abstract String obtenerResumenDelContenido(boolean opcion);
}
