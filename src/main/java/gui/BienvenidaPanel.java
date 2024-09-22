package gui;

import conexion.Cliente;

/**
 * JFrame que supone un panel de bienvenida para el usuario.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class BienvenidaPanel extends javax.swing.JPanel {

    /**
     * Constructor que instancia la clase e inicializa los atributos necesarios.
     *
     * @param cliente cliente actual.
     * @param rol rol del cliente actual.
     */
    public BienvenidaPanel(Cliente cliente, String rol) {
        initComponents();
        lblSubtitulo.putClientProperty("FlatLaf.styleClass", "h2");
        if (rol.equals("docente")) {
            txtPrimerRenglon.setText("Este es el espacio donde podrá crear y gestionar evaluaciones de manera fácil.");
            txtSegundoRenglon.setText(
                    "Aquí también podrá acceder a los historiales de evaluaciones para un seguimiento detallado.");
        }
        if (rol.equals("estudiante")) {
            txtPrimerRenglon
                    .setText(
                            "Este es el espacio donde podrá realizar evaluaciones de manera fácil y ver sus resultados.");
            txtSegundoRenglon.setText(
                    "Aquí también podrá acceder a los historiales de evaluaciones para un seguimiento detallado.");
        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSubtitulo = new javax.swing.JLabel();
        txtPrimerRenglon = new javax.swing.JLabel();
        txtSegundoRenglon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(730, 520));
        setRequestFocusEnabled(false);

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSubtitulo.setText("Bienvenido ");

        txtPrimerRenglon.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtPrimerRenglon.setText("Este es el espacio donde podrá crear y gestionar evaluaciones de manera fácil.");

        txtSegundoRenglon.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtSegundoRenglon.setText("Aquí también podrá acceder a los historiales de evaluaciones para un seguimiento detallado. ");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagenbienvenida.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSegundoRenglon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPrimerRenglon, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrimerRenglon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSegundoRenglon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel txtPrimerRenglon;
    private javax.swing.JLabel txtSegundoRenglon;
    // End of variables declaration//GEN-END:variables
}
