package gui;

import conexion.Cliente;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * JFrame destinado a proveer una interfaz gráfica para el acceso al sistema
 * mediante credenciales.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class Login extends javax.swing.JFrame {

    /**
     * Cliente actual conectado al sistema.
     */
    private Cliente cliente;

    /**
     * Constructor vacío que inicializa los componentes de la interfaz y
     * solicita establecer una conexión con el servidor.
     */
    public Login() throws IOException {
        initComponents();
        initStyles();
        this.cliente = new Cliente(); // Nueva instancia de cliente
        this.inicializarInterfaz();
        this.solicitarEstablecerConexion();
    }

    /**
     * Método que inicializa los estilos de la clase.
     */
    private void initStyles() {
        tituloHeader.putClientProperty("FlatLaf.styleClass", "h1");
        UIManager.put("Button.arc", 0);
        lblServidor.setVisible(false);
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
     * @param cliente a establecer.
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
            lblServidor.setVisible(true);
        } catch (IOException e) { // Offline
            lblServidor.setVisible(true);
            lblServidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/errorserver.png")));
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "El servidor no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que redirecciona al cliente a la ventana correspondiente a partir
     * de su rol.
     *
     * @return true si el redireccionamiento fue efectivo o false en caso
     * contrario.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean ventanaInicial() throws FileNotFoundException, IOException {
        String rol = cliente.obtenerMensaje();
        String codigo = cliente.obtenerCodigo();
        boolean validacion = false;

        if (codigo.equals("200")) {
            switch (rol) {
                case "docente":
                case "estudiante":
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

    /**
     * Método que inicializa los elementos gráficos de la interfaz.
     */
    private void inicializarInterfaz() {
        this.txtUsuario.setText("");
        this.txtUsuario.setToolTipText("Escriba aquí su número identificador o CI");
        this.txtContrasenia.setText("");
        this.txtContrasenia.setToolTipText("Escriba aquí su contraseña");
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1020, 640));
        setResizable(false);

        background.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(13, 71, 161));

        imgLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imglogin.png"))); // NOI18N
        imgLogin.setToolTipText("");
        imgLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imgLogin.setName(""); // NOI18N
        imgLogin.setRequestFocusEnabled(false);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );

        header.setBackground(new java.awt.Color(25, 118, 210));
        header.setPreferredSize(new java.awt.Dimension(750, 150));

        tituloHeader.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        tituloHeader.setForeground(new java.awt.Color(255, 255, 255));
        tituloHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloHeader.setText("Iniciar sesión en el Sistema de Evaluación");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tituloHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setForeground(new java.awt.Color(255, 255, 255));

        txtUsuario.setBackground(new java.awt.Color(234, 234, 234));
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

        txtContrasenia.setBackground(new java.awt.Color(234, 234, 234));
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
        lblServidor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblServidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/checkserver.png"))); // NOI18N
        lblServidor.setOpaque(true);

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

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblServidor)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboxMostrarContraseña)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboxMostrarContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(416, 416, 416)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que proveé funcionamiento al combobox mostrar contraseña.
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

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtUsuarioActionPerformed

    /**
     * ´Método que proveé funcionamiento al botón "ingresar", que solicita al
     * servidor validar el usuario y determinar su rol.
     *
     * @param evt
     */
    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnIngresarActionPerformed
        try {
            String ci = txtUsuario.getText();
            String contrasenia = new String(txtContrasenia.getPassword());
            String instruccion = this.getCliente().formatearMensaje(ci + ";;;" + contrasenia, "Usuarios", "Login");
            this.getCliente().intercambiarMensajes(instruccion);
            this.getCliente().setId(ci);
            if (this.ventanaInicial()) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JCheckBox cboxMostrarContraseña;
    private javax.swing.JPanel header;
    private javax.swing.JLabel imgLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblServidor;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel panelContent;
    private javax.swing.JLabel tituloHeader;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
