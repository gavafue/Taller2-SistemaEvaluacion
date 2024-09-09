package consola;

/**
 * Esta clase permite crear un fichero de tipo directorio.
 *
 */
public class Directorio extends Fichero {

    /**
     * Contenido del directorio.
     */
    private Ficheros contenido; //Suponiendo que va a ser navegable

    /**
     * COnstructor
     *
     * @param nombre.
     */
    public Directorio(String nombre) {
        super(nombre, "drwxr--r--", "user", "Directorio");
        contenido = new Ficheros();
    }

    /**
     * @return contenido
     */
    public Ficheros getContenido() {
        return contenido;
    }

    /**
     * Establece el valor de contenido recibido por
     *
     * @param contenido.
     */
    public void setContenido(Ficheros contenido) {
        this.contenido = contenido;
    }

    /**
     * Este metodo sobreescribe el metodo de Fichero para responder indicando
     * que es un fichero sin mostrar el contenido. De momento esta implementado
     * para manejar un solo nivel en el sistema de archivos. Es decir, no hay
     * nada dentro de los directorios.
     */
    @Override
    public String obtenerContenido() {
        return "Esto es un directorio";
    }

}
