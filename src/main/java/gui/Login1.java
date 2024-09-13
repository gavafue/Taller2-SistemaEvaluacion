/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import conexion.Cliente;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Gabriel
 */
public class Login1 extends javax.swing.JFrame {

    private Cliente cliente;

    /**
     * Creates new form Login1
     */
    public Login1(Cliente cliente, String rol) throws IOException {
        initComponents();
        this.cliente = new Cliente(); // Nueva instancia de cliente
        this.inicializarInterfaz();
        this.solicitarEstablecerConexion();

    }

    private void initStyles() {
        tituloHeader.putClientProperty("FlatLaf.styleClass", "h1");
        UIManager.put("Button.arc", 0);
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
     * Método que permite modificar el cliente actual dado otro cliente.
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que solicita al servidor establecer una conexión e indica mediante
     * la interfaz gráfica si la sesión se encuentra online o offline.
     */
    private void solicitarEstablecerConexion() {
        try { // Online
            this.getCliente().establecerConexion();
            this.getCliente().getConexion().probarConexion();
            lblServidor.setForeground(Color.GREEN);
            lblServidor.setText("online");
        } catch (IOException e) { // Offline
            lblServidor.setForeground(Color.red);
            lblServidor.setText("offline");
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "El servidor no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cboxMostrarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxMostrarContraseñaActionPerformed
        if (cboxMostrarContraseña.isSelected()) {
            txtContrasenia.setEchoChar((char) 0); // Mostrar el texto
        } else {
            txtContrasenia.setEchoChar('*'); // Ocultar el texto
        }
    }// GEN-LAST:ev

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnIngresarActionPerformed
        try {
            String ci = txtUsuario.getText();
            String contrasenia = new String(txtContrasenia.getPassword());
            String instruccion = this.getCliente().formatearMensaje(ci + ";;;" + contrasenia, "Usuarios", "Login");
            this.getCliente().intercambiarMensajes(instruccion);
            this.getCliente().setId(ci);
            if (this.ventanaInicial()) {// Método que valida rol y código recibido, abriendo la ventana correspondiente
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login fallido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "El servidor se ha desconectado.", "Error", JOptionPane.ERROR_MESSAGE);
            lblServidor.setForeground(Color.RED);
            lblServidor.setText("[offline]");
        }
    }// GEN-LAST:e

    public boolean ventanaInicial() throws FileNotFoundException, IOException {
        String rol = cliente.obtenerMensaje();
        String codigo = cliente.obtenerCodigo();
        boolean validacion = false;

        if (codigo.equals("200")) {
            switch (rol) {
                case "docente":
                case "estudiante":

                    /*
                     * GestionEvaluaciones evaluaciones = new GestionEvaluaciones(cliente, rol);
                     * evaluaciones.setVisible(true)
                     */
                    Dashboard dashboard = new Dashboard(cliente, rol);
                    dashboard.setVisible(true);
                    validacion = true;
                    break;
                case "administrativo":
                    Registro registros = new Registro(cliente);
                    registros.setVisible(true);
                    validacion = true;
                    break;
            }
        }
        return validacion;
    }

    private void inicializarInterfaz() {
        this.txtUsuario.setText("");
        this.txtUsuario.setToolTipText("Escriba aquí su número identificador o CI");

        this.txtContrasenia.setText("");
        this.txtContrasenia.setToolTipText("Escriba aquí su contraseña");
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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        imgLogin = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        tituloHeader = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtContrasenia = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        lblServidor = new javax.swing.JLabel();
        cboxMostrarContraseña = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setBackground(new java.awt.Color(13, 71, 161));

        imgLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagenes/imglogin.png"))); // NOI18N
        imgLogin.setToolTipText("");
        imgLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgLogin.setName(""); // NOI18N
        imgLogin.setRequestFocusEnabled(false);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
                menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuLayout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(imgLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 239,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(99, Short.MAX_VALUE)));
        menuLayout.setVerticalGroup(
                menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imgLogin, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE));

        background.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 601));

        header.setBackground(new java.awt.Color(25, 118, 210));
        header.setPreferredSize(new java.awt.Dimension(750, 150));

        tituloHeader.setForeground(new java.awt.Color(255, 255, 255));
        tituloHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloHeader.setText("Iniciar sesión en el sistema de Evaluación");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tituloHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE));
        headerLayout.setVerticalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(headerLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(tituloHeader)
                                .addContainerGap(103, Short.MAX_VALUE)));

        background.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 0, 620, 160));

        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setForeground(new java.awt.Color(255, 255, 255));

        txtUsuario.setBackground(new java.awt.Color(204, 204, 204));
        txtUsuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtUsuario.setToolTipText("");
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Usuario:");

        txtContrasenia.setBackground(new java.awt.Color(204, 204, 204));
        txtContrasenia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Contraseña:");

        btnIngresar.setBackground(new java.awt.Color(0, 0, 51));
        btnIngresar.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Ingresar");
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        lblServidor.setBackground(new java.awt.Color(255, 255, 255));
        lblServidor.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblServidor.setForeground(new java.awt.Color(0, 255, 0));
        lblServidor.setOpaque(true);

        cboxMostrarContraseña.setBackground(new java.awt.Color(255, 255, 255));
        cboxMostrarContraseña.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        cboxMostrarContraseña.setForeground(new java.awt.Color(51, 51, 51));
        cboxMostrarContraseña.setText("Mostrar contraseña");
        cboxMostrarContraseña.setBorder(null);

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
                panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContentLayout.createSequentialGroup()
                                .addContainerGap(29, Short.MAX_VALUE)
                                .addGroup(panelContentLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cboxMostrarContraseña)
                                        .addGroup(panelContentLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(panelContentLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(txtContrasenia,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 550,
                                                                Short.MAX_VALUE)
                                                        .addComponent(txtUsuario))
                                                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 222,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                        panelContentLayout.createSequentialGroup()
                                                                .addGap(334, 334, 334)
                                                                .addComponent(lblServidor,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 203,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(31, 31, 31)));
        panelContentLayout.setVerticalGroup(
                panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelContentLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboxMostrarContraseña)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47,
                                        Short.MAX_VALUE)
                                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)));

        background.add(panelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 159, 610, 440));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtUsuarioActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnIngresarActionPerformed

    private void btnEvaluacionesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEvaluacionesActionPerformed
        GestionEvaluacionesPanel evaluacionesPanel;
        try {
            evaluacionesPanel = new GestionEvaluacionesPanel(cliente, rol);
            evaluacionesPanel.setSize(730, 520);
            evaluacionesPanel.setLocation(0, 0);
            panelContent.removeAll();
            panelContent.add(evaluacionesPanel);
            panelContent.revalidate();
            panelContent.repaint();
        } catch (IOException ex) {
            Logger.getLogger(Login1.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO add your handling code here:
        // TODO add your handling code here:
    }// GEN-LAST:event_btnEvaluacionesActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnInicioActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JCheckBox cboxMostrarContraseña;
    private javax.swing.JPanel header;
    private javax.swing.JLabel imgLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblServidor;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel panelContent;
    private javax.swing.JLabel tituloHeader;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
