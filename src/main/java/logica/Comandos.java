package logica;

import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase permite crear un HashMap de comandos para la consola Linux. if
 * (this.getListaUsuarios().existeUsuarioLogin(nombreUsuario,contrasenia)){ //Si
 * el usuario se encuentra en el hash JOptionPane.showMessageDialog(Login.this,
 * "Login exitoso");
 *
 * @author
 * @since version 1 if
 * (this.getListaUsuarios().existeUsuarioLogin(nombreUsuario,contrasenia)){ //Si
 * el usuario se encuentra en el hash JOptionPane.showMessageDialog(Login.this,
 * "Login exitoso");
 *
 */
public class Comandos {

    // Atributos
    private Map<String, Comando> hashComandos; // El nombre del comando es la clave del HashMap

    // Constructor vacio
    public Comandos() {
        hashComandos = new HashMap<String, Comando>();
        cargarComandos();
    }

    /**
     * Este metodo dado el nombre del comando y sus caracteristicas lo agrega al
     * HashMap.
     *
     * @param nombre del comando
     * @param comando
     */
    public void agregarComando(String nombre, Comando comando) {
        hashComandos.put(nombre, comando);
    }

    /**
     * Este metodo determina si un comando existe o no.
     *
     * @param nombre del comando
     * @return si el comando existe
     */
    public boolean existeComando(String nombre) {
        return hashComandos.containsKey(nombre);
    }

    /**
     * Este metodo muestra en pantalla los comandos aceptados por la consola.
     */
    public void verComandos() {
        for (String comando : hashComandos.keySet()) {
            System.out.println(comando);
        }
    }

    /**
     * Este metodo devuelve la descripcion de un comando dado su nombre.
     *
     * @param nombre del comando
     * @return descipcion
     */
    public String obtenerDescripcion(String nombre) {
        return hashComandos.get(nombre).getDescripcion();
    }

    /**
     * Este metodo devuelve un ejemplo en formato String dado el nombre de un
     * comando.
     *
     * @param nombre del comando
     * @return ejemplo
     */
    public String obtenerEjemplo(String nombre) {
        return hashComandos.get(nombre).getEjemplo();
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
        // Inicializo arreglos con opciones
        // String [] opc1 = {"-l","-a","-h"};

        // Instancio los comandos
        Comando com0 = new Comando(
                "\nEsta consola valida la sintaxis de algunos comandos\nde Bash.\n\n[ls|mkdir|rmdir|mv|cat|clear|ps|cp|kill|grep|tail|head|cut|sort|chmod| '|']",
                "\n\nSi desea conocer algún comando en particular:\n\nman [COMANDO]\n\n", null);
        Comando com1 = new Comando("Lista el contenido de un directorio",
                "\nEjemplo: ls /etc \n\nLista el contenido del directorio /etc\n\n", null);
        Comando com2 = new Comando("Copia ficheros de [ORIGEN] a [DESTINO]",
                "\nEjemplo: cp arch1 /home/respaldo/\n\nCopia arch1 al directorio respaldo\n\n", null);
        Comando com3 = new Comando("Crea un directorio", "Ejemplo: mkdir dir1\n\nCrea dir1 en el directorio actual",
                null);
        Comando com4 = new Comando("Borra un directorio sin contenido",
                "\nEjemplo: rmdir dir1\n\nElimina dir1 siempre que no contenga nada.\n\n", null);
        Comando com5 = new Comando("Mueve o renombra un directorio.",
                "\nEjemplo: mv arch1 arh2\n\nRenombra arch1 como arch2\n\n", null);
        Comando com6 = new Comando("Muestra el contenido de un archivo",
                "\nEjemplo: cat respaldo.txt\n\nMuestra el contenido de respaldo.txt por pantalla.\n\n", null);
        Comando com7 = new Comando("Borra el texto en pantalla.", "\nEjemplo: clear\n\n[No admite parámetros]\n\n",
                null);
        Comando com8 = new Comando("Muestra los procesos activos.", "\nEjemplo: ps\n\n[No admite parámetros]\n\n",
                null);
        Comando com9 = new Comando("Elimina un proceso segun su id.",
                "\nEjemplo: kill pid\n\n[No admite parámetros]\n\n", null);
        // ###################################################################################################################################################
        // INSTANCIO NUEVOS COMANDOS. JUEVES 20/6

        // grep RF29.
        Comando com10 = new Comando("Busca patrones en archivos de texto. ",
                "\nEjemplo: grep palabraAbuscar nombreArchivo", null);
        // tail RF30.
        Comando com11 = new Comando("Mostrar las últimas líneas de un archivo. ",
                "\nEjemplo: tail [opción] [argumento]", null);
        // head RF31.
        Comando com12 = new Comando("Mostrar las primeras líneas de un archivo. ",
                "\nEjemplo: head [opción] [argumento]", null); // admite como opciones “-n [número]” si no se
        // especifica, las primeras 10.
        // cut RF34 Violeta: Gonza?
        Comando com13 = new Comando("Para extraer secciones de cada línea de un archivo. ",
                "\nEjemplo: cut [opción] [argumento]", null); // admite como opciones “-d [delimitador]"
        // sort RF38
        // creo que el RF38 se podria redactarse distinto (si estamos a tiempo): La
        // consola permitirá el comando “sort” para mostrar en pantalla las líneas de un
        // archivo de manera ordenada. No hay modificacion en el archivo.
        Comando com14 = new Comando("Para ordenar las líneas de un archivo. ", "\nEjemplo: sort [opciones] [archivo]",
                null); // opciones disponibles “-n” para ordenar numéricamente.
        Comando com15 = new Comando("Para modificar los permisos asignados en un archivo o directorio ",
                "\nEjemplos: \nchmod rwxrwxrwx arch1\n chmod 555 arch1", null);

        // RF39. La consola permitirá el uso de pipe ( | ) entre los comandos tail, grep y head. La sintaxis para el uso del pipe sería la siguiente “comando1 | comando2”
        Comando com16 = new Comando("Para redirigir la salida del primer comando hacia la entrada del segundo.\nPrimer comando debe ser tail o head. El segundo, grep (sin especificiar archivo).", "\nEjemplo: comando1 | comando2 \nEjemplo 2: head arch1 | grep INET", null);

        // ###################################################################################################################################################
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
        // ###################################################################################################################################################
        // AGREGO NUEVOS COMANDOS. JUEVES 20/6
        agregarComando("grep", com10);
        agregarComando("tail", com11);
        agregarComando("head", com12);
        agregarComando("cut", com13);
        agregarComando("sort", com14);
        agregarComando("chmod", com15);
        agregarComando("|", com16);

        /*
         * try {
         * Scanner s = new Scanner(new File("comandos.txt"));
         * while (s.hasNextLine()) {
         * String linea = s.nextLine();
         * String[] tokens = linea.split(";"); // la misma idea que usuarios
         * this.agregarComando(tokens[0], new Comando(tokens[1], tokens[2], null)); //
         * el unico que tiene extensiones es el ls x ahora ... y como esta pensado
         * } // para funcar sin extensiones y por la prog como está hecha no es
         * necesario.
         * s.close();
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */
    }
}
