package consola;

/**
 * Esta clase permite crear un comando para la consola Linux.
 *
 */
public class Comando {

    /**
     * Descripcion del comando.
     *
     */
    private String descripcion;

    /**
     * Un ejemplo de como usar el comando.
     */
    private String ejemplo;

    /**
     * Opciones que admite el comando.
     */
    private String[] opciones;

    /**
     * Constructor.
     *
     * @param descripcion
     * @param ejemplo
     * @param opciones
     */
    public Comando(String descripcion, String ejemplo, String[] opciones) {
        this.descripcion = descripcion;
        this.ejemplo = ejemplo;
        this.opciones = opciones;
    }

    /**
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return ejemplo
     */
    public String getEjemplo() {
        return ejemplo;
    }

    /**
     * @return opciones
     */
    public String[] getOpciones() {
        return opciones;
    }

    /**
     * Establece el valor de la descripcion recibido por
     *
     * @param descripcion.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Establece el valor de los ejemplos recibido por
     *
     * @param ejemplo.
     */
    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    /**
     * Establece el valor de las opciones recibido por
     *
     * @param opciones.
     *
     * NOTA: ESTE CAMPO NO SE USA EN TODO EL PROGRAMA. SIEMPRE SE INICIALIZA Y
     * PERMANECE EN NULL.
     */
    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }
}
