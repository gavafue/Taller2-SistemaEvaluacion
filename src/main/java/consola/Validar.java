package consola;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextPane;

/**
 * Esta clase permite validar un comando y sus opciones
 *
 */
public class Validar {

    private String comandoIngresado; // El comando ingresado por el usuario
    private String[] tokens; // Los tokens resultantes de dividir la orden.
    private boolean esValido, usaModificadores;
    
    /**
     * Constructor que inicializa el comando a validar.
     *
     * @param comandoIngresado El comando ingresado por el usuario.
     */
    public Validar(String comandoIngresado) {
        this.comandoIngresado = comandoIngresado.stripLeading(); //Agregando este strip, carece de sentido el verificar si comienza con espacio. Se boora el metodo para ello.
        this.tokens = this.comandoIngresado.split(" ");
    }

    /**
     * @return comandoIngresado
     */
    public String getComandoIngresado() {
        return comandoIngresado;
    }

    /**
     * @return tokens
     */
    public String[] getTokens() {
        return tokens;
    }

    /**
     * Establece el valor de comandoIngresado y tokens a partir del
     *
     * @param input de la linea de la terminal.
     */
    public void setComandoIngresado(String input) {
        this.comandoIngresado = input.stripLeading();
        this.tokens = input.stripLeading().split(" ");
    }

    /**
     * Método que determina si el comando ingresado es válido o no. Este método
     * verifica si el comando ingresado cumple con los criterios básicos de
     * validez, tales como no comenzar con un espacio y ser un comando
     * reconocido. También verifica si las opciones proporcionadas con el
     * comando son válidas.
     *
     * @param comandos La lista de comandos disponibles.
     * @return true si el comando es válido, false en caso contrario.
     */
    public boolean validarComando(Comandos comandos){           
        String comando = tokens[0];
        boolean existe= comandos.existeComando(comando);
        boolean esCorrecto=false;
        
        if (existe) {
            String[] opcionesValidas = comandos.obtenerOpciones(comando);
            
            //Se validan los comandos que permiten opciones
            if (opcionesValidas != null) {
                usaModificadores=true;
                esCorrecto = validarOpciones();
            }else {
                esCorrecto=validarSintaxis();//Se validan comandos sin opciones
                usaModificadores=false;
            }
        }
        return esCorrecto;//no vale
    }
    
