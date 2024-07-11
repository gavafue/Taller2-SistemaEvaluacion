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

    private String[] tokens;
    private String hora;
    private DateTimeFormatter formatoFecha;

    public Ejecutar(String[] tokens) {
        this.tokens = tokens;
        this.formatoFecha = DateTimeFormatter.ofPattern("HH:mm:ss");// formato para mostrar la variable hora en cada
                                                                    // ejecucion }
    }

    private String getHora() {
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
            mensaje = "[AYUDA]\n\n" + comandos.obtenerDescripcion(tokens[1]) + "\n"
                    + comandos.obtenerEjemplo(tokens[1]);
        } catch (Exception e) {
            mensaje = "No hay informacion disponible";
        }
        return mensaje;
    }

    public String ejecutarComando(Comandos comandos, Ficheros ficheros, Procesos procesos, JLabel sintaxis,
            JTextArea salida) {
        String mensaje = "";
        actualizarHora();
        switch (tokens[0]) {
            case "man":
                mensaje = ejecutarMan(comandos, sintaxis);
                break;
            case "mkdir":
                mensaje = ejecutarMkdir(ficheros, sintaxis);
                break;
            case "rmdir":
                mensaje = ejecutarRmdir(ficheros, sintaxis);
                break;
            case "clear":
                mensaje = ejecutarClear(sintaxis, salida);
                break;
            case "cat":
                mensaje = ejecutarCat(ficheros, sintaxis);
                break;
            case "mv":
                mensaje = ejecutarMv(ficheros, sintaxis);
                break;
            case "ls":
                mensaje = ejecutarLs(ficheros, sintaxis);
                break;
            case "ps":
                mensaje = ejecutarPs(procesos, sintaxis);
                break;
            case "kill":
                mensaje = ejecutarKill(procesos, sintaxis);
                break;
            case "grep":
                mensaje = ejecutarGrep(ficheros, sintaxis);
                break;
            case "tail":
                mensaje = ejecutarTail(ficheros, sintaxis);
                break;
            case "head":
                mensaje = ejecutarHead(ficheros, sintaxis);
                break;
            case "cut":
                mensaje = ejecutarCut(ficheros, sintaxis);
                break;
            case "sort":
                mensaje = ejecutarSort(ficheros, sintaxis);
                break;
            case "chmod":
                mensaje = ejecutarChmod(ficheros, sintaxis);
                break;
            default:
                sintaxis.setForeground(Color.red);
                mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta\n\n";
                break;
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
    public String ejecutarMan(Comandos comandos, JLabel sintaxis) {
        String mensaje = "";
        if (tokens != null && tokens.length == 1) {
            mensaje = comandos.obtenerDescripcion("man") + "\n" + comandos.obtenerEjemplo("man");
        } else {
            mensaje = obtenerAyudaComando(comandos);
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
    public String ejecutarMkdir(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";
        if (tokens != null && tokens.length == 2) {
            mensaje = crearDirectorio(tokens[1], ficheros, sintaxis);
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente mkdir [nombre] o man mkdir\n\n";
        }
        return mensaje;
    }

    /**
     * Crea un directorio si no existe previamente.
     *
     * @param nombreDirectorio Nombre del directorio a crear.
     * @param ficheros         Objeto que gestiona los ficheros y directorios.
     * @param procesos         Objeto para gestionar los procesos.
     * @param sintaxis         Etiqueta donde mostrar mensajes de sintaxis.
     * @return Mensaje resultante de la operación.
     */
    private String crearDirectorio(String nombreDirectorio, Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";
        if (!ficheros.existeFichero(nombreDirectorio)) {
            Directorio nuevo = new Directorio(nombreDirectorio);
            ficheros.agregarFichero(nuevo);
            mensaje = "[" + getHora() + "]\n\n" + "Se ha creado un directorio\n\n";
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "Ya existe un fichero con ese nombre\n\n";
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
    public String ejecutarRmdir(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";
        if (tokens != null && tokens.length == 2) {
            mensaje = eliminarDirectorio(tokens[1], ficheros, sintaxis);
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente rmdir [nombre] o man rmdir\n\n";
        }
        return mensaje;
    }

    /**
     * Elimina un directorio si existe.
     *
     * @param nombreDirectorio Nombre del directorio a eliminar.
     * @param ficheros         Objeto que gestiona los ficheros y directorios.
     * @param procesos         Objeto para gestionar los procesos.
     * @param sintaxis         Etiqueta donde mostrar mensajes de sintaxis.
     * @return Mensaje resultante de la operación.
     */
    private String eliminarDirectorio(String nombreDirectorio, Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            ficheros.eliminarFichero(nombreDirectorio);
            mensaje = "[" + getHora() + "]\n\n" + "Directorio eliminado\n\n";
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando 'clear' para limpiar el área de salida.
     *
     * @param sintaxis Etiqueta donde mostrar mensajes de sintaxis.
     * @param salida   Área de texto que se limpia.
     * @return Mensaje resultante de la ejecución.
     */
    public String ejecutarClear(JLabel sintaxis, JTextArea salida) {
        String mensaje = "";
        if (tokens != null && tokens.length == 1) {
            salida.setText("");
            mensaje = "";
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "Sintaxis incorrecta\n\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando 'cat' para mostrar el contenido de un archivo si existe y
     * no es un directorio.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarCat(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";

        if (tokens.length != 2) {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente cat [nombre] o man cat\n\n";
        } else {
            String nombreArchivo = tokens[1];
            if (ficheros.existeFichero(nombreArchivo) && !ficheros.esDirectorio(nombreArchivo)) {
                sintaxis.setForeground(Color.cyan);
                Fichero archivo = ficheros.obtenerFichero(nombreArchivo);
                mensaje = "[" + getHora() + "]\n\n" + archivo.obtenerContenido() + "\n\n";
            } else {
                sintaxis.setForeground(Color.red);
                mensaje = "[" + getHora() + "]\n\n" + "No existe un archivo con ese nombre\n\n";
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
    public String ejecutarMv(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";

        // Verifica la cantidad de tokens para determinar la sintaxis correcta.
        if (tokens.length != 3) {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n"
                    + "Sintaxis incorrecta.\n\nIntente mv [nombreActual] [nombreNuevo] o man mv\n\n";
        } else {
            String nombreActual = tokens[1];
            String nombreNuevo = tokens[2];

            // Verifica si el archivo existe y no es un directorio.
            if (ficheros.existeFichero(nombreActual) && !ficheros.esDirectorio(nombreActual)) {
                sintaxis.setForeground(Color.cyan);

                // Encuentra el índice del archivo en la lista.
                int i = 0;
                while (!ficheros.obtenerFichero(i).getNombre().equals(nombreActual)) {
                    i++;
                }

                // Cambia el nombre del archivo.
                ficheros.obtenerFichero(i).setNombre(nombreNuevo);

                // Construye el mensaje de éxito.
                mensaje = "[" + getHora() + "]\n\n" + "El fichero " + nombreActual + " ha sido renombrado a "
                        + nombreNuevo
                        + "\n\n";
            } else {
                // Si no existe el archivo, muestra un mensaje de error.
                sintaxis.setForeground(Color.red);
                mensaje = "[" + getHora() + "]\n\n" + "No existe un archivo con ese nombre\n\n";
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
    public String ejecutarLs(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";

        switch (tokens.length) {
            case 1:
                // Comando ls (sin opciones)
                mensaje = obtenerListaSimple(ficheros);
                break;
            case 2:
                mensaje = manejarOpcionesUnParametro(ficheros, sintaxis);
                break;
            case 3:
                mensaje = manejarOpcionesDosParametros(ficheros, sintaxis);
                break;
            case 4:
                mensaje = manejarOpcionesTresParametros(ficheros, sintaxis);
                break;
            default:
                sintaxis.setForeground(Color.red);
                mensaje = "[" + getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente man ls\n\n";
                break;
        }

        return mensaje;
    }

    /**
     * Obtiene la lista simple de nombres de archivos y directorios visibles.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return un mensaje con la lista de nombres de archivos y directorios
     *         visibles.
     */
    private String obtenerListaSimple(Ficheros ficheros) {
        String mensaje;
        mensaje = "[" + getHora() + "]\n\n" + ficheros.obtenerNombres(false) + "\n\n"; // Muestra nombres sin ocultos
        return mensaje;
    }

    /**
     * Maneja las opciones de ls con un parámetro.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return un mensaje detallando el resultado de la ejecución del comando con un
     *         parámetro.
     */
    private String manejarOpcionesUnParametro(Ficheros ficheros, JLabel sintaxis) {
        String mensaje;
        switch (tokens[1]) {
            case "-l":
                mensaje = "[" + getHora() + "]\n\n" + ficheros.obtenerInformacionDetallada(false) + "\n\n"; // Información
                                                                                                            // detallada
                                                                                                            // sin
                                                                                                            // ocultos
                break;
            case "-a":
                mensaje = "[" + getHora() + "]\n\n" + ficheros.obtenerNombres(true) + "\n\n"; // Muestra nombres
                                                                                              // incluyendo ocultos
                break;
            default:
                mensaje = obtenerContenidoDirectorio(ficheros, tokens[1], sintaxis);
                break;
        }
        return mensaje;
    }

    /**
     * Maneja las opciones de ls con dos parámetros.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return un mensaje detallando el resultado de la ejecución del comando con
     *         dos parámetros.
     */
    private String manejarOpcionesDosParametros(Ficheros ficheros, JLabel sintaxis) {
        String mensaje;
        if ((tokens[1].equals("-l") && tokens[2].equals("-a")) || (tokens[1].equals("-a") && tokens[2].equals("-l"))) {
            mensaje = "[" + getHora() + "]\n\n" + ficheros.obtenerInformacionDetallada(true) + "\n\n"; // Información
                                                                                                       // detallada
                                                                                                       // incluyendo
                                                                                                       // ocultos
        } else if (tokens[1].equals("-l")) {
            mensaje = obtenerComandoLsL(ficheros, tokens[2], sintaxis);
        } else if (tokens[1].equals("-a")) {
            mensaje = obtenerComandoLsA(ficheros, tokens[2], sintaxis);
        } else {
            mensaje = mensajeErrorSintaxisIncorrecta(sintaxis);
        }
        return mensaje;
    }

    /**
     * Maneja las opciones de ls con tres parámetros.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return un mensaje detallando el resultado de la ejecución del comando con
     *         tres parámetros.
     */
    private String manejarOpcionesTresParametros(Ficheros ficheros, JLabel sintaxis) {
        String mensaje;
        if ((tokens[1].equals("-l") && tokens[2].equals("-a")) || (tokens[1].equals("-a") && tokens[2].equals("-l"))) {
            mensaje = obtenerComandoLsLsA(ficheros, tokens[3], sintaxis);
        } else {
            mensaje = mensajeErrorSintaxisIncorrecta(sintaxis);
        }
        return mensaje;
    }

    /**
     * Obtiene el contenido de un directorio para el comando ls [directorio].
     *
     * @param ficheros         el objeto que maneja la lista de archivos
     *                         disponibles.
     * @param nombreDirectorio el nombre del directorio a listar.
     * @return un mensaje con el contenido del directorio especificado.
     */
    private String obtenerContenidoDirectorio(Ficheros ficheros, String nombreDirectorio, JLabel sintaxis) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            int i = 0;
            while (!ficheros.obtenerFichero(i).getNombre().equals(nombreDirectorio)) {
                i++;
            }
            mensaje = "[" + getHora() + "]\n\n" + ficheros.obtenerFichero(i).obtenerContenido() + "\n\n";
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje de sintaxis incorrecta para ls.
     *
     * @return un mensaje indicando que la sintaxis es incorrecta para ls.
     */
    private String mensajeErrorSintaxisIncorrecta(JLabel sintaxis) {
        sintaxis.setForeground(Color.red);
        return "[" + getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente man ls\n\n";
    }

    /**
     * Obtiene el mensaje para el comando ls -l [directorio].
     *
     * @param ficheros         el objeto que maneja la lista de archivos
     *                         disponibles.
     * @param nombreDirectorio el nombre del directorio a listar detalladamente.
     * @return un mensaje indicando el resultado de ejecutar ls -l [directorio].
     */
    private String obtenerComandoLsL(Ficheros ficheros, String nombreDirectorio, JLabel sintaxis) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "[" + getHora() + "]\n\nComando ls -l al directorio " + nombreDirectorio + "\n\n";
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -a [directorio].
     *
     * @param ficheros         el objeto que maneja la lista de archivos
     *                         disponibles.
     * @param nombreDirectorio el nombre del directorio a listar con todos los
     *                         archivos.
     * @return un mensaje indicando el resultado de ejecutar ls -a [directorio].
     */
    private String obtenerComandoLsA(Ficheros ficheros, String nombreDirectorio, JLabel sintaxis) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "[" + getHora() + "]\n\nComando ls -a al directorio " + nombreDirectorio + "\n\n";
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -l -a [directorio].
     *
     * @param ficheros         el objeto que maneja la lista de archivos
     *                         disponibles.
     * @param nombreDirectorio el nombre del directorio a listar detalladamente con
     *                         todos los archivos.
     * @return un mensaje indicando el resultado de ejecutar ls -l -a [directorio].
     */
    private String obtenerComandoLsLsA(Ficheros ficheros, String nombreDirectorio, JLabel sintaxis) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "[" + getHora() + "]\n\nComando (ls -a -l) o (ls -l -a) al directorio " + nombreDirectorio
                    + "\n\n";
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
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
    public String ejecutarKill(Procesos procesos, JLabel sintaxis) {
        String mensaje = "";

        if (tokens.length == 2) {
            // Intenta obtener el ID del proceso del segundo token
            String procesoIdStr = tokens[1];

            int procesoID = Integer.parseInt(procesoIdStr);
            if (procesos.existeProceso(procesoID)) {
                // Si el proceso existe, se elimina de la lista de procesos
                procesos.getListaProcesos().remove(procesos.obtenerProceso(procesoID));
                mensaje = "[" + getHora() + "]\n\n" + "Proceso eliminado\n\n";
                sintaxis.setForeground(Color.cyan);
            } else {
                // Si no existe el proceso con el ID proporcionado
                mensaje = "[" + getHora() + "]\n\n" + "No existe el proceso\n\n";
            }
        } else {
            // Si la sintaxis del comando es incorrecta
            mensaje = "[" + getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nPruebe man kill\n\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'ps' para listar los procesos en ejecución del sistema.
     *
     * @param procesos el objeto que maneja la lista de procesos activos.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje detallando los procesos en ejecución o un mensaje de error
     *         si la sintaxis es incorrecta.
     */
    public String ejecutarPs(Procesos procesos, JLabel sintaxis) {
        String mensaje = "";

        if (tokens.length == 1) {
            if (procesos.getListaProcesos().isEmpty()) {
                mensaje = "[" + getHora() + "]\n\n" + "No existen procesos en ejecución\n\n";
            } else {
                mensaje = "PID | USER | % MEMORY | % CPU | TIME | COMMAND\n";
                for (Proceso proceso : procesos.getListaProcesos()) {
                    mensaje += proceso.toString() + "\n";
                }
            }
        } else {
            mensaje = "[" + getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nPruebe man ps\n\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'grep' para buscar un patrón en un archivo específico.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje indicando si se encontró el patrón en el archivo o un
     *         mensaje de error si la expresión es incorrecta.
     */
    private String ejecutarGrep(Ficheros ficheros, JLabel sintaxis) {
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
     *         mensaje de error si la sintaxis es incorrecta.
     */
    private String ejecutarTail(Ficheros ficheros, JLabel sintaxis) {
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
                sintaxis.setForeground(Color.red);
                mensaje = "[" + getHora() + "]\n\n" + "El número de líneas debe ser un valor numérico.\n\n";
            }
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nPruebe man tail\n\n";
        }

        return mensaje;
    }

    /**
     * Obtiene las últimas 10 líneas de un archivo.
     *
     * @param ficheros      el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las líneas.
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
     * @param ficheros      el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las líneas.
     * @param n             el número de líneas que se desean obtener.
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
     *         mensaje de error si la sintaxis es incorrecta.
     */
    public String ejecutarHead(Ficheros ficheros, JLabel sintaxis) {
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
                sintaxis.setForeground(Color.red);
                mensaje = "[" + getHora() + "]\n\n" + "El número de líneas debe ser un valor numérico.\n\n";
            }
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nPruebe man head\n\n";
        }

        return mensaje;
    }

    /**
     * Obtiene las primeras 10 líneas de un archivo.
     *
     * @param ficheros      el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las líneas.
     * @param numLineas     el número de líneas que se desean obtener.
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
     * @param ficheros      el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las líneas.
     * @param numLineas     el número de líneas que se desean obtener.
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
     * archivo
     * usando un delimitador específico.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje con las columnas seleccionadas del archivo especificado o
     *         un mensaje de error si la sintaxis es incorrecta.
     */
    public String ejecutarCut(Ficheros ficheros, JLabel sintaxis) {
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
                sintaxis.setForeground(Color.RED); // Cambiar color a rojo
                return mensaje;
            }

            // Verificar que el archivo tenga contenido
            String contenidoArchivo = ficheros.obtenerFichero(archivo).obtenerContenido();
            if (contenidoArchivo == null || contenidoArchivo.isEmpty()) {
                mensaje += "Error: El archivo especificado está vacío.";
                sintaxis.setForeground(Color.RED); // Cambiar color a rojo
                return mensaje;
            }

            // Validar que el delimitador sea el correcto (solo se admite ':')
            if (!delimitador.equals(":")) {
                mensaje += "Error: El delimitador especificado no es válido. Se admite solo ':' (dos puntos).";
                sintaxis.setForeground(Color.RED); // Cambiar color a rojo
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
                    sintaxis.setForeground(Color.RED); // Cambiar color a rojo
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
                        sintaxis.setForeground(Color.RED); // Cambiar color a rojo
                        return mensaje;
                    }
                }

                mensaje += lineaResultado.toString() + "\n";
            }

            sintaxis.setForeground(Color.CYAN); // Cambiar color a celeste
        } else {
            mensaje += "Error: Sintaxis incorrecta. La sintaxis correcta es: cut -d ':' -f 1,4 archivo.txt";
            sintaxis.setForeground(Color.RED); // Cambiar color a rojo
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando 'sort' para mostrar las líneas de un archivo ordenadas
     * alfabéticamente.
     * Si se especifica la opción '-n', ordena numéricamente.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param sintaxis el JLabel donde se muestra la sintaxis del comando.
     * @return un mensaje con las líneas del archivo ordenadas o un mensaje de error
     *         si la sintaxis es incorrecta.
     */
    public String ejecutarSort(Ficheros ficheros, JLabel sintaxis) {
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
            sintaxis.setForeground(Color.RED);
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta\n\n";
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
    public String ejecutarChmod(Ficheros ficheros, JLabel sintaxis) {
        String permisos = tokens[1];
        String fich = tokens[2];
        String mensaje = "";

        // Verificar la cantidad de argumentos
        if (tokens.length != 3) {
            mensaje = "Sintaxis incorrecta: número incorrecto de argumentos";
        } else if (!verificarExistenciaFichero(ficheros, fich, sintaxis)) {
            // Verificar si el fichero existe
            mensaje = "Sintaxis incorrecta: el fichero o directorio '" + fich + "' no existe";
        } else if (!validarLongitudPermisos(permisos, sintaxis)) {
            // Validar longitud de permisos
            mensaje = "Sintaxis incorrecta: longitud incorrecta de permisos";
        } else {
            switch (permisos.length()) {
                case 3:
                    if (!aplicarPermisosNumericos(ficheros, fich, permisos, sintaxis)) {
                        mensaje = "Sintaxis incorrecta: valor de permisos inválido";
                    } else {
                        mensaje = "Comando ejecutado correctamente";
                    }
                    break;
                case 9:
                    if (!aplicarPermisosSimbolicos(ficheros, fich, permisos, sintaxis)) {
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
     * Verifica si el fichero o directorio existe.
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @param fich     Nombre del fichero o directorio a verificar.
     * @param sintaxis Etiqueta donde se indica la sintaxis.
     * @return true si el fichero o directorio existe, false de lo contrario.
     */
    private boolean verificarExistenciaFichero(Ficheros ficheros, String fich, JLabel sintaxis) {
        if (!ficheros.existeFichero(fich)) {
            sintaxis.setForeground(Color.RED);
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
     *         contrario.
     */
    private boolean validarLongitudPermisos(String permisos, JLabel sintaxis) {
        if (permisos.length() != 3 && permisos.length() != 9) {
            sintaxis.setForeground(Color.RED);
            return false;
        }
        return true;
    }

    /**
     * Aplica los permisos numéricos al fichero o directorio.
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @param fich     Nombre del fichero o directorio al que se aplicarán los
     *                 permisos.
     * @param permisos String con los permisos numéricos.
     * @param sintaxis Etiqueta donde se indica la sintaxis.
     * @return true si se aplicaron los permisos correctamente, false de lo
     *         contrario.
     */
    private boolean aplicarPermisosNumericos(Ficheros ficheros, String fich, String permisos, JLabel sintaxis) {
        String permisosEnLetras = ficheros.obtenerFichero(fich).getPermisos().charAt(0) + "";
        boolean permisosValidos = true;

        for (int i = 0; i < 3; i++) {
            int permisoNum = Character.getNumericValue(permisos.charAt(i));
            if (permisoNum < 0 || permisoNum > 7) {
                sintaxis.setForeground(Color.RED);
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
            sintaxis.setForeground(Color.CYAN);
        }

        return permisosValidos;
    }

    /**
     * Aplica los permisos simbólicos al fichero o directorio.
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @param fich     Nombre del fichero o directorio al que se aplicarán los
     *                 permisos.
     * @param permisos String con los permisos simbólicos.
     * @param sintaxis Etiqueta donde se indica la sintaxis.
     * @return true si se aplicaron los permisos correctamente, false de lo
     *         contrario.
     */
    private boolean aplicarPermisosSimbolicos(Ficheros ficheros, String fich, String permisos, JLabel sintaxis) {
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
            sintaxis.setForeground(Color.CYAN);
        } else {
            sintaxis.setForeground(Color.RED);
        }
    
        return permisosValidos;
    }
    
}