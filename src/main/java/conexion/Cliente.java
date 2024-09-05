package conexion;

import gui.GestionEvaluaciones;
import gui.Registro;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Cliente {

    private String id; //Número identificador del cliente, en general la CI
    private String instruccion; //Última instruccion por enviar del cliente al servidor
    private String respuesta; //Última respuesta dada por el servidor al cliente
    private ConexionCliente conexion;

    public void establecerConexion() throws IOException {
        conexion = new ConexionCliente("127.0.0.1", 6464);
    }

    //Metodo que permite dar formato al mensaje enviado por el cliente al servidor
    public String formatearMensaje(String mensaje, String clase, String operacion) {
        return mensaje + ",;," + clase + ",;," + operacion;
    }

    //Metodo que permite a obtener el código enviado por el servidor
    public String obtenerCodigo(){
        String[] tokens = respuesta.split(",;,");
        return tokens[1];
    }
    
    //Metodo que permite obtener el mensaje eviado por el servidor
    public String obtenerMensaje(){
        String[] tokens = respuesta.split(",;,");
        return tokens[0];
    }
    
    public String getId() {
        return id;
    }
    
     public String getInstruccion() {
        return instruccion;
    }

    public String getRespuesta() {
        return respuesta;
    }
  
    public ConexionCliente getConexion() {
        return conexion;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void setConexion(ConexionCliente conexion) {
        this.conexion = conexion;
    }
 
    //Metodo que dada la instruccion a enviar por el cliente realiza el intercambio de mensajes
    public void intercambiarMensajes(String instruccion) throws IOException{
        this.establecerConexion();
        this.setInstruccion(instruccion);
        //System.out.println(instruccion);
        this.getConexion().enviarMensaje(this.instruccion);
        this.setRespuesta(this.getConexion().recibirMensaje());
    }
    
    //Metodo que dado un fragemento de la instruccion lo concatena a la instrucción a enviar
    public void concatenarMensaje(String fragmento){
        String instruccion = this.getInstruccion().concat(fragmento);
        this.setInstruccion(instruccion);
    }
    
    /**
     * Método que da formato a la pregunta de tipo multiple. El formato
     * corresponde a
     * "enunciado,,,Multiple,,,puntaje,,,op1,,,op2,,,op3,,,op4,,,respuesta".
     * @param enunciado
     * @param puntaje
     * @param op1
     * @param op2
     * @param op3
     * @param op4
     * @param respuesta
     * 
     */
    public void armarMultiple(String enunciado, String puntaje, String op1, String op2, String op3, String op4, String respuesta) {
        String pregunta = ";;;" + enunciado + ",,,Multiple,,," + puntaje + ",,," + op1 + ",,," + op2 + ",,," + op3 + ",,," + op4 + ",,," + respuesta;
        this.concatenarMensaje(pregunta);
    }

    /**
     * Método que da formato a la pregunta de tipo vf. El formato corresponde a
     * "enunciado,,,VF,,,puntaje,,,respuesta".
     * @param enunciado
     * @param puntaje
     * @param respuesta verdadero o falso.
     */
    public void armarVF(String enunciado, String puntaje, String respuesta) {
        String pregunta = ";;;" + enunciado + ",,,VF,,," + puntaje + ",,," + respuesta;
        this.concatenarMensaje(pregunta);
    }

    /**
     * Método que da formato a la pregunta de tipo espacios. EL formato
     * corresponde a "enunciado,,,Completar,,,puntaje,,,respuestas separadas por
     * coma".
     * @param enunciado
     * @param puntaje
     * @param respuestas separadas por coma.
     */
    public void armarEspacios(String enunciado, String puntaje, String respuestas) {
        String pregunta = ";;;" + enunciado + ",,,Completar,,," + puntaje + ",,," + respuestas;
        this.concatenarMensaje(pregunta);
    }

    /**
     * Método que da formato a las respuestas del estudiante, ordenadas. El
     * formato corresponde a "respuesta0;;;respuesta1;;;...;;;respuestaN".
     *
     * @param respuestas dadas por el estudiante a lo largo de la evaluación.
     * @param respuesta de la pregunta actual.
     * @return respuestas actuales.
     */
    public String prepararRespuestas(String respuestas, String respuesta) {
        String[] tokens = respuesta.split(",;,");
        tokens = tokens[0].split(";;;");
        respuestas += ";;;" + tokens[0];
        return respuestas;
    }
}
