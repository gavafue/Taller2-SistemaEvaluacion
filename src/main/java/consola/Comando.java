package consola;

/**
 * Esta clase permite crear un comando para la consola Linux.
 *
 * @author
 * @since version 1
 */
public class Comando {

    //Atributos
    private String descripcion;
    private String ejemplo;
    private String[] opciones;

    //Constructor comun
    public Comando(String descripcion, String ejemplo, String[] opciones) {
        this.descripcion = descripcion;
        this.ejemplo = ejemplo;
        this.opciones = opciones;
    }

    //Getters
    public String getDescripcion() {
        return descripcion;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public String[] getOpciones() {
        return opciones;
    }

    //Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }
} 
    
    

