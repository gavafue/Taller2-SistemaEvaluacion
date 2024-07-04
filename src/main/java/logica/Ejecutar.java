/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import static java.lang.Integer.parseInt;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Gabriel
 */
public class Ejecutar {

    private String cadena;
    private String[] tokens;
    private String hora;
    private DateTimeFormatter formatoFecha;

    public Ejecutar(String cadena, String[] tokens) {
        this.cadena = cadena;
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
                mensaje = ejecutarMan(comandos, procesos, sintaxis);
                break;
            case "mkdir":
                mensaje = ejecutarMkdir(ficheros, procesos, sintaxis);
                break;
            case "rmdir":
                mensaje = ejecutarRmdir(ficheros, procesos, sintaxis);
                break;
            case "clear":
                mensaje = ejecutarClear(sintaxis, procesos, salida);
                break;
            case "cat":
                mensaje = ejecutarCat(ficheros, procesos, sintaxis);
                break;
            case "mv":
                mensaje = ejecutarMv(ficheros, procesos, sintaxis);
                break;
            case "ls":
                mensaje = ejecutarLs(ficheros, procesos, sintaxis);
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
    } // fin ejecutarComando

    /**
     * Metodo que permite ejecutar el comando man.
     *
     * @param comandos
     * @param procesos
     * @param sintaxis
     * @return
     */
    public String ejecutarMan(Comandos comandos, Procesos procesos, JLabel sintaxis) {
        String mensaje = "";
        if (tokens.length == 1) {
            mensaje = comandos.obtenerDescripcion("man") + "\n" + comandos.obtenerEjemplo("man");
        } else {
            mensaje = obtenerAyudaComando(comandos);
        }
        procesos.agregarProceso(cadena);
        return mensaje;
    }

    public String ejecutarMkdir(Ficheros ficheros, Procesos procesos, JLabel sintaxis) {
        String mensaje = "";
        if (tokens.length == 2) {
            if (!ficheros.existeFichero(tokens[1])) {
                Directorio nuevo = new Directorio(tokens[1]);
                ficheros.agregarFichero(nuevo);
                mensaje = "[" + this.getHora() + "]\n\n" + "Se ha creado un directorio\n\n";
                procesos.agregarProceso(cadena);
            } else {
                sintaxis.setForeground(Color.red);
                mensaje = "[" + this.getHora() + "]\n\n" + "Ya existe un fichero con ese nombre\n\n";
            }
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente mkdir [nombre] o man mkdir\n\n";
        }
        return mensaje;
    }

    public String ejecutarRmdir(Ficheros ficheros, Procesos procesos, JLabel sintaxis) {
        String mensaje = "";
        if (tokens.length == 2) {
            if (ficheros.existeFichero(tokens[1]) && ficheros.esDirectorio(tokens[1])) { // Si existe y es directorio
                ficheros.eliminarFichero(tokens[1]);
                mensaje = "[" + this.getHora() + "]\n\n" + "Directorio eliminado\n\n";
                procesos.agregarProceso(cadena);
            } else {
                sintaxis.setForeground(Color.red);
                mensaje = "[" + this.getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
            }
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente rmdir [nombre] o man rmdir\n\n";
        }
        return mensaje;
    }

    public String ejecutarClear(JLabel sintaxis, Procesos procesos, JTextArea salida) {
        String mensaje = "";
        if (tokens.length == 1) {
            salida.setText("");
            mensaje = "";
            procesos.agregarProceso(cadena);
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta\n\n";
        }
        return mensaje;
    }

    public String ejecutarCat(Ficheros ficheros, Procesos procesos, JLabel sintaxis) {
        String mensaje = "";
        if (tokens.length == 2) {
            if (ficheros.existeFichero(tokens[1]) && !ficheros.esDirectorio(tokens[1])) { // Si existe y es archivo
                sintaxis.setForeground(Color.cyan);
                int i = 0;
                while (!ficheros.obtenerFichero(i).getNombre().equals(tokens[1])) { // Sin control de rango porque
                                                                                    // existe
                    i++;
                }
                mensaje = "[" + this.getHora() + "]\n\n" + ficheros.obtenerFichero(i).obtenerContenido() + "\n\n";
                procesos.agregarProceso(cadena);
            } else {
                sintaxis.setForeground(Color.red);
                mensaje = "[" + this.getHora() + "]\n\n" + "No existe un archivo con ese nombre\n\n";
            }
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente cat [nombre] o man cat\n\n";
        }
        return mensaje;
    }

