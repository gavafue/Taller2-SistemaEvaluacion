/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import conexion.Cliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Gabriel
 */
public class Dashboard extends javax.swing.JFrame {

    private Cliente cliente;
    private String rol;

    /**
     * Creates new form Login1
     */
    public Dashboard(Cliente cliente, String rol) throws IOException {
        initComponents();
        initStyles();
        this.cliente = cliente;
        this.rol = rol;

    }

    private void initStyles() {
        tituloHeader.putClientProperty("FlatLaf.styleClass", "h1");
        lblMenu.putClientProperty("FlatLaf.styleClass", "h2");
        UIManager.put("Button.arc", 0);
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

        background = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        imgEvaluacion = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        btnEvaluaciones = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        header = new javax.swing.JPanel();
        tituloHeader = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1020, 640));

        background.setBackground(new java.awt.Color(255, 255, 255));

        menu.setBackground(new java.awt.Color(13, 71, 161));

        imgEvaluacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgEvaluacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagenes/imgEv.png"))); // NOI18N

        lblMenu.setForeground(new java.awt.Color(255, 255, 255));
        lblMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMenu.setText("Menú");

        btnEvaluaciones.setBackground(new java.awt.Color(26, 35, 126));
        btnEvaluaciones.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnEvaluaciones.setForeground(new java.awt.Color(255, 255, 255));
        btnEvaluaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagenes/evaluacionicon.png"))); // NOI18N
        btnEvaluaciones.setText("Evaluaciones");
        btnEvaluaciones.setBorder(null);
        btnEvaluaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEvaluaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvaluacionesActionPerformed(evt);
            }
        });

        btnInicio.setBackground(new java.awt.Color(26, 35, 126));
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagenes/homeicon.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.setBorder(null);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio.setMargin(new java.awt.Insets(2, 20, 3, 14));
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
                menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imgEvaluacion, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                        .addComponent(lblMenu, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnEvaluaciones, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addComponent(jSeparator1));
        menuLayout.setVerticalGroup(
                menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuLayout.createSequentialGroup()
                                .addComponent(imgEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, 145,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMenu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEvaluaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(369, Short.MAX_VALUE)));

        header.setBackground(new java.awt.Color(25, 118, 210));
        header.setPreferredSize(new java.awt.Dimension(750, 150));

        tituloHeader.setForeground(new java.awt.Color(255, 255, 255));
        tituloHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloHeader.setText("Sistema de evaluación - Linux");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(headerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tituloHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                                .addContainerGap()));
        headerLayout.setVerticalGroup(
                headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(headerLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(tituloHeader)
                                .addContainerGap(43, Short.MAX_VALUE)));

        panelContent.setBackground(new java.awt.Color(255, 255, 255));
        panelContent.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
                panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));
        panelContentLayout.setVerticalGroup(
                panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(backgroundLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap()))));
        backgroundLayout.setVerticalGroup(
                backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));

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
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private javax.swing.JButton btnEvaluaciones;
    private javax.swing.JButton btnInicio;
    private javax.swing.JPanel header;
    private javax.swing.JLabel imgEvaluacion;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel panelContent;
    private javax.swing.JLabel tituloHeader;
    // End of variables declaration//GEN-END:variables

}
