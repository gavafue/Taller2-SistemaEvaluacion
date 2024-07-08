package logica;

/**
 * Esta clase permite validar un comando y sus opciones.
 * 
 * @since version 1
 */
public class Validar {

    private String comandoIngresado; // El comando ingresado por el usuario
    private String[] tokens; // Los tokens resultantes de dividir el comando

    /**
     * Constructor que inicializa el comando a validar.
     * 
     * @param comandoIngresado El comando ingresado por el usuario.
     */
    public Validar(String comandoIngresado) {
        this.comandoIngresado = comandoIngresado;
        this.tokens = comandoIngresado.split(" ");
    }

    // Getters
    public String getComandoIngresado() {
        return comandoIngresado;
    }

    public String[] getTokens() {
        return tokens;
    }

    // Setters
    public void setComandoIngresado(String input) {
        this.comandoIngresado = input;
        this.tokens = input.split(" ");
    }

    /**
     * Método que determina si el comando ingresado es válido o no.
     * Este método verifica si el comando ingresado cumple con los criterios básicos
     * de validez, tales como no comenzar con un espacio y ser un comando reconocido.
     * También verifica si las opciones proporcionadas con el comando son válidas.
     * 
     * @param comandos La lista de comandos disponibles.
     * @return true si el comando es válido, false en caso contrario.
     */
    public boolean validarComando(Comandos comandos) {
        if (comandoComienzaConEspacio()) {
            return false;
        }
        
        String comando = tokens[0];
        if (!comandos.existeComando(comando)) {
            return false;
        }

        String[] opcionesValidas = comandos.obtenerOpciones(comando);
        if (opcionesValidas == null) {
            return true; // El comando no tiene opciones adicionales para validar
        }

        return validarOpciones(opcionesValidas);
    }

    /**
     * Método auxiliar que verifica si el comando está malformado.
     * Este método comprueba si el comando ingresado comienza con un espacio.
     * 
     * @return true si el comando es inválido por comenzar con un espacio, false en caso contrario.
     */
    private boolean comandoComienzaConEspacio() {
        return comandoIngresado.length() > 0 && comandoIngresado.charAt(0) == ' ';
    }

    /**
     * Método que determina si las opciones del comando ingresado son válidas.
     * Este método recorre las opciones del comando (tokens) y verifica si cada una
     * de ellas es una opción válida utilizando el método auxiliar esOpcionValida.
     * 
     * @param opcionesValidas Las opciones válidas para el comando.
     * @return true si todas las opciones son válidas, false en caso contrario.
     */
    private boolean validarOpciones(String[] opcionesValidas) {
        for (int i = 1; i < tokens.length; i++) {
            if (!esOpcionValida(tokens[i], opcionesValidas)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método auxiliar que verifica si una opción es válida.
     * Este método compara una opción específica (token) con la lista de opciones
     * válidas para determinar si es aceptable.
     * 
     * @param opcion La opción a validar.
     * @param opcionesValidas Las opciones válidas para el comando.
     * @return true si la opción es válida, false en caso contrario.
     */
    private boolean esOpcionValida(String opcion, String[] opcionesValidas) {
        for (String valida : opcionesValidas) {
            if (opcion.equals(valida)) {
                return true;
            }
        }
        return false;
    }
}
