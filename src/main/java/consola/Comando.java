package consola;

/**
 * Esta clase permite crear un comando para la consola Linux. El nombre del
 * comando no se incluye en esta clase al ser la clave del hashmap comandos.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 *
 */
public class Comando {

    /**
     * Descripción del comando.
     *
     */
    private String descripcion;

    /**
     * Un ejemplo en formato String de cómo usar el comando.
     */
    private String ejemplo;

    /**
     * Opciones que admite el comando.
     */
    private String[] opciones;

    /**
     * Constructor común.
     *
     * @param descripcion del comando.
     * @param ejemplo del uso del comando.
     * @param opciones que permite el comando.
     */
    public Comando(String descripcion, String ejemplo, String[] opciones) {
        this.descripcion = descripcion;
        this.ejemplo = ejemplo;
        this.opciones = opciones;
    }

    /**
     * Método que permite obtener la descripción del comando.
     *
     * @return descripcion del comando.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que permite obtener un ejemplo de uso del comando.
     *
     * @return ejemplo de uso del comando.
     */
    public String getEjemplo() {
        return ejemplo;
    }

    /**
     * Método que permite obtener las opciones que permite el comando.
     *
     * @return opciones que permite el comando.
     */
    public String[] getOpciones() {
        return opciones;
    }

    /**
     * Establece el valor de la descripción del comando.
     *
     * @param descripcion del comando.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Establece el valor de ejemplo de uso del comando.
     *
     * @param ejemplo de uso del comando.
     */
    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    /**
     * Establece el valor de las opciones disponibles del comando.
     *
     * @param opciones disponibles del comando.
     *
     */
    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }
}
