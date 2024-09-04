package gui;

import conexion.Cliente;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AltaPregunta extends javax.swing.JFrame {

    private Cliente cliente;
    private String evaluacion;
    private String pregunta;
    private String enunciado;
    private String tipoPregunta;
    private String respuestas;
    private static int cantidadPreguntas; // Atributo propio de a clase y no de la instancia
    private JPanel vistaPrevia; // Atributo poder intercambiar datos con la vista previa del Generador

    public AltaPregunta(JPanel vistaPrevia, Cliente cliente) {
        this.cliente = cliente;
        this.vistaPrevia = vistaPrevia;
        initComponents();
        setLocationRelativeTo(null); // Centrar JFrame
        panelEnunciado.setVisible(true); // Un panel en funcion del tipo de pregunta, por defecto el que permite crear el enunciado y seleccionar el tipo
        panelMultiple.setVisible(false);
        panelRespuesta.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelEnunciado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboxTipoPregunta = new javax.swing.JComboBox<>();
        bntSiguiente = new javax.swing.JButton();
        lblSigno = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEnunciado = new javax.swing.JTextArea();
        btnConsola2 = new javax.swing.JButton();
        panelMultiple = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboxOpciones = new javax.swing.JComboBox<>();
        txtOpc1 = new javax.swing.JTextField();
        txtOpc3 = new javax.swing.JTextField();
        txtOpc2 = new javax.swing.JTextField();
        txtOpc4 = new javax.swing.JTextField();
        panelPuntaje = new javax.swing.JPanel();
        btnFinalizarMultiple = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        spnPuntajeMultiple = new javax.swing.JSpinner();
        btnConsola = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblEnunciadoMultiple = new javax.swing.JLabel();
        panelRespuesta = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEnunciadoVF = new javax.swing.JTextArea();
        panel = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cboxVerdaderoOFalso = new javax.swing.JComboBox<>();
        btnFinalizarEspaciosVF = new javax.swing.JButton();
        txtRespuesta = new javax.swing.JTextField();
        spnPuntaje = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        btnConsola1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setForeground(panelEnunciado.getForeground());
        setLocationByPlatform(true);
        setResizable(false);

        panelEnunciado.setBackground(new java.awt.Color(255, 255, 255));
        panelEnunciado.setPreferredSize(new java.awt.Dimension(587, 227));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Tipo");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Pregunta");

        cboxTipoPregunta.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        cboxTipoPregunta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rellenar espacios", "Multiple opción", "Verdadero o Falso" }));
        cboxTipoPregunta.setBorder(null);
        cboxTipoPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxTipoPreguntaActionPerformed(evt);
            }
        });

        bntSiguiente.setBackground(new java.awt.Color(0, 0, 204));
        bntSiguiente.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        bntSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        bntSiguiente.setText("Siguiente");
        bntSiguiente.setMaximumSize(new java.awt.Dimension(129, 27));
        bntSiguiente.setMinimumSize(new java.awt.Dimension(129, 27));
        bntSiguiente.setPreferredSize(new java.awt.Dimension(129, 27));
        bntSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSiguienteActionPerformed(evt);
            }
        });

        txtEnunciado.setBackground(new java.awt.Color(204, 204, 204));
        txtEnunciado.setColumns(20);
        txtEnunciado.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        txtEnunciado.setForeground(new java.awt.Color(0, 0, 0));
        txtEnunciado.setLineWrap(true);
        txtEnunciado.setRows(5);
        jScrollPane1.setViewportView(txtEnunciado);

        btnConsola2.setBackground(new java.awt.Color(0, 153, 255));
        btnConsola2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnConsola2.setText("Abrir consola");
        btnConsola2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsola2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEnunciadoLayout = new javax.swing.GroupLayout(panelEnunciado);
        panelEnunciado.setLayout(panelEnunciadoLayout);
        panelEnunciadoLayout.setHorizontalGroup(
            panelEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEnunciadoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEnunciadoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1))
                    .addGroup(panelEnunciadoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConsola2)
                        .addGap(38, 38, 38))
                    .addGroup(panelEnunciadoLayout.createSequentialGroup()
                        .addGroup(panelEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxTipoPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                        .addComponent(bntSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblSigno)
                .addGap(7, 7, 7))
        );
        panelEnunciadoLayout.setVerticalGroup(
            panelEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEnunciadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnConsola2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(panelEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bntSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEnunciadoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboxTipoPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
            .addComponent(lblSigno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelMultiple.setBackground(new java.awt.Color(255, 255, 255));
        panelMultiple.setPreferredSize(new java.awt.Dimension(587, 227));
        panelMultiple.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Opción 3");
        jLabel4.setToolTipText("");
        panelMultiple.add(jLabel4);
        jLabel4.setBounds(20, 120, 97, 32);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Opción 2");
        panelMultiple.add(jLabel5);
        jLabel5.setBounds(20, 90, 140, 32);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Opción 4");
        panelMultiple.add(jLabel6);
        jLabel6.setBounds(20, 150, 150, 32);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Respuesta");
        panelMultiple.add(jLabel7);
        jLabel7.setBounds(20, 210, 170, 30);

        cboxOpciones.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cboxOpciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opción 1", "Opción 2", "Opción 3", "Opción 4" }));
        cboxOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxOpcionesActionPerformed(evt);
            }
        });
        panelMultiple.add(cboxOpciones);
        cboxOpciones.setBounds(270, 210, 130, 30);

        txtOpc1.setBackground(new java.awt.Color(204, 204, 204));
        txtOpc1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc1.setForeground(new java.awt.Color(0, 0, 0));
        txtOpc1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtOpc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOpc1ActionPerformed(evt);
            }
        });
        panelMultiple.add(txtOpc1);
        txtOpc1.setBounds(180, 60, 225, 30);

        txtOpc3.setBackground(new java.awt.Color(204, 204, 204));
        txtOpc3.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc3.setForeground(new java.awt.Color(0, 0, 0));
        txtOpc3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        panelMultiple.add(txtOpc3);
        txtOpc3.setBounds(180, 120, 225, 30);

        txtOpc2.setBackground(new java.awt.Color(204, 204, 204));
        txtOpc2.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc2.setForeground(new java.awt.Color(0, 0, 0));
        txtOpc2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        panelMultiple.add(txtOpc2);
        txtOpc2.setBounds(180, 90, 225, 30);

        txtOpc4.setBackground(new java.awt.Color(204, 204, 204));
        txtOpc4.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc4.setForeground(new java.awt.Color(0, 0, 0));
        txtOpc4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        panelMultiple.add(txtOpc4);
        txtOpc4.setBounds(180, 150, 225, 30);

        panelPuntaje.setBackground(new java.awt.Color(0, 0, 51));

        btnFinalizarMultiple.setBackground(new java.awt.Color(51, 0, 204));
        btnFinalizarMultiple.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnFinalizarMultiple.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarMultiple.setText("Finalizar");
        btnFinalizarMultiple.setMaximumSize(new java.awt.Dimension(129, 27));
        btnFinalizarMultiple.setMinimumSize(new java.awt.Dimension(129, 27));
        btnFinalizarMultiple.setPreferredSize(new java.awt.Dimension(129, 27));
        btnFinalizarMultiple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarMultipleActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Puntaje");

        spnPuntajeMultiple.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        spnPuntajeMultiple.setModel(new javax.swing.SpinnerNumberModel());

        btnConsola.setBackground(new java.awt.Color(0, 153, 255));
        btnConsola.setForeground(new java.awt.Color(255, 255, 255));
        btnConsola.setText("Abrir consola");
        btnConsola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsolaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPuntajeLayout = new javax.swing.GroupLayout(panelPuntaje);
        panelPuntaje.setLayout(panelPuntajeLayout);
        panelPuntajeLayout.setHorizontalGroup(
            panelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPuntajeLayout.createSequentialGroup()
                .addGroup(panelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPuntajeLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(btnFinalizarMultiple, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelPuntajeLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(spnPuntajeMultiple, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelPuntajeLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnConsola)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        panelPuntajeLayout.setVerticalGroup(
            panelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPuntajeLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(btnConsola)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spnPuntajeMultiple, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnFinalizarMultiple, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        panelMultiple.add(panelPuntaje);
        panelPuntaje.setBounds(440, 0, 230, 260);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Opción 1");
        panelMultiple.add(jLabel8);
        jLabel8.setBounds(20, 60, 140, 32);

        lblEnunciadoMultiple.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblEnunciadoMultiple.setForeground(new java.awt.Color(0, 0, 0));
        lblEnunciadoMultiple.setText(":P");
        lblEnunciadoMultiple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEnunciadoMultipleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEnunciadoMultipleMouseEntered(evt);
            }
        });
        panelMultiple.add(lblEnunciadoMultiple);
        lblEnunciadoMultiple.setBounds(20, 20, 370, 24);

        panelRespuesta.setBackground(new java.awt.Color(255, 255, 255));
        panelRespuesta.setPreferredSize(new java.awt.Dimension(587, 227));

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Enunciado");

        txtEnunciadoVF.setBackground(new java.awt.Color(204, 204, 204));
        txtEnunciadoVF.setColumns(20);
        txtEnunciadoVF.setFont(new java.awt.Font("Dialog", 2, 20)); // NOI18N
        txtEnunciadoVF.setForeground(new java.awt.Color(0, 0, 0));
        txtEnunciadoVF.setLineWrap(true);
        txtEnunciadoVF.setRows(5);
        jScrollPane3.setViewportView(txtEnunciadoVF);

        panel.setBackground(new java.awt.Color(0, 0, 51));
        panel.setLayout(new java.awt.GridBagLayout());

        lblTipo.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo.setText("[-]");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 19, 0);
        panel.add(lblTipo, gridBagConstraints);

        cboxVerdaderoOFalso.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        cboxVerdaderoOFalso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Verdadero", "Falso" }));
        cboxVerdaderoOFalso.setAlignmentX(-2.0F);
        cboxVerdaderoOFalso.setAlignmentY(0.0F);
        cboxVerdaderoOFalso.setBorder(null);
        cboxVerdaderoOFalso.setMaximumSize(new java.awt.Dimension(146, 30));
        cboxVerdaderoOFalso.setMinimumSize(new java.awt.Dimension(144, 30));
        cboxVerdaderoOFalso.setPreferredSize(new java.awt.Dimension(144, 30));
        cboxVerdaderoOFalso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxVerdaderoOFalsoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 0, 0);
        panel.add(cboxVerdaderoOFalso, gridBagConstraints);

        btnFinalizarEspaciosVF.setBackground(new java.awt.Color(51, 0, 204));
        btnFinalizarEspaciosVF.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnFinalizarEspaciosVF.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarEspaciosVF.setText("Finalizar");
        btnFinalizarEspaciosVF.setAlignmentY(0.0F);
        btnFinalizarEspaciosVF.setMaximumSize(new java.awt.Dimension(155, 47));
        btnFinalizarEspaciosVF.setMinimumSize(new java.awt.Dimension(155, 47));
        btnFinalizarEspaciosVF.setPreferredSize(new java.awt.Dimension(155, 47));
        btnFinalizarEspaciosVF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarEspaciosVFActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 0, 0);
        panel.add(btnFinalizarEspaciosVF, gridBagConstraints);

        txtRespuesta.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        panel.add(txtRespuesta, gridBagConstraints);

        spnPuntaje.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        spnPuntaje.setModel(new javax.swing.SpinnerNumberModel());

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 27)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Puntaje");

        btnConsola1.setBackground(new java.awt.Color(0, 153, 255));
        btnConsola1.setText("Abrir consola");
        btnConsola1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsola1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRespuestaLayout = new javax.swing.GroupLayout(panelRespuesta);
        panelRespuesta.setLayout(panelRespuestaLayout);
        panelRespuestaLayout.setHorizontalGroup(
            panelRespuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRespuestaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelRespuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRespuestaLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsola1))
                    .addGroup(panelRespuestaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRespuestaLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnPuntaje, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        panelRespuestaLayout.setVerticalGroup(
            panelRespuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRespuestaLayout.createSequentialGroup()
                .addGroup(panelRespuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRespuestaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelRespuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsola1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRespuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spnPuntaje, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(panelRespuestaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMultiple, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelEnunciado, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelRespuesta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMultiple, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panelEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panelRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 2, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método encargado de dar funcionamiento al botón "Siguiente", que permite al escribir el enunciado y seleccionar el tipo de pregunta abrir el panel correspondiente.
     */
    private void bntSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSiguienteActionPerformed
        enunciado = txtEnunciado.getText();
        tipoPregunta = (String) cboxTipoPregunta.getSelectedItem();
        this.remove(panelEnunciado);

        lblTipo.setText("Respuesta");
        switch (tipoPregunta) {
            case "Verdadero o Falso":
                txtEnunciadoVF.setText(enunciado);
                panelRespuesta.setVisible(true);
                txtRespuesta.setVisible(false);
                cboxVerdaderoOFalso.setVisible(true);
                break;
            case "Multiple opción":
                lblEnunciadoMultiple.setText(enunciado);
                panelMultiple.setVisible(true);
                break;
            case "Rellenar espacios":
                txtEnunciadoVF.setText(enunciado);
                lblTipo.setForeground(Color.black);
                panelRespuesta.setBackground(new Color(255, 255, 255));
                panel.setBackground(new Color(204, 204, 204));
                panelRespuesta.setVisible(true);
                txtRespuesta.setVisible(true);
                cboxVerdaderoOFalso.setVisible(false);
                break;
        }
    }//GEN-LAST:event_bntSiguienteActionPerformed

    /**
     * Método que permite dar funcionalidad al botón "Finalizar" desde el rol docente y "Siguiente" desde el rol estudiante.
     * @param evt 
     */
    private void btnFinalizarMultipleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarMultipleActionPerformed
        if ("Finalizar".equals(btnFinalizarMultiple.getText())) {//Es el docente creando la pregunta
            cantidadPreguntas++;
            agregarPreguntaVistaPrevia();
            this.armarMultiple();
            this.dispose();
        } else {
            if ("Siguiente".equals(btnFinalizarMultiple.getText())) { //Es un alumno contestando la pregunta multipleopcion 
                try {
                    cantidadPreguntas++;
                    siguientePregunta(String.valueOf(cboxOpciones.getSelectedIndex() + 1), this);
                } catch (IOException ex) {
                    Logger.getLogger(AltaPregunta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnFinalizarMultipleActionPerformed

    private void txtOpc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOpc1ActionPerformed

    }//GEN-LAST:event_txtOpc1ActionPerformed

    private void cboxOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxOpcionesActionPerformed
        
    }//GEN-LAST:event_cboxOpcionesActionPerformed

    private void cboxTipoPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxTipoPreguntaActionPerformed
       
    }//GEN-LAST:event_cboxTipoPreguntaActionPerformed

    /**
     * Método que permite dar funcionalidad al botón "Finalizar" desde el rol docente y "Siguiente" desde el rol estudiante.
     * @param evt 
     */
    private void btnFinalizarEspaciosVFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarEspaciosVFActionPerformed
        if (btnFinalizarEspaciosVF.getText().equals("Finalizar")) {//Es el docente creando la pregunta
            cantidadPreguntas++;
            agregarPreguntaVistaPrevia();
            if (tipoPregunta.equals("Verdadero o Falso")) {
                this.armarVF();
            } else {
                this.armarEspacios();
            }
            this.dispose();
        } else {
            if (btnFinalizarEspaciosVF.getText().equals("Siguiente")) {//Es un alumno contestando la pregunta vf o de completar
                try {
                    String laRespuesta;
                    if (cboxVerdaderoOFalso.isVisible()) {
                        laRespuesta = (String) cboxVerdaderoOFalso.getSelectedItem();
                    } else {
                        laRespuesta = txtRespuesta.getText();
                    }
                    cantidadPreguntas++;
                    String numeroPregunta = String.valueOf(cantidadPreguntas);
                    siguientePregunta(laRespuesta, this);
                } catch (IOException ex) {
                    Logger.getLogger(AltaPregunta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnFinalizarEspaciosVFActionPerformed

    private void cboxVerdaderoOFalsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxVerdaderoOFalsoActionPerformed
        
    }//GEN-LAST:event_cboxVerdaderoOFalsoActionPerformed

    private void lblEnunciadoMultipleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEnunciadoMultipleMouseClicked
        JOptionPane.showMessageDialog(null, lblEnunciadoMultiple.getText(), "Pregunta:", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblEnunciadoMultipleMouseClicked

    private void lblEnunciadoMultipleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEnunciadoMultipleMouseEntered
        lblEnunciadoMultiple.setCursor(new Cursor(HAND_CURSOR));
    }//GEN-LAST:event_lblEnunciadoMultipleMouseEntered

    private void btnConsolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsolaActionPerformed
        Consola consola = new Consola();
        consola.setVisible(true);
    }//GEN-LAST:event_btnConsolaActionPerformed

    private void btnConsola1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsola1ActionPerformed
        Consola consola = new Consola();
        consola.setVisible(true);
    }//GEN-LAST:event_btnConsola1ActionPerformed

    private void btnConsola2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsola2ActionPerformed
        Consola consola = new Consola();
        consola.setVisible(true);
    }//GEN-LAST:event_btnConsola2ActionPerformed

    //Getters de elementos visuales y respuestas de alumnos
    public JButton getBtnFinalizar() {
        return btnFinalizarEspaciosVF;
    }

    public JButton getBtnFinalizarMultiple() {
        return btnFinalizarMultiple;
    }

    public JComboBox<String> getCboxOpciones() {
        return cboxOpciones;
    }

    public JComboBox<String> getCboxVerdaderoOFalso() {
        return cboxVerdaderoOFalso;
    }

    public JLabel getLblEnunciadoMultiple() {
        return lblEnunciadoMultiple;
    }

    public JTextArea getTxtEnunciado() {
        return txtEnunciado;
    }

    public JTextArea getTxtEnunciadoVF() {
        return txtEnunciadoVF;
    }

    public JTextField getTxtOpc1() {
        return txtOpc1;
    }

    public JTextField getTxtOpc2() {
        return txtOpc2;
    }

    public JTextField getTxtOpc3() {
        return txtOpc3;
    }

    public JTextField getTxtOpc4() {
        return txtOpc4;
    }

    public JTextField getTxtRespuesta() {
        return txtRespuesta;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel getPanelEnunciado() {
        return panelEnunciado;
    }

    public JPanel getPanelMultiple() {
        return panelMultiple;
    }

    public JLabel getLblTipo() {
        return lblTipo;
    }

    public JPanel getPanelRespuesta() {
        return panelRespuesta;
    }

    public JSpinner getspnPuntaje() {
        return spnPuntaje;
    }

    public JSpinner getspnPuntajeMultiple() {
        return spnPuntajeMultiple;
    }

    public static int getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    public static void setCantidadPreguntas(int cantidadPreguntas) {
        AltaPregunta.cantidadPreguntas = cantidadPreguntas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    //Formato de pregunta "enunciado,,,Multiple,,,puntaje,,,op1,,,op2,,,op3,,,op4,,,respuesta" 
    public void armarMultiple() {
        pregunta = ";;;" + enunciado + ",,,Multiple,,," + this.getspnPuntajeMultiple().getValue() + ",,," + this.getTxtOpc1().getText() + ",,," + this.getTxtOpc2().getText() + ",,," + this.getTxtOpc3().getText() + ",,," + this.getTxtOpc4().getText() + ",,," + (this.getCboxOpciones().getSelectedIndex() + 1);
        cliente.concatenarMensaje(pregunta);
    }

    //Formato de pregunta "enunciado,,,VF,,,puntaje,,,respuesta" 
    public void armarVF() {
        pregunta = ";;;" + enunciado + ",,,VF,,," + this.getspnPuntaje().getValue() + ",,," + this.getCboxVerdaderoOFalso().getSelectedItem().toString();
        cliente.concatenarMensaje(pregunta);
    }

    //Formato de pregunta "enunciado,,,Completar,,,puntaje,,,respuestas separadas por espacio" 
    public void armarEspacios() {
        pregunta = ";;;" + enunciado + ",,,Completar,,," + this.getspnPuntaje().getValue() + ",,," + this.getTxtRespuesta().getText();
        cliente.concatenarMensaje(pregunta);
    }

    //Formato de respuestas "respuesta0;;;respuesta1;;;respuesta2;;;respuesta3
    public void prepararRespuestas(String respuesta) {
        String[] tokens = respuesta.split(",;,");
        tokens = tokens[0].split(";;;");
        respuestas += ";;;" + tokens[0];
    }

    //Agrega la pregunta a la evaluacion y genera la vista previa para el docente
    private void agregarPreguntaVistaPrevia() {
        Font fuente = new Font("Dialog", Font.PLAIN, 15);
        String respuesta = "";
        // Establecer el ancho máximo permitido en píxeles
        int anchoMaximo = 555; // Cambia este valor según el tamaño que desees

// Crear el JLabel utilizando HTML para ajustar el texto
        JLabel pregunta = new JLabel(cantidadPreguntas + " - " + enunciado);
        pregunta.setOpaque(true);
        pregunta.setFont(new java.awt.Font("Dialog", 0, 18));
        pregunta.setForeground(new java.awt.Color(255, 255, 255));
        pregunta.setBackground(new java.awt.Color(0, 0, 0));
        vistaPrevia.add(pregunta);

        switch (tipoPregunta) {
            case "Verdadero o Falso": //Vista como una multiple opcion de dos opciones
                respuesta = (String) cboxVerdaderoOFalso.getSelectedItem();
                break;
            case "Multiple opción":
                respuesta = (String) cboxOpciones.getSelectedItem();
                String[] opciones = {txtOpc1.getText(), txtOpc2.getText(), txtOpc3.getText(), txtOpc4.getText()};

                //RadioButtons
                JRadioButton opc1 = new JRadioButton(txtOpc1.getText());
                JRadioButton opc2 = new JRadioButton(txtOpc2.getText());
                JRadioButton opc3 = new JRadioButton(txtOpc3.getText());
                JRadioButton opc4 = new JRadioButton(txtOpc4.getText());
                vistaPrevia.add(opc1);
                vistaPrevia.add(opc2);
                vistaPrevia.add(opc3);
                vistaPrevia.add(opc4);

                //Estetica
                opc1.setOpaque(false);
                opc2.setOpaque(false);
                opc3.setOpaque(false);
                opc4.setOpaque(false);
                opc1.setFont(fuente);
                opc2.setFont(fuente);
                opc3.setFont(fuente);
                opc4.setFont(fuente);
                break;
            case "Rellenar espacios":
                respuesta = txtRespuesta.getText();

                break;
        }
        JLabel lblRespuesta = new JLabel("Respuesta: " + respuesta);
        lblRespuesta.setFont(fuente);
        vistaPrevia.add(lblRespuesta);
        vistaPrevia.revalidate();
        vistaPrevia.repaint();
    }

    private void siguientePregunta(String unaRespuesta, AltaPregunta framePregunta) throws IOException {
        prepararRespuestas(unaRespuesta);//Va almacenando las respuestas seleccionadas en el formato correcto para enviarlas al final     
        cliente.intercambiarMensajes(evaluacion + ";;;" + cantidadPreguntas + ",;,Evaluaciones,;,ObtenerPregunta");
        if (cliente.obtenerMensaje().equals("Finalizar")) { //confirmacion de que no hay mas preguntas
            System.out.println(cliente.getRespuesta());
            //Solicitud de correccion
            int finalizar = JOptionPane.showConfirmDialog(null, "¿Desea enviar sus respuestas?", "Fin de la evaluación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            // Evaluar la respuesta
            if (finalizar == JOptionPane.YES_OPTION) {
                String enviarRespuestas = cliente.formatearMensaje(respuestas, "Evaluaciones", "Correccion");
                cliente.intercambiarMensajes(enviarRespuestas);
                System.out.println(respuestas);
                System.out.println(cliente.getRespuesta());
                cantidadPreguntas = 0;
                this.dispose();
            } else if (finalizar == JOptionPane.NO_OPTION) {
                System.out.println("Usuario seleccionó No.");
                cantidadPreguntas = 0;
                this.dispose();
            }
        } else if (cliente.obtenerCodigo().equals("200")) {
            String[] pregunta = cliente.obtenerMensaje().split(";;;"); // el primercampo lo tokenizo por ;;;
            if ((pregunta.length >= 3) && (pregunta.length <= 7)) {//si es correcto y tiene la estructura de una pregunta nueva                
                cliente.cargarEnGui(pregunta, framePregunta);
                System.out.println("pregunta:" + cliente.getRespuesta());//para pruebas
                framePregunta.revalidate();
                framePregunta.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error en la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntSiguiente;
    private javax.swing.JButton btnConsola;
    private javax.swing.JButton btnConsola1;
    private javax.swing.JButton btnConsola2;
    private javax.swing.JButton btnFinalizarEspaciosVF;
    private javax.swing.JButton btnFinalizarMultiple;
    private javax.swing.JComboBox<String> cboxOpciones;
    private javax.swing.JComboBox<String> cboxTipoPregunta;
    private javax.swing.JComboBox<String> cboxVerdaderoOFalso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblEnunciadoMultiple;
    private javax.swing.JLabel lblSigno;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelEnunciado;
    private javax.swing.JPanel panelMultiple;
    private javax.swing.JPanel panelPuntaje;
    private javax.swing.JPanel panelRespuesta;
    private javax.swing.JSpinner spnPuntaje;
    private javax.swing.JSpinner spnPuntajeMultiple;
    private javax.swing.JTextArea txtEnunciado;
    private javax.swing.JTextArea txtEnunciadoVF;
    private javax.swing.JTextField txtOpc1;
    private javax.swing.JTextField txtOpc2;
    private javax.swing.JTextField txtOpc3;
    private javax.swing.JTextField txtOpc4;
    private javax.swing.JTextField txtRespuesta;
    // End of variables declaration//GEN-END:variables
}
