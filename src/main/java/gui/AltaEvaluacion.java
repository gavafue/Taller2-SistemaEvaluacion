package gui;

import conexion.Cliente;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.io.IOException;

public class AltaEvaluacion extends javax.swing.JFrame {

    private String tituloEvaluacion;
    private Cliente cliente;

    public AltaEvaluacion(Cliente cliente) throws FileNotFoundException {
        this.cliente = cliente;
        initComponents();
        
        setLocationRelativeTo(null); //Centrar JFrame
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PanelVista = new javax.swing.JPanel();
        btnNuevaPregunta = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        txtTitulo = new javax.swing.JTextField();
        txtMensaje = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generador de Evaluaciones");
        setName(""); // NOI18N
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        PanelVista.setBackground(new java.awt.Color(204, 204, 204));
        PanelVista.setForeground(new java.awt.Color(0, 0, 0));
        PanelVista.setOpaque(false);
        PanelVista.setLayout(new javax.swing.BoxLayout(PanelVista, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(PanelVista);

        btnNuevaPregunta.setBackground(new java.awt.Color(51, 0, 204));
        btnNuevaPregunta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaPregunta.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaPregunta.setText("Nueva Pregunta");
        btnNuevaPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPreguntaActionPerformed(evt);
            }
        });

        btnFinalizar.setBackground(new java.awt.Color(0, 0, 51));
        btnFinalizar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        txtTitulo.setBackground(new java.awt.Color(204, 204, 204));
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

        txtMensaje.setFont(new java.awt.Font("Lucida Console", 2, 24)); // NOI18N
        txtMensaje.setForeground(new java.awt.Color(255, 0, 0));

        btnAtras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Título de la evaluación");

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Crear una evaluación");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(3, 3, 3)
                            .addComponent(btnNuevaPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                        .addComponent(txtTitulo, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevaPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(txtMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Activa placeholder de ayuda
    private void TextoAyudaOn(JTextField text) {
        Font fuente = text.getFont();
        fuente = fuente.deriveFont(Font.ITALIC);
        text.setFont(fuente);
        text.setForeground(Color.DARK_GRAY);
    }

    //Desactiva placeholder de ayuda
    private void TextoAyudaOff(JTextField text) {
        Font fuente = text.getFont();
        fuente = fuente.deriveFont(Font.PLAIN);
        text.setFont(fuente);
        text.setForeground(Color.DARK_GRAY);
    }

    private void btnNuevaPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaPreguntaActionPerformed
        if (txtTitulo.getText().isBlank() || txtTitulo.getText().equals("Titulo de la Evaluacion")) { //Si el titulo es vacio
            JOptionPane.showMessageDialog(this, "Ingrese un titulo para la evaluación", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                tituloEvaluacion = txtTitulo.getText();
                if (AltaPregunta.getCantidadPreguntas() == 0) { //Si aún no se agregaron preguntas
                    if (!this.existeTitulo(tituloEvaluacion)) { //Si el título es válido
                        cliente.setInstruccion(tituloEvaluacion);
                        txtTitulo.setEditable(false);
                        AltaPregunta ventanaPregunta = new AltaPregunta(PanelVista, cliente);
                        ventanaPregunta.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Título en uso", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    AltaPregunta ventanaPregunta = new AltaPregunta(PanelVista, cliente);
                    ventanaPregunta.setVisible(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(AltaEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnNuevaPreguntaActionPerformed

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        if (txtTitulo.getText().isBlank() || txtTitulo.getText().equals("Titulo de la Evaluacion")) { //Si el titulo esta vacio
            JOptionPane.showMessageDialog(this, "Ingrese un titulo para la evaluación", "Error", JOptionPane.ERROR_MESSAGE);
        } else try {
            if ((AltaPregunta.getCantidadPreguntas() >= 3)) {
                cliente.concatenarMensaje(";;;" + AltaPregunta.getCantidadPreguntas() + ",;,Evaluaciones,;,Alta");
                cliente.intercambiarMensajes(cliente.getInstruccion());
                if (cliente.obtenerCodigo().equals("200")) {
                    JOptionPane.showMessageDialog(this, "Evaluacion creada con exito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    AltaPregunta.setCantidadPreguntas(0);
                    GestionEvaluaciones evaluaciones = new GestionEvaluaciones(cliente, "docente");
                    evaluaciones.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, cliente.obtenerMensaje(), "Error" + cliente.obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Faltan elementos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(AltaEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void txtTituloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTituloFocusGained
        if (txtTitulo.getText().equals("Titulo de la Evaluacion")) {
            txtTitulo.setText(null);
            txtTitulo.requestFocus();
            TextoAyudaOff(txtTitulo);
        }
    }//GEN-LAST:event_txtTituloFocusGained

    private void txtTituloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTituloFocusLost
        if (txtTitulo.getText().isBlank()) {
            TextoAyudaOn(txtTitulo);
            txtTitulo.setText("Titulo de la Evaluacion");
        }
    }//GEN-LAST:event_txtTituloFocusLost

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        try {
            GestionEvaluaciones gestionEvaluaciones = new GestionEvaluaciones(cliente, "docente");
            gestionEvaluaciones.setVisible(true);
            AltaPregunta.setCantidadPreguntas(0);
            this.dispose();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AltaEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AltaEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    //Este método manda al server a verificar si existe el titulo
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtMensaje;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
