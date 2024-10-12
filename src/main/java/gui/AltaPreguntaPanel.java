package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import conexion.Cliente;
import java.awt.Color;
import java.awt.Font;

/**
 * JFrame utilizado para hacer altas de preguntas como docentes, y completar las
 * preguntas como estudiantes. Admite preguntas de multiple opción, verdadero o
 * falso y completas espacios en blanco.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class AltaPreguntaPanel extends javax.swing.JPanel {

    /**
     * Cliente actual en el sistema.
     */
    private Cliente cliente;

    /**
     * Panel de vista previa para manejo de interfaz.
     */
    private JPanel vistaPrevia; // Atributo para poder intercambiar datos con la vista previa de la evaluación
    // de forma dinámica

    /**
     * Rol del cliente actual.
     */
    private String rol;

    /**
     * Título de la evaluación actual.
     */
    private String evaluacion;

    /**
     * Pregunta actual en formato String.
     */
    private String pregunta;

    /**
     * Enunciado de la pregunta actual.
     */
    private String enunciado;

    /**
     * Tipo de pregunta de la pregunta actual.
     */
    private String tipoPregunta;

    /**
     * Respuestas dadas hasta el momento por el estudiante.
     */
    private String respuestas; // Respuestas del estudiante a las preguntas de la evaluación

    /**
     * Cantidad de preguntas creadas por el docente, o respondidas por el
     * estudiante. El uso cambia según el rol.
     */
    private static int cantidadPreguntas; // Atributo propio de a clase y no de la instancia

    /**
     * Panel de contenido para manejo de interfaz.
     */
    private JPanel panelContentDashboard;

    /**
     * JFrame AltaEvaluacionPanel para manejo de interfaz dinámico.
     */
    private AltaEvaluacionPanel panelContentVistaPrevia;

    /**
     * Constructor que permite crear una instancia de la clase a partir del
     * cliente actual y el JPanel vista previa de la evaluación.
     *
     * @param vistaPrevia             permite visualizar los cambios en la
     *                                evaluación de
     *                                forma dinámica.
     * @param cliente                 cliente actual.
     * @param rol                     rol del cliente actual.
     * @param evaluacion              título de la evaluación.
     * @param panelContentDashboard   panel de contenido.
     * @param panelContentVistaPrevia panel de vista previa.
     */
    public AltaPreguntaPanel(JPanel vistaPrevia, Cliente cliente, String rol, String evaluacion,
            JPanel panelContentDashboard,
            AltaEvaluacionPanel panelContentVistaPrevia) {
        this.cliente = cliente;
        this.vistaPrevia = vistaPrevia;
        this.rol = rol;
        this.evaluacion = evaluacion;
        initComponents();
        valoresPorDefecto();
        this.agregarMensajesAyuda(); // Carga en interfaz mensajes de ayuda
        this.interfazPorDefecto(); // Inicializa la interfaz
        this.panelContentDashboard = panelContentDashboard;
        this.panelContentVistaPrevia = panelContentVistaPrevia;

    }

    /**
     * Método que permite obtener el cliente actual.
     *
     * @return el cliente actual.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Método que permite obtener el JPanel vista previa de la evaluación.
     *
     * @return el JPanel de vista previa
     */
    public JPanel getVistaPrevia() {
        return vistaPrevia;
    }

    /**
     * Método que permite obtener el rol del cliente actual.
     *
     * @return rol del cliente actual
     */
    public String getRol() {
        return rol;
    }

    /**
     * Método que permite obtener el título de la evaluación actual.
     *
     * @return título de la evaluación actual.
     */
    public String getEvaluacion() {
        return evaluacion;
    }

    /**
     * Método que permite obtener el enunciado de la pregunta.
     *
     * @return el enunciado de la pregunta.
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * Método que permite obtener la pregunta actual.
     *
     * @return la pregunta actual.
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Método que permite obtener el tipo de pregunta.
     *
     * @return el tipo de pregunta.
     */
    public String getTipoPregunta() {
        return tipoPregunta;
    }

    /**
     * Método que permite obtener las respuestas del estudiante.
     *
     * @return las respuestas del estudiante a la evaluación.
     */
    public String getRespuestas() {
        return respuestas;
    }

    /**
     * Método que permite obtener la cantidad de preguntas.
     *
     * @return la cantidad de preguntas.
     */
    public static int getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    /**
     * Método que permite establecer el título de la evaluación en interfaz
     * gráfica.
     *
     * @param titulo de la evaluación actual.
     */
    public void setNombreEvaluacion(String titulo) {
        txtNombreEvaluacion.setText(titulo);
    }

    /**
     * Método que permite modificar el cliente actual, a partir de otro cliente.
     *
     * @param cliente el cliente a asignar.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que permite modificar el JPanel actual, a partir de otro JPanel.
     *
     * @param vistaPrevia el JPanel de vista previa a asignar.
     */
    public void setVistaPrevia(JPanel vistaPrevia) {
        this.vistaPrevia = vistaPrevia;
    }

    /**
     * Método que permite modificar el rol de cliente actual, a partir de otro
     * rol.
     *
     * @param rol del cliente actual.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Método que permite modificar el título de evaluación actual, a partir de
     * otro título.
     *
     * @param evaluacion la evaluación a asignar.
     */
    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     * Método que permite modificar la pregunta actual, a partir de otra
     * pregunta.
     *
     * @param pregunta la pregunta a asignar.
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Método que permite modificar el enunciado de la pregunta actual, a partir
     * de otro enunciado.
     *
     * @param enunciado el enunciado a asignar.
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    /**
     * Método que permite modificar el tipo de pregunta actual, a partir de otro
     * tipo.
     *
     * @param tipoPregunta el tipo de pregunta a asignar
     */
    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    /**
     * Método que permite modificar las respuestas del estudiante, a partir de
     * otras respuestas.
     *
     * @param respuestas las respuestas a asignar
     */
    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }

    /**
     * Método que permite modificar la cantidad de preguntas actual, a partir de
     * otra cantidad.
     *
     * @param cantidadPreguntas la cantidad de preguntas a asignar
     */
    public static void setCantidadPreguntas(int cantidadPreguntas) {
        AltaPreguntaPanel.cantidadPreguntas = cantidadPreguntas;
    }

    /**
     * Método que devuelve true si el texto contiene mas de una coma "," false
     * en caso contrario
     *
     * @param texto a procesar
     * @return
     */
    private boolean muchasRespuestas(String texto) {
        String[] respuestas = texto.split(",");
        return (respuestas.length > 2 || texto.endsWith(","));
    }

    /**
     * Método que pone los elementos de la interfaz en valores por defecto. Abre
     * el panel enunciado que permite escribir el enunciado de la pregunta y
     * seleccionar el tipo.
     */
    private void interfazPorDefecto() {
        panelEnunciado.setVisible(true);
        panelMultiple.setVisible(false);
        panelEspaciosVF.setVisible(false); // JPanel utilizado tanto para preguntas de completar espacios como VF
        // Los carteles de ayuda solo son para el docente
        if (this.getRol().equals("docente")) {
            this.lblAyudaEnunciado.setVisible(true);
            this.lblAyudaMultiple.setVisible(true);
            this.lblAyudaEspaciosVF.setVisible(true);
        } else {
            this.lblAyudaEnunciado.setVisible(false);
            this.lblAyudaMultiple.setVisible(false);
            this.lblAyudaEspaciosVF.setVisible(false);
        }
        this.txtEnunciadoEspaciosVF.setEditable(false);
        this.setNombreEvaluacion(this.getEvaluacion());
    }

    /**
     * Método que pone los elementos de la interfaz al crear una pregunta
     * multiple opción.
     */
    public void interfazMultiple() {
        lblEnunciadoMultiple.setText(enunciado);
        panelMultiple.setVisible(true);
        this.lblEnunciadoMultiple.setToolTipText(enunciado);
    }

    /**
     * Método que pone los elementos de la interfaz al crear una pregunta
     * espacios en blanco.
     */
    public void interfazEspacios() {
        txtEnunciadoEspaciosVF.setText(enunciado);
        lblTipo.setForeground(Color.white);
        panelEspaciosVF.setBackground(new Color(255, 255, 255));
        panelRespuestaEspaciosVF.setBackground(new Color(0, 0, 51));
        panelEspaciosVF.setVisible(true);
        txtRespuestaEspacios.setVisible(true);
        cboxVerdaderoOFalso.setVisible(false);
        this.txtEnunciadoEspaciosVF.setToolTipText(enunciado);
    }

    /**
     * Método que pone los elementos de la interfaz al crear una pregunta
     * verdadero o falso.
     */
    public void interfazVF() {
        txtEnunciadoEspaciosVF.setText(enunciado);
        panelEspaciosVF.setVisible(true);
        txtRespuestaEspacios.setText("a");
        txtRespuestaEspacios.setVisible(false);
        cboxVerdaderoOFalso.setVisible(true);
        this.txtEnunciadoEspaciosVF.setToolTipText(enunciado);
    }

    /**
     * Método encargado de dar funcionamiento al botón "Siguiente" del JPanel
     * enunciado, que permite al escribir el enunciado y seleccionar el tipo de
     * pregunta a crear abrir el panel correspondiente.
     */
    private void bntSiguienteActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_bntSiguienteActionPerformed
        // Verificar si el campo de texto está vacío
        if (txtEnunciado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
        } else {
            String textoEnunciado = txtEnunciado.getText();
            String tipoPregunta = (String) cboxTipoPregunta.getSelectedItem();
            
            int cantidadGuiones = textoEnunciado.length() - textoEnunciado.replace("_", "").length();

            // Verificar si el tipo de pregunta es "Rellenar Espacios" y si el enunciado no
            // contiene '_'
            if (tipoPregunta.equals("Rellenar espacios") && !textoEnunciado.contains("_")) {
                JOptionPane.showMessageDialog(null, "Por favor, debe poner mínimo un '_' en su enunciado.");
                return; // Salir del método para evitar continuar
            } else if (tipoPregunta.equals("Rellenar espacios") && cantidadGuiones > 2){
                JOptionPane.showMessageDialog(null, "Por favor, se permiten un máximo dos '_' en su enunciado.");
                return; // Salir del método para evitar continuar
            }

            // Si todas las validaciones son correctas, establecer el enunciado y tipo de
            // pregunta
            this.setEnunciado(textoEnunciado);
            this.setTipoPregunta(tipoPregunta);
            lblTipo.setText("Respuesta");
            this.remove(panelEnunciado);

            // Continuar con el flujo según el tipo de pregunta
            switch (tipoPregunta) {
                case "Verdadero o Falso":
                    this.interfazVF();
                    break;
                case "Multiple opción":
                    this.interfazMultiple();
                    break;
                case "Rellenar espacios":
                    this.interfazEspacios();
                    break;
            }
        }
    }// GEN-LAST:event_bntSiguienteActionPerformed

    /**
     * Método que permite dar funcionalidad desde el JPanel multiple al botón
     * "Finalizar" desde el rol docente y "Siguiente" desde el rol estudiante.
     * En caso de ser un docente se agrega la pregunta a la evaluación y en caso
     * de ser un estudiante se despliega la siguiente pregunta y se almacena la
     * respuesta en memoria.
     *
     * @param evento
     */
    private void btnFinalizarMultipleActionPerformed(java.awt.event.ActionEvent evento) {
        // Verificar si el botón es "Finalizar" (rol docente)
        if ("Finalizar".equals(btnFinalizarMultiple.getText())) {
            // Verificar si todos los campos están llenos y válidos
            if (!estaCampoLleno()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos con valores válidos.");
            } else {
                // Agregar pregunta a la evaluación
                agregarPregunta();
            }
        } // Verificar si el botón es "Siguiente" (rol estudiante)
        else if ("Siguiente".equals(btnFinalizarMultiple.getText())) {
            try {
                // Solicitar la siguiente pregunta
                solicitarSiguientePregunta();
            } catch (IOException ex) {
                Logger.getLogger(AltaPreguntaPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Método para verificar si todos los campos están llenos y válidos
    private boolean estaCampoLleno() {
        return ((Integer) spnPuntajeMultiple.getValue() > 0)
                && !txtEnunciado.getText().isEmpty()
                && !txtOpc1.getText().isEmpty()
                && !txtOpc2.getText().isEmpty()
                && !txtOpc3.getText().isEmpty()
                && !txtOpc4.getText().isEmpty();
    }

    // Método para agregar pregunta a la evaluación
    private void agregarPregunta() {
        cantidadPreguntas++;
        this.agregarPreguntaVistaPrevia();
        this.getCliente().armarMultiple(this.getEnunciado(), String.valueOf(this.spnPuntajeMultiple.getValue()),
                this.txtOpc1.getText(), this.txtOpc2.getText(), this.txtOpc3.getText(), this.txtOpc4.getText(),
                String.valueOf(this.cboxOpcionesMultiple.getSelectedIndex() + 1));
        panelContentDashboard.removeAll();
        panelContentVistaPrevia.setSize(730, 520);
        panelContentVistaPrevia.setLocation(0, 0);
        panelContentDashboard.add(panelContentVistaPrevia);
        panelContentDashboard.revalidate();
        panelContentDashboard.repaint();
    }

    // Método para solicitar la siguiente pregunta
    private void solicitarSiguientePregunta() throws IOException {
        cantidadPreguntas++;
        this.solicitarSiguientePregunta(String.valueOf(cboxOpcionesMultiple.getSelectedIndex() + 1)/* respuesta */,
                this);
    }

    /**
     * Método que permite dar funcionalidad desde el JPanel espaciosVF al botón
     * "Finalizar" desde el rol docente y "Siguiente" desde el rol estudiante.
     * En caso de ser un docente se agrega la pregunta a la evaluación y en caso
     * de ser estudiante se despliega la siguiente pregunta y se almacena la
     * respuesta en memoria.
     *
     * Este botón es compartido por el tipo de pregunta espacios y vf.
     *
     * @param evt
     */
    /**
     * Método que permite dar funcionalidad desde el JPanel de espacios en
     * blanco o verdadero/falso al botón "Finalizar" desde el rol docente y
     * "Siguiente" desde el rol estudiante.
     *
     * @param evento
     */
    private void btnFinalizarEspaciosVFActionPerformed(java.awt.event.ActionEvent evento) {
        // Verificar si la respuesta no tiene más de 2 comas
        if (!muchasRespuestas(txtRespuestaEspacios.getText())) {
            // Verificar si el botón es "Finalizar" (rol docente)
            if (btnFinalizarEspaciosVF.getText().equals("Finalizar")) {
                // Verificar si todos los campos están llenos y válidos
                if (!estaCampoLlenoVF()) {
                    JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos con valores válidos.");
                } else {
                    // Agregar pregunta a la evaluación
                    agregarPreguntaVF();
                }
            } // Verificar si el botón es "Siguiente" (rol estudiante)
            else if (btnFinalizarEspaciosVF.getText().equals("Siguiente")) {
                try {
                    // Obtener la respuesta del estudiante
                    String respuesta = obtenerRespuestaEstudiante();
                    // Solicitar la siguiente pregunta
                    solicitarSiguientePregunta(respuesta);
                } catch (IOException ex) {
                    Logger.getLogger(AltaPreguntaPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Máximo 2 respuestas separadas por ','");
        }
    }

    // Método para verificar si todos los campos están llenos y válidos
    private boolean estaCampoLlenoVF() {
        return ((Integer) spnPuntajeEspaciosVF.getValue() > 0)
                && !txtEnunciado.getText().isEmpty()
                && !txtRespuestaEspacios.getText().isEmpty();
    }

    // Método para agregar pregunta a la evaluación
    private void agregarPreguntaVF() {
        cantidadPreguntas++;
        this.agregarPreguntaVistaPrevia();
        if (this.getTipoPregunta().equals("Verdadero o Falso")) {
            this.getCliente().armarVF(this.getEnunciado(),
                    String.valueOf(this.spnPuntajeEspaciosVF.getValue())/* puntaje */,
                    String.valueOf(this.cboxVerdaderoOFalso.getSelectedItem())/* respuesta */);
        } else {
            this.getCliente().armarEspacios(this.getEnunciado(),
                    String.valueOf(this.spnPuntajeEspaciosVF.getValue()),
                    this.txtRespuestaEspacios.getText());
        }
        panelContentDashboard.removeAll();
        panelContentVistaPrevia.setSize(730, 520);
        panelContentVistaPrevia.setLocation(0, 0);
        panelContentDashboard.add(panelContentVistaPrevia);
        panelContentDashboard.revalidate();
        panelContentDashboard.repaint();
    }

    // Método para obtener la respuesta del estudiante
    private String obtenerRespuestaEstudiante() {
        String respuesta;
        if (cboxVerdaderoOFalso.isVisible()) { // Si es vf
            respuesta = String.valueOf(cboxVerdaderoOFalso.getSelectedItem());
        } else {
            String valorPorDefecto = "xF_45&3";
            if (txtRespuestaEspacios.getText().isEmpty()) {
                respuesta = valorPorDefecto;
            } else {
                respuesta = txtRespuestaEspacios.getText();
            }
        }
        return respuesta;
    }

    // Método para solicitar la siguiente pregunta
    private void solicitarSiguientePregunta(String respuesta) throws IOException {
        cantidadPreguntas++;
        this.solicitarSiguientePregunta(respuesta, this);
    }

    /**
     * Método que le solicita al servidor la siguiente pregunta de la
     * evaluación.
     *
     * @param respuesta     de la pregunta anterior.
     * @param framePregunta JFrame donde visualiza el estudiante la pregunta de
     *                      la evaluación.
     * @throws IOException
     */
    private void solicitarSiguientePregunta(String respuesta, AltaPreguntaPanel framePregunta) throws IOException {
        this.setRespuestas(this.getCliente().prepararRespuestas(this.getRespuestas(), respuesta)); // Almacena la
        // respuesta de la
        // pregunta anterior
        this.getCliente()
                .intercambiarMensajes(this.getEvaluacion() + ";;;"
                        + cantidadPreguntas /* Corresponde al número de pregunta solicitada */
                        + ",;,Evaluaciones,;,ObtenerPregunta");
        // Confirmación de envío de respuestas
        if (this.getCliente().obtenerMensaje().equals("Finalizar")) { // Si el server manda "Finalizar,;,200" no hay más
            // preguntas disponibles
            int finalizar = JOptionPane.showConfirmDialog(null, "¿Desea enviar sus respuestas?", "Fin de la evaluación",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (finalizar == 0) { // Envía las respuestas del estudiante
                try {
                    String enviarRespuestas = this.getCliente().formatearMensaje(this.getRespuestas(), "Evaluaciones",
                            "Correccion");
                    this.getCliente().intercambiarMensajes(enviarRespuestas);
                    // Mensaje de confirmación de envío de respuestas
                    JOptionPane.showMessageDialog(null, "Respuestas enviadas con éxito");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error en la solicitud: " + e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Evaluación cancelada");
            }
            AltaPreguntaPanel.setCantidadPreguntas(0); // Inicializa el número de pregunta al finalizar
            this.setRespuestas(""); // Inicializa las respuestas
            framePregunta.setNombreEvaluacion("");
            BienvenidaPanel panelBienvenida = new BienvenidaPanel(cliente, rol);
            panelBienvenida.setSize(730, 520);
            panelBienvenida.setLocation(0, 0);
            panelContentDashboard.removeAll();
            panelContentDashboard.add(panelBienvenida);
            panelContentDashboard.revalidate();
            panelContentDashboard.repaint();
        } else if (this.getCliente().obtenerCodigo().equals("200")) { // Si la obtiene crea un JFrame con la misma
            this.crearFrameNuevaPregunta(framePregunta);
        } else {
            JOptionPane.showMessageDialog(this, "Error en la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que agrega los elementos correspondientes a una pregunta de tipo
     * multiple opción a la vista previa de la evaluación.
     *
     * @param fuente a utilizar.
     * @param indice de la respuesta correcta
     */
    public void vistaPreviaMultiple(Font fuente, int indice) {
        // Creación de radio buttons con las opciones correspondientes
        JRadioButton opc1 = new JRadioButton(txtOpc1.getText());
        JRadioButton opc2 = new JRadioButton(txtOpc2.getText());
        JRadioButton opc3 = new JRadioButton(txtOpc3.getText());
        JRadioButton opc4 = new JRadioButton(txtOpc4.getText());

        // Marcamos solo la opciön correcta
        switch (indice) {
            case 1:
                opc1.setSelected(true);
                break;
            case 2:
                opc2.setSelected(true);
                break;
            case 3:
                opc3.setSelected(true);
                break;
            default:
                opc4.setSelected(true);
                break;
        }

        // Estética de las opciones
        opc1.setOpaque(false);
        opc2.setOpaque(false);
        opc3.setOpaque(false);
        opc4.setOpaque(false);
        opc1.setFont(fuente);
        opc2.setFont(fuente);
        opc3.setFont(fuente);
        opc4.setFont(fuente);
        opc1.setEnabled(false);
        opc2.setEnabled(false);
        opc3.setEnabled(false);
        opc4.setEnabled(false);

        // Alta en la vista previa
        vistaPrevia.add(opc1);
        vistaPrevia.add(opc2);
        vistaPrevia.add(opc3);
        vistaPrevia.add(opc4);
    }

    /**
     * Método que crea todos los elementos necesarios para agregar en la vista
     * previa de la evaluación la nueva pregunta.
     *
     * @param fuente a utilizar.
     */
    public void crearPreguntaVistaPrevia(Font fuente) {
        JLabel lblpregunta = new JLabel(cantidadPreguntas + " - " + enunciado);
        lblpregunta.setOpaque(false);
        lblpregunta.setFont(new java.awt.Font("Arial", 0, 18));
        vistaPrevia.add(lblpregunta);
    }

    /**
     * Método que crea todos los elementos necesarios para agregar en la vista
     * previa de la evaluación la respuesta de la nueva pregunta.
     *
     * @param fuente a utilizar.
     */
    public void crearRespuestaVistaPrevia(Font fuente) {
        String respuesta = "";
        switch (this.getTipoPregunta()) {
            case "Verdadero o Falso": // Vista como una multiple opcion de dos opciones
                respuesta = (String) cboxVerdaderoOFalso.getSelectedItem();
                break;
            case "Multiple opción":
                respuesta = (String) cboxOpcionesMultiple.getSelectedItem();
                int indice = (Integer) cboxOpcionesMultiple.getSelectedIndex() + 1;
                this.vistaPreviaMultiple(fuente, indice);
                break;
            case "Rellenar espacios":
                respuesta = txtRespuestaEspacios.getText();
                break;
        }
        JLabel lblRespuesta = new JLabel("Respuesta: " + respuesta);
        lblRespuesta.setOpaque(false);
        lblRespuesta.setFont(new java.awt.Font("Arial", 0, 18));
        lblRespuesta.setForeground(new Color(0, 0, 153));
        vistaPrevia.add(lblRespuesta);
    }

    /**
     * Método que agrega la pregunta junto a su respuesta en la vista previa de
     * la evaluación para el docente.
     */
    private void agregarPreguntaVistaPrevia() {
        Font fuente = new Font("Dialog", Font.PLAIN, 15);
        this.crearPreguntaVistaPrevia(fuente);
        this.crearRespuestaVistaPrevia(fuente);
        vistaPrevia.revalidate();
        vistaPrevia.repaint();
    }

    /**
     * Método que crea el JFrame destinado a la nueva pregunta a responder por
     * el estudiante.
     *
     * @param framePregunta en el que se creará
     */
    public void crearFrameNuevaPregunta(AltaPreguntaPanel framePregunta) {
        String[] nuevaPregunta = this.getCliente().obtenerMensaje().split(";;;");
        if ((nuevaPregunta.length >= 3) && (nuevaPregunta.length <= 7)) { // Si tiene la estructura de una pregunta
            this.cargarEnGui(nuevaPregunta, framePregunta);
            framePregunta.revalidate();
            framePregunta.repaint();
        }
    }

    /**
     * Método que permite cargar en un JFrame de tipo pregunta una pregunta de
     * tipo multiple.
     *
     * @param pregunta      a cargar
     * @param puntaje       asociado a la pregunta.
     * @param framePregunta en el que se cargaran los componentes.
     */
    public void cargarEnGuiMultiple(String[] pregunta, int puntaje, AltaPreguntaPanel framePregunta) {
        framePregunta.lblEnunciadoMultiple.setText(enunciado);// Se carga el enunciado en Label
        framePregunta.txtRespuestaEspacios.setVisible(false);
        framePregunta.cboxOpcionesMultiple.setVisible(true);
        framePregunta.cboxVerdaderoOFalso.setVisible(false);
        framePregunta.panelRespuestaEspaciosVF.setVisible(false);
        this.panelEspaciosVF.setVisible(false);
        this.panelMultiple.setVisible(true);
        // Aparecen las opciones y el puntaje pero sin posibilidad de editar
        framePregunta.spnPuntajeMultiple.setEnabled(false);
        framePregunta.spnPuntajeMultiple.setValue(puntaje);
        framePregunta.txtOpc1.setText(pregunta[2]); // Las opciones son los tokens 2-5
        framePregunta.txtOpc2.setText(pregunta[3]);
        framePregunta.txtOpc3.setText(pregunta[4]);
        framePregunta.txtOpc4.setText(pregunta[5]);
        framePregunta.txtOpc1.setEnabled(false);
        framePregunta.txtOpc2.setEnabled(false);
        framePregunta.txtOpc3.setEnabled(false);
        framePregunta.txtOpc4.setEnabled(false);
        framePregunta.btnFinalizarMultiple.setText("Siguiente");
        // Elimina el resultado cargado en la pregunta anterior
        framePregunta.cboxOpcionesMultiple.setSelectedIndex(0);
        this.lblEnunciadoMultiple.setToolTipText(enunciado);
    }

    /**
     * Método que permite cargar en un JFrame de tipo pregunta una pregunta de
     * tipo vf.
     *
     * @param puntaje       asociado a la pregunta.
     * @param framePregunta en el que se cargaran los componentes.
     */
    public void cargarEnGuiVF(int puntaje, AltaPreguntaPanel framePregunta) {
        this.panelMultiple.setVisible(false);
        framePregunta.txtEnunciadoEspaciosVF.setText(enunciado); // Se carga el enunciado en el txtArea
        this.panelEspaciosVF.setVisible(true);
        framePregunta.txtRespuestaEspacios.setVisible(false);
        framePregunta.panelRespuestaEspaciosVF.setVisible(true);
        framePregunta.cboxVerdaderoOFalso.setVisible(true);
        framePregunta.spnPuntajeEspaciosVF.setEnabled(false);
        framePregunta.spnPuntajeEspaciosVF.setValue(puntaje);
        framePregunta.btnFinalizarEspaciosVF.setText("Siguiente");
        // Elimina el resultado cargado en la pregunta anterior
        framePregunta.cboxVerdaderoOFalso.setSelectedIndex(0);
        this.txtEnunciadoEspaciosVF.setToolTipText(enunciado);
    }

    /**
     * Método que permite cargar en un JFrame de tipo pregunta una pregunta de
     * tipo espacios.
     *
     * @param puntaje       asociado a la pregunta.
     * @param framePregunta en el que se cargaran los componentes.
     */
    public void cargarEnGuiEspacios(int puntaje, AltaPreguntaPanel framePregunta) {
        this.panelMultiple.setVisible(false);
        framePregunta.txtEnunciadoEspaciosVF.setText(enunciado);// Se carga el enunciado en el txtArea
        framePregunta.cboxVerdaderoOFalso.setVisible(false);
        framePregunta.txtRespuestaEspacios.setVisible(true);
        this.panelEspaciosVF.setVisible(true);
        framePregunta.panelRespuestaEspaciosVF.setVisible(true);
        framePregunta.spnPuntajeEspaciosVF.setEnabled(false);
        framePregunta.spnPuntajeEspaciosVF.setValue(puntaje);
        framePregunta.btnFinalizarEspaciosVF.setText("Siguiente");
        // Elimina el resultado cargado en la pregunta anterior
        framePregunta.txtRespuestaEspacios.setText("");
        this.txtEnunciadoEspaciosVF.setToolTipText(enunciado);
    }

    /**
     * Metodo que permite cargar en un JFrame de tipo pregunta con la pregunta
     * actual.
     *
     * @param pregunta      actual.
     * @param framePregunta en el que se agregarán los componentes.
     */
    public void cargarEnGui(String[] pregunta, AltaPreguntaPanel framePregunta) {
        String tipo = pregunta[0];
        this.setEnunciado(pregunta[1]);
        int puntaje = Integer.parseInt(pregunta[pregunta.length - 1]); // El último token es el puntaje

        framePregunta.panelEnunciado.setVisible(false); // Solo visible al crear la pregunta, en este caso es realizar
        // evaluación como estudiante
        framePregunta.setVisible(true);
        switch (tipo) {
            case "Multiple":
                this.cargarEnGuiMultiple(pregunta, puntaje, framePregunta);
                break;
            case "VF":
                this.cargarEnGuiVF(puntaje, framePregunta);
                break;
            case "Completar":
                this.cargarEnGuiEspacios(puntaje, framePregunta);
                break;
        }
    }

    /**
     * Método que inicializa la cantidad de preguntas y las respuestas si se
     * cierra la evaluación a medias desde el rol estudiante.
     *
     * @param evt
     */
    private void valoresPorDefecto() {
        if (rol.equals("estudiante")) {
            AltaPreguntaPanel.setCantidadPreguntas(0);
            this.setRespuestas("");
        }
    }

    /**
     * Método que agrega mensajes de ayuda a los componentes de la interfaz.
     * Estos mensajes aparecen al posarse con el cursor del mouse encima de los
     * mismos.
     */
    private void agregarMensajesAyuda() {
        this.txtRespuestaEspacios.setToolTipText("Escriba aquí un máximo de dos respuestas separadas por coma");
        this.cboxOpcionesMultiple.setToolTipText("Seleccione la opción correcta");
        this.cboxVerdaderoOFalso.setToolTipText("Seleccione la opción correcta");
        this.cboxTipoPregunta.setToolTipText("Seleccione el tipo de pregunta");
        this.txtEnunciado.setToolTipText("Ingrese aquí el enunciado de la consigna");
    }

    /**
     * Método que crea el cartel de ayuda del JPanel "enunciado".
     */
    private void cartelAyudaEnunciado() {
        System.out.println("clic en ayuda");
        JOptionPane.showMessageDialog(this,
                "Ingrese el enunciado de la consiga y el tipo de pregunta que desea crear, luego presione el botón siguiente.",
                "Ayuda",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método que crea el cartel de ayuda del JPanel "multiple".
     */
    private void cartelAyudaMultiple() {
        System.out.println("clic en ayuda");
        JOptionPane.showMessageDialog(this,
                "Ingrese el puntaje y cuatro respuestas posibles, luego seleccione finalizar.",
                "Ayuda",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método que crea el cartel de ayuda del JPanel "espaciosVF".
     */
    private void cartelAyudaEspaciosVF() {
        System.out.println("clic en ayuda");
        if (this.getTipoPregunta().equals("Rellenar espacios")) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese el puntaje y un máximo de dos respuestas separadas por coma, luego seleccione finalizar.",
                    "Ayuda",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Ingrese el puntaje y seleccione la respuesta correcta, luego seleccione finalizar.",
                    "Ayuda",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombreEvaluacion = new javax.swing.JLabel();
        panelEnunciado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboxTipoPregunta = new javax.swing.JComboBox<>();
        bntSiguiente = new javax.swing.JButton();
        lblSigno = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEnunciado = new javax.swing.JTextArea();
        lblAyudaEnunciado = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelMultiple = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboxOpcionesMultiple = new javax.swing.JComboBox<>();
        txtOpc1 = new javax.swing.JTextField();
        txtOpc3 = new javax.swing.JTextField();
        txtOpc2 = new javax.swing.JTextField();
        txtOpc4 = new javax.swing.JTextField();
        panelPuntaje = new javax.swing.JPanel();
        btnFinalizarMultiple = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        spnPuntajeMultiple = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        lblEnunciadoMultiple = new javax.swing.JLabel();
        lblAyudaMultiple = new javax.swing.JLabel();
        panelEspaciosVF = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEnunciadoEspaciosVF = new javax.swing.JTextArea();
        panelRespuestaEspaciosVF = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cboxVerdaderoOFalso = new javax.swing.JComboBox<>();
        btnFinalizarEspaciosVF = new javax.swing.JButton();
        txtRespuestaEspacios = new javax.swing.JTextField();
        spnPuntajeEspaciosVF = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        lblAyudaEspaciosVF = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreEvaluacion.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        add(txtNombreEvaluacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 390, 40));

        panelEnunciado.setBackground(new java.awt.Color(255, 255, 255));
        panelEnunciado.setPreferredSize(new java.awt.Dimension(587, 227));
        panelEnunciado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Ingrese tipo:");
        panelEnunciado.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Nueva Pregunta");
        panelEnunciado.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        cboxTipoPregunta.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        cboxTipoPregunta.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Rellenar espacios", "Multiple opción", "Verdadero o Falso" }));
        cboxTipoPregunta.setBorder(null);
        cboxTipoPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxTipoPreguntaActionPerformed(evt);
            }
        });
        panelEnunciado.add(cboxTipoPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 246, 31));

        bntSiguiente.setBackground(new java.awt.Color(0, 0, 204));
        bntSiguiente.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bntSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        bntSiguiente.setText("Siguiente");
        bntSiguiente.setMaximumSize(new java.awt.Dimension(129, 27));
        bntSiguiente.setMinimumSize(new java.awt.Dimension(129, 27));
        bntSiguiente.setPreferredSize(new java.awt.Dimension(129, 27));
        bntSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSiguienteActionPerformed(evt);
            }
        });
        panelEnunciado.add(bntSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 155, 47));
        panelEnunciado.add(lblSigno, new org.netbeans.lib.awtextra.AbsoluteConstraints(723, 0, -1, 520));

        txtEnunciado.setBackground(new java.awt.Color(234, 234, 234));
        txtEnunciado.setColumns(20);
        txtEnunciado.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtEnunciado.setLineWrap(true);
        txtEnunciado.setRows(5);
        jScrollPane1.setViewportView(txtEnunciado);

        panelEnunciado.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 657, 157));

        lblAyudaEnunciado.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblAyudaEnunciado.setForeground(new java.awt.Color(102, 102, 255));
        lblAyudaEnunciado.setText("¿Necesita Ayuda?");
        lblAyudaEnunciado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAyudaEnunciadoMouseClicked(evt);
            }
        });
        panelEnunciado.add(lblAyudaEnunciado, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 130, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel11.setText("Ingrese enunciado:");
        panelEnunciado.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        add(panelEnunciado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));

        panelMultiple.setBackground(new java.awt.Color(255, 255, 255));
        panelMultiple.setPreferredSize(new java.awt.Dimension(587, 227));
        panelMultiple.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setText("Opción 3");
        jLabel4.setToolTipText("");
        panelMultiple.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 160, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setText("Opción 2");
        panelMultiple.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 160, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("Opción 4");
        panelMultiple.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 160, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Respuesta:");
        panelMultiple.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 240, 30));

        cboxOpcionesMultiple.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cboxOpcionesMultiple.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Opción 1", "Opción 2", "Opción 3", "Opción 4" }));
        cboxOpcionesMultiple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxOpcionesMultipleActionPerformed(evt);
            }
        });
        panelMultiple.add(cboxOpcionesMultiple, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 150, 30));

        txtOpc1.setBackground(new java.awt.Color(234, 234, 234));
        txtOpc1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtOpc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOpc1ActionPerformed(evt);
            }
        });
        panelMultiple.add(txtOpc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 280, 30));

        txtOpc3.setBackground(new java.awt.Color(234, 234, 234));
        txtOpc3.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        panelMultiple.add(txtOpc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 280, 30));

        txtOpc2.setBackground(new java.awt.Color(234, 234, 234));
        txtOpc2.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        panelMultiple.add(txtOpc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 280, 30));

        txtOpc4.setBackground(new java.awt.Color(234, 234, 234));
        txtOpc4.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        panelMultiple.add(txtOpc4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 280, 30));

        panelPuntaje.setBackground(new java.awt.Color(0, 0, 51));
        panelPuntaje.setPreferredSize(new java.awt.Dimension(260, 520));

        btnFinalizarMultiple.setBackground(new java.awt.Color(51, 0, 204));
        btnFinalizarMultiple.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnFinalizarMultiple.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarMultiple.setText("Finalizar");
        btnFinalizarMultiple.setMaximumSize(new java.awt.Dimension(129, 27));
        btnFinalizarMultiple.setMinimumSize(new java.awt.Dimension(129, 27));
        btnFinalizarMultiple.setPreferredSize(new java.awt.Dimension(129, 27));
        btnFinalizarMultiple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarMultipleActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Puntaje");

        spnPuntajeMultiple.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        spnPuntajeMultiple.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        javax.swing.GroupLayout panelPuntajeLayout = new javax.swing.GroupLayout(panelPuntaje);
        panelPuntaje.setLayout(panelPuntajeLayout);
        panelPuntajeLayout.setHorizontalGroup(
                panelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPuntajeLayout.createSequentialGroup()
                                .addContainerGap(55, Short.MAX_VALUE)
                                .addGroup(panelPuntajeLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                panelPuntajeLayout.createSequentialGroup()
                                                        .addComponent(btnFinalizarMultiple,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 155,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(50, 50, 50))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                panelPuntajeLayout.createSequentialGroup()
                                                        .addComponent(spnPuntajeMultiple,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 77,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(90, 90, 90)))));
        panelPuntajeLayout.setVerticalGroup(
                panelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPuntajeLayout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(spnPuntajeMultiple, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnFinalizarMultiple, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(202, Short.MAX_VALUE)));

        panelMultiple.add(panelPuntaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 0, 260, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel8.setText("Opción 1");
        panelMultiple.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 160, -1));

        lblEnunciadoMultiple.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblEnunciadoMultiple.setText("Enunciado");
        lblEnunciadoMultiple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEnunciadoMultipleMouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEnunciadoMultipleMouseEntered(evt);
            }
        });
        panelMultiple.add(lblEnunciadoMultiple, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 390, -1));

        lblAyudaMultiple.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblAyudaMultiple.setForeground(new java.awt.Color(51, 102, 255));
        lblAyudaMultiple.setText("¿Necesita Ayuda?");
        lblAyudaMultiple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAyudaMultipleMouseClicked(evt);
            }
        });
        panelMultiple.add(lblAyudaMultiple, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 130, 16));

        add(panelMultiple, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));

        panelEspaciosVF.setBackground(new java.awt.Color(255, 255, 255));
        panelEspaciosVF.setPreferredSize(new java.awt.Dimension(587, 227));

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel9.setText("Enunciado");

        txtEnunciadoEspaciosVF.setBackground(new java.awt.Color(234, 234, 234));
        txtEnunciadoEspaciosVF.setColumns(20);
        txtEnunciadoEspaciosVF.setFont(new java.awt.Font("Dialog", 2, 20)); // NOI18N
        txtEnunciadoEspaciosVF.setLineWrap(true);
        txtEnunciadoEspaciosVF.setRows(5);
        jScrollPane3.setViewportView(txtEnunciadoEspaciosVF);

        panelRespuestaEspaciosVF.setBackground(new java.awt.Color(0, 0, 51));
        panelRespuestaEspaciosVF.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTipo.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipo.setText("Respuesta");
        lblTipo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelRespuestaEspaciosVF.add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 260, -1));

        cboxVerdaderoOFalso.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        cboxVerdaderoOFalso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Verdadero", "Falso" }));
        cboxVerdaderoOFalso.setAlignmentX(-2.0F);
        cboxVerdaderoOFalso.setAlignmentY(0.0F);
        cboxVerdaderoOFalso.setBorder(null);
        cboxVerdaderoOFalso.setMaximumSize(new java.awt.Dimension(146, 30));
        cboxVerdaderoOFalso.setMinimumSize(new java.awt.Dimension(144, 30));
        cboxVerdaderoOFalso.setPreferredSize(new java.awt.Dimension(144, 30));
        cboxVerdaderoOFalso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxVerdaderoOFalsoActionPerformed(evt);
            }
        });
        panelRespuestaEspaciosVF.add(cboxVerdaderoOFalso,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 160, 36));

        btnFinalizarEspaciosVF.setBackground(new java.awt.Color(51, 0, 204));
        btnFinalizarEspaciosVF.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnFinalizarEspaciosVF.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarEspaciosVF.setText("Finalizar");
        btnFinalizarEspaciosVF.setAlignmentY(0.0F);
        btnFinalizarEspaciosVF.setMaximumSize(new java.awt.Dimension(155, 47));
        btnFinalizarEspaciosVF.setMinimumSize(new java.awt.Dimension(155, 47));
        btnFinalizarEspaciosVF.setPreferredSize(new java.awt.Dimension(155, 47));
        btnFinalizarEspaciosVF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarEspaciosVFActionPerformed(evt);
            }
        });
        panelRespuestaEspaciosVF.add(btnFinalizarEspaciosVF,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 160, -1));

        txtRespuestaEspacios.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        panelRespuestaEspaciosVF.add(txtRespuestaEspacios,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 160, 36));

        spnPuntajeEspaciosVF.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        spnPuntajeEspaciosVF.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Puntaje:");

        lblAyudaEspaciosVF.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lblAyudaEspaciosVF.setForeground(new java.awt.Color(51, 102, 255));
        lblAyudaEspaciosVF.setText("¿Necesita Ayuda?");
        lblAyudaEspaciosVF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAyudaEspaciosVFMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelEspaciosVFLayout = new javax.swing.GroupLayout(panelEspaciosVF);
        panelEspaciosVF.setLayout(panelEspaciosVFLayout);
        panelEspaciosVFLayout.setHorizontalGroup(
                panelEspaciosVFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(panelEspaciosVFLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spnPuntajeEspaciosVF,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 135,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelEspaciosVFLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel9)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 392,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblAyudaEspaciosVF, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 32,
                                        Short.MAX_VALUE)
                                .addComponent(panelRespuestaEspaciosVF, javax.swing.GroupLayout.PREFERRED_SIZE, 260,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
        panelEspaciosVFLayout.setVerticalGroup(
                panelEspaciosVFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(lblAyudaEspaciosVF)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(panelEspaciosVFLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(spnPuntajeEspaciosVF, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                                .addComponent(panelRespuestaEspaciosVF, javax.swing.GroupLayout.PREFERRED_SIZE, 559,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        add(panelEspaciosVF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que abre el cartel de ayuda al presionar clic en el label ayuda
     * del panel enunciado.
     *
     * @param evt
     */
    private void lblAyudaEnunciadoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAyudaEnunciadoMouseClicked
        cartelAyudaEnunciado();
    }// GEN-LAST:event_lblAyudaEnunciadoMouseClicked

    /**
     * Método que abre el cartel de ayuda al presionar clic en el label ayuda
     * del panel multiple.
     *
     * @param evt
     */
    private void lblAyudaMultipleMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAyudaMultipleMouseClicked
        cartelAyudaMultiple();
    }// GEN-LAST:event_lblAyudaMultipleMouseClicked

    /**
     * Método que abre el cartel de ayuda al presionar clic en el label ayuda
     * del panel espaciosVF.
     *
     * @param evt
     */
    private void lblAyudaEspaciosVFMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAyudaEspaciosVFMouseClicked
        cartelAyudaEspaciosVF();
    }// GEN-LAST:event_lblAyudaEspaciosVFMouseClicked

    private void cboxTipoPreguntaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxTipoPreguntaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cboxTipoPreguntaActionPerformed

    private void cboxOpcionesMultipleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxOpcionesMultipleActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cboxOpcionesMultipleActionPerformed
     // GEN-LAST:event_cboxOpcionesMultipleActionPerformed

    private void txtOpc1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtOpc1ActionPerformed
        // TODO add your handling code here:
    }
    // GEN-FIRST:event_btnConsolaMultipleActionPerformed

    private void lblEnunciadoMultipleMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEnunciadoMultipleMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_lblEnunciadoMultipleMouseClicked

    private void lblEnunciadoMultipleMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEnunciadoMultipleMouseEntered
        // TODO add your handling code here:
    }// GEN-LAST:event_lblEnunciadoMultipleMouseEntered

    private void cboxVerdaderoOFalsoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxVerdaderoOFalsoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cboxVerdaderoOFalsoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntSiguiente;
    private javax.swing.JButton btnFinalizarEspaciosVF;
    private javax.swing.JButton btnFinalizarMultiple;
    private javax.swing.JComboBox<String> cboxOpcionesMultiple;
    private javax.swing.JComboBox<String> cboxTipoPregunta;
    private javax.swing.JComboBox<String> cboxVerdaderoOFalso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAyudaEnunciado;
    private javax.swing.JLabel lblAyudaEspaciosVF;
    private javax.swing.JLabel lblAyudaMultiple;
    private javax.swing.JLabel lblEnunciadoMultiple;
    private javax.swing.JLabel lblSigno;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel panelEnunciado;
    private javax.swing.JPanel panelEspaciosVF;
    private javax.swing.JPanel panelMultiple;
    private javax.swing.JPanel panelPuntaje;
    private javax.swing.JPanel panelRespuestaEspaciosVF;
    private javax.swing.JSpinner spnPuntajeEspaciosVF;
    private javax.swing.JSpinner spnPuntajeMultiple;
    private javax.swing.JTextArea txtEnunciado;
    private javax.swing.JTextArea txtEnunciadoEspaciosVF;
    private javax.swing.JLabel txtNombreEvaluacion;
    private javax.swing.JTextField txtOpc1;
    private javax.swing.JTextField txtOpc2;
    private javax.swing.JTextField txtOpc3;
    private javax.swing.JTextField txtOpc4;
    private javax.swing.JTextField txtRespuestaEspacios;
    // End of variables declaration//GEN-END:variables
}
