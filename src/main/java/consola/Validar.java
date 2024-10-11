package consola;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextPane;

/**
 * Esta clase permite validar un comando y sus opciones. A partir de esto
 * determinar a que clase ejecutora derivar.
 * 
 * También se encarga de la concatenación con pipe "|"
 *
 * @author Gabriel, Ana, Santiago, Juan y Gonzalo
 *
 */
public class Validar {

    /**
     * Comando ingresado.
     */
    private String comandoIngresado; // El comando ingresado por el usuario

    /**
     * Tokens del comando fraccionados por espacio.
     */
    private String[] tokens; // Los tokens resultantes de dividir la orden.

    /**
     * Atributos de tipo boolean para verificar si es válido el comando y si usa
     * modificadores o no.
     */
    private boolean esValido, usaModificadores;

    /**
     * Constructor que inicializa el comando a validar.
     *
     * @param comandoIngresado El comando ingresado por el usuario.
     */
    public Validar(String comandoIngresado) {
        this.comandoIngresado = comandoIngresado.stripLeading();
        this.tokens = this.comandoIngresado.split(" ");
    }

    /**
     * Obtiene el comando ingresado por el usuario.
     *
     * @return el comando ingresado por el usuario
     */
    public String getComandoIngresado() {
        return comandoIngresado;
    }

    /**
     * Establece el comando ingresado por el usuario.
     *
     * @param comandoIngresado el comando que fue ingresado por el usuario
     */
    public void setComandoIngresado(String comandoIngresado) {
        this.comandoIngresado = comandoIngresado;
    }

    /**
     * Obtiene los tokens resultantes de dividir la orden ingresada.
     *
     * @return un arreglo de strings que representa los tokens del comando
     */
    public String[] getTokens() {
        return tokens;
    }

    /**
     * Establece los tokens resultantes de dividir la orden ingresada.
     *
     * @param tokens el arreglo de strings que representa los tokens del comando
     */
    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Verifica si el comando ingresado es válido.
     *
     * @return true si el comando es válido, false en caso contrario
     */
    public boolean getEsValido() {
        return esValido;
    }

    /**
     * Establece si el comando ingresado es válido.
     *
     * @param esValido true si el comando es válido, false en caso contrario
     */
    public void setEsValido(boolean esValido) {
        this.esValido = esValido;
    }

    /**
     * Verifica si el comando ingresado utiliza modificadores.
     *
     * @return true si el comando utiliza modificadores, false en caso contrario
     */
    public boolean getUsaModificadores() {
        return usaModificadores;
    }

    /**
     * Establece si el comando ingresado utiliza modificadores.
     *
     * @param usaModificadores true si el comando utiliza modificadores, false
     * en caso contrario
     */
    public void setUsaModificadores(boolean usaModificadores) {
        this.usaModificadores = usaModificadores;
    }

    /**
     * Método que determina si el comando ingresado es válido o no. Este método
     * verifica si el comando ingresado cumple con los criterios básicos de
     * validez, tales como no comenzar con un espacio y ser un comando
     * reconocido. También verifica si las opciones proporcionadas con el
     * comando son válidas.
     *
     * @param comandos La lista de comandos disponibles.
     * @return String "200" si el comando es válido o el error concreto en caso
     * contrario.
     */
    public String validarComando(Comandos comandos) {
        String comando = tokens[0];
        String resultado = ">> Comando ingresado '"
                + comando + "' inexistente <<\n\nIntente [man] para ver los comandos disponibles\n";

        boolean existe = comandos.existeComando(comando);
        if (existe) {
            String[] opcionesValidas = comandos.obtenerOpciones(comando);
            if (opcionesValidas != null) {
                usaModificadores = true;
                resultado = validarOpciones();
            } else {
                usaModificadores = false;
                resultado = validarSintaxis();
            }
        }
        return resultado;
    }