    public String ejecutarMv(Ficheros ficheros, Procesos procesos, JLabel sintaxis) {
        String mensaje = "";
        if (tokens.length == 3) {
            if (ficheros.existeFichero(tokens[1]) && !ficheros.esDirectorio(tokens[1])) { // Si existe y es directorio
                sintaxis.setForeground(Color.cyan);
                int i = 0;
                while (!ficheros.obtenerFichero(i).getNombre().equals(tokens[1])) { // Sin control de rango porque
                                                                                    // existe
                    i++;
                }
                ficheros.obtenerFichero(i).setNombre(tokens[2]);
                mensaje = "[" + this.getHora() + "]\n\n" + "El fichero " + tokens[1] + " ha sido renombrado a "
                        + tokens[2] + "\n\n";
                procesos.agregarProceso(cadena);
            } else {
                sintaxis.setForeground(Color.red);
                mensaje = "[" + this.getHora() + "]\n\n" + "No existe un archivo con ese nombre\n\n";
            }
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + this.getHora() + "]\n\n"
                    + "Sintaxis incorrecta.\n\nIntente mv [nombreActual] [nombreNuevo] o man mv\n\n";
        }
        return mensaje;
    }

    public String ejecutarLs(Ficheros ficheros, Procesos procesos, JLabel sintaxis) {
        String mensaje = "";
        switch (tokens.length) {
            case 1: // Comando ls
                mensaje = "[" + this.getHora() + "]\n\n" + ficheros.obtenerNombres(false) + "\n\n"; // Muestra obviando
                                                                                                    // los ocultos
                procesos.agregarProceso(cadena);
                break;
            case 2:
                switch (tokens[1]) {
                    case "-l": // Comando ls -l
                        mensaje = "[" + this.getHora() + "]\n\n" + ficheros.obtenerInformacionDetallada(false) + "\n\n";// Muestra
                                                                                                                        // informacion
                                                                                                                        // detallada
                                                                                                                        // con
                                                                                                                        // ocultos
                        break;
                    case "-a": // Comando ls -a
                        mensaje = "[" + this.getHora() + "]\n\n" + ficheros.obtenerNombres(true) + "\n\n";// Muestra
                                                                                                          // nombres
                                                                                                          // incluyendo
                                                                                                          // ocultos
                        break;
                    default: // Comando ls directorio
                        if (ficheros.existeFichero(tokens[1]) && (ficheros.esDirectorio(tokens[1]))) { // Si existe y es
                                                                                                       // directorio
                            int i = 0;
                            while (!ficheros.obtenerFichero(i).getNombre().equals(tokens[1])) { // Sin control de rango
                                                                                                // porque existe
                                i++; // Obtengo indice en el que se encuentra el directorio a listar
                            }
                            mensaje = "[" + this.getHora() + "]\n\n" + ficheros.obtenerFichero(i).obtenerContenido()
                                    + "\n\n";
                        } else {
                            sintaxis.setForeground(Color.red);
                            mensaje = "[" + this.getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
                        }
                        break;
                }
                break;
            case 3:
                if (((tokens[1].equals("-l")) && (tokens[2].equals("-a")))
                        || ((tokens[1].equals("-a")) && (tokens[2].equals("-l")))) { // Comando (ls -l -a) o (ls -a -l)
                    mensaje = "[" + this.getHora() + "]\n\n" + ficheros.obtenerInformacionDetallada(true) + "\n\n"; // Informacion
                                                                                                                    // detallada
                                                                                                                    // pero
                                                                                                                    // sin
                                                                                                                    // ocultos
                } else if (tokens[1].equals("-l")) { // Comando ls -l directorio
                    if (ficheros.existeFichero(tokens[2]) && (ficheros.esDirectorio(tokens[2]))) { // Si existe y es
                                                                                                   // directorio
                        mensaje = "[" + this.getHora() + "]\n\nComando ls -l al directorio " + tokens[2] + "\n\n";
                    } else {
                        sintaxis.setForeground(Color.red);
                        mensaje = "[" + this.getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
                    }
                } else if (tokens[1].equals("-a")) { // Comando ls -a directorio
                    if (ficheros.existeFichero(tokens[2]) && (ficheros.esDirectorio(tokens[2]))) { // Si existe y es
                                                                                                   // directorio
                        mensaje = "[" + this.getHora() + "]\n\nComando ls -a al directorio " + tokens[2] + "\n\n";
                    } else {
                        sintaxis.setForeground(Color.red);
                        mensaje = "[" + this.getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
                    }
                } else {
                    sintaxis.setForeground(Color.red);
                    mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente man ls\n\n";
                }
                break;
            case 4:
                if (((tokens[1].equals("-l")) && (tokens[2].equals("-a")))
                        || ((tokens[1].equals("-a")) && (tokens[2].equals("-l")))) { // Comando (ls -l -a directorio) o
                                                                                     // (ls -a -l directorio)
                    if ((ficheros.existeFichero(tokens[3])) && (ficheros.esDirectorio(tokens[3]))) { // Si existe y es
                                                                                                     // directorio
                        mensaje = "[" + this.getHora() + "]\n\nComando (ls -a -l) o (ls -l -a) al directorio "
                                + tokens[3] + "\n\n";
                    } else {
                        sintaxis.setForeground(Color.red);
                        mensaje = "[" + this.getHora() + "]\n\n" + "No existe un directorio con ese nombre\n\n";
                    }
                } else {
                    sintaxis.setForeground(Color.red);
                    mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente man ls\n\n";
                }
                break;
            default:
                sintaxis.setForeground(Color.red);
                mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta.\n\nIntente man ls\n\n";
                break;
        }
        return mensaje;
    }

