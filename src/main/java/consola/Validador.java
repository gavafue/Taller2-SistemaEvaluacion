package consola;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
/**
 * Esta clase permite validar un comando y sus extensiones.
 *
 * @author Gabriel, Anna, Santiago, Juan y Gonzalo
 * @since version 1
 */
public class Validador {    
    /**
     * String a validar. Es el input del usuario en la consola.
     */
    private String cadena;

    /**
     * Arreglo que contiene a cadena tokenizado.
     */
    private String[] tokens;
    private DateTimeFormatter formatoFecha;
    private String hora;

    /**
     * Constructor comun.
     * @param cadena para tokenizar y validar
     */
    public Validador(String cadena) {
        this.cadena = cadena;
        tokens = cadena.split(" "); //Tokenización de la cadena en funcion de espacios
        formatoFecha = DateTimeFormatter.ofPattern("HH:mm:ss");//formato para mostrar la variable hora en cada ejecucion
    }

    /**
     * 
     * @return el atributo cadena 
     */
    public String getCadena() {
        return cadena;//Constructor comun
    }
    
    /**
     * 
     * @return el arreglo de tokens
     */
    public String[] getTokens() {
        return tokens;
    } 
    
    /**
     * 
     * @return hora actual 
     */
    private String getHora() {
        return hora;
    }

    /**
     * 
     * @param cadena para validar 
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
    
    /**
     * 
     * @param tokens para validar comando y parametros 
     */

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }
     
    /**
     * 
     * @param hora actual 
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Metodo que permite actualizar la variable hora en funcion de la hora del
     * sistema.
     */
    private void actualizarHora() {
        hora = LocalDateTime.now().format(formatoFecha);
    }

    /**
     * Metodo que determina si el comando ingresado es valido o no.
     *
     * @param comandos posibles
     * @return si lo almacenado en cadena no comienza por espacios y el primer token es un comando valido
     */
    public boolean validarComando(Comandos comandos) {
        String conEspacio = "^\\s.*"; //Expresion regular que busca un espacio al principio del string
        boolean valido = false;
        if ((!cadena.matches(conEspacio)) && (comandos.existeComando(tokens[0]))) { //Si no tiene espacio al comienzo y existe el comando encontrado en el token 0.
            if ((comandos.obtenerOpciones(tokens[0])) == null) { //Si no cuenta con extensiones
                valido = true;
            } else {
                valido = comprobarOpciones(comandos);
            }
        }
        return valido;
    }

    /**
     * Metodo que determina si las opciones o flags del comando ingresado son validas
     * o no.
     *
     * @param comandos validos
     * @return si las opciones son validas o no
     */
    private boolean comprobarOpciones(Comandos comandos) {
        String[] validas = comandos.obtenerOpciones(tokens[0]); //Arreglo que contiene las extensiones validas del comando
        int i = 1; //Comienzo en la segunda posicion, porque el token 0 es el comando
        boolean todasValidas = true;
        while ((i < tokens.length) && (todasValidas)) {
            boolean encontrado = false;
            int j = 0;
            while ((j < validas.length) && (!encontrado)) {
                if (tokens[i].equals(validas[j])) { //Si es valido
                    encontrado = true;
                }
                j++;
            }
            //Si el token actual no coincide con ninguna opcion valida
            if (encontrado == false) {
                todasValidas = false;
            }
            i++;
        }
        return todasValidas;
    }

    /**
     * Metodo que permite obtener ayuda sobre un comando en especifico. Utiliado
     * por el comando man.
     *
     * @param comandos validos
     * @return mensaje de ayuda y ejemplo de uso para ese comando
     */
    public String obtenerAyudaComando(Comandos comandos) {
        String mensaje = "";
        try {
            mensaje = "[AYUDA]\n\n" + comandos.obtenerDescripcion(tokens[1]) + "\n" + comandos.obtenerEjemplo(tokens[1]);
        } catch (Exception e) {
            mensaje = "No hay informacion disponible";
        }
        return mensaje;
    }  
}
