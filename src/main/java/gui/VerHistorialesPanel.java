/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import conexion.Cliente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Gabriel
 */
public class VerHistorialesPanel extends javax.swing.JPanel {

    private Cliente cliente;
    private String titulo;
    private String rol;
    private JPanel panelContent;

    /**
     * Creates new form VerHistorialesPanel
     */
    public VerHistorialesPanel(String titulo, Cliente cliente, String rol, JPanel panelContent) {
        this.titulo = titulo;
        this.cliente = cliente;
        this.rol = rol;
        this.panelContent = panelContent;
        initComponents();
        lblTitulo.setText("Evaluación: " + titulo);
        this.solicitarHistoriales();
        this.visualizarBtnRespuestas();
        this.solicitarPuntajeTotalEvaluacion();
    }

    /**
     * Método que permite obtener el cliente actual conectado.
     *
     * @return el cliente actual.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Método que permite obtener el título de la evaluación.
     *
     * @return el título de la evaluación.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Método que permite obtener el rol del cliente actual.
     *
     * @return el rol del cliente actual.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Método que permite modificar el cliente actual conectado, dado otro
     * cliente.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que pemite modificar el título de la evaluación, dado otro título.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Método que permite modificar el rol del cliente, dado otro rol.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Método que solicita al servidor los historiales de una evaluación en
     * particular.
     */
    public void solicitarHistoriales() {
        try {
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Historiales", "Ver");
            this.getCliente().intercambiarMensajes(instruccion);
            if (this.getCliente().obtenerCodigo().equals("200")) {
                this.cargarTablaHistoriales();
            } else {
                JOptionPane.showMessageDialog(this, this.getCliente().obtenerMensaje(),
                        "Error " + this.getCliente().obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(AltaEvaluacionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que carga en la interfaz gráfica los historiales de una
     * evaluación, mediante una tabla.
     *
     * @throws IOException
     */
    public void cargarTablaHistoriales() throws IOException {
        String[] historiales = this.getCliente().obtenerMensaje().split(";;;");
        String[] historial = null;

        String[] columnas = { "CI alumno", "Puntaje Obtenido" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < historiales.length; i++) {
            historial = historiales[i].split(",,,");
            if (this.getRol().equals("docente")) {
                Object[] fila = { historial[0]/* cialumno] */, historial[1]/* puntaje */ };
                modelo.addRow(fila);
            }
            if (this.getCliente().getId().equals(historial[0]) && this.getRol().equals("estudiante")) {
                Object[] fila = { historial[0]/* cialumno] */, historial[1]/* puntaje */ };
                modelo.addRow(fila);
            }
        }
        
        this.darEstiloTabla();
        tableHistorico.setModel(modelo);
    }
    
    public void darEstiloTabla(){
        // Estilo de la tabla
        tableHistorico.setGridColor(Color.LIGHT_GRAY);
        tableHistorico.setShowGrid(true);
        tableHistorico.setRowHeight(30);
        tableHistorico.setIntercellSpacing(new Dimension(0, 0)); // Espacio entre celdas verticalmente
  
        // Configurar el encabezado
        JTableHeader header = tableHistorico.getTableHeader();
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
    }

    public Boolean estudianteRealizoEvaluacion() {
        boolean estudianteRealizoEvaluacion = false;
        String[] historiales = this.getCliente().obtenerMensaje().split(";;;");
        String ciEstudianteConectado = this.getCliente().getId();
        for (int i = 0; i < historiales.length; i++) {
            String[] historial = historiales[i].split(",,,");
            if (historial[0].equals(ciEstudianteConectado)) {
                estudianteRealizoEvaluacion = true;
            }
        }
        return estudianteRealizoEvaluacion;
    }

    public void visualizarBtnRespuestas() {
        try {
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Evaluaciones",
                    "ValorCheckboxRespuestas");
            this.getCliente().intercambiarMensajes(instruccion);
            if (this.getCliente().obtenerCodigo().equals("200")) {
                if (this.getCliente().obtenerMensaje().equals("true")) {
                    btnRespuestas.setVisible(true);
                } else {
                    btnRespuestas.setVisible(false);
                }
            }
        } catch (IOException e) {
            // Manejo de errores de entrada/salida, como problemas de red
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de comunicación con el servidor.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            // Manejo de errores de puntero nulo, por ejemplo, si cliente o respuesta son
            // null
            System.err.println("Referencia nula detectada: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: datos incompletos o nulos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Manejo general de excepciones para cualquier otro error no específico
            System.err.println("Ha ocurrido un error inesperado: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean hayHistorialesDisponibles() {
        try {
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Historiales", "Ver");
            this.getCliente().intercambiarMensajes(instruccion);

            // Verifica si el código de respuesta es "200" para historiales disponibles
            if (this.getCliente().obtenerCodigo().equals("200")) {
                String[] historiales = this.getCliente().obtenerMensaje().split(";;;");
                return historiales.length > 0 && !historiales[0].isEmpty();
            }
        } catch (IOException ex) {
            // Maneja el error pero no muestra alerta aquí
            Logger.getLogger(VerHistorialesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Devuelve false si ocurre algún error o no hay historiales
        return false;
    }

    /**
     * Solicita el puntaje total de la evaluación al cliente y actualiza el campo de
     * texto correspondiente.
     * Muestra mensajes de error detallados en caso de problemas durante la
     * solicitud o al procesar la respuesta.
     */
    public void solicitarPuntajeTotalEvaluacion() {
        String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Evaluaciones",
                "ObtenerPuntajeTotal");

        try {
            // Intercambia mensajes con el cliente para solicitar el puntaje total
            this.getCliente().intercambiarMensajes(instruccion);

            // Verifica el código de respuesta del cliente
            String codigoRespuesta = this.getCliente().obtenerCodigo();
            String mensajeRespuesta = this.getCliente().obtenerMensaje();

            if ("200".equals(codigoRespuesta)) {
                // Actualiza el campo de texto con el puntaje total
                txtPuntajeTotal.setText("Puntaje total: " + mensajeRespuesta);
            } else {
                // Maneja códigos de respuesta inesperados
                throw new RuntimeException(
                        "Código de respuesta inesperado: " + codigoRespuesta + " con mensaje: " + mensajeRespuesta);
            }

        } catch (IOException e) {
            // Manejo de errores de entrada/salida, como problemas de red o comunicación
            System.err.println("Error de comunicación con el cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de comunicación con el cliente. Por favor, intente nuevamente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException e) {
            // Manejo de errores inesperados relacionados con la respuesta del cliente
            System.err.println("Error al solicitar el puntaje total de la evaluación: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al procesar la respuesta del cliente: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            System.err.println("Error inesperado: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado. Por favor, intente nuevamente.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tableHistorico = new javax.swing.JTable();
        btnAtras = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnRespuestas = new javax.swing.JButton();
        txtPuntajeTotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(730, 520));

        tableHistorico.setBackground(new java.awt.Color(204, 204, 204));
        tableHistorico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CI Estudiante", "Puntaje Obtenido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableHistorico);

        btnAtras.setBackground(new java.awt.Color(51, 51, 51));
        btnAtras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTitulo.setText("Evaluación:");

        btnRespuestas.setBackground(new java.awt.Color(0, 0, 153));
        btnRespuestas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRespuestas.setForeground(new java.awt.Color(255, 255, 255));
        btnRespuestas.setText("Ver Respuestas");
        btnRespuestas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespuestasActionPerformed(evt);
            }
        });

        txtPuntajeTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPuntajeTotal.setForeground(new java.awt.Color(0, 0, 153));
        txtPuntajeTotal.setText("Puntaje total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPuntajeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnRespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPuntajeTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // GEN-FIRST:event_btnAtrasActionPerformed
            GestionEvaluacionesPanel evaluacionesPanel = new GestionEvaluacionesPanel(cliente, rol, panelContent);
            evaluacionesPanel.setSize(730, 520);
            evaluacionesPanel.setLocation(0, 0);
            panelContent.removeAll();
            panelContent.add(evaluacionesPanel);
            panelContent.revalidate();
            panelContent.repaint();
        } // GEN-LAST:event_btnAtrasActionPerformed
        catch (IOException ex) {
            Logger.getLogger(VerHistorialesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnRespuestasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRespuestasActionPerformed
        VerRespuestasPanel evaluacionesPanel = new VerRespuestasPanel(cliente, titulo, rol, panelContent);
        evaluacionesPanel.setSize(730, 520);
        evaluacionesPanel.setLocation(0, 0);
        panelContent.removeAll();
        panelContent.add(evaluacionesPanel);
        panelContent.revalidate();
        panelContent.repaint();
    }// GEN-LAST:event_btnRespuestasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnRespuestas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tableHistorico;
    private javax.swing.JLabel txtPuntajeTotal;
    // End of variables declaration//GEN-END:variables
}