    /**
     * Metodo que permite eliminar un proceso.
     *
     * @param procesos
     * @param sintaxis
     * @return
     */
    public String ejecutarKill(Procesos procesos, JLabel sintaxis) {
        String mensaje = "";
        if (tokens.length == 2) {
            if (procesos.existeProceso(parseInt(tokens[1]))) {
                procesos.getListaProcesos().remove(procesos.obtenerProceso(parseInt(tokens[1])));
                mensaje = "[" + this.getHora() + "]\n\n" + "Proceso eliminado\n\n";
                sintaxis.setForeground(Color.cyan);
            } else {
                mensaje = "[" + this.getHora() + "]\n\n" + "No existe el proceso\n\n";
            }
        } else {
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintasis incorrecta.\n\nPruebe man kill\n\n";
        }
        return mensaje;
    }

    /**
     * Metodo que permite listar los procesos en ejecucion del sistema.
     *
     * @param procesos
     * @param sintaxis
     * @return
     */
    public String ejecutarPs(Procesos procesos, JLabel sintaxis) {
        String mensaje = "";
        if (tokens.length == 1) {
            if (procesos.getListaProcesos().isEmpty()) {
                mensaje = "[" + this.getHora() + "]\n\n" + "No existen procesos en ejecucion\n\n";
            } else {
                mensaje = "PID | USER | % MEMORY | % CPU | TIME | COMMAND\n";
                for (Proceso proceso : procesos.getListaProcesos()) {
                    mensaje += proceso.toString();
                    mensaje += "\n";
                }
            }
        } else {
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintasis incorrecta.\n\nPruebe man ps\n\n";
        }
        return mensaje;
    }

    /**
     * Metodo que permite buscar patrones en archivos.
     *
     * @param ficheros
     * @param sintaxis
     * @return mensaje a imprimir en consola.
     */
    private String ejecutarGrep(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";
        if (tokens.length != 3) {
            mensaje = "Expresion incorrecta: debe ingresar string a buscar y archivo en el que buscar.";
        } else {
            if (ficheros.existeFichero(tokens[2])) {
                mensaje = Boolean.toString(ficheros.obtenerFichero(tokens[2]).obtenerContenido().contains(tokens[1]));
                // De momento solo confirma que este presente la expresion. Cumple RF29.
                // Por hacer: mostrar el texto resaltado o... contar cantidad de ocurrencias.
                // EXTRA
            } else {
                mensaje = "El fichero indicado no existe.";
            }

        }

        return mensaje;
    }

    /**
     * Metodo que permite mostrar las últimas líneas de un archivo.
     *
     * @param ficheros
     * @param sintaxis
     * @return mensaje a imprimir en consola.
     *
     */
    private String ejecutarTail(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";
        String[] mensajeTokenizado;
        int i;
        if (tokens.length == 2) {
            mensajeTokenizado = ficheros.obtenerFichero(tokens[1]).obtenerContenido().split("\\R");
            if (mensajeTokenizado.length >= 10) {
                i = mensajeTokenizado.length - 10;

            } else {
                i = 0;
            }

            while (i < mensajeTokenizado.length) {
                mensaje += mensajeTokenizado[i] + "\n";
                i++;
            }
        } else if (tokens.length == 4) {
            mensajeTokenizado = ficheros.obtenerFichero(tokens[3]).obtenerContenido().split("\\R");
            if (tokens[1].equals("-n")) {
                if (mensajeTokenizado.length >= Integer.parseInt(tokens[2])) {
                    i = mensajeTokenizado.length - Integer.parseInt(tokens[2]); // Validar, q pasa si no es numero?

                } else {
                    i = 0;
                }

                while (i < mensajeTokenizado.length) {
                    mensaje += mensajeTokenizado[i] + "\n";
                    i++;
                }
            }
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta\n\n";
        }

        return mensaje;
    }

