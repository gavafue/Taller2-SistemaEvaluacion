package consola;

import java.util.HashMap;

/**
 * Esta clase permite crear un HashMap de comandos para la consola Linux.
 *
 * @author Gabriel, Anna, Santiago, Juan y Gonzalo
 */
public class Comandos {

    /**
     * Coleccion de elementos tipo Comando. En esta, la clave es el nombre del
     * comando. El nombre del comando es asignado al agregarlo a la coleccion
     *
     */
    private HashMap<String, Comando> hashComandos;

    /**
     * Constructor comun.
     */
    public Comandos() {
        hashComandos = new HashMap<String, Comando>();
        cargarComandos();
    }

    /**
     * Este metodo agrega un comando a hashComandos dado el nombre del comando y
     * sus caracteristicas.
     *
     * @param nombre del comando
     * @param comando
     */
    public void agregarComando(String nombre, Comando comando) {
        hashComandos.put(nombre, comando);
    }

    /**
     * Este metodo determina si un comando existe.
     *
     * @param nombre del comando
     * @return si el comando existe
     */
    public boolean existeComando(String nombre) {
        return hashComandos.containsKey(nombre);
    }

    /**
     * Este metodo devuelve la descripcion de un comando dado su nombre.
     *
     * @param nombre del comando
     * @return descripcion
     */
    public String obtenerDescripcion(String nombre) {
        String descripcion = "";
        if (this.existeComando(nombre)) {
            descripcion = hashComandos.get(nombre).getDescripcion();
        } else {
            descripcion = "No hay informacion disponible sobre el comando " +nombre;
        }
        return descripcion;
    }

    /**
     * Este metodo devuelve un ejemplo en formato String dado el nombre de un
     * comando.
     *
     * @param nombre del comando
     * @return ejemplo
     */
    public String obtenerEjemplo(String nombre) {
        String ejemplo = "";
        if (this.existeComando(nombre)) {
            ejemplo = hashComandos.get(nombre).getEjemplo();
        } else {
            ejemplo = "No hay ejemplos disponibles sobre el comando " + nombre+"\n\n"+" Intente man"+"\n";
        }
        return ejemplo;
    }

    /**
     * Este metodo dado el nombre de un comando retorna sus extensiones validas.
     *
     * @param nombre del comando
     * @return extensiones validas del comando
     */
    public String[] obtenerOpciones(String nombre) {
        return hashComandos.get(nombre).getOpciones();
    }

    /**
     * Este metodo carga los comandos al sistema a partir de un archivo de
     * texto.
     */
    public void cargarComandos() {
        
        String opcLs [] = {"-l","-a"};
        String opcSort [] = {"-n"};
        String opcHeadTail [] = {"-n"};
        String opcCut [] = {"-d","-f"};
        
        
        Comando com0 = new Comando(
                "\nEsta consola valida la sintaxis de algunos comandos\nde Bash.\n\n[ls|mkdir|rmdir|mv|cat|clear|ps|cp|kill|grep|tail|head|cut|sort|chmod| '|']",
                "\n     Ejemplo: man [COMANDO]\n\n", null);
        Comando com1 = new Comando("Lista el contenido de un directorio",
                "\n Ejemplo: ls /etc \n\nLista el contenido del directorio /etc\n\n", opcLs);
        Comando com2 = new Comando("Copia ficheros de [ORIGEN] a [DESTINO]",
                "\n     Ejemplo: cp arch1 /home/respaldo/\n\nCopia arch1 al directorio respaldo\n\n", null);
        Comando com3 = new Comando("Crea un directorio", "  Ejemplo: mkdir dir1 Crea dir1 en el directorio actual",
                null);
        Comando com4 = new Comando("Borra un directorio sin contenido",
                "\n     Ejemplo: rmdir dir1 Elimina dir1 siempre que no contenga nada.\n\n", null);
        Comando com5 = new Comando("Mueve o renombra un directorio.",
                "\n     Ejemplo: mv arch1 arh2\n\nRenombra arch1 como arch2\n\n", null);
        Comando com6 = new Comando("Muestra el contenido de un archivo",
                "\n     Ejemplo: cat respaldo.txt Muestra el contenido de respaldo.txt por pantalla.\n\n", null);
        Comando com7 = new Comando("Borra el texto en pantalla.",
                "\n     Ejemplo: clear [No admite parámetros]\n\n", null);
        Comando com8 = new Comando("Muestra los procesos activos.", "\n     Ejemplo: ps [No admite parámetros]\n\n",
                null);
        Comando com9 = new Comando("Elimina un proceso segun su id.",
                "\n     Ejemplo: kill pid [No admite parámetros]\n\n", null);
        Comando com10 = new Comando("Busca patrones en archivos de texto. ",
                "\n     Ejemplo: grep palabraAbuscar nombreArchivo", null);
        // tail RF30.
        Comando com11 = new Comando("Mostrar las últimas líneas de un archivo. ",
                "\n Ejemplo: tail [opción] [argumento]", opcHeadTail);
        // head RF31.
        Comando com12 = new Comando("Mostrar las primeras líneas de un archivo. ",
                "\n Ejemplo: head [opción] [argumento]", opcHeadTail); // admite como opciones “-n [número]” si no se
        // especifica, las primeras 10.
        // cut RF34
        Comando com13 = new Comando("Para extraer secciones de cada línea de un archivo. ",
                "\nEjemplo: cut [opción] [argumento]", opcCut); // admite como opciones “-d [delimitador]"
        // sort RF38
        // La consola permitirá el comando “sort” para mostrar en pantalla las líneas de un
        // archivo de manera ordenada. No hay modificacion en el archivo.
        Comando com14 = new Comando("Para ordenar las líneas de un archivo. ",
                "\n Ejemplo: sort [opciones] [archivo]",
                opcSort); // opciones disponibles “-n” para ordenar numéricamente.
        Comando com15 = new Comando("Para modificar los permisos asignados en un archivo o directorio ",
                "\n     Ejemplo: chmod rwxrwxrwx arch1\n     Ejemplo: chmod 555 arch1", null);

        // RF39. La consola permitirá el uso de pipe ( | ) entre los comandos tail, grep y head. La sintaxis para el uso del pipe sería la siguiente “comando1 | comando2”
        Comando com16 = new Comando("Para redirigir la salida del primer comando hacia la entrada del segundo.\nPrimer comando debe ser tail o head. El segundo, grep (sin especificiar archivo).",
                "\n     Ejemplo: comando1 | comando2 \n Ejemplo: head arch1 | grep INET", null);

        agregarComando("man", com0);
        agregarComando("ls", com1);
        agregarComando("cp", com2);
        agregarComando("mkdir", com3);
        agregarComando("rmdir", com4);
        agregarComando("mv", com5);
        agregarComando("cat", com6);
        agregarComando("clear", com7);
        agregarComando("ps", com8);
        agregarComando("kill", com9);
        agregarComando("grep", com10);
        agregarComando("tail", com11);
        agregarComando("head", com12);
        agregarComando("cut", com13);
        agregarComando("sort", com14);
        agregarComando("chmod", com15);
        agregarComando("|", com16);

    }
}