    /**
     * Método que comienza la ejecuciòn de un comando. Dependiendo si utiliza o
     * no paràmetros se instancia un objeto u otro.
     *
     * El comando grep se ejecuta desde la clase EjecutarConModificadores para
     * facilitar la concatenaciòn con pipe.
     *
     * @param comandos posibles.
     * @param ficheros es la lista con el sistema de ficheros.
     * @param procesos en ejecucion.
     * @param salida es el JtextPane donde se muestra el resultado de la
     * ejecucion.
     * @return un String con el resultado de la ejecucion
     */
    public String comenzarEjecucion(Comandos comandos, Ficheros ficheros, Procesos procesos, JTextPane salida) {
        String resultado;
        if (usaModificadores || tokens[0].equals("grep")) {
            EjecutarConModificadores ejecutor = new EjecutarConModificadores(tokens);
            resultado = ejecutor.ejecutarComando(comandos, ficheros, procesos, salida);
        } else {
            EjecutarSinModificadores ejecutor = new EjecutarSinModificadores(tokens);
            resultado = ejecutor.ejecutarComandoSinMod(comandos, ficheros, procesos, salida);
        }
        return resultado;
    }

    /**
     * Método que determina si las opciones del comando ingresado son válidas.
     * Este método recorre las opciones del comando (tokens) y verifica si cada
     * una de ellas es una opción válida utilizando el método auxiliar
     * esOpcionValida.
     *
     * @return true si todas las opciones son válidas, false en caso contrario.
     */
    private String validarOpciones() {
        String resultado = "200";
        switch (tokens[0]) {
            case "ls":
                if (tokens.length > 1) {
                    resultado = validarOpLs();
                }
                break;
            case "head":
            case "tail":
                resultado = validarHeadTail();
                break;
            case "sort":
                resultado = validarSort();
                break;
            case "cut":
                resultado = validarCut();
                break;
        }
        return resultado;
    }

    /**
     * Método auxiliar que valida las opciones ingresadas con "-" para un
     * comando determinado
     *
     * @param comando del cual desea validar las opciones
     * @param aComprobar arreglo con las opciones ingresadas por el usuario
     * @return un String "200" sin todas las opciones con correctas o un mensaje
     * descriptivo en caso contrario
     */
    private String compararConOpcPosibles(String comando, String[] aComprobar) {
        Comandos comandos = new Comandos();
        // Arreglo con las opciones válidas del comando pasado por parámetros
        String[] opcionesValidas = comandos.obtenerOpciones(comando);
        String resultado = "200"; //Valor valido por defecto
        boolean esValida;

        for (String opcion : aComprobar) {
            esValida = Arrays.asList(opcionesValidas).contains(opcion);
            if (!esValida) {
                resultado = ">> El modificador " + opcion + " no es válido <<\n";
                //Se podría utilizar while o un break para salir del bucle, ya que alcanza con encontrar una sola inválida
                //Tambien podrían mostrarse todas las opciones incorrectas
            }
        }
        return resultado;
    }