    /**
     * Metodo que permite mostrar las primeras líneas de un archivo.
     *
     * @param ficheros
     * @param sintaxis
     * @return mensaje a imprimir en consola.
     *
     */
    public String ejecutarHead(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";
        String[] mensajeTokenizado;
        int i = 0;
        if (tokens.length == 2) {
            mensajeTokenizado = ficheros.obtenerFichero(tokens[1]).obtenerContenido().split("\\R");
            while (i < mensajeTokenizado.length & i < 10) {
                mensaje += mensajeTokenizado[i] + "\n";
                i++;
            }
        } else if (tokens.length == 4) {
            mensajeTokenizado = ficheros.obtenerFichero(tokens[3]).obtenerContenido().split("\\R");

            if (tokens[1].equals("-n")) {
                while (i < mensajeTokenizado.length & i < Integer.parseInt(tokens[2])) { // Validar, q pasa si no es
                                                                                         // numero?
                    mensaje += mensajeTokenizado[i] + "\n";
                    i++;
                }
            }
        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta\n\n";
        }

        return mensaje;
    }

    /**
     * Metodo que permite mostrar las columnas seleccionadas de un archivo
     * usando como delimitador el punto doble :
     *
     * @param ficheros
     * @param sintaxis
     * @return mensaje a imprimir en consola.
     *
     */
    public String ejecutarCut(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";

        // Verificar si el comando tiene la cantidad correcta de tokens y las opciones
        // correctas
        if (tokens.length == 6 && tokens[0].equals("cut") && tokens[1].equals("-d") && tokens[3].equals("-f")) {
            String delimitador = tokens[2]; // Delimitador especificado
            String campos = tokens[4]; // Campos a extraer
            String archivo = tokens[5]; // Nombre del archivo

            // Remover comillas simples si están presentes
            delimitador = delimitador.replace("'", "");

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

            // Validar que el delimitador sea el correcto
            if (!delimitador.equals(":")) {
                mensaje += "Error: El delimitador especificado no es válido. Se admite solo ':' (dos puntos).";
                sintaxis.setForeground(Color.RED); // Cambiar color a rojo
            } else {
                String[] camposArray = campos.split(",");
                List<Integer> indicesCampos = new ArrayList<>();

                // Convertir camposArray a lista de índices (restamos 1 para hacerlos 0-based)
                for (int j = 0; j < camposArray.length; j++) {
                    indicesCampos.add(Integer.parseInt(camposArray[j].trim()) - 1);
                }

                // Dividir el contenido del archivo por líneas simulando la lectura desde el
                // String
                contenidoArchivo = ficheros.obtenerFichero(archivo).obtenerContenido();
                String[] lineas = contenidoArchivo.split("\n");

                // Verificar que los índices especificados en campos sean válidos
                for (int indice : indicesCampos) {
                    if (indice < 0 || indice >= lineas.length) {
                        mensaje += "Error: El índice de línea especificado '" + (indice + 1)
                                + "' está fuera del rango de líneas del archivo.";
                        sintaxis.setForeground(Color.RED); // Cambiar color a rojo
                        return mensaje;
                    }
                }

                // Procesar cada línea
                for (int k = 0; k < lineas.length; k++) {
                    String linea = lineas[k];
                    String[] partes = linea.split(delimitador); // Usar el delimitador especificado
                    String lineaResultado = "";

                    // Obtener las columnas después de los campos especificados
                    for (int i = 0; i < partes.length; i++) {
                        if (indicesCampos.contains(i)) {
                            if (!lineaResultado.isEmpty()) {
                                lineaResultado += delimitador; // Agregar delimitador entre columnas
                            }
                            lineaResultado += partes[i];
                        }
                    }

                    mensaje += lineaResultado + "\n";
                }

                sintaxis.setForeground(Color.CYAN); // Cambiar color a celeste
            }
        } else {
            mensaje += "Error: Sintaxis incorrecta. La sintaxis correcta es: cut -d ':' -f 1,4 archivo.txt";
            sintaxis.setForeground(Color.RED); // Cambiar color a rojo
        }

        return mensaje;
    }