    /**
     * 
     *Método que comienza la ejecuciòn de un comando.
     *Dependiendo si utiliza o no paràmetros se instancia un objeto u otro.
     *
     * El comando grep se ejecuta desde la clase EjecutarConModificadores para 
     * facilitar la concatenaciòn con pipe.
     * 
     * @param comandos posibles.  
     * @param ficheros es la lista con el sistema de ficheros.
     * @param procesos en ejecucion.
     * @param salida es el JtextPane donde se muestra el resultado de la ejecucion.
     * @return un String con el resultado de la ejecucion 
     */
    public String comenzarEjecucion(Comandos comandos, Ficheros ficheros, Procesos procesos, JTextPane salida){
        String resultado;
        //Grep se ejecuta desde la clase EjecutarConModificadores, para poder concatenarlo mas facilmente
        if (usaModificadores||tokens[0].equals("grep")) {        
            EjecutarConModificadores ejecutor = new EjecutarConModificadores(tokens);
            resultado=ejecutor.ejecutarComando(comandos, ficheros, procesos, salida);
        } else{
            EjecutarSinModificadores ejecutor = new EjecutarSinModificadores(tokens);
            resultado=ejecutor.ejecutarComandoSinMod(comandos, ficheros, procesos, salida);  
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
    private boolean validarOpciones() {
        
        boolean todasValidas = false;
        
            switch (tokens[0]){                    

                case "ls":
                            if(tokens.length==1){
                                todasValidas=true;
                            } else {
                                todasValidas=validarOpLs(); 
                            }
                            break;
                case "head":
                            todasValidas=validarHeadTail();
                            break;
                case "tail":
                            todasValidas=validarHeadTail();
                            break;
                case "sort":
                            todasValidas=validarSort();
                            break;
                case "cut":
                            todasValidas=validarCut();                            
                            break;

            }
            esValido=todasValidas;
            return todasValidas;
        }
        
    
    /**
     * Metodo auxiliar que valida las opciones ingresadas con "-" para un comando determinado
     * 
     * @param comando del cual desea validar las opciones
     * @param aComprobar arreglo con las opciones ingresadas por el usuario
     * @return un booleano indicando si todas las opciones son validas para ese comando 
     */        
    private boolean compararConOpcPosibles (String comando,String[]aComprobar) {
        Comandos comandos = new Comandos();
        //arreglo con las opciones validas del comando pasado por parametros
        String [] opcionesValidas = comandos.obtenerOpciones(comando);

         for (String opcion : aComprobar) {
                if (!Arrays.asList(opcionesValidas).contains(opcion)) {
                    return false; // Si algún elemento no está, devuelve false
                }
            }
            return true; // Si todos los elementos están, devuelven true
    }
    
    /**
     * Metodo que controla la posibilidad de colocar 2 veces la misma opcion por parte del usuario
     * Ejemplo: ls -l -l //NO ES VALIDO
     * @return si se han encontrado o no opciones duplicadas 
     */
    private boolean encontrarDuplicados(){
        // Variable para verificar si hay duplicados
        boolean duplicadoEncontrado = false;        
        // Creo un arreglo booleano para marcar duplicados
        boolean[] yaIncluido = new boolean[tokens.length];
        // Recorrer el arreglo para contar cuántos elementos únicos empiezan con '-'
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].startsWith("-") && !yaIncluido[i]) {
                // Marcar este elemento y buscar duplicados
                for (int j = i + 1; j < tokens.length; j++) {
                    if (tokens[i].equals(tokens[j])) {
                        duplicadoEncontrado = true;
                        yaIncluido[j] = true; // Marcar duplicados
                        System.out.println("Duplicado encontrado: " + tokens[i]);
                    }
                }                
            }            
        }
        return duplicadoEncontrado;
    }
    
    /**
     * Metodo auxiliar que cuenta la cantidad de opciones ingresadas
     * @return cantidad de elementos que comienzan con '-' 
     */   
    private int contarOpciones(){    
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
      * Metodo que utiliza compararConOpcPosibles y encontrarDuplicado para validar
      * las opciones de un ls en todas sus variantes
      * ls
      * ls dir
      * ls -l || ls -l dir       
      * ls -a || ls -a dir
      * ls -l -a || ls -l -a dir
      * ls -a -l || ls -a -l dir
      * 
      * @return si las opciones utilizadas con ls son validas
      */ 
    private boolean validarOpLs() {        
        boolean todasValidas=false;
        boolean hayDuplicados=encontrarDuplicados();        
        
        if (!hayDuplicados&&(tokens.length>=2&&tokens.length<=4)){
            // Crear un nuevo arreglo sin duplicados
            int tamanio= contarOpciones();
            String[] resultado = new String [tamanio];
            int index = 0;
            // Agregar los elementos únicos al nuevo arreglo
            for (String token : tokens) {
                if (token.startsWith("-")) {
                    resultado[index++] = token;
                }
            }
            todasValidas=compararConOpcPosibles("ls",resultado);                 
        } else {
            todasValidas=false;                 
        }
        return todasValidas;
    }
    
    /**
     * Metodo que utiliza contarOpciones y encontrarDuplicados
     * para validar la sintaxis de tail y head
     * 
     * @return si las opciones utilizadas con tail o head son validas
     */
    private boolean validarHeadTail(){        
        boolean valido;
        boolean hayDuplicados=encontrarDuplicados();
        int tamanio= contarOpciones();
        
        if(tamanio==0&&tokens.length==2){
            valido=true;
        }else{
            valido = !hayDuplicados&&tamanio==1&&tokens[1].equals("-n")&&tokens.length==4;
        }
        return valido;
        
    }

    /**
     * Metodo que utiliza contarOpciones y encontrarDuplicados
     * para validar la sintaxis de sort
     * 
     * @return si la sintaxis de sort es correcta
     */
    private boolean validarSort(){
        boolean valido;
        boolean hayDuplicados=encontrarDuplicados();
        int tamanio=contarOpciones();
        
        if(tamanio==0&&tokens.length==2){
            valido=true;
        } else {
            valido=!hayDuplicados&&tokens.length == 3 && tokens[1].equals("-n");
        }
        return valido;    
    }
    
     /**
     * Metodo que utiliza contarOpciones y encontrarDuplicados
     * para validar la sintaxis cut
     * 
     * @return si la sintaxis de cut es correcta
     */    
    private boolean validarCut(){        
        boolean valido;
        boolean hayDuplicados=encontrarDuplicados();
        int tamanio=contarOpciones();
        if (tokens.length!=6){
            valido =false;
        } else {        
        valido = !hayDuplicados && tamanio==2 && tokens[0].equals("cut") && tokens[1].equals("-d") && tokens[3].equals("-f");
        }
        return valido;
    }
     
     /**
     * Método que busca la existencia del caracter pipe y devuelve su posición
     *
     * @return la poscion en la que se encuentra el pipe
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
     * Metodo para validar la sintaxis de los comandos que no utilizan modificadores.     
     *       
     * @return la validez de la sintaxis en base a la cantidad de tokens esperados
     */    
    private boolean validarSintaxis() {
        boolean sintaxisCorrecta = false;
        
        switch (tokens[0]){                    

            case "chmod":
                        sintaxisCorrecta=(tokens.length==3);
                        break;
            case "cp":
                        sintaxisCorrecta=false;//Futura implementacion
                        break;
            case "mv":
                        sintaxisCorrecta=(tokens.length==3);
                        break;
            case "mkdir":
                        sintaxisCorrecta=(tokens.length==2);
                        break;
            case "grep":
                        sintaxisCorrecta=(tokens.length==3);                           
                        break;
            case "kill":
                        sintaxisCorrecta=(tokens.length==2);                           
                        break;
            case "cat":
                        sintaxisCorrecta=(tokens.length==2);                           
                        break;
            case "man":
                        sintaxisCorrecta=(tokens.length==1)||(tokens.length==2);                            
                        break;
            case "rmdir":
                        sintaxisCorrecta=(tokens.length==2);
                        break;
            case "clear":
                        sintaxisCorrecta=(tokens.length==1);
                        break;
            case "ps":
                        sintaxisCorrecta=(tokens.length==1);
                        break;

        }
        esValido=sintaxisCorrecta;
        return sintaxisCorrecta;
    }

    
    //GetterySetter
    public boolean isEsValido() {
        return esValido;
    }

    public void setEsValido(boolean esValido) {
        this.esValido = esValido;
    }

    public boolean isUsaModificadores() {
        return usaModificadores;
    }

    public void setUsaModificadores(boolean usaModificadores) {
        this.usaModificadores = usaModificadores;
    }
    
    
    
  
}

