package gui;

import conexion.Cliente;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * JFrame que representa el sistema de registro de nuevos usuarios de tipo
 * estudiante. Accesible solo por el rol administrativo.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class Registro extends javax.swing.JFrame {

    /**
     * Cliente actual en el sistema.
     */
    private Cliente cliente;

    /**
     * Constructor que instancia la clase.
     * @param cliente cliente actual.
     * @throws java.io.IOException
     */
    public Registro(Cliente cliente) throws IOException {
        initComponents();
        initStyles();
        this.cliente = new Cliente();
        setLocationRelativeTo(null);
    }

    /**
     * Método que inicializa los estilos de la clase.
     */
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
     * @param cliente actual
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @SuppressWarnings("unchecked")
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        tituloHeader = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtContrasenia = new javax.swing.JPasswordField();
        cboxMostrarContraseña = new javax.swing.JCheckBox();
        btnRegistrar = new javax.swing.JButton();
        menu = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(25, 118, 210));
        header.setPreferredSize(new java.awt.Dimension(750, 150));

        tituloHeader.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tituloHeader.setForeground(new java.awt.Color(255, 255, 255));
        tituloHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloHeader.setText("Registrar un nuevo estudiante en el sistema");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tituloHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 608,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
        headerLayout.setVerticalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tituloHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 160,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        background.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 160));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Esta es la forma que tendrá el estudiante de iniciar sesión en el sistema.");
        background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Para registrar un nuevo estudiante en el sistema debes brindar su cédula y su contraseña. ");
        background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Usuario:");
        background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        txtCedula.setBackground(new java.awt.Color(234, 234, 234));
        txtCedula.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCedula.setToolTipText("");
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        background.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 550, 40));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Contraseña:");
        background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, 33));

        txtContrasenia.setBackground(new java.awt.Color(234, 234, 234));
        txtContrasenia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        background.add(txtContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 550, 40));

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
        background.add(cboxMostrarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        btnRegistrar.setBackground(new java.awt.Color(0, 51, 0));
        btnRegistrar.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        background.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 560, 222, 50));

        menu.setBackground(new java.awt.Color(13, 71, 161));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addusericon.png"))); // NOI18N

        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
                menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuLayout.createSequentialGroup()
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 388,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(menuLayout.createSequentialGroup()
                                                .addGap(251, 251, 251)
                                                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(423, Short.MAX_VALUE)));
        menuLayout.setVerticalGroup(
                menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnCerrarSesion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 616,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        background.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, -1, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 631,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que proveé funcionamiento al botón "cerrar sesión", que
     * redirecciona a la pestaña de ingreso al sistema.
     *
     * @param evt
     */
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCerrarSesionActionPerformed
        this.dispose();
        try {
            Login login = new Login();
            login.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }// GEN-LAST:event_btnCerrarSesionActionPerformed

    /**
     *
     * @param evt
     */
    protected void txtCedulaActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'txtCedulaActionPerformed'");
    }

    /**
     * Método que da funcionamiento al checkbox mostrar contraseña.
     *
     * @param evt
     */
    private void cboxMostrarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxMostrarContraseñaActionPerformed
        // TODO add your handling code here:
        if (cboxMostrarContraseña.isSelected()) {
            txtContrasenia.setEchoChar((char) 0); // Mostrar el texto
        } else {
            txtContrasenia.setEchoChar('*'); // Ocultar el texto
        }
    }// GEN-LAST:event_cboxMostrarContraseñaActionPerformed

    /**
     * Método que da funcionamiento al botón registrar, que solicita al servidor
     * la creación de un nuevo usuario. Incluye manejo de errores.
     *
     * @param evt
     */
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String cedula = txtCedula.getText().trim();
            String contrasenia = new String(txtContrasenia.getPassword());
            if (consultarValidez(cedula) && !consultarExistencia(cedula)) {
                try {
                    String instruccion = this.getCliente().formatearMensaje(cedula + ";;;" + contrasenia, "Usuarios",
                            "Alta");
                    this.getCliente().intercambiarMensajes(instruccion);
                    String codigo = this.getCliente().obtenerCodigo();
                    String mensaje = this.getCliente().obtenerMensaje();
                    if ("200".equals(codigo)) {
                        JOptionPane.showMessageDialog(this, mensaje, "Creación Exitosa",
                                JOptionPane.INFORMATION_MESSAGE);
                        txtCedula.setText(null);
                        txtContrasenia.setText((null));
                    } else {
                        JOptionPane.showMessageDialog(this, mensaje, "Error " + codigo, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AltaEvaluacionPanel.class.getName()).log(Level.SEVERE,
                            "Error de comunicación con el servidor", ex);
                    JOptionPane.showMessageDialog(this,
                            "Hubo un problema al comunicarse con el servidor. Por favor, intente nuevamente.",
                            "Error de comunicación", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error: " + cliente.obtenerMensaje(),
                        "Error de Validación", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // GEN-LAST:event_btnRegistrarActionPerformed

    /**
     * Método que consulta al servidor si la CI ingresada es válida.
     *
     * @param cedula del usuario.
     * @return true si es válida y false en caso contrario.
     * @throws java.io.IOException
     */
    public boolean consultarValidez(String cedula) throws IOException {
        String instruccion = this.getCliente().formatearMensaje(cedula, "Usuarios", "Validez");
        this.getCliente().intercambiarMensajes(instruccion);
        return this.getCliente().obtenerCodigo().equals("200");
    }

    /**
     * Método que consulta al servidor si la CI ingresada ya está registrada.
     *
     * @param cedula del usuario.
     * @return true si existe y false en caso contrario.
     * @throws java.io.IOException
     */
    private boolean consultarExistencia(String cedula) throws IOException {
        String instruccion = this.getCliente().formatearMensaje(cedula, "Usuarios", "Existencia");
        this.getCliente().intercambiarMensajes(instruccion);
        return this.getCliente().obtenerCodigo().equals("200");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JCheckBox cboxMostrarContraseña;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel tituloHeader;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtContrasenia;
    // End of variables declaration//GEN-END:variables
}