    /**
     * Metodo que permite mostrar las lineas de un archivo ordenadas.
     *
     * @param ficheros
     * @param sintaxis
     * @return mensaje a imprimir en consola.
     *
     */
    public String ejecutarSort(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";
        String[] mensajeTokenizado;
        if (tokens.length == 2) {
            mensajeTokenizado = ficheros.obtenerFichero(tokens[1]).obtenerContenido().split("\\R");
            Arrays.sort(mensajeTokenizado); // comparar como ordena sort bash y como ordena este sort?
            for (String token : mensajeTokenizado) {
                mensaje += token + "\n";
            }

        } else if (tokens.length == 3) {
            mensajeTokenizado = ficheros.obtenerFichero(tokens[2]).obtenerContenido().split("\\R");
            if (tokens[1].equals("-n")) {
                Arrays.sort(mensajeTokenizado); // DEBERIA ORDENAR EN BASE A VALOR NUMERICO. PENDIENTE.
                for (String token : mensajeTokenizado) {
                    mensaje += token + "\n";
                }
            }

        } else {
            sintaxis.setForeground(Color.red);
            mensaje = "[" + this.getHora() + "]\n\n" + "Sintaxis incorrecta\n\n";
        }
        return mensaje;
    }

    /**
     * Metodo que permite modificar los permisos de un archivo y/o directorio.
     *
     * @param ficheros
     * @param sintaxis
     * @return mensaje a imprimir en consola.
     *
     */
    public String ejecutarChmod(Ficheros ficheros, JLabel sintaxis) {
        String mensaje = "";
        String permisos = tokens[1];
        String fich = tokens[2];

        // Verificar la cantidad de argumentos
        if (tokens.length != 3) {
            mensaje = "Sintaxis incorrecta: número incorrecto de argumentos";
            sintaxis.setForeground(Color.RED); // Cambiar color a rojo
            return mensaje;
        }

        // Verificar si el fichero existe
        if (!ficheros.existeFichero(fich)) {
            mensaje = "Sintaxis incorrecta: el fichero o directorio '" + fich + "' no existe";
            sintaxis.setForeground(Color.RED); // Cambiar color a rojo
            return mensaje;
        }

        // Validar longitud de permisos
        if (permisos.length() != 3 && permisos.length() != 9) {
            mensaje = "Sintaxis incorrecta: longitud incorrecta de permisos";
            sintaxis.setForeground(Color.RED); // Cambiar color a rojo
            return mensaje;
        }

        switch (permisos.length()) {
            case 3:
                // Convertir permisos numéricos a caracteres
                String permisosEnLetras = ficheros.obtenerFichero(fich).getPermisos().charAt(0) + "";
                for (int i = 0; i < 3; i++) {
                    int permisoNum = Character.getNumericValue(permisos.charAt(i));
                    if (permisoNum < 0 || permisoNum > 7) {
                        mensaje = "Sintaxis incorrecta: valor de permisos inválido";
                        sintaxis.setForeground(Color.RED); // Cambiar color a rojo
                        return mensaje;
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
                ficheros.obtenerFichero(fich).setPermisos(permisosEnLetras);
                mensaje = "Comando ejecutado correctamente";
                sintaxis.setForeground(Color.CYAN); // Cambiar color a celeste
                break;

            case 9:
                // Validar permisos
                boolean permisosValidos = true;
                for (int i = 0; i < permisos.length(); i++) {
                    char permiso = permisos.charAt(i);
                    // Validar permisos en el orden correcto
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
                    // Aplicar permisos al archivo
                    char primerCaracter = ficheros.obtenerFichero(fich).getPermisos().charAt(0);
                    permisos = primerCaracter + permisos;
                    ficheros.obtenerFichero(fich).setPermisos(permisos);
                    mensaje = "Comando ejecutado correctamente";
                    sintaxis.setForeground(Color.CYAN); // Cambiar color a celeste
                } else {
                    mensaje = "Sintaxis incorrecta: permisos inválidos";
                    sintaxis.setForeground(Color.RED); // Cambiar color a rojo
                }
                break;

            default:
                mensaje = "Sintaxis incorrecta: longitud incorrecta de permisos";
                sintaxis.setForeground(Color.RED); // Cambiar color a rojo
                break;
        }

        return mensaje;
    }
}
