package gui;

import conexion.Cliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Registro extends javax.swing.JFrame {

    private Cliente cliente;

    public Registro(Cliente cli) {
        this.cliente = cli;
        initComponents();
        setLocationRelativeTo(null); //Centrar
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        txtCedula = new javax.swing.JTextField();
        txtContrasenia = new javax.swing.JPasswordField();
        cboxMostrarContraseña = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblRegistro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnRegistrar.setBackground(new java.awt.Color(0, 0, 51));
        btnRegistrar.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        txtCedula.setBackground(new java.awt.Color(204, 204, 204));
        txtCedula.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(0, 0, 0));
        txtCedula.setToolTipText("");
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });

        txtContrasenia.setBackground(new java.awt.Color(204, 204, 204));
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

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Contraseña:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Cedula:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxMostrarContraseña)
                            .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(cboxMostrarContraseña)
                .addGap(54, 54, 54)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        lblRegistro.setBackground(new java.awt.Color(204, 204, 204));
        lblRegistro.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        lblRegistro.setForeground(new java.awt.Color(255, 255, 255));
        lblRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistro.setText("Registro");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboxMostrarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxMostrarContraseñaActionPerformed
        if (cboxMostrarContraseña.isSelected()) {
            txtContrasenia.setEchoChar((char) 0); //Mostrar el texto
        } else {
            txtContrasenia.setEchoChar('*'); //Ocultar el texto
        }
    }//GEN-LAST:event_cboxMostrarContraseñaActionPerformed

    //Cliente solicita crear usuario con "ci;;;contraseña,;,Usuarios,;,Alta"
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            // Obtener los valores ingresados por el usuario
            String cedula = txtCedula.getText().trim();
            String contrasenia = new String(txtContrasenia.getPassword());
            
            if(comprobarValidez(cedula)){
                String instruccion = cliente.formatearMensaje(cedula + ";;;" + contrasenia, "Usuarios", "Alta");
                try {
                    cliente.intercambiarMensajes(instruccion);
                    System.out.println(cliente.getRespuesta() + "\n");//Temporal para ver la respuesta del servidor por consola
                    if (cliente.obtenerCodigo().equals("200")) {
                        JOptionPane.showMessageDialog(this, cliente.obtenerMensaje(), "Creación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, cliente.obtenerMensaje(), "Error" + cliente.obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AltaEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(this, cliente.obtenerMensaje(), "Error" + cliente.obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_btnRegistrarActionPerformed

    //Este método manda al server a verificar si el título es válido
    private boolean comprobarValidez(String cedula) throws IOException {
        String instruccion = cliente.formatearMensaje(cedula, "Usuarios", "Existencia");
        cliente.intercambiarMensajes(instruccion);
        return cliente.obtenerCodigo().equals("200");
    }
       
    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus

    }//GEN-LAST:event_formWindowGainedFocus

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed

    }//GEN-LAST:event_txtCedulaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JCheckBox cboxMostrarContraseña;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblRegistro;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtContrasenia;
    // End of variables declaration//GEN-END:variables
}
