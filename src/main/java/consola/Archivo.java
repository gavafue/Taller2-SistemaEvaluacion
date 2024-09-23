package consola;

/**
 * Este clase permite crear un fichero de tipo archivo. Extiende la clase
 * fichero.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class Archivo extends Fichero {

    /**
     * Contenido del archivo
     */
    private String contenido;

    /**
     * Constructor común que crea un archivo vacío a partir de su nombre.
     *
     * @param nombre a dar al archivo.
     */
    public Archivo(String nombre) {
        super(nombre, "-rwxr--r--", "user", "Archivo");
        contenido = "[vacio]";
    }

    /**
     * Constructor común que crea un archivo a partir de su nombre y contenido.
     *
     * @param nombre a dar al archivo.
     * @param contenido del archivo.
     */
    public Archivo(String nombre, String contenido) {
        super(nombre, "-rwxr--r--", "user", "Archivo");
        this.contenido = contenido;
    }

    /**
     * Permite obtener el contendio del archivo.
     *
     * @return contenido del archivo.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el valor del contenido en función del parámetro ingresado.
     *
     * @param contenido por establecer.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Método que permite obtener el resumen del contenido de un archivo de
     * texto.
     *
     * @return resumen del contenido en formato String.
     */
    @Override
    public String obtenerResumenDelContenido() {
        return contenido;
    }

    /**
     * Método que permite obtener resumen del contenido de un archivo de texto.
     *
     * @param opcion modificador asociado al comando.
     * @return resumen del contenido en formato String.
     */
    @Override
    public String obtenerResumenDelContenido(boolean opcion) {
        String retorno = this.getContenido();
        if (opcion) {
            retorno += "\n";
        }
        return retorno;
    }
}
