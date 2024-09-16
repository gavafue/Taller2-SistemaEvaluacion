/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import conexion.Cliente;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Gabriel
 */
public class Registro extends javax.swing.JFrame {

    private Cliente cliente;

    /**
     * Creates new form Login1
     */
    public Registro(Cliente cliente) throws IOException {
        initComponents();
        initStyles();
        this.cliente = new Cliente();
        setLocationRelativeTo(null); // Nueva instancia de cliente

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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(25, 118, 210));
        header.setPreferredSize(new java.awt.Dimension(750, 150));

        tituloHeader.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
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

        jLabel4.setText("que tendrá el estudiante de iniciar sesión en su sistema.");
        background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jLabel3.setText(
                "Para registrar un nuevo estudiante en el sistema debes brindar su cédula y su contraseña. Esta es la forma");
        background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Usuario:");
        background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        txtCedula.setBackground(new java.awt.Color(204, 204, 204));
        txtCedula.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCedula.setToolTipText("");
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        background.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 550, 40));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Contraseña:");
        background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, 33));

        txtContrasenia.setBackground(new java.awt.Color(204, 204, 204));
        txtContrasenia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        background.add(txtContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 550, 40));

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
        background.add(cboxMostrarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        btnRegistrar.setBackground(new java.awt.Color(0, 51, 0));
        btnRegistrar.setFont(new java.awt.Font("Cascadia Code", 0, 18)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar estudiante");
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnRegistrarActionPerformed(evt);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        background.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, 222, 50));

        menu.setBackground(new java.awt.Color(13, 71, 161));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addusericon.png"))); // NOI18N

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
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)));
        menuLayout.setVerticalGroup(
                menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnCerrarSesion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11,
                                        Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 674,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        background.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, -1, 720));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 717,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCerrarSesionActionPerformed
        this.dispose();
        try {
            Login login = new Login();
            login.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }// GEN-LAST:event_btnCerrarSesionActionPerformed

    protected void txtCedulaActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'txtCedulaActionPerformed'");
    }

    private void cboxMostrarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxMostrarContraseñaActionPerformed
        // TODO add your handling code here:
        if (cboxMostrarContraseña.isSelected()) {
            txtContrasenia.setEchoChar((char) 0); // Mostrar el texto
        } else {
            txtContrasenia.setEchoChar('*'); // Ocultar el texto
        }
    }// GEN-LAST:event_cboxMostrarContraseñaActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
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
                    JOptionPane.showMessageDialog(this, mensaje, "Creación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, mensaje, "Error " + codigo, JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(AltaEvaluacion.class.getName()).log(Level.SEVERE,
                        "Error de comunicación con el servidor", ex);
                JOptionPane.showMessageDialog(this,
                        "Hubo un problema al comunicarse con el servidor. Por favor, intente nuevamente.",
                        "Error de comunicación", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "La cédula es inválida o ya existe en el sistema.",
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }
    // GEN-LAST:event_btnRegistrarActionPerformed

    /**
     * Método que consulta al servidor si la CI ingresada es válida.
     *
     * @param cedula
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
     * @param cedula
     * @return true si existe y false en caso contrario.
     * @throws java.io.IOException
     */
    private boolean consultarExistencia(String cedula) throws IOException {
        String instruccion = this.getCliente().formatearMensaje(cedula, "Usuarios", "Existencia");
        this.getCliente().intercambiarMensajes(instruccion);
        return this.getCliente().obtenerCodigo().equals("200");
    }

    /**
     * @param args the command line arguments
     */
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel tituloHeader;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtContrasenia;
    // End of variables declaration//GEN-END:variables

}
