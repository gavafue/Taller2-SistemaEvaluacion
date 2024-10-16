package gui;

import conexion.Cliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 * JFrame que representa el Dashboard del sistema.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Cliente actual en el sistema.
     */
    private Cliente cliente;

    /**
     * Rol del cliente actual.
     */
    private String rol;

    /**
     * Constructor que inicializa los elementos y atributos de la clase.
     * Abre por defecto el panel de bienvenida.
     * @param cliente cliente actual.
     * @param rol rol del cliente actual.
     * @throws IOException
     */
    public Dashboard(Cliente cliente, String rol) throws IOException {
        initComponents();
        initStyles();
        this.cliente = cliente;
        this.rol = rol;
        BienvenidaPanel panelBienvenida = new BienvenidaPanel(cliente, rol);
        panelBienvenida.setSize(730, 520);
        panelBienvenida.setLocation(0, 0);
        panelContent.removeAll();
        panelContent.add(panelBienvenida);
        panelContent.revalidate();
        panelContent.repaint();
    }

    /**
     * Método que inicializa los estilos de la interfaz.
     */
    private void initStyles() {
        tituloHeader.putClientProperty("FlatLaf.styleClass", "h1");
        lblMenu.putClientProperty("FlatLaf.styleClass", "h2");
        UIManager.put("Button.arc", 0);
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        background = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        imgEvaluacion = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        btnEvaluaciones = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnConsola = new javax.swing.JButton();
        btnPerfil = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        tituloHeader = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        panelContent = new javax.swing.JPanel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        background.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(13, 71, 161));

        imgEvaluacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgEvaluacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgEv.png"))); // NOI18N

        lblMenu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(255, 255, 255));
        lblMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMenu.setText("Menú");

        btnEvaluaciones.setBackground(new java.awt.Color(26, 35, 126));
        btnEvaluaciones.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnEvaluaciones.setForeground(new java.awt.Color(255, 255, 255));
        btnEvaluaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/evaluacionicon.png"))); // NOI18N
        btnEvaluaciones.setText("Evaluaciones");
        btnEvaluaciones.setBorder(null);
        btnEvaluaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEvaluaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvaluacionesActionPerformed(evt);
            }
        });

        btnInicio.setBackground(new java.awt.Color(26, 35, 126));
        btnInicio.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/homeicon.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.setBorder(null);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInicio.setMargin(new java.awt.Insets(2, 20, 3, 14));
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        btnConsola.setBackground(new java.awt.Color(102, 102, 102));
        btnConsola.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnConsola.setForeground(new java.awt.Color(255, 255, 255));
        btnConsola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/linuxicon.png"))); // NOI18N
        btnConsola.setText("Abrir consola Linux");
        btnConsola.setBorder(null);
        btnConsola.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnConsola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsolaActionPerformed(evt);
            }
        });

        btnPerfil.setBackground(new java.awt.Color(26, 35, 126));
        btnPerfil.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnPerfil.setForeground(new java.awt.Color(255, 255, 255));
        btnPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profileicon.png"))); // NOI18N
        btnPerfil.setText("Perfil");
        btnPerfil.setBorder(null);
        btnPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgEvaluacion, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
            .addComponent(lblMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEvaluaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsola, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPerfil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(imgEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEvaluaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        header.setBackground(new java.awt.Color(25, 118, 210));
        header.setPreferredSize(new java.awt.Dimension(750, 150));

        tituloHeader.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tituloHeader.setForeground(new java.awt.Color(255, 255, 255));
        tituloHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloHeader.setText("Sistema de Evaluación - Linux");

        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tituloHeader)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
     * Método que proveé funcionamiento al botón consola, que abre la consola
     * linux.
     *
     * @param evt
     */
    private void btnConsolaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPerfil1ActionPerformed
        Consola consola = new Consola();
        consola.setVisible(true);
        consola.setLocationRelativeTo(null);
    }// GEN-LAST:event_btnPerfil1ActionPerformed

    /**
     * Método que proveé funcionamiento al botón cerrar sesión, que cierra la
     * sesión actual y redirecciona al login.
     *
     * @param evt
     */
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCerrarSesionActionPerformed
        this.dispose();
        try {
            Login login = new Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }// GEN-LAST:event_btnCerrarSesionActionPerformed

    /**
     * Método que proveé funcionamiento al botón perfil, que deriva a la pestaña
     * de cambio de contraseña.
     *
     * @param evt
     */
    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {
        ActualizarContraseniaPanel actualizarContraseniaPanel = new ActualizarContraseniaPanel(cliente); // Aquí puedes mostrar un mensaje al usuario si lo deseas
        actualizarContraseniaPanel.setSize(730, 520);
        actualizarContraseniaPanel.setLocation(0, 0);
        panelContent.removeAll();
        panelContent.add(actualizarContraseniaPanel);
        panelContent.revalidate();
        panelContent.repaint();
    }

    /**
     * Método que proveé funcionamiento al botón evaluaciones, que redirecciona
     * a la pestaña de gestión de evaluaciones.
     *
     * @param evt
     */
    private void btnEvaluacionesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEvaluacionesActionPerformed
        try {
            GestionEvaluacionesPanel evaluacionesPanel = new GestionEvaluacionesPanel(cliente, rol,
                    panelContent);
            evaluacionesPanel.setSize(730, 520);
            evaluacionesPanel.setLocation(0, 0);
            panelContent.removeAll();
            panelContent.add(evaluacionesPanel);
            panelContent.revalidate();
            panelContent.repaint();
        } catch (IOException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }// GEN-LAST:event_btnEvaluacionesActionPerformed

    /**
     * Método que proveé funcionamiento al botón inicio, que redirecciona a una
     * pestaña con un mesnaje de bienvenida al sistema.
     *
     * @param evt 
     */
    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInicioActionPerformed
        BienvenidaPanel panelBienvenida = new BienvenidaPanel(cliente, rol);
        panelBienvenida.setSize(730, 520);
        panelBienvenida.setLocation(0, 0);
        panelContent.removeAll();
        panelContent.add(panelBienvenida);
        panelContent.revalidate();
        panelContent.repaint();
    }// GEN-LAST:event_btnInicioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnConsola;
    private javax.swing.JButton btnEvaluaciones;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnPerfil;
    private javax.swing.JPanel header;
    private javax.swing.JLabel imgEvaluacion;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel panelContent;
    private javax.swing.JLabel tituloHeader;
    // End of variables declaration//GEN-END:variables
}
