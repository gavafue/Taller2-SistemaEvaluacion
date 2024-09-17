/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import conexion.Cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel
 */
public class GestionEvaluacionesPanel extends javax.swing.JPanel {

    private Cliente cliente;
    private String rol;
    private JPanel panelContent;
    private AltaEvaluacionPanel generador;

    public void setGenerador(AltaEvaluacionPanel generador) {
        this.generador = generador;
    }

    /**
     * Creates new form GestionEvaluacionesPanel
     */
    public GestionEvaluacionesPanel(Cliente cliente, String rol, JPanel panelContent) throws IOException {
        this.rol = rol;
        this.cliente = cliente;
        this.panelContent = panelContent;
        initComponents();
        this.determinarInterfaz(); // Muestra determinados elementos gráficos dependiendo del rol
        this.solicitarTitulosEvaluaciones();
        setGenerador(new AltaEvaluacionPanel(cliente, panelContent));
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
            AltaPreguntaPanel framePregunta = new AltaPreguntaPanel(null, this.getCliente(), "estudiante",
                    panelContent, this.generador);
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
                try {
                    btnAgregarActionPerformed(evt);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
                try {
                    btnHistoricoActionPerformed(evt);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 396,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(305, Short.MAX_VALUE))
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
                                .addGap(35, 35, 35)
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException {// GEN-FIRST:event_btnAgregarActionPerformed
        generador.setSize(730, 520);
        generador.setLocation(0, 0);
        panelContent.removeAll();
        panelContent.add(generador);
        panelContent.revalidate();
        panelContent.repaint();
    }// GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEliminarActionPerformed
        int selectedRow = tableEvaluaciones.getSelectedRow();
        if (selectedRow != -1) {
            String titulo = (String) tableEvaluaciones.getValueAt(selectedRow, 0);

            // Ventana de confirmación
            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro? En caso de eliminar la evaluación, también se eliminarán todos los historiales asociados a esta.",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) { // Si el usuario confirma
                try {
                    this.getCliente().intercambiarMensajes(titulo + ",;,Evaluaciones,;,Eliminar");
                    if (this.getCliente().obtenerCodigo().equals("200")) {
                        JOptionPane.showMessageDialog(null, "Evaluación eliminada");
                        this.solicitarTitulosEvaluaciones();
                        cargarTablaEvaluaciones();
                    } else {
                        JOptionPane.showMessageDialog(this, this.getCliente().obtenerMensaje(),
                                "Error " + this.getCliente().obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            } // Si el usuario elige "No", no se hace nada
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una evaluación");
        }
    }// GEN-LAST:event_btnEliminarActionPerformed

    private void tableEvaluacionesMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tableEvaluacionesMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_tableEvaluacionesMouseClicked

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_btnHistoricoActionPerformed
        int selectedRow = tableEvaluaciones.getSelectedRow();
        if (selectedRow != -1) {
            String titulo = (String) tableEvaluaciones.getValueAt(selectedRow, 0);
            VerHistorialesPanel historico = new VerHistorialesPanel(titulo, this.getCliente(), this.getRol(),
                    panelContent);
            if (this.getRol().equals("docente")) {
                if (historico.hayHistorialesDisponibles()) {
                    historico.setSize(730, 520);
                    historico.setLocation(0, 0);
                    panelContent.removeAll();
                    panelContent.add(historico);
                    panelContent.revalidate();
                    panelContent.repaint();
                    historico.setVisible(true);
                }
            } else if (this.getRol().equals("estudiante")) {
                if (historico.hayHistorialesDisponibles() && historico.estudianteRealizoEvaluacion()) {
                    historico.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No has realizado esta evaluación aún");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una evaluación.");
        }

    }// GEN-LAST:event_btnHistoricoActionPerformed

    private void btnRealizarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRealizarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnRealizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnRealizar;
    private javax.swing.JButton btnRealizarAlAzar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tableEvaluaciones;
    // End of variables declaration//GEN-END:variables
}
