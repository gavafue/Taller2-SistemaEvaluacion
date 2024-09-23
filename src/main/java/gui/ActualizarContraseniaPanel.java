package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import conexion.Cliente;
import java.util.Arrays;

/**
 * JFrame que proveé la interfaz y las funcionalidade necesarias para actualizar
 * la contraseña del usuario.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class ActualizarContraseniaPanel extends javax.swing.JPanel {

    /**
     * Cliente actual conectado.
     */
    private Cliente cliente;

    /**
     * Constructor de la clase que obtiene por parámetro el cliente actual.
     *
     * @param cliente cliente actual.
     */
    public ActualizarContraseniaPanel(Cliente cliente) {
        this.cliente = cliente;
        initComponents();
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
     * Método que permite establecer el cliente actual a partir de otro cliente.
     *
     * @param cliente a establecer.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtContrasenia = new javax.swing.JPasswordField();
        cboxMostrarContraseña = new javax.swing.JCheckBox();
        btnActualizarContrasenia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtContrasenia2 = new javax.swing.JPasswordField();

        setPreferredSize(new java.awt.Dimension(730, 520));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nueva contraseña:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Repetir contraseña:");

        txtContrasenia.setBackground(new java.awt.Color(234, 234, 234));
        txtContrasenia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        cboxMostrarContraseña.setBackground(new java.awt.Color(255, 255, 255));
        cboxMostrarContraseña.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        cboxMostrarContraseña.setForeground(new java.awt.Color(51, 51, 51));
        cboxMostrarContraseña.setText("Mostrar contraseña");
        cboxMostrarContraseña.setBorder(null);
        cboxMostrarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxMostrarContraseñaActionPerformed(evt);
            }
        });

        btnActualizarContrasenia.setBackground(new java.awt.Color(0, 0, 51));
        btnActualizarContrasenia.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        btnActualizarContrasenia.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarContrasenia.setText("Actualizar");
        btnActualizarContrasenia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizarContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarContraseniaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("En este apartado usted podrá actualizar su contraseña.");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("A continuación, rellene ambos campos y seleccione \"Actualizar\":");

        txtContrasenia2.setBackground(new java.awt.Color(234, 234, 234));
        txtContrasenia2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnActualizarContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtContrasenia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboxMostrarContraseña, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtContrasenia2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 52, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtContrasenia2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboxMostrarContraseña)
                .addGap(24, 24, 24)
                .addComponent(btnActualizarContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    /**
     * Método que proveé funcionamiento al combobox mostrar contraseña.
     *
     * @param evt
     */
    private void cboxMostrarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxMostrarContraseñaActionPerformed
        if (cboxMostrarContraseña.isSelected()) {
            txtContrasenia.setEchoChar((char) 0); // Mostrar el texto
            txtContrasenia2.setEchoChar((char) 0);
        } else {
            txtContrasenia.setEchoChar('*'); // Ocultar el texto
            txtContrasenia2.setEchoChar('*'); // Ocultar el texto
        }
    }// GEN-LAST:event_cboxMostrarContraseñaActionPerformed

    /**
     * Método que proveé funcionamiento al botón actualizar, que solicita al
     * server actualizar la contraseña del usuario.
     *
     * @param evt
     */
    private void btnActualizarContraseniaActionPerformed(java.awt.event.ActionEvent evt) {
        String password = new String(txtContrasenia.getPassword());// GEN-FIRST:event_btnActualizarContraseniaActionPerformed
        if (txtContrasenia != null && !password.isBlank() && (Arrays.equals(txtContrasenia.getPassword(), txtContrasenia2.getPassword()))) {
            try {
                this.getCliente().intercambiarMensajes(
                        this.getCliente().getId() + ";;;" + password
                        + ",;,Usuarios,;,CambioPassword");
            } catch (IOException ex) {
                Logger.getLogger(GestionEvaluacionesPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.getCliente().obtenerCodigo().equals("200")) {
                JOptionPane.showMessageDialog(this, "Modificada con éxito");
            } else {
                JOptionPane.showMessageDialog(this, this.getCliente().obtenerMensaje(),
                        "Error" + this.getCliente().obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Asegurese de ingresar datos válidos");
        }
    }// GEN-LAST:event_btnActualizarContraseniaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarContrasenia;
    private javax.swing.JCheckBox cboxMostrarContraseña;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JPasswordField txtContrasenia2;
    // End of variables declaration//GEN-END:variables
}