    /**
     * Método que controla la posibilidad de colocar 2 veces la misma opcion por
     * parte del usuario Ejemplo: ls -l -l
     *
     * @return "200" si no existen opciones duplicadas o el mensaje de error
     * concreto en caso contrario
     */
    private String encontrarDuplicados() {
        boolean[] incluido = new boolean[tokens.length];
        String resultado = "200";

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].startsWith("-") && !incluido[i]) {
                for (int j = i + 1; j < tokens.length; j++) {
                    if (tokens[i].equals(tokens[j])) {
                        resultado = ">> Modificador " + tokens[i] + " duplicado <<\n";
                    }
                }
            }
        }
        return resultado;
    }

    /**
     * Metodo auxiliar que cuenta la cantidad de opciones ingresadas
     *
     * @return cantidad de elementos que comienzan con '-'
     */
    private int contarOpciones() {
        int contador = 0;
        // Recorrer el arreglo y contar los elementos que comienzan con '-'
        for (String token : tokens) {
            if (token.startsWith("-")) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Método que utiliza compararConOpcPosibles y encontrarDuplicado para
     * validar las opciones de un ls en todas sus variantes ls ls dir ls -l ||
     * ls -l dir ls -a || ls -a dir ls -l -a || ls -l -a dir ls -a -l || ls -a
     * -l dir
     *
     * @return un mensaje con el error concreto o "200" si es valido
     */
    private String validarOpLs() {
        String duplicados = encontrarDuplicados();
        // Mensaje de error por defecto
        String mensajeError = ">> Sintaxis incorrecta. Intente [man ls] <<\n";

        if (!duplicados.equals("200")) {
            // Si hay un error con los duplicados, establecer mensajeError
            mensajeError = duplicados;
        } else {
            // Crear un nuevo arreglo sin duplicados
            int tamanio = contarOpciones();
            String[] opcionesUnicas = new String[tamanio];
            int index = 0;
            // Agregar los elementos únicos al nuevo arreglo
            for (String token : tokens) {
                if (token.startsWith("-")) {
                    opcionesUnicas[index++] = token;
                }
            }
            // Validar las opciones y actualizar el mensaje de error
            mensajeError = compararConOpcPosibles("ls", opcionesUnicas);
        }
        return mensajeError;
    }

    /**
     * Método que utiliza contarOpciones y encontrarDuplicados para validar la
     * sintaxis de tail y head
     *
     * @return un mensaje con el error concreto o "200" si la sintaxis es valida
     */
    private String validarHeadTail() {
        String resultado;
        // Caso especial para una sola opción
        if (tokens.length == 1) {
            resultado = ">> Faltan opciones/parámetros <<\n";
        } else {
            // Encontrar duplicados en las opciones
            String duplicados = encontrarDuplicados();

            // Verificar el caso de duplicados
            if (!duplicados.equals("200")) {
                resultado = duplicados;
            } else {
                int tamanio = contarOpciones();
                // Verificar el caso especial para tamaño de opciones
                if (tamanio == 0 && tokens.length == 2) {
                    resultado = "200"; // Valor válido
                } else {
                    // Crear un nuevo arreglo sin duplicados
                    String[] resultadoArray = new String[tamanio];
                    int index = 0;

                    // Agregar los elementos únicos al nuevo arreglo
                    for (String token : tokens) {
                        if (token.startsWith("-")) {
                            resultadoArray[index++] = token;
                        }
                    }
                    // Comparar con opciones posibles
                    String opcValidas = compararConOpcPosibles("head", resultadoArray);
                    // Determinar el resultado final basado en el tamaño de tokens y las opciones válidas
                    if (tokens.length == 4 && opcValidas.equals("200")) {
                        resultado = "200"; // Valor válido
                    } else if (!opcValidas.equals("200")) {
                        resultado = opcValidas; // Mensaje de error de opciones válidas
                    } else {
                        resultado = ">> Sintaxis incorrecta intente man " + tokens[0] + "<<\n"; // Mensaje de error de sintaxis
                    }
                }
            }
        }
        return resultado;
    }

    /**
     * Método que utiliza contarOpciones y encontrarDuplicados para validar la
     * sintaxis de sort.
     *
     * @return un String "200" si la sintaxis de sort es correcta y el mensaje
     * de error en caso contrario.
     */
    private String validarSort() {
        String duplicados = encontrarDuplicados();
        // Mensaje de error por defecto
        String mensajeError = ">> Sintaxis incorrecta. Intente [man sort] <<\n";

        if (!duplicados.equals("200")) {
            // Si hay opciones duplicadas, las devuelve
            mensajeError = duplicados;
        } else {
            int tamanio = contarOpciones();
            if (tokens.length == 1) {
                // Si solo hay un token
                mensajeError = ">> Faltas opciones/parámetros <<\n";
            } else if (tamanio == 0 && tokens.length == 2) {
                // Si no hay opciones y tokens.length es 2 el comando puede ejecutarse
                mensajeError = "200";
            } else if (tamanio > 0 && tokens.length == 3) {
                // Si hay opciones y tokens.length es 3, validar las opciones
                String[] resultadoOpciones = new String[tamanio];
                int index = 0;
                for (String token : tokens) {
                    if (token.startsWith("-")) {
                        resultadoOpciones[index++] = token;
                    }
                }
                String opcValidas = compararConOpcPosibles("sort", resultadoOpciones);
                // Determinar el resultado final basado en el tamaño de tokens y las opciones válidas
                if (tokens.length == 3 && opcValidas.equals("200")) {
                    mensajeError = "200"; // Valor válido
                } else if (!opcValidas.equals("200")) {
                    mensajeError = opcValidas; // Mensaje de error de opciones válidas
                } else {
                    mensajeError = ">> Sintaxis incorrecta intente man " + tokens[0] + "<<\n"; // Mensaje de error de sintaxis
                }
            }
        }
        return mensajeError;
    }

    /**
     * Método que utiliza contarOpciones y encontrarDuplicados para validar la
     * sintaxis cut.
     *
     * @return si la sintaxis de cut es correcta devuelve el String "200", en
     * caso contrario el mensaje de error
     */
    private String validarCut() {
        String duplicados = encontrarDuplicados();
        // Inicializar el mensaje de error por defecto
        String mensajeError = ">> Sintaxis incorrecta. Intente [man cut] <<\n";
        // Si hay opciones duplicadas, las devuelve
        if (!duplicados.equals("200")) {
            mensajeError = duplicados;
        } else {
            int tamanio = contarOpciones();
            String[] resultadoOpciones = new String[tamanio];
            int index = 0;
            for (String token : tokens) {
                if (token.startsWith("-")) {
                    resultadoOpciones[index++] = token;
                }
            }
            String opValidas = compararConOpcPosibles("cut", resultadoOpciones);
            if (tokens.length == 6 && opValidas.equals("200") && tokens[1].equals("-d")) {
                mensajeError = "200"; // Valor válido
            } else if (!opValidas.equals("200")) {
                mensajeError = opValidas; // Mensaje de error de opciones válidas
            } else if (tokens.length != 6) {
                mensajeError = ">> Cantidad de opciones incorrecta para este comando <<\n";
            } else {
                mensajeError = ">> Sintaxis incorrecta intente man " + tokens[0] + "<<\n"; // Mensaje de error de sintaxis
            }
        }
        return mensajeError;
    }

    /**
     * Método que busca la existencia del caracter pipe y devuelve su posición
     *
     * @return la poscion en la que se encuentra el pipe.
     */
    public int posicionPipe() {
        int indicePipe = 0;
        // Creo un arraylist temporal para usar su metodo
        // contains(). Esto es mas corto que un for y el codigo mas legible          
        ArrayList<String> tokensParaElPipe = new ArrayList<>(Arrays.asList(tokens));
        if (tokensParaElPipe.contains("|")) {
            indicePipe = tokensParaElPipe.indexOf("|");
        }
        return indicePipe;
    }

    /**
     * Método que determina si el comando ingresado tiene pipe.
     *
     * @return true si tiene pipe y false en caso contrario.
     */
    public boolean tienePipe() {
        return this.posicionPipe() != 0;
    }

    /**
     * Método que determina si el primer comando del pipe es válido o no. En
     * caso de serlo, genera un fichero auxiliar para almacenar el resultado y
     * llama al método concatenarComando
     *
     * @param listaFicheros ficheros actuales.
     * @return resultado de la concatenacion de comandos.
     */
    public String validarPipe(Ficheros listaFicheros, Procesos listaProcesos) {
        String mensaje = "";
        String msjComando1 = "";
        int indexPipe = this.posicionPipe();
        boolean esValido;
        String[] tokensA = Arrays.copyOfRange(tokens, 0, indexPipe); // antes del pipe. indexPipe queda afuera
        String[] tokensB = Arrays.copyOfRange(tokens, indexPipe + 1, tokens.length);
        tokens = tokensA;
        System.out.println(tokens);
        
        System.out.println("TokensA=" + tokens.length);
        EjecutarConModificadores ejecutar = new EjecutarConModificadores(tokens);
        EjecutarSinModificadores ejecutarSin = new EjecutarSinModificadores(tokens);
        
        switch (tokens[0]) {
            case "tail":
                esValido = validarOpciones().equals("200");
                msjComando1 = ejecutar.ejecutarTail(listaFicheros);
                System.out.println("esValido:" + esValido + "//" + msjComando1);
                break;
            case "head":
                esValido = validarOpciones().equals("200");
                msjComando1 = ejecutar.ejecutarHead(listaFicheros);
                System.out.println("esValido:" + esValido + "//" + msjComando1);
                break;
            case "ps":
                esValido = validarSintaxis().equals("200");
                msjComando1 = ejecutarSin.ejecutarPs(listaProcesos);
                System.out.println("esValido:" + esValido + "//" + msjComando1);
                break;
            case "grep":
                String mensajeSinFiltrar = ejecutar.ejecutarGrep(listaFicheros);
                esValido = validarSintaxis().equals("200");
                String[] lineas = mensajeSinFiltrar.split("\\n");
                /*Si el grep encuentra coincidencia aparece al principio "-Coincidencias-\n"
                         Se elimina la primera linea antes de concatenar la segunda busqueda*/
                String[] lineasSinPrimeras = Arrays.copyOfRange(lineas, 1, lineas.length);
                msjComando1 = String.join("\n", lineasSinPrimeras);
                System.out.println("esValido:" + esValido + "//" + msjComando1);
                break;
            default:
                mensaje = ">> El primer comando a concatenar debe ser head|tail|grep|ps|cat <<\n";
                esValido = false;
                break;
        }
        //Si msjComnado1 no esta vacio el comando se ejecuto correctamnte
        if (!msjComando1.isEmpty() && esValido && !msjComando1.startsWith(">> La expresión")) {
            // creo un archivo temporal con el resultado del primer comando               
            listaFicheros.agregarFichero(new Archivo("especificado", msjComando1));
            //llamo a método auxiliar para concatenar el segundocomando
            mensaje = concatenarComando(tokensB, listaFicheros, ejecutar);
            listaFicheros.eliminarFichero("especificado");
        } else if (!esValido) {
            mensaje = ">> El comando " + tokens[0] + " tiene un problema de sintaxis <<\n";
        } else {
            mensaje = ">> No hay resultados <<\n";//Puede que el primer comando sea correcto pero que no encuentre coincidencias
        }
        return mensaje;
    }

    /**
     * Metodo auxiliar que utiliza el resultado de la ejecucion de un comando
     * como entrada.
     *
     * @param tokensB nuevos tokens que contienen el fichero auxiliar con los
     * resultados del comando anterior.
     * @param listaFicheros lista de ficheros actuales.
     * @param ejecutar para realizar la segunda ejecucion.
     * @return
     */
    private String concatenarComando(String[] tokensB, Ficheros listaFicheros, EjecutarConModificadores ejecutar) {
        String mensaje = "";
        String[] tokensSegundoComando;

        // Verificar que tokensB tenga al menos un elemento
        if (tokensB.length >= 1) {
            String comando = tokensB[0];
            System.out.println("SEGUNDO PIPE: " + comando);
            // Validar que el comando sea uno de los permitidos
            if (comando.equals("tail") || comando.equals("head") || comando.equals("grep")) {
                // Determinar la longitud válida de tokensB según el comando
                boolean longitudValida;
                if (comando.equals("tail") || comando.equals("head")) {
                    // tail/head con y sin -n 
                    longitudValida = (tokensB.length == 1 || tokensB.length == 3);
                } else {
                    // Para grep longitud debe ser 2
                    longitudValida = (tokensB.length == 2);
                }
                if (longitudValida) {
                    // Crear tokensSegundoComando copiando tokensB y agregando "especificado" al final
                    tokensSegundoComando = Arrays.copyOf(tokensB, tokensB.length + 1);
                    tokensSegundoComando[tokensB.length] = "especificado";

                    // Actualizar tokens y el ejecutor
                    tokens = tokensSegundoComando;
                    System.out.println(tokens);
                    ejecutar.setTokens(tokensSegundoComando);
                    // Validar opciones o sintaxis según el comando
                    boolean validacionExitosa;
                    String mensajeValidacion;
                    if (comando.equals("tail") || comando.equals("head")) {
                        mensajeValidacion = validarOpciones();
                        validacionExitosa = mensajeValidacion.equals("200");
                    } else {
                        // Comando es "grep"
                        mensajeValidacion = validarSintaxis();
                        validacionExitosa = mensajeValidacion.equals("200");
                    }

                    if (validacionExitosa) {
                        // Ejecutar el comando correspondiente
                        switch (comando) {
                            case "tail":
                                mensaje = ejecutar.ejecutarTail(listaFicheros);
                                break;
                            case "head":
                                mensaje = ejecutar.ejecutarHead(listaFicheros);
                                break;
                            case "grep":
                                mensaje = ejecutar.ejecutarGrep(listaFicheros);
                                break;
                        }
                    } else {
                        // Si la validación falla, obtener el mensaje de error
                        mensaje = mensajeValidacion;
                    }
                } else {
                    // Si la longitud de tokensB no es la esperada
                    mensaje = ">> Cantidad de parámetros/opciones incorrectas <<\n";
                }
            } else {
                // Comando no reconocido
                mensaje = ">> Comando no reconocido <<\n";
            }
        } else {
            // Si tokensB está vacío
            mensaje = ">> Indique el comando a concatenar luego de '|' <<\n";
        }
        return mensaje;
    }

    /**
     * Método para validar la sintaxis de los comandos que no utilizan
     * modificadores "-"
     *
     *
     * @return la validez de la sintaxis en base a la cantidad de tokens
     * esperados.
     *
     * "200" se interpreta como sintaxis válida.
     */
    private String validarSintaxis() {
        boolean sintaxisCorrecta = false;
        String mensaje;

        switch (tokens[0]) {
            case "chmod":
                sintaxisCorrecta = (tokens.length == 3);
                break;
            case "cp":
                sintaxisCorrecta = false;//Futura implementacion
                break;
            case "mv":
                sintaxisCorrecta = (tokens.length == 3);
                break;
            case "mkdir":
                sintaxisCorrecta = (tokens.length == 2);
                break;
            case "grep":
                sintaxisCorrecta = (tokens.length == 3);
                break;
            case "kill":
                sintaxisCorrecta = (tokens.length == 2);
                break;
            case "cat":
                sintaxisCorrecta = (tokens.length == 2);
                break;
            case "man":
                sintaxisCorrecta = (tokens.length == 1) || (tokens.length == 2);
                break;
            case "rmdir":
                sintaxisCorrecta = (tokens.length == 2);
                break;
            case "clear":
                sintaxisCorrecta = (tokens.length == 1);
                break;
            case "ps":
                sintaxisCorrecta = (tokens.length == 1);
                break;
        }
        if (sintaxisCorrecta) {
            mensaje = "200";
        } else {
            mensaje = ">> Sintaxis incorrecta intente man " + tokens[0] + "<<\n";
        }
        esValido = sintaxisCorrecta;
        return mensaje;
    }
}
