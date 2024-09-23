package consola;

/**
 * Esta clase permite crear un fichero de tipo directorio. Extiende la clase
 * fichero.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class Directorio extends Fichero {

    /**
     * Contenido del directorio.
     */
    private Ficheros contenido; //Suponiendo que va a ser navegable

    /**
     * Constructor que permite crear un directorio vacío dado su nombre.
     *
     * @param nombre del Directorio
     */
    public Directorio(String nombre) {
        super(nombre, "drwxr--r--", "user", "Directorio");
        contenido = new Ficheros();
    }

    /**
     * Método que permite obtener el contenido del directorio.
     *
     * @return contenido del directorio.
     */
    public Ficheros getContenido() {
        return contenido;
    }

    /**
     * Establece el valor de contenido.
     *
     * @param contenido a establecer.
     */
    public void setContenido(Ficheros contenido) {
        this.contenido = contenido;
    }

    /**
     * Agrega un nuevo fichero a la coleccion que representa el contenido del
     * fichero.
     *
     * @param contenido del fichero.
     */
    public void agregarContenido(Fichero contenido) {
        this.contenido.agregarFichero(contenido);
    }

    /**
     * Método que retorna el resumen de contenido del directorio.
     *
     * @return resumen del contenido del directorio.
     */
    @Override
    public String obtenerResumenDelContenido() {
        String s = "-";
        if (getContenido().toString().isEmpty()) {
            s = "\n[Directorio vacio]";
        } else {
            s += "\n\n" + this.getContenido().obtenerNombres(false);
        }
        return s;
    }

    /**
     * Método que permite obtener el reusmen del contenido de un directorio.
     *
     * @param ocultos habilitados o no.
     * @return resumen del contenido.
     */
    @Override
    public String obtenerResumenDelContenido(boolean ocultos) {
        String s = "";
        if (getContenido().toString().isEmpty()) {
            s = "\n[Directorio vacio]";
        } else {
            s = getContenido().obtenerInformacionDetallada(ocultos);
        }
        return s;
    }
}
