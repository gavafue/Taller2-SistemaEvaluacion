/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Gabriel
 */
public class Ejecutar {

    private String[] tokens; // PENDIENTE: deberia haber un getTOkens para no acceder el atributo de clase directamente
    private ArrayList<String> tokensParaElPipe; //pasar todos los comandos al arraylist?
    private String hora;
    private DateTimeFormatter formatoFecha;

    public Ejecutar(String[] tokens) {
        this.tokens = tokens;
        this.formatoFecha = DateTimeFormatter.ofPattern("HH:mm:ss");// formato para mostrar la variable hora en cada
        // ejecucion
        this.hora = LocalDateTime.now().format(formatoFecha);
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    private void actualizarHora() {
        hora = LocalDateTime.now().format(formatoFecha);
    }

    public String obtenerAyudaComando(Comandos comandos) {
        String mensaje = "";
        try {
            mensaje = "[AYUDA]\n" + comandos.obtenerDescripcion(tokens[1]) + "\n"
                    + comandos.obtenerEjemplo(tokens[1]);
        } catch (Exception e) {
            mensaje = "No hay informacion disponible sobre el comando " + tokens[1];
        }
        return mensaje;
    }

    public String ejecutarComando(Comandos comandos, Ficheros ficheros, Procesos procesos, JTextArea salida) {
        String mensaje = "";
        actualizarHora();

        tokensParaElPipe = new ArrayList<>(Arrays.asList(tokens)); // Creo un arraylist temporal para usar su metodo contains(). Esto es mas corto que un for.

        if (tokensParaElPipe.contains("|")) { //NO PUEDE HABER UN CASE PIPE PORQUE NO ARRANCA CON PIPE.
            mensaje = ejecutarPipe(tokensParaElPipe.indexOf("|"), ficheros);
        } else {
            switch (tokens[0]) {
                case "man":
                    mensaje = ejecutarMan(comandos);
                    break;
                case "mkdir":
                    mensaje = ejecutarMkdir(ficheros);
                    break;
                case "rmdir":
                    mensaje = ejecutarRmdir(ficheros);
                    break;
                case "clear":
                    mensaje = ejecutarClear(salida);
                    break;
                case "cat":
                    mensaje = ejecutarCat(ficheros);
                    break;
                case "mv":
                    mensaje = ejecutarMv(ficheros);
                    break;
                case "ls":
                    mensaje = ejecutarLs(ficheros);
                    break;
                case "ps":
                    mensaje = ejecutarPs(procesos);
                    break;
                case "kill":
                    mensaje = ejecutarKill(procesos);
                    break;
                case "grep":
                    mensaje = ejecutarGrep(ficheros);
                    break;
                case "tail":
                    mensaje = ejecutarTail(ficheros);
                    break;
                case "head":
                    mensaje = ejecutarHead(ficheros);
                    break;
                case "cut":
                    mensaje = ejecutarCut(ficheros);
                    break;
                case "sort":
                    mensaje = ejecutarSort(ficheros);
                    break;
                case "chmod":
                    mensaje = ejecutarChmod(ficheros);
                    break;

                default:
                    mensaje = "Sintaxis incorrecta\n";
                    break;
            }
        }
        return mensaje;

    }

    /**
     * Ejecuta el comando 'man'.
     *
     * @param comandos Objeto que contiene los comandos disponibles.
     * @param sintaxis Etiqueta donde mostrar mensajes de sintaxis.
     * @return Mensaje resultante de la ejecución.
     */
    public String ejecutarMan(Comandos comandos) {
        String mensaje = "";
        if (tokens != null && tokens.length == 1) {
            mensaje = comandos.obtenerDescripcion("man") + "\n" + comandos.obtenerEjemplo("man");
        } else if (tokens.length == 2) {
            mensaje = obtenerAyudaComando(comandos);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente man [nombre_del_comando] o man";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando 'mkdir' para crear directorios.
     *
     * @param ficheros Objeto que gestiona los ficheros y directorios.
     * @param procesos Objeto para gestionar los procesos.
     * @param sintaxis Etiqueta donde mostrar mensajes de sintaxis.
     * @return Mensaje resultante de la ejecución.
     */
    public String ejecutarMkdir(Ficheros ficheros) {
        String mensaje = "";
        if (tokens != null && tokens.length == 2) {
            mensaje = crearDirectorio(tokens[1], ficheros);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente mkdir [nombre] o man mkdir\n";
        }
        return mensaje;
    }

    /**
     * Crea un directorio si no existe previamente.
     *
     * @param nombreDirectorio Nombre del directorio a crear.
     * @param ficheros Objeto que gestiona los ficheros y directorios.
     * @param procesos Objeto para gestionar los procesos.
     * @param sintaxis Etiqueta donde mostrar mensajes de sintaxis.
     * @return Mensaje resultante de la operación.
     */
    private String crearDirectorio(String nombreDirectorio, Ficheros ficheros) {
        String mensaje = "";
        if (!ficheros.existeFichero(nombreDirectorio)) {
            Directorio nuevo = new Directorio(nombreDirectorio);
            ficheros.agregarFichero(nuevo);
            mensaje = "Se ha creado el directorio " + nombreDirectorio;
        } else {
            mensaje = "Ya existe un fichero con el nombre" + nombreDirectorio;
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando 'rmdir' para eliminar directorios.
     *
     * @param ficheros Objeto que gestiona los ficheros y directorios.
     * @param procesos Objeto para gestionar los procesos.
     * @param sintaxis Etiqueta donde mostrar mensajes de sintaxis.
     * @return Mensaje resultante de la ejecución.
     */
    public String ejecutarRmdir(Ficheros ficheros) {
        String mensaje = "";
        if (tokens != null && tokens.length == 2) {
            mensaje = eliminarDirectorio(tokens[1], ficheros);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente rmdir [nombre] o man rmdir\n";
        }
        return mensaje;
    }

    /**
     * Elimina un directorio si existe.
     *
     * @param nombreDirectorio Nombre del directorio a eliminar.
     * @param ficheros Objeto que gestiona los ficheros y directorios.
     * @param procesos Objeto para gestionar los procesos.
     * @param sintaxis Etiqueta donde mostrar mensajes de sintaxis.
     * @return Mensaje resultante de la operación.
     */
    private String eliminarDirectorio(String nombreDirectorio, Ficheros ficheros) {
        String mensaje = "";
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            ficheros.eliminarFichero(nombreDirectorio);
            mensaje = "Directorio eliminado\n";
        } else {
            mensaje = "No existe un directorio con ese nombre\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando 'clear' para limpiar el área de salida.
     *
     * @param sintaxis Etiqueta donde mostrar mensajes de sintaxis.
     * @param salida Área de texto que se limpia.
     * @return Mensaje resultante de la ejecución.
     */
    public String ejecutarClear(JTextArea salida) {
        String mensaje = "";
        if (tokens != null && tokens.length == 1) {
            salida.setText("");
            mensaje = "";
        } else {
            mensaje = "Sintaxis incorrecta\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando 'cat' para mostrar el contenido de un archivo si
     * existe y no es un directorio.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarCat(Ficheros ficheros) {
        String mensaje = "";

        if (tokens.length != 2) {
            mensaje = "Sintaxis incorrecta.\nIntente cat [nombre] o man cat\n";
        } else {
            String nombreArchivo = tokens[1];
            if (ficheros.existeFichero(nombreArchivo) && !ficheros.esDirectorio(nombreArchivo)) {
                Fichero archivo = ficheros.obtenerFichero(nombreArchivo);
                mensaje = archivo.obtenerContenido() + "\n";
            } else {
                mensaje = "No existe un archivo con ese nombre\n";
            }
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando 'mv' para renombrar un archivo si existe y no es un
     * directorio.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarMv(Ficheros ficheros) {
        String mensaje = "";

        // Verifica la cantidad de tokens para determinar la sintaxis correcta.
        if (tokens.length != 3) {
            mensaje = "[" + getHora() + "]\n"
                    + "Sintaxis incorrecta.\nIntente mv [nombreActual] [nombreNuevo] o man mv\n";
        } else {
            String nombreActual = tokens[1];
            String nombreNuevo = tokens[2];

            // Verifica si el archivo existe y no es un directorio.
            if (ficheros.existeFichero(nombreActual) && !ficheros.esDirectorio(nombreActual)) {

                // Encuentra el índice del archivo en la lista.
                int i = 0;
                while (!ficheros.obtenerFichero(i).getNombre().equals(nombreActual)) {
                    i++;
                }

                // Cambia el nombre del archivo.
                ficheros.obtenerFichero(i).setNombre(nombreNuevo);

                // Construye el mensaje de éxito.
                mensaje = "El fichero " + nombreActual + " ha sido renombrado a "
                        + nombreNuevo
                        + "\n";
            } else {
                // Si no existe el archivo, muestra un mensaje de error.
                mensaje = "No existe un archivo con ese nombre\n";
            }
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'ls' para listar archivos y directorios según los
     * parámetros proporcionados.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param procesos el objeto que gestiona los procesos activos.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarLs(Ficheros ficheros) {
        String mensaje = "";

        switch (tokens.length) {
            case 1:
                // Comando ls (sin opciones)
                mensaje = obtenerListaSimple(ficheros);
                break;
            case 2:
                mensaje = manejarOpcionesUnParametro(ficheros);
                break;
            case 3:
                mensaje = manejarOpcionesDosParametros(ficheros);
                break;
            case 4:
                mensaje = manejarOpcionesTresParametros(ficheros);
                break;
            default:
                mensaje = "Sintaxis incorrecta.\nIntente man ls\n";
                break;
        }

        return mensaje;
    }

    /**
     * Obtiene la lista simple de nombres de archivos y directorios visibles.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return un mensaje con la lista de nombres de archivos y directorios
     * visibles.
     */
    private String obtenerListaSimple(Ficheros ficheros) {
        String mensaje;
        mensaje = ficheros.obtenerNombres(false) + "\n"; // Muestra nombres sin ocultos
        return mensaje;
    }

    /**
     * Maneja las opciones de ls con un parámetro.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return un mensaje detallando el resultado de la ejecución del comando
     * con un parámetro.
     */
    private String manejarOpcionesUnParametro(Ficheros ficheros) {
        String mensaje;
        switch (tokens[1]) {
            case "-l":
                mensaje = ficheros.obtenerInformacionDetallada(false) + "\n"; // Información
                // detallada
                // sin
                // ocultos
                break;
            case "-a":
                mensaje = ficheros.obtenerNombres(true) + "\n"; // Muestra nombres
                // incluyendo ocultos
                break;
            default:
                mensaje = obtenerContenidoDirectorio(ficheros, tokens[1]);
                break;
        }
        return mensaje;
    }

    /**
     * Maneja las opciones de ls con dos parámetros.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return un mensaje detallando el resultado de la ejecución del comando
     * con dos parámetros.
     */
    private String manejarOpcionesDosParametros(Ficheros ficheros) {
        String mensaje;
        if ((tokens[1].equals("-l") && tokens[2].equals("-a")) || (tokens[1].equals("-a") && tokens[2].equals("-l"))) {
            mensaje = ficheros.obtenerInformacionDetallada(true) + "\n"; // Información
            // detallada
            // incluyendo
            // ocultos
        } else if (tokens[1].equals("-l")) {
            mensaje = obtenerComandoLsL(ficheros, tokens[2]);
        } else if (tokens[1].equals("-a")) {
            mensaje = obtenerComandoLsA(ficheros, tokens[2]);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente man ls\n";
        }
        return mensaje;
    }

    /**
     * Maneja las opciones de ls con tres parámetros.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return un mensaje detallando el resultado de la ejecución del comando
     * con tres parámetros.
     */
    private String manejarOpcionesTresParametros(Ficheros ficheros) {
        String mensaje;
        if ((tokens[1].equals("-l") && tokens[2].equals("-a")) || (tokens[1].equals("-a") && tokens[2].equals("-l"))) {
            mensaje = obtenerComandoLsLsA(ficheros, tokens[3]);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente man ls\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el contenido de un directorio para el comando ls [directorio].
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param nombreDirectorio el nombre del directorio a listar.
     * @return un mensaje con el contenido del directorio especificado.
     */
    private String obtenerContenidoDirectorio(Ficheros ficheros, String nombreDirectorio) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            int i = 0;
            while (!ficheros.obtenerFichero(i).getNombre().equals(nombreDirectorio)) {
                i++;
            }
            mensaje = ficheros.obtenerFichero(i).obtenerContenido() + "\n";
        } else {
            mensaje = "No existe un directorio con ese nombre\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -l [directorio].
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param nombreDirectorio el nombre del directorio a listar detalladamente.
     * @return un mensaje indicando el resultado de ejecutar ls -l [directorio].
     */
    private String obtenerComandoLsL(Ficheros ficheros, String nombreDirectorio) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "[" + getHora() + "]\nComando ls -l al directorio " + nombreDirectorio + "\n";
        } else {
            mensaje = "No existe un directorio con ese nombre\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -a [directorio].
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param nombreDirectorio el nombre del directorio a listar con todos los
     * archivos.
     * @return un mensaje indicando el resultado de ejecutar ls -a [directorio].
     */
    private String obtenerComandoLsA(Ficheros ficheros, String nombreDirectorio) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "[" + getHora() + "]\nComando ls -a al directorio " + nombreDirectorio + "\n";
        } else {
            mensaje = "No existe un directorio con ese nombre\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -l -a [directorio].
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param nombreDirectorio el nombre del directorio a listar detalladamente
     * con todos los archivos.
     * @return un mensaje indicando el resultado de ejecutar ls -l -a
     * [directorio].
     */
    private String obtenerComandoLsLsA(Ficheros ficheros, String nombreDirectorio) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "[" + getHora() + "]\nComando (ls -a -l) o (ls -l -a) al directorio " + nombreDirectorio
                    + "\n";
        } else {
            mensaje = "No existe un directorio con ese nombre\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando 'kill' para eliminar un proceso por su ID.
     *
     * @param procesos el objeto que maneja la lista de procesos activos.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarKill(Procesos procesos) {
        String mensaje = "";

        if (tokens.length == 2) {
            // Intenta obtener el ID del proceso del segundo token
            String procesoIdStr = tokens[1];

            int procesoID = Integer.parseInt(procesoIdStr);
            if (procesos.existeProceso(procesoID)) {
                // Si el proceso existe, se elimina de la lista de procesos
                procesos.getListaProcesos().remove(procesos.obtenerProceso(procesoID));
                mensaje = "Proceso eliminado\n";
            } else {
                // Si no existe el proceso con el ID proporcionado
                mensaje = "No existe el proceso\n";
            }
        } else {
            // Si la sintaxis del comando es incorrecta
            mensaje = "Sintaxis incorrecta.\nPruebe man kill\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'ps' para listar los procesos en ejecución del
     * sistema.
     *
     * @param procesos el objeto que maneja la lista de procesos activos.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje detallando los procesos en ejecución o un mensaje de
     * error si la sintaxis es incorrecta.
     */
    public String ejecutarPs(Procesos procesos) {
        String mensaje = "";

        if (tokens.length == 1) {
            if (procesos.getListaProcesos().isEmpty()) {
                mensaje = "No existen procesos en ejecución\n";
            } else {
                mensaje = "PID | USER | % MEMORY | % CPU | TIME | COMMAND\n";
                for (Proceso proceso : procesos.getListaProcesos()) {
                    mensaje += proceso.toString() + "\n";
                }
            }
        } else {
            mensaje = "Sintaxis incorrecta.\nPruebe man ps\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'grep' para buscar un patrón en un archivo específico.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje indicando si se encontró el patrón en el archivo o un
     * mensaje de error si la expresión es incorrecta.
     */
    private String ejecutarGrep(Ficheros ficheros) {
        String mensaje = "";

        if (tokens.length != 3) {
            mensaje = "Sintaxis incorrecta: debe ingresar la expresión a buscar y el nombre del archivo.";
        } else {
            String expresion = tokens[1];
            String nombreArchivo = tokens[2];

            if (ficheros.existeFichero(nombreArchivo)) {
                String contenidoArchivo = ficheros.obtenerFichero(nombreArchivo).obtenerContenido();
                if (contenidoArchivo.contains(expresion)) {
                    mensaje = "Se encontró la expresión '" + expresion + "' en el archivo '" + nombreArchivo + "'.";
                    // Por hacer: mostrar el texto resaltado o contar cantidad de ocurrencias.
                } else {
                    mensaje = "La expresión '" + expresion + "' no se encontró en el archivo '" + nombreArchivo + "'.";
                }
            } else {
                mensaje = "El archivo '" + nombreArchivo + "' indicado no existe.";
            }
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'tail' para mostrar las últimas líneas de un archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje con las últimas líneas del archivo especificado o un
     * mensaje de error si la sintaxis es incorrecta.
     */
    private String ejecutarTail(Ficheros ficheros) {
        String mensaje = "";

        if (tokens.length == 2) {
            // Caso sin opción -n: mostrar las últimas 10 líneas
            mensaje = tailDefault(ficheros, tokens[1]);
        } else if (tokens.length == 4 && tokens[1].equals("-n")) {
            // Caso con opción -n: mostrar las últimas n líneas
            try {
                int n = Integer.parseInt(tokens[2]);
                mensaje = tailN(ficheros, tokens[3], n);
            } catch (NumberFormatException e) {
                mensaje = "El número de líneas debe ser un valor numérico.\n";
            }
        } else {
            mensaje = "Sintaxis incorrecta.\nPruebe man tail\n";
        }

        return mensaje;
    }

    /**
     * Obtiene las últimas 10 líneas de un archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las
     * líneas.
     * @return un mensaje con las últimas 10 líneas del archivo.
     */
    private String tailDefault(Ficheros ficheros, String nombreArchivo) {
        String mensaje = "";
        String[] lineasArchivo = ficheros.obtenerFichero(nombreArchivo).obtenerContenido().split("\\R");
        int inicio = Math.max(0, lineasArchivo.length - 10);

        for (int i = inicio; i < lineasArchivo.length; i++) {
            mensaje += lineasArchivo[i] + "\n";
        }

        return mensaje;
    }

    /**
     * Obtiene las últimas n líneas de un archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las
     * líneas.
     * @param n el número de líneas que se desean obtener.
     * @return un mensaje con las últimas n líneas del archivo.
     */
    private String tailN(Ficheros ficheros, String nombreArchivo, int n) {
        String mensaje = "";
        String[] lineasArchivo = ficheros.obtenerFichero(nombreArchivo).obtenerContenido().split("\\R");
        int inicio = Math.max(0, lineasArchivo.length - n);

        for (int i = inicio; i < lineasArchivo.length; i++) {
            mensaje += lineasArchivo[i] + "\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'head' para mostrar las primeras líneas de un archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje con las primeras líneas del archivo especificado o un
     * mensaje de error si la sintaxis es incorrecta.
     */
    public String ejecutarHead(Ficheros ficheros) {
        String mensaje = "";
        int numLineas = 10; // Por defecto, mostrar 10 líneas

        if (tokens.length == 2) {
            // Caso sin opción -n: mostrar las primeras 10 líneas
            mensaje = headDefault(ficheros, tokens[1], numLineas);
        } else if (tokens.length == 4 && tokens[1].equals("-n")) {
            // Caso con opción -n: mostrar las primeras n líneas
            try {
                numLineas = Integer.parseInt(tokens[2]);
                mensaje = headN(ficheros, tokens[3], numLineas);
            } catch (NumberFormatException e) {
                mensaje = "El número de líneas debe ser un valor numérico.\n";
            }
        } else {
            mensaje = "Sintaxis incorrecta.\nPruebe man head\n";
        }

        return mensaje;
    }

    /**
     * Obtiene las primeras 10 líneas de un archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las
     * líneas.
     * @param numLineas el número de líneas que se desean obtener.
     * @return un mensaje con las primeras 10 líneas del archivo.
     */
    private String headDefault(Ficheros ficheros, String nombreArchivo, int numLineas) {
        String mensaje = "";
        String[] lineasArchivo = ficheros.obtenerFichero(nombreArchivo).obtenerContenido().split("\\R");

        for (int i = 0; i < Math.min(numLineas, lineasArchivo.length); i++) {
            mensaje += lineasArchivo[i] + "\n";
        }

        return mensaje;
    }

    /**
     * Obtiene las primeras n líneas de un archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las
     * líneas.
     * @param numLineas el número de líneas que se desean obtener.
     * @return un mensaje con las primeras n líneas del archivo.
     */
    private String headN(Ficheros ficheros, String nombreArchivo, int numLineas) {
        String mensaje = "";
        String[] lineasArchivo = ficheros.obtenerFichero(nombreArchivo).obtenerContenido().split("\\R");

        for (int i = 0; i < Math.min(numLineas, lineasArchivo.length); i++) {
            mensaje += lineasArchivo[i] + "\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'cut' para mostrar las columnas seleccionadas de un
     * archivo usando un delimitador específico.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje con las columnas seleccionadas del archivo
     * especificado o un mensaje de error si la sintaxis es incorrecta.
     */
    public String ejecutarCut(Ficheros ficheros) {
        String mensaje = "";

        // Verificar si el comando tiene la cantidad correcta de tokens y las opciones
        // correctas
        if (tokens.length == 6 && tokens[0].equals("cut") && tokens[1].equals("-d") && tokens[3].equals("-f")) {
            String delimitador = tokens[2].replace("'", ""); // Delimitador especificado sin comillas simples
            String campos = tokens[4]; // Campos a extraer
            String archivo = tokens[5]; // Nombre del archivo

            // Verificar si el archivo existe y no es un directorio
            if (ficheros.esDirectorio(archivo)) {
                mensaje += "Error: El archivo especificado no existe o es un directorio.";
                // Cambiar color a rojo
                return mensaje;
            }

            // Verificar que el archivo tenga contenido
            String contenidoArchivo = ficheros.obtenerFichero(archivo).obtenerContenido();
            if (contenidoArchivo == null || contenidoArchivo.isEmpty()) {
                mensaje += "Error: El archivo especificado está vacío.";
                // Cambiar color a rojo
                return mensaje;
            }

            // Validar que el delimitador sea el correcto (solo se admite ':')
            if (!delimitador.equals(":")) {
                mensaje += "Error: El delimitador especificado no es válido. Se admite solo ':' (dos puntos).";
                // Cambiar color a rojo
                return mensaje;
            }

            String[] camposArray = campos.split(",");
            List<Integer> indicesCampos = new ArrayList<>();

            // Convertir camposArray a lista de índices (restamos 1 para hacerlos 0-based)
            for (String campo : camposArray) {
                try {
                    indicesCampos.add(Integer.parseInt(campo.trim()) - 1);
                } catch (NumberFormatException e) {
                    mensaje += "Error: Los campos especificados no son números válidos.";
                    // Cambiar color a rojo
                    return mensaje;
                }
            }

            // Dividir el contenido del archivo por líneas
            String[] lineas = contenidoArchivo.split("\n");

            // Procesar cada línea
            for (String linea : lineas) {
                String[] partes = linea.split(delimitador); // Usar el delimitador especificado
                StringBuilder lineaResultado = new StringBuilder();

                // Obtener las columnas después de los campos especificados
                for (int indice : indicesCampos) {
                    if (indice >= 0 && indice < partes.length) {
                        if (lineaResultado.length() > 0) {
                            lineaResultado.append(delimitador); // Agregar delimitador entre columnas
                        }
                        lineaResultado.append(partes[indice]);
                    } else {
                        mensaje += "Error: El índice de columna especificado está fuera del rango de columnas en la línea.";
                        // Cambiar color a rojo
                        return mensaje;
                    }
                }

                mensaje += lineaResultado.toString() + "\n";
            }

            // Cambiar color a celeste
        } else {
            mensaje += "Error: Sintaxis incorrecta. La sintaxis correcta es: cut -d ':' -f 1,4 archivo.txt";
            // Cambiar color a rojo
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'sort' para mostrar las líneas de un archivo ordenadas
     * alfabéticamente. Si se especifica la opción '-n', ordena numéricamente.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje con las líneas del archivo ordenadas o un mensaje de
     * error si la sintaxis es incorrecta.
     */
    public String ejecutarSort(Ficheros ficheros) {
        String mensaje = "";
        String[] mensajeTokenizado;

        // Verificar la cantidad de tokens para determinar el tipo de ordenamiento
        if (tokens.length == 2) {
            // Ordenar alfabéticamente las líneas del archivo especificado
            mensajeTokenizado = ficheros.obtenerFichero(tokens[1]).obtenerContenido().split("\\R");
            Arrays.sort(mensajeTokenizado);
            for (String linea : mensajeTokenizado) {
                mensaje += linea + "\n";
            }
        } else if (tokens.length == 3 && tokens[1].equals("-n")) {
            // Ordenar numéricamente las líneas del archivo especificado
            mensajeTokenizado = ficheros.obtenerFichero(tokens[2]).obtenerContenido().split("\\R");
            Arrays.sort(mensajeTokenizado, Comparator.comparingInt(Integer::parseInt));
            for (String linea : mensajeTokenizado) {
                mensaje += linea + "\n";
            }
        } else {
            // Sintaxis incorrecta
            mensaje = "Sintaxis incorrecta\n";
        }

        return mensaje;
    }

    /**
     * Método que permite modificar los permisos de un archivo y/o directorio.
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @param sintaxis Etiqueta donde se indica la sintaxis.
     * @return Mensaje a imprimir en consola.
     */
    public String ejecutarChmod(Ficheros ficheros) {
        String permisos = tokens[1];
        String fich = tokens[2];
        String mensaje = "";

        // Verificar la cantidad de argumentos
        if (tokens.length != 3) {
            mensaje = "Sintaxis incorrecta: número incorrecto de argumentos";
        } else if (!verificarExistenciaFichero(ficheros, fich)) {
            // Verificar si el fichero existe
            mensaje = "Sintaxis incorrecta: el fichero o directorio '" + fich + "' no existe";
        } else if (!validarLongitudPermisos(permisos)) {
            // Validar longitud de permisos
            mensaje = "Sintaxis incorrecta: longitud incorrecta de permisos";
        } else {
            switch (permisos.length()) {
                case 3:
                    if (!aplicarPermisosNumericos(ficheros, fich, permisos)) {
                        mensaje = "Sintaxis incorrecta: valor de permisos inválido";
                    } else {
                        mensaje = "Comando ejecutado correctamente";
                    }
                    break;
                case 9:
                    if (!aplicarPermisosSimbolicos(ficheros, fich, permisos)) {
                        mensaje = "Sintaxis incorrecta: permisos inválidos";
                    } else {
                        mensaje = "Comando ejecutado correctamente";
                    }
                    break;
                default:
                    mensaje = "Sintaxis incorrecta: longitud incorrecta de permisos";
            }
        }

        return mensaje;
    }

    /**
     * Este metodo es para ejecutar el comando pipe.
     *
     * @param indexPipe - indice del simbolo pipe en la linea ingresada.
     * @param ficheros
     * @return mensaje
     */
    public String ejecutarPipe(int indexPipe, Ficheros ficheros) {
        String mensaje = "";
        String msjComando1 = "";
        String msjComando2 = "";
        String[] tokensA = Arrays.copyOfRange(tokens, 0, indexPipe); //antes del pipe. indexPipe queda afuera
        String[] tokensB = Arrays.copyOfRange(tokens, indexPipe + 1, tokens.length + 1); // despues del pipe. y uno mas, que queda con null

        if (tokens[0].equals("tail")) {// primero bloque de IFs para procesar el primer comando
            tokens = tokensA;
            msjComando1 = ejecutarTail(ficheros);
        } else if (tokens[0].equals("head")) {
            tokens = tokensA;
            msjComando1 = ejecutarHead(ficheros);
        } else {
            mensaje = "Sintaxis incorrecta en primer comando. Debe ser head o tail de un archivo.";
        }

        if (tokensB[0].equals("grep")) { // segundo bloque de IFs para procesar el segundo comando

            ficheros.agregarFichero(new Archivo("temporal", msjComando1)); //creo un archivo con el contenido del mensaje1. Ya que grep esta programado para levantar contenido de ficheros.
            tokensB[2] = "temporal"; //modifico tokens para poner como 3 parametro el nombre del archivo temporal.
            tokens = tokensB;
            msjComando2 = ejecutarGrep(ficheros);//Va a tomar el tokens global, el cual no esta como lo necesita. PROUESTA: que todos los ejecutarComando() reciban el tokens como parametro.
            //borro ese archivo que no debe existir mas.
            ficheros.eliminarFichero("temporal");
            mensaje = msjComando2;
        } else {
            mensaje = "Sintaxis incorrecta en segundo comando. Debe ser grep.";
        }

        return mensaje;
    }

    /**
     * Verifica si el fichero o directorio existe.
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @param fich Nombre del fichero o directorio a verificar.
     * @param sintaxis Etiqueta donde se indica la sintaxis.
     * @return true si el fichero o directorio existe, false de lo contrario.
     */
    private boolean verificarExistenciaFichero(Ficheros ficheros, String fich) {
        if (!ficheros.existeFichero(fich)) {
            return false;
        }
        return true;
    }

    /**
     * Valida la longitud de los permisos.
     *
     * @param permisos String con los permisos a validar.
     * @param sintaxis Etiqueta donde se indica la sintaxis.
     * @return true si la longitud de los permisos es correcta, false de lo
     * contrario.
     */
    private boolean validarLongitudPermisos(String permisos) {
        if (permisos.length() != 3 && permisos.length() != 9) {
            return false;
        }
        return true;
    }

    /**
     * Aplica los permisos numéricos al fichero o directorio.
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @param fich Nombre del fichero o directorio al que se aplicarán los
     * permisos.
     * @param permisos String con los permisos numéricos.
     * @param sintaxis Etiqueta donde se indica la sintaxis.
     * @return true si se aplicaron los permisos correctamente, false de lo
     * contrario.
     */
    private boolean aplicarPermisosNumericos(Ficheros ficheros, String fich, String permisos) {
        String permisosEnLetras = ficheros.obtenerFichero(fich).getPermisos().charAt(0) + "";
        boolean permisosValidos = true;

        for (int i = 0; i < 3; i++) {
            int permisoNum = Character.getNumericValue(permisos.charAt(i));
            if (permisoNum < 0 || permisoNum > 7) {
                permisosValidos = false;
            }
            switch (permisoNum) {
                case 0:
                    permisosEnLetras += "---";
                    break;
                case 1:
                    permisosEnLetras += "--x";
                    break;
                case 2:
                    permisosEnLetras += "-w-";
                    break;
                case 3:
                    permisosEnLetras += "-wx";
                    break;
                case 4:
                    permisosEnLetras += "r--";
                    break;
                case 5:
                    permisosEnLetras += "r-x";
                    break;
                case 6:
                    permisosEnLetras += "rw-";
                    break;
                case 7:
                    permisosEnLetras += "rwx";
                    break;
            }
        }

        if (permisosValidos) {
            ficheros.obtenerFichero(fich).setPermisos(permisosEnLetras);
        }

        return permisosValidos;
    }

    /**
     * Aplica los permisos simbólicos al fichero o directorio.
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @param fich Nombre del fichero o directorio al que se aplicarán los
     * permisos.
     * @param permisos String con los permisos simbólicos.
     * @param sintaxis Etiqueta donde se indica la sintaxis.
     * @return true si se aplicaron los permisos correctamente, false de lo
     * contrario.
     */
    private boolean aplicarPermisosSimbolicos(Ficheros ficheros, String fich, String permisos) {
        boolean permisosValidos = true;

        for (int i = 0; i < permisos.length(); i++) {
            char permiso = permisos.charAt(i);
            switch (i % 3) {
                case 0: // r
                    if (permiso != 'r' && permiso != '-') {
                        permisosValidos = false;
                    }
                    break;
                case 1: // w
                    if (permiso != 'w' && permiso != '-') {
                        permisosValidos = false;
                    }
                    break;
                case 2: // x
                    if (permiso != 'x' && permiso != '-') {
                        permisosValidos = false;
                    }
                    break;
            }
            if (!permisosValidos) {
                break;
            }
        }

        if (permisosValidos) {
            char primerCaracter = ficheros.obtenerFichero(fich).getPermisos().charAt(0);
            permisos = primerCaracter + permisos;
            ficheros.obtenerFichero(fich).setPermisos(permisos);
        } else {
        }

        return permisosValidos;
    }

}
