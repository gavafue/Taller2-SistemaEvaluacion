package conexion;

import gui.AltaPregunta;
import gui.GestionEvaluaciones;
import gui.Registro;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JPanel;

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
        this.getConexion().enviarMensaje(this.instruccion);
        this.setRespuesta(this.getConexion().recibirMensaje());
    }
    
    //Metodo que dado un fragemento de la instruccion lo concatena a la instrucción a enviar
    public void concatenarMensaje(String fragmento){
        String instruccion = this.getInstruccion().concat(fragmento);
        this.setInstruccion(instruccion);
    }
    
    
    
    /*--------------MANEJO DE INTERFAZ--------------*/
    
    
    
    //El server válida el login con la instrucción "rol,;,200"
    public boolean ventanaInicial() throws FileNotFoundException, IOException {
        String rol = obtenerMensaje();
        String codigo = obtenerCodigo();
        boolean validacion = false;

        if (codigo.equals("200")) {
            switch (rol) {
                case "docente": case "estudiante":
                    GestionEvaluaciones evaluaciones = new GestionEvaluaciones(this, rol);
                    evaluaciones.setVisible(true);
                    validacion = true;
                    break;
                case "administrativo":
                    Registro registros = new Registro(this);
                    registros.setVisible(true);
                    validacion = true;
                    break;    
            }
        }
        return validacion;
    }

    //Metodo que permite cargar la vista previa de una evaluacion en la interfaz
    public void cargarEnGui(String[] pregunta, AltaPregunta framePregunta) {
        String tipo = pregunta[0];
        String enunciado = pregunta[1];
        int puntaje = Integer.parseInt(pregunta[pregunta.length - 1]);//El último token es el puntaje

        JPanel multiple, espacios;               
        framePregunta.setLocationRelativeTo(null);
        multiple = framePregunta.getPanelMultiple();
        espacios = framePregunta.getPanelRespuesta();//Este panel se utiliza si la pregutna es VF o para completar
        framePregunta.getPanelEnunciado().setVisible(false);//Solo visible al crear la pregunta
        framePregunta.setVisible(true);

        switch (tipo) {
            case "MultipleOpcion":
                framePregunta.getLblEnunciadoMultiple().setText(enunciado);//Se carga el enunciado en Label                
                framePregunta.getTxtRespuesta().setVisible(false);
                framePregunta.getCboxOpciones().setVisible(true);
                framePregunta.getCboxVerdaderoOFalso().setVisible(false);
                framePregunta.getPanel().setVisible(false);
                espacios.setVisible(false);
                multiple.setVisible(true);
                //Aparecen las opciones y el puntaje pero sin posibilidad de editar                
                framePregunta.getspnPuntajeMultiple().setEnabled(false);
                framePregunta.getspnPuntajeMultiple().setValue(puntaje);//el token 6 tiene el puntaje
                framePregunta.getTxtOpc1().setText(pregunta[2]);//las opciones son los tokens 2-5
                framePregunta.getTxtOpc2().setText(pregunta[3]);
                framePregunta.getTxtOpc3().setText(pregunta[4]);
                framePregunta.getTxtOpc4().setText(pregunta[5]);
                framePregunta.getTxtOpc1().setEnabled(false);
                framePregunta.getTxtOpc2().setEnabled(false);
                framePregunta.getTxtOpc3().setEnabled(false);
                framePregunta.getTxtOpc4().setEnabled(false);
                framePregunta.getBtnFinalizarMultiple().setText("Siguiente");
                break;
            case "VerdaderoFalso":
                framePregunta.getTxtEnunciadoVF().setText(enunciado);//Se carga el enunciado en el txtArea  
                espacios.setVisible(true);
                multiple.setVisible(false);
                framePregunta.getTxtRespuesta().setVisible(false);
                framePregunta.getPanel().setVisible(true);
                framePregunta.getCboxVerdaderoOFalso().setVisible(true);
                framePregunta.getLblTipo().setText("True/False");
                framePregunta.getspnPuntaje().setEnabled(false);
                framePregunta.getspnPuntaje().setValue(puntaje);
                framePregunta.getBtnFinalizar().setText("Siguiente");
                break;
            case "Completar":
                framePregunta.getTxtEnunciadoVF().setText(enunciado);//Se carga el enunciado en el txtArea    
                framePregunta.getCboxVerdaderoOFalso().setVisible(false);
                framePregunta.getTxtRespuesta().setVisible(true);
                espacios.setVisible(true);
                framePregunta.getPanel().setVisible(true);
                framePregunta.getspnPuntaje().setEnabled(false);
                framePregunta.getspnPuntaje().setValue(puntaje);
                framePregunta.getLblTipo().setText("Respuesta/s");
                framePregunta.getBtnFinalizar().setText("Siguiente");
                break;
        }

    }
    
    
    
}
