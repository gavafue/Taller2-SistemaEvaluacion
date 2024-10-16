package gui;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import conexion.Cliente;
import java.awt.Color;

/**
 * JFrame destinado a la creación de evaluaciones, que contiene una vista previa
 * de la misma en tiempo real.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class AltaEvaluacionPanel extends javax.swing.JPanel {

    /**
     * Título de la nueva evaluación.
     */
    private String tituloEvaluacion;

    /**
     * Cliente actual en el sistema.
     */
    private Cliente cliente;

    /**
     * Panel de contenido para manejo de interfaz.
     */
    private JPanel panelContent;

    /**
     * Constructor que inicializa la interfaz y todos sus componentes.
     *
     * @param cliente cliente actual.
     * @param panelContent panel de contenido para manejo de interfaz.
     * @throws FileNotFoundException
     */
    public AltaEvaluacionPanel(Cliente cliente, JPanel panelContent) throws FileNotFoundException {
        this.cliente = cliente;
        this.panelContent = panelContent;
        initComponents();
    }

    /**
     * Este método devuelve el título de la evaluación.
     *
     * @return el título de la evaluación.
     */
    public String getTituloEvaluacion() {
        return tituloEvaluacion;
    }

    /**
     * Este método devuelve el cliente actual.
     *
     * @return el cliente actual.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Este método establece el título de la evaluación.
     *
     * @param tituloEvaluacion el título que se asignará a la evaluación.
     */
    public void setTituloEvaluacion(String tituloEvaluacion) {
        this.tituloEvaluacion = tituloEvaluacion;
    }

    /**
     * Este método establece el cliente actual.
     *
     * @param cliente el cliente que se asignará.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNuevaPregunta = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        txtTitulo = new javax.swing.JTextField();
        txtMensaje = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PanelVista = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbxRespuestasValidas = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(730, 520));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevaPregunta.setBackground(new java.awt.Color(25, 118, 210));
        btnNuevaPregunta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNuevaPregunta.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaPregunta.setText("Nueva Pregunta");
        btnNuevaPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPreguntaActionPerformed(evt);
            }
        });
        add(btnNuevaPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 190, 40));

        btnFinalizar.setBackground(new java.awt.Color(51, 0, 204));
        btnFinalizar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });
        add(btnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 155, 40));

        txtTitulo.setBackground(new java.awt.Color(234, 234, 234));
        txtTitulo.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(102, 102, 102));
        txtTitulo.setText("Titulo de la Evaluacion");
        txtTitulo.setBorder(null);
        txtTitulo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTituloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTituloFocusLost(evt);
            }
        });
        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });
        add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 647, 31));

        txtMensaje.setFont(new java.awt.Font("Lucida Console", 2, 24)); // NOI18N
        txtMensaje.setForeground(new java.awt.Color(255, 0, 0));
        add(txtMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 616, 84, -1));

        btnAtras.setBackground(new java.awt.Color(51, 0, 204));
        btnAtras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atrás");
        btnAtras.setBorder(null);
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, 146, 40));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Ingrese título de la nueva evaluación:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 369, 20));

        PanelVista.setBackground(new java.awt.Color(204, 204, 204));
        PanelVista.setOpaque(false);
        PanelVista.setLayout(new javax.swing.BoxLayout(PanelVista, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(PanelVista);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 647, 234));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Vista previa de la evaluación:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        cbxRespuestasValidas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxRespuestasValidas.setText("Permitir al estudiante ver las respuestas al finalizar");
        add(cbxRespuestasValidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, 33));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que activa placeholder de ayuda.
     *
     * @param texto texto de ayuda.
     */
    private void TextoAyudaOn(JTextField texto) {
        Font fuente = texto.getFont();
        fuente = fuente.deriveFont(Font.ITALIC);
        texto.setFont(fuente);
        texto.setForeground(Color.DARK_GRAY);
    }

    /**
     * Método que desactiva placeholder de ayuda.
     *
     * @param texto texto de ayuda.
     */
    private void TextoAyudaOff(JTextField texto) {
        Font fuente = texto.getFont();
        fuente = fuente.deriveFont(Font.PLAIN);
        texto.setFont(fuente);
        texto.setForeground(Color.DARK_GRAY);
    }

    /**
     * Este método permite asignar preguntas a la evaluación actual a partir del
     * botón "Agregar Pregunta", una vez el título haya sido ingresado y único.
     *
     * @param evt
     */
    private void btnNuevaPreguntaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNuevaPreguntaActionPerformed
        if (txtTitulo.getText().isBlank() || txtTitulo.getText().equals("Titulo de la Evaluacion")) { // Si el titulo es
            // // vacio
            JOptionPane.showMessageDialog(this, "Ingrese un titulo para la evaluación", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                tituloEvaluacion = txtTitulo.getText();
                if (AltaPreguntaPanel.getCantidadPreguntas() == 0) { // Si aún no se agregaron preguntas
                    if (!this.existeTitulo(tituloEvaluacion)) { // Si el título es válido
                        cliente.setInstruccion(tituloEvaluacion);
                        txtTitulo.setEditable(false);
                        AltaPreguntaPanel ventanaPregunta = new AltaPreguntaPanel(PanelVista, cliente, "docente",
                                tituloEvaluacion,
                                panelContent, this);
                        ventanaPregunta.setSize(730, 520);
                        ventanaPregunta.setLocation(0, 0);
                        panelContent.removeAll();
                        panelContent.add(ventanaPregunta);
                        panelContent.revalidate();
                        panelContent.repaint();
                    } else {
                        JOptionPane.showMessageDialog(this, "Título en uso", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    AltaPreguntaPanel ventanaPregunta = new AltaPreguntaPanel(PanelVista, cliente, "docente",
                            tituloEvaluacion,
                            panelContent, this);
                    ventanaPregunta.setSize(730, 520);
                    ventanaPregunta.setLocation(0, 0);
                    panelContent.removeAll();
                    panelContent.add(ventanaPregunta);
                    panelContent.revalidate();
                    panelContent.repaint();
                }
            } catch (IOException ex) {
                Logger.getLogger(AltaEvaluacionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }// GEN-LAST:event_btnNuevaPreguntaActionPerformed

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTituloActionPerformed

    }// GEN-LAST:event_txtTituloActionPerformed

    /**
     * Este método permite crear la evaluación a partir del botón "Finalizar",
     * una vez se haya ingresado el título y por lo menos seis preguntas.
     *
     * @param evt
     */
    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFinalizarActionPerformed
        if (txtTitulo.getText().isBlank() || txtTitulo.getText().equals("Titulo de la Evaluacion")) { // Si el titulo
            // esta vacio
            JOptionPane.showMessageDialog(this, "Ingrese un titulo para la evaluación", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else
            try {
            if (AltaPreguntaPanel.getCantidadPreguntas() >= 6) {
                // Obtener el estado del checkbox
                System.out.println("CANTIDAD DE PREGUNTAS: " + AltaPreguntaPanel.getCantidadPreguntas());
                boolean respuestasValidas = cbxRespuestasValidas.isSelected(); // Asigna true si está seleccionado,
                // false si no lo está

                // Concatenar la información necesaria para enviarla al cliente, incluyendo el
                // valor de respuestasValidas
                // Prepara el mensaje con el número de preguntas
                String mensaje = ";;;" + respuestasValidas + ";;;" + AltaPreguntaPanel.getCantidadPreguntas()
                        + ",;,Evaluaciones,;,Alta";
                cliente.concatenarMensaje(mensaje);
                cliente.intercambiarMensajes(cliente.getInstruccion());

                if (cliente.obtenerCodigo().equals("200")) {
                    JOptionPane.showMessageDialog(this, "Evaluacion creada con exito", "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                    AltaPreguntaPanel.setCantidadPreguntas(0);
                    GestionEvaluacionesPanel gestionEvaluacionesPanel = new GestionEvaluacionesPanel(cliente,
                            "docente", panelContent);
                    gestionEvaluacionesPanel.setSize(730, 520);
                    gestionEvaluacionesPanel.setLocation(0, 0);
                    panelContent.removeAll();
                    panelContent.add(gestionEvaluacionesPanel);
                    panelContent.revalidate();
                    panelContent.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, cliente.obtenerMensaje(), "Error" + cliente.obtenerCodigo(),
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "La cantidad mínima de preguntas debe ser 6.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(AltaEvaluacionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// GEN-LAST:event_btnFinalizarActionPerformed

    /**
     * Este método se ejecuta cuando el campo de texto 'txtTitulo' gana el foco.
     * Si el texto actual es "Titulo de la Evaluacion", lo borra y desactiva el
     * texto de ayuda.
     *
     * @param evt el evento que indica que el foco fue ganado por el campo de
     * texto.
     */
    private void txtTituloFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtTituloFocusGained
        if (txtTitulo.getText().equals("Titulo de la Evaluacion")) {
            txtTitulo.setText(null);
            txtTitulo.requestFocus();
            TextoAyudaOff(txtTitulo);
        }
    }// GEN-LAST:event_txtTituloFocusGained

    /**
     * Este método se ejecuta cuando el campo de texto 'txtTitulo' pierde el
     * foco. Si el campo está en blanco, activa el texto de ayuda y establece
     * "Titulo de la Evaluacion" como texto predeterminado.
     *
     * @param evt el evento que indica que el foco fue perdido por el campo de
     * texto
     */
    private void txtTituloFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txtTituloFocusLost
        if (txtTitulo.getText().isBlank()) {
            TextoAyudaOn(txtTitulo);
            txtTitulo.setText("Titulo de la Evaluacion");
        }
    }// GEN-LAST:event_txtTituloFocusLost

    /**
     * Este método da funcionamiento al botón "Atrás", que devuelve a la ventana
     * de gestión.
     *
     * @param evt
     * @throws IOException
     */
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_btnAtrasActionPerformed
        GestionEvaluacionesPanel gestionEvaluacionesPanel = null; // TODO Auto-generated catch block
        try {
            gestionEvaluacionesPanel = new GestionEvaluacionesPanel(cliente, "docente",
                    panelContent);
        } catch (IOException ex) {
            Logger.getLogger(AltaEvaluacionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        gestionEvaluacionesPanel.setSize(730, 520);
        gestionEvaluacionesPanel.setLocation(0, 0);
        panelContent.removeAll();
        panelContent.add(gestionEvaluacionesPanel);
        panelContent.revalidate();
        panelContent.repaint();

    }// GEN-LAST:event_btnAtrasActionPerformed

    /**
     * Este método solicita al server si el título de una evaluación a crear ya
     * existe.
     *
     * @param titulo de la evaluación.
     * @return true si existe y false en caso contrario.
     * @throws IOException
     */
    private boolean existeTitulo(String titulo) throws IOException {
        String instruccion = cliente.formatearMensaje(titulo, "Evaluaciones", "Existencia");
        cliente.intercambiarMensajes(instruccion);
        return cliente.obtenerCodigo().equals("200");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelVista;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnNuevaPregunta;
    private javax.swing.JCheckBox cbxRespuestasValidas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtMensaje;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
