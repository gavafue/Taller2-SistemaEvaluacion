package consola;

/**
 * Este clase permite crear un fichero de tipo archivo.
 *
 * Esta clase extiende la clase fichero.
 *
 */
public class Archivo extends Fichero {

    /**
     * Este String almacena el contenido del archivo.
     */
    private String contenido;

    /**
     * Constructor comun. Invoca al constructor de la clase superior asignando
     * por defecto permisos: -rwxr--r-- propietario: user tipo: Arhivo
     *
     * @param nombre a dar al archivo.
     */
    public Archivo(String nombre) {
        super(nombre, "-rwxr--r--", "user", "Archivo");
        contenido = "[vacio]";
    }

    /**
     * Constructor comun.
     *
     * @param nombre a dar al archivo.
     * @param contenido del archivo.
     */
    public Archivo(String nombre, String contenido) {
        super(nombre, "-rwxr--r--", "user", "Archivo");
        this.contenido = contenido;
    }

    /**
     * @return contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el valor del contenido recibido por
     *
     * @param contenido.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String obtenerResumenDelContenido() {
        return contenido;
    }

    @Override
    public String obtenerResumenDelContenido(boolean opcion) {
        String retorno = this.getContenido();
        if (opcion){
            retorno += "\n";
        }
        return retorno;
    }
}
