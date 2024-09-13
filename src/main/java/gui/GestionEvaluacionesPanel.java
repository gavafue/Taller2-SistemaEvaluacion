/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import conexion.Cliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel
 */
public class GestionEvaluacionesPanel extends javax.swing.JPanel {

    private Cliente cliente;
    private String rol;

    /**
     * Creates new form GestionEvaluacionesPanel
     */
    public GestionEvaluacionesPanel(Cliente cliente, String rol) throws IOException {
        this.rol = rol;
        this.cliente = cliente;
        initComponents();
        this.determinarInterfaz(); // Muestra determinados elementos gráficos dependiendo del rol
        this.solicitarTitulosEvaluaciones();
        lblUsuario.setText("Usuario: " + cliente.getId());
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
     * Método que permite modificar el rol del cliente, dado otro rol.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    public void solicitarEvaluacionAlAzar() throws IOException {
        String aEnviar = this.getCliente().formatearMensaje("Solicitud", "Evaluaciones", "ObtenerTituloAlAzar");
        // Solicita un título aleatorio
        this.getCliente().intercambiarMensajes(aEnviar);
        if (this.getCliente().obtenerCodigo().equals("200")) {
            String titulo = this.getCliente().obtenerMensaje();
            this.solicitarEvaluacion(titulo);
        } else {
            JOptionPane.showMessageDialog(this, this.getCliente().obtenerMensaje(), this.getCliente().obtenerCodigo(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que permite solicitar una evaluación.
     *
     * @param titulo de la evaluación seleccionada.
     * @throws IOException
     */
    public void solicitarEvaluacion(String titulo) throws IOException {
        String aEnviar = this.getCliente().formatearMensaje(titulo + ";;;0", "Evaluaciones", "ObtenerPregunta");
        // Solicita la pregunta 0
        // El servidor deberia responder con:
        // TipoPregunta;;;Enunciado;;;Opc1(opcional);;Opc2(opcional);;;Opc3(opcional);;;Opc4(opcional);;;puntaje,;,200
        this.getCliente().intercambiarMensajes(aEnviar);
        String[] pregunta = this.getCliente().obtenerMensaje().split(";;;");
        if (this.getCliente().obtenerCodigo().equals("200")) {
            // MulitpleOpcion;;;Enunciado;;;Opc1;;Opc2;;;Opc3;;;Opc4;;;puntaje
            // VerdaderoFalso;;;Enunciado;;;puntaje
            // Completar;;;Enunciado;;;puntaje
            AltaPregunta framePregunta = new AltaPregunta(null, this.getCliente(), "estudiante");
            framePregunta.setRespuestas(this.getCliente().getId() + ";;;" + titulo);
            framePregunta.setEvaluacion(titulo);
            framePregunta.cargarEnGui(pregunta, framePregunta);
        } else {
            JOptionPane.showMessageDialog(this, "Error en la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void solicitarTitulosEvaluaciones() throws IOException {
        cliente.intercambiarMensajes("titulos,;,Evaluaciones,;,Listar");
        if (this.getCliente().obtenerCodigo().equals("200")) {
            this.cargarTablaEvaluaciones();
        } else {
            JOptionPane.showMessageDialog(this, "El servidor no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarTablaEvaluaciones() throws IOException {
        String[] titulos = this.getCliente().obtenerMensaje().split(";;;");
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no serán editables
            }
        };
        modelo.addColumn("Títulos");
        for (String titulo : titulos) {
            modelo.addRow(new Object[] { titulo });
        }
        tableEvaluaciones.setModel(modelo);
    } // Color alternado para las filas

    private void determinarInterfaz() { // Administrativo no puede acceder
        if (rol.equals("docente")) { // Docente
            btnAgregar.setVisible(true);
            btnRealizar.setVisible(false);
            btnRealizarAlAzar.setVisible(false);
            btnEliminar.setVisible(true);
        } else { // Estudiante
            btnAgregar.setVisible(false);
            btnRealizar.setVisible(true);
            btnRealizarAlAzar.setVisible(true);
            btnEliminar.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRealizarAlAzar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableEvaluaciones = new javax.swing.JTable();
        btnHistorico = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnRealizar = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        btnActualizarPassword = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(730, 520));

        btnRealizarAlAzar.setBackground(new java.awt.Color(0, 0, 153));
        btnRealizarAlAzar.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarAlAzar.setText("Al Azar");
        btnRealizarAlAzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarAlAzarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(0, 0, 51));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(51, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tableEvaluaciones.setBackground(new java.awt.Color(204, 204, 204));
        tableEvaluaciones.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Titulo"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        tableEvaluaciones.setShowGrid(true);
        tableEvaluaciones.setShowHorizontalLines(false);
        tableEvaluaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEvaluacionesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableEvaluaciones);

        btnHistorico.setBackground(new java.awt.Color(0, 51, 51));
        btnHistorico.setForeground(new java.awt.Color(255, 255, 255));
        btnHistorico.setText("Historico");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblTitulo.setText("Evaluaciones");

        btnRealizar.setBackground(new java.awt.Color(0, 0, 153));
        btnRealizar.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizar.setText("Realizar");
        btnRealizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarActionPerformed(evt);
            }
        });

        lblUsuario.setText("Bienvenido ");

        btnActualizarPassword.setBackground(new java.awt.Color(102, 102, 102));
        btnActualizarPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarPassword.setText("Actualizar Contraseña");
        btnActualizarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblUsuario,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnActualizarPassword))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblTitulo,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 396,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 299, Short.MAX_VALUE)))
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 479,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(btnHistorico,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 190,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(btnEliminar,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(btnAgregar,
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE))
                                                        .addComponent(btnRealizar,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnRealizarAlAzar,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(20, 20, 20)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblUsuario)
                                        .addComponent(btnActualizarPassword))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTitulo)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 414,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addComponent(btnRealizarAlAzar, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(33, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRealizarAlAzarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRealizarAlAzarActionPerformed
        try {
            this.solicitarEvaluacionAlAzar();
        } catch (IOException ex) {
            Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// GEN-LAST:event_btnRealizarAlAzarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnEliminarActionPerformed

    private void tableEvaluacionesMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tableEvaluacionesMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_tableEvaluacionesMouseClicked

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnHistoricoActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_btnHistoricoActionPerformed

    private void btnRealizarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRealizarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnRealizarActionPerformed

    private void btnActualizarPasswordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnActualizarPasswordActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnActualizarPasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarPassword;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnRealizar;
    private javax.swing.JButton btnRealizarAlAzar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tableEvaluaciones;
    // End of variables declaration//GEN-END:variables
}
