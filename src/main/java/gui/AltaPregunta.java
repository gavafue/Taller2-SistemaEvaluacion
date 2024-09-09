package gui;

import conexion.Cliente;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * En caso de rol docente, JFrame destinado a crear una pregunta de tipo
 * multiple opción, completar espacios o verdadero o falso, en función del tipo
 * de pregunta los elementos que se muestran en la interfaz. En caso de rol
 * estudiante, destinado a mostrar y permitir completar las preguntas de una
 * evaluación.
 */
public class AltaPregunta extends javax.swing.JFrame {

    private Cliente cliente;
    private JPanel vistaPrevia; // Atributo para poder intercambiar datos con la vista previa de la evaluación
                                // de forma dinámica
    private String rol;

    // Atributos utilizados como variables globales
    private String evaluacion; // Título de la evaluación seleccionada o por crear
    private String pregunta;
    private String enunciado;
    private String tipoPregunta;

    private String respuestas; // Respuestas del estudiante a las preguntas de la evaluación
    private static int cantidadPreguntas; // Atributo propio de a clase y no de la instancia

    /**
     * Constructor que permite crear una instancia de la clase a partir del
     * cliente actual y el JPanel vista previa de la evaluación.
     *
     * @param vistaPrevia permite visualizar los cambios en la evaluación de
     *                    forma dinámica.
     * @param cliente
     */
    public AltaPregunta(JPanel vistaPrevia, Cliente cliente, String rol) {
        this.cliente = cliente;
        this.vistaPrevia = vistaPrevia;
        this.rol = rol;
        initComponents();
        setLocationRelativeTo(null); // Centrar JFrame
        this.interfazPorDefecto(); // Inicializa la interfaz
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) { // En caso de que cierre una evaluación a medias borra todo lo almacendo en memoria
                valoresPorDefecto(evt);
            }
        });
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
     * Método que permite obtener el JPanel vista previa de la evaluación.
     *
     * @return el JPanel de vista previa
     */
    public JPanel getVistaPrevia() {
        return vistaPrevia;
    }

    /**
     * Método que permite obtener el título de la evaluación actual.
     *
     * @return título de la evaluación actual.
     */
    public String getEvaluacion() {
        return evaluacion;
    }

    /**
     * Método que permite obtener el enunciado de la pregunta.
     *
     * @return el enunciado de la pregunta.
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * Método que permite obtener la pregunta actual.
     *
     * @return la pregunta actual.
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Método que permite obtener el tipo de pregunta.
     *
     * @return el tipo de pregunta.
     */
    public String getTipoPregunta() {
        return tipoPregunta;
    }

    /**
     * Método que permite obtener las respuestas del estudiante.
     *
     * @return las respuestas del estudiante a la evaluación.
     */
    public String getRespuestas() {
        return respuestas;
    }

    /**
     * Método que permite obtener la cantidad de preguntas.
     *
     * @return la cantidad de preguntas.
     */
    public static int getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    /**
     * Método que permite modificar el cliente actual, a partir de otro cliente.
     *
     * @param cliente el cliente a asignar.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que permite modificar el JPanel actual, a partir de otro JPanel.
     *
     * @param vistaPrevia el JPanel de vista previa a asignar.
     */
    public void setVistaPrevia(JPanel vistaPrevia) {
        this.vistaPrevia = vistaPrevia;
    }

    /**
     * Método que permite modificar el título de evaluación actual, a partir de
     * otro título.
     *
     * @param evaluacion la evaluación a asignar.
     */
    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     * Método que permite modificar la pregunta actual, a partir de otra
     * pregunta.
     *
     * @param pregunta la pregunta a asignar.
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Método que permite modificar el enunciado de la pregunta actual, a partir
     * de otro enunciado.
     *
     * @param enunciado el enunciado a asignar.
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    /**
     * Método que permite modificar el tipo de pregunta actual, a partir de otro
     * tipo.
     *
     * @param tipoPregunta el tipo de pregunta a asignar
     */
    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    /**
     * Método que permite modificar las respuestas del estudiante, a partir de
     * otras respuestas.
     *
     * @param respuestas las respuestas a asignar
     */
    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }

    /**
     * Método que permite modificar la cantidad de preguntas actual, a partir de
     * otra cantidad.
     *
     * @param cantidadPreguntas la cantidad de preguntas a asignar
     */
    public static void setCantidadPreguntas(int cantidadPreguntas) {
        AltaPregunta.cantidadPreguntas = cantidadPreguntas;
    }

    /**
     * Método que pone los elementos de la interfaz en valores por defecto. Abre
     * el panel enunciado que permite escribir el enunciado de la pregunta y
     * seleccionar el tipo.
     */
    private void interfazPorDefecto() {
        panelEnunciado.setVisible(true);
        panelMultiple.setVisible(false);
        panelEspaciosVF.setVisible(false); // JPanel utilizado tanto para preguntas de completar espacios como VF
    }

    /**
     * Método que pone los elementos de la interfaz al crear una pregunta
     * multiple opción.
     */
    public void interfazMultiple() {
        lblEnunciadoMultiple.setText(enunciado);
        panelMultiple.setVisible(true);
    }

    /**
     * Método que pone los elementos de la interfaz al crear una pregunta
     * espacios en blanco.
     */
    public void interfazEspacios() {
        txtEnunciadoEspaciosVF.setText(enunciado);
        lblTipo.setForeground(Color.black);
        panelEspaciosVF.setBackground(new Color(255, 255, 255));
        panelRespuestaEspaciosVF.setBackground(new Color(204, 204, 204));
        panelEspaciosVF.setVisible(true);
        txtRespuestaEspacios.setVisible(true);
        cboxVerdaderoOFalso.setVisible(false);
    }

    /**
     * Método que pone los elementos de la interfaz al crear una pregunta
     * verdadero o falso.
     */
    public void interfazVF() {
        txtEnunciadoEspaciosVF.setText(enunciado);
        panelEspaciosVF.setVisible(true);
        txtRespuestaEspacios.setVisible(false);
        cboxVerdaderoOFalso.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
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
        panelMultiple = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboxOpcionesMultiple = new javax.swing.JComboBox<>();
        txtOpc1 = new javax.swing.JTextField();
        txtOpc3 = new javax.swing.JTextField();
        txtOpc2 = new javax.swing.JTextField();
        txtOpc4 = new javax.swing.JTextField();
        panelPuntaje = new javax.swing.JPanel();
        btnFinalizarMultiple = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        spnPuntajeMultiple = new javax.swing.JSpinner();
        btnConsolaMultiple = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblEnunciadoMultiple = new javax.swing.JLabel();
        panelEspaciosVF = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEnunciadoEspaciosVF = new javax.swing.JTextArea();
        panelRespuestaEspaciosVF = new javax.swing.JPanel();
        lblTipo = new javax.swing.JLabel();
        cboxVerdaderoOFalso = new javax.swing.JComboBox<>();
        btnFinalizarEspaciosVF = new javax.swing.JButton();
        txtRespuestaEspacios = new javax.swing.JTextField();
        spnPuntajeEspaciosVF = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        btnConsolaEspaciosVF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setForeground(panelEnunciado.getForeground());
        setLocationByPlatform(true);
        setResizable(false);

        panelEnunciado.setBackground(new java.awt.Color(255, 255, 255));
        panelEnunciado.setPreferredSize(new java.awt.Dimension(587, 227));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 28)); // NOI18N
        jLabel1.setText("Tipo");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
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
        txtEnunciado.setLineWrap(true);
        txtEnunciado.setRows(5);
        jScrollPane1.setViewportView(txtEnunciado);

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
                        .addGap(38, 459, Short.MAX_VALUE))
                    .addGroup(panelEnunciadoLayout.createSequentialGroup()
                        .addGroup(panelEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxTipoPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                        .addComponent(bntSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblSigno)
                .addGap(7, 7, 7))
        );
        panelEnunciadoLayout.setVerticalGroup(
            panelEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEnunciadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
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
        jLabel4.setText("Opción 3");
        jLabel4.setToolTipText("");
        panelMultiple.add(jLabel4);
        jLabel4.setBounds(20, 120, 97, 32);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setText("Opción 2");
        panelMultiple.add(jLabel5);
        jLabel5.setBounds(20, 90, 140, 32);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel6.setText("Opción 4");
        panelMultiple.add(jLabel6);
        jLabel6.setBounds(20, 150, 150, 32);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        jLabel7.setText("Respuesta");
        panelMultiple.add(jLabel7);
        jLabel7.setBounds(20, 210, 170, 30);

        cboxOpcionesMultiple.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cboxOpcionesMultiple.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Opción 1", "Opción 2", "Opción 3", "Opción 4" }));
        cboxOpcionesMultiple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxOpcionesMultipleActionPerformed(evt);
            }
        });
        panelMultiple.add(cboxOpcionesMultiple);
        cboxOpcionesMultiple.setBounds(270, 210, 130, 30);

        txtOpc1.setBackground(new java.awt.Color(204, 204, 204));
        txtOpc1.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtOpc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOpc1ActionPerformed(evt);
            }
        });
        panelMultiple.add(txtOpc1);
        txtOpc1.setBounds(180, 60, 225, 26);

        txtOpc3.setBackground(new java.awt.Color(204, 204, 204));
        txtOpc3.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        panelMultiple.add(txtOpc3);
        txtOpc3.setBounds(180, 120, 225, 26);

        txtOpc2.setBackground(new java.awt.Color(204, 204, 204));
        txtOpc2.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        panelMultiple.add(txtOpc2);
        txtOpc2.setBounds(180, 90, 225, 26);

        txtOpc4.setBackground(new java.awt.Color(204, 204, 204));
        txtOpc4.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        txtOpc4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        panelMultiple.add(txtOpc4);
        txtOpc4.setBounds(180, 150, 225, 26);

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

        btnConsolaMultiple.setBackground(new java.awt.Color(0, 153, 255));
        btnConsolaMultiple.setForeground(new java.awt.Color(255, 255, 255));
        btnConsolaMultiple.setText("Abrir consola");
        btnConsolaMultiple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsolaMultipleActionPerformed(evt);
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
                        .addComponent(btnConsolaMultiple)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        panelPuntajeLayout.setVerticalGroup(
            panelPuntajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPuntajeLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(btnConsolaMultiple)
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
        jLabel8.setText("Opción 1");
        panelMultiple.add(jLabel8);
        jLabel8.setBounds(20, 60, 140, 32);

        lblEnunciadoMultiple.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
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

        panelEspaciosVF.setBackground(new java.awt.Color(255, 255, 255));
        panelEspaciosVF.setPreferredSize(new java.awt.Dimension(587, 227));

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        jLabel9.setText("Enunciado");

        txtEnunciadoEspaciosVF.setBackground(new java.awt.Color(204, 204, 204));
        txtEnunciadoEspaciosVF.setColumns(20);
        txtEnunciadoEspaciosVF.setFont(new java.awt.Font("Dialog", 2, 20)); // NOI18N
        txtEnunciadoEspaciosVF.setLineWrap(true);
        txtEnunciadoEspaciosVF.setRows(5);
        jScrollPane3.setViewportView(txtEnunciadoEspaciosVF);

        panelRespuestaEspaciosVF.setBackground(new java.awt.Color(0, 0, 51));
        panelRespuestaEspaciosVF.setLayout(new java.awt.GridBagLayout());

        lblTipo.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo.setText("[-]");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 19, 0);
        panelRespuestaEspaciosVF.add(lblTipo, gridBagConstraints);

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
        panelRespuestaEspaciosVF.add(cboxVerdaderoOFalso, gridBagConstraints);

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
        panelRespuestaEspaciosVF.add(btnFinalizarEspaciosVF, gridBagConstraints);

        txtRespuestaEspacios.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        panelRespuestaEspaciosVF.add(txtRespuestaEspacios, gridBagConstraints);

        spnPuntajeEspaciosVF.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        spnPuntajeEspaciosVF.setModel(new javax.swing.SpinnerNumberModel());

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 27)); // NOI18N
        jLabel10.setText("Puntaje");

        btnConsolaEspaciosVF.setBackground(new java.awt.Color(0, 153, 255));
        btnConsolaEspaciosVF.setText("Abrir consola");
        btnConsolaEspaciosVF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsolaEspaciosVFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEspaciosVFLayout = new javax.swing.GroupLayout(panelEspaciosVF);
        panelEspaciosVF.setLayout(panelEspaciosVFLayout);
        panelEspaciosVFLayout.setHorizontalGroup(
            panelEspaciosVFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelEspaciosVFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsolaEspaciosVF))
                    .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnPuntajeEspaciosVF, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(panelRespuestaEspaciosVF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        panelEspaciosVFLayout.setVerticalGroup(
            panelEspaciosVFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                .addGroup(panelEspaciosVFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelEspaciosVFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsolaEspaciosVF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelEspaciosVFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spnPuntajeEspaciosVF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(panelEspaciosVFLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(panelRespuestaEspaciosVF, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMultiple, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelEnunciado, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelEspaciosVF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
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
                    .addComponent(panelEspaciosVF, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 2, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método encargado de dar funcionamiento al botón "Siguiente" del JPanel
     * enunciado, que permite al escribir el enunciado y seleccionar el tipo de
     * pregunta a crear abrir el panel correspondiente.
     */
    private void bntSiguienteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bntSiguienteActionPerformed
        this.setEnunciado(txtEnunciado.getText());
        this.setTipoPregunta((String) cboxTipoPregunta.getSelectedItem());
        lblTipo.setText("Respuesta");
        this.remove(panelEnunciado);

        switch (this.getTipoPregunta()) {
            case "Verdadero o Falso":
                this.interfazVF();
                break;
            case "Multiple opción":
                this.interfazMultiple();
                break;
            case "Rellenar espacios":
                this.interfazEspacios();
                break;
        }
    }// GEN-LAST:event_bntSiguienteActionPerformed

    /**
     * Método que permite dar funcionalidad desde el JPanel multiple al botón
     * "Finalizar" desde el rol docente y "Siguiente" desde el rol estudiante.
     * En caso de ser un docente se agrega la pregunta a la evaluación y en caso
     * de ser un estudiante se despliega la siguiente pregunta y se almacena la
     * respuesta en memoria.
     *
     * @param evt
     */
    private void btnFinalizarMultipleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFinalizarMultipleActionPerformed
        if ("Finalizar".equals(btnFinalizarMultiple.getText())) {// Es el docente creando la pregunta
            cantidadPreguntas++;
            this.agregarPreguntaVistaPrevia();
            this.getCliente().armarMultiple(this.getEnunciado(), String.valueOf(this.spnPuntajeMultiple.getValue()),
                    this.txtOpc1.getText(), this.txtOpc2.getText(), this.txtOpc3.getText(), this.txtOpc4.getText(),
                    String.valueOf(this.cboxOpcionesMultiple.getSelectedIndex() + 1));
            this.dispose();
        } else {
            if ("Siguiente".equals(btnFinalizarMultiple.getText())) { // Es un alumno contestando la pregunta multiple
                                                                      // opción
                try {
                    cantidadPreguntas++;
                    this.solicitarSiguientePregunta(
                            String.valueOf(cboxOpcionesMultiple.getSelectedIndex() + 1)/* respuesta */, this);
                } catch (IOException ex) {
                    Logger.getLogger(AltaPregunta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }// GEN-LAST:event_btnFinalizarMultipleActionPerformed

    private void txtOpc1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtOpc1ActionPerformed

    }// GEN-LAST:event_txtOpc1ActionPerformed

    private void cboxOpcionesMultipleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxOpcionesMultipleActionPerformed

    }// GEN-LAST:event_cboxOpcionesMultipleActionPerformed

    private void cboxTipoPreguntaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxTipoPreguntaActionPerformed

    }// GEN-LAST:event_cboxTipoPreguntaActionPerformed

    /**
     * Método que permite dar funcionalidad desde el JPanel espaciosVF al botón
     * "Finalizar" desde el rol docente y "Siguiente" desde el rol estudiante.
     * En caso de ser un docente se agrega la pregunta a la evaluación y en caso
     * de ser estudiante se despliega la siguiente pregunta y se almacena la
     * respuesta en memoria.
     * 
     * Este botón es compartido por el tipo de pregunta espacios y vf.
     *
     * @param evt
     */
    private void btnFinalizarEspaciosVFActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFinalizarEspaciosVFActionPerformed
        if (btnFinalizarEspaciosVF.getText().equals("Finalizar")) {// Es el docente creando la pregunta
            cantidadPreguntas++;
            this.agregarPreguntaVistaPrevia();
            if (this.getTipoPregunta().equals("Verdadero o Falso")) {
                this.getCliente().armarVF(this.getEnunciado(),
                        String.valueOf(this.spnPuntajeEspaciosVF.getValue())/* puntaje */,
                        String.valueOf(this.cboxVerdaderoOFalso.getSelectedItem())/* respuesta */);
            } else {
                this.getCliente().armarEspacios(this.getEnunciado(),
                        String.valueOf(this.spnPuntajeEspaciosVF.getValue()), this.txtRespuestaEspacios.getText());
            }
            this.dispose();
        } else {
            if (btnFinalizarEspaciosVF.getText().equals("Siguiente")) {// Es un alumno contestando la pregunta vf o de
                                                                       // completar
                try {
                    String respuesta;
                    if (cboxVerdaderoOFalso.isVisible()) { // Si es vf
                        respuesta = String.valueOf(cboxVerdaderoOFalso.getSelectedItem());
                    } else {
                        respuesta = txtRespuestaEspacios.getText();
                    }
                    cantidadPreguntas++;
                    this.solicitarSiguientePregunta(respuesta, this);
                } catch (IOException ex) {
                    Logger.getLogger(AltaPregunta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }// GEN-LAST:event_btnFinalizarEspaciosVFActionPerformed

    private void cboxVerdaderoOFalsoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cboxVerdaderoOFalsoActionPerformed

    }// GEN-LAST:event_cboxVerdaderoOFalsoActionPerformed

    private void lblEnunciadoMultipleMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEnunciadoMultipleMouseClicked

    }// GEN-LAST:event_lblEnunciadoMultipleMouseClicked

    private void lblEnunciadoMultipleMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEnunciadoMultipleMouseEntered

    }// GEN-LAST:event_lblEnunciadoMultipleMouseEntered

    /**
     * Método que da funcionamiento al botón "consola" ubicado en el JPanel
     * multiple, que abre la consola de Linux.
     *
     * @param evt
     */
    private void btnConsolaMultipleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnConsolaMultipleActionPerformed
        Consola consola = new Consola();
        consola.setVisible(true);
    }// GEN-LAST:event_btnConsolaMultipleActionPerformed

    /**
     * Método que da funcionamiento al botón "consola" ubicado en el JPanel
     * espaciosVF, que abre la consola de Linux.
     *
     * @param evt
     */
    private void btnConsolaEspaciosVFActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnConsolaEspaciosVFActionPerformed
        Consola consola = new Consola();
        consola.setVisible(true);
    }// GEN-LAST:event_btnConsolaEspaciosVFActionPerformed

    /**
     * Método que le solicita al servidor la siguiente pregunta de la
     * evaluación.
     *
     * @param respuesta     de la pregunta anterior.
     * @param framePregunta JFrame donde visualiza el estudiante la pregunta de
     *                      la evaluación.
     * @throws IOException
     */
    private void solicitarSiguientePregunta(String respuesta, AltaPregunta framePregunta) throws IOException {
        this.setRespuestas(this.getCliente().prepararRespuestas(this.getRespuestas(), respuesta)); // Almacena la
                                                                                                   // respuesta de la
                                                                                                   // pregunta anterior
        this.getCliente()
                .intercambiarMensajes(this.getEvaluacion() + ";;;"
                        + cantidadPreguntas /* Corresponde al número de pregunta solicitada */
                        + ",;,Evaluaciones,;,ObtenerPregunta");
        // Confirmación de envío de respuestas
        if (this.getCliente().obtenerMensaje().equals("Finalizar")) { // Si el server manda "Finalizar,;,200" no hay más
                                                                      // preguntas disponibles
            int finalizar = JOptionPane.showConfirmDialog(null, "¿Desea enviar sus respuestas?", "Fin de la evaluación",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (finalizar == JOptionPane.YES_OPTION) { // Envía las respuestas del estudiante
                String enviarRespuestas = this.getCliente().formatearMensaje(this.getRespuestas(), "Evaluaciones",
                        "Correccion");
                this.getCliente().intercambiarMensajes(enviarRespuestas);
                AltaPregunta.setCantidadPreguntas(0); // Inicializa el número de pregunta al finalizar
                this.setRespuestas(""); // Inicializa las respuestas
                this.dispose();
            } else if (finalizar == JOptionPane.NO_OPTION) {
                AltaPregunta.setCantidadPreguntas(0); // Inicializa el número de pregunta al finalizar
                this.setRespuestas(""); // Inicializa las respuestas
                this.dispose();
            }
        } else if (this.getCliente().obtenerCodigo().equals("200")) { // Si la obtiene crea un JFrame con la misma
            this.crearFrameNuevaPregunta(framePregunta);
        } else {
            JOptionPane.showMessageDialog(this, "Error en la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que agrega los elementos correspondientes a una pregunta de tipo
     * multiple opción a la vista previa de la evaluación.
     *
     * @param fuente a utilizar.
     */
    public void vistaPreviaMultiple(Font fuente) {
        // Creación de radio buttons con las opciones correspondientes
        JRadioButton opc1 = new JRadioButton(txtOpc1.getText());
        JRadioButton opc2 = new JRadioButton(txtOpc2.getText());
        JRadioButton opc3 = new JRadioButton(txtOpc3.getText());
        JRadioButton opc4 = new JRadioButton(txtOpc4.getText());

        // Estética de las opciones
        opc1.setOpaque(false);
        opc2.setOpaque(false);
        opc3.setOpaque(false);
        opc4.setOpaque(false);
        opc1.setFont(fuente);
        opc2.setFont(fuente);
        opc3.setFont(fuente);
        opc4.setFont(fuente);

        // Alta en la vista previa
        vistaPrevia.add(opc1);
        vistaPrevia.add(opc2);
        vistaPrevia.add(opc3);
        vistaPrevia.add(opc4);
    }

    /**
     * Método que crea todos los elementos necesarios para agregar en la vista
     * previa de la evaluación la nueva pregunta.
     *
     * @param fuente a utilizar.
     */
    public void crearPreguntaVistaPrevia(Font fuente) {
        JLabel lblpregunta = new JLabel(cantidadPreguntas + " - " + enunciado);
        lblpregunta.setOpaque(true);
        lblpregunta.setFont(new java.awt.Font("Dialog", 0, 18));
        lblpregunta.setForeground(new java.awt.Color(255, 255, 255));
        lblpregunta.setBackground(new java.awt.Color(0, 0, 0));
        vistaPrevia.add(lblpregunta);
    }

    /**
     * Método que crea todos los elementos necesarios para agregar en la vista
     * previa de la evaluación la respuesta de la nueva pregunta.
     *
     * @param fuente a utilizar.
     */
    public void crearRespuestaVistaPrevia(Font fuente) {
        String respuesta = "";
        switch (this.getTipoPregunta()) {
            case "Verdadero o Falso": // Vista como una multiple opcion de dos opciones
                respuesta = (String) cboxVerdaderoOFalso.getSelectedItem();
                break;
            case "Multiple opción":
                respuesta = (String) cboxOpcionesMultiple.getSelectedItem();
                this.vistaPreviaMultiple(fuente);
                break;
            case "Rellenar espacios":
                respuesta = txtRespuestaEspacios.getText();
                break;
        }

        JLabel lblRespuesta = new JLabel("Respuesta: " + respuesta);
        lblRespuesta.setFont(fuente);
        vistaPrevia.add(lblRespuesta);
    }

    /**
     * Método que agrega la pregunta junto a su respuesta en la vista previa de
     * la evaluación para el docente.
     */
    private void agregarPreguntaVistaPrevia() {
        Font fuente = new Font("Dialog", Font.PLAIN, 15);
        this.crearPreguntaVistaPrevia(fuente);
        this.crearRespuestaVistaPrevia(fuente);
        vistaPrevia.revalidate();
        vistaPrevia.repaint();
    }

    /**
     * Método que crea el JFrame destinado a la nueva pregunta a responder por
     * el estudiante.
     *
     * @param framePregunta.
     */
    public void crearFrameNuevaPregunta(AltaPregunta framePregunta) {
        String[] nuevaPregunta = this.getCliente().obtenerMensaje().split(";;;");
        if ((nuevaPregunta.length >= 3) && (nuevaPregunta.length <= 7)) { // Si tiene la estructura de una pregunta
            this.cargarEnGui(nuevaPregunta, framePregunta);
            framePregunta.revalidate();
            framePregunta.repaint();
        }
    }

    /**
     * Método que permite cargar en un JFrame de tipo pregunta una pregunta de
     * tipo multiple.
     *
     * @param pregunta
     * @param puntaje       asociado a la pregunta.
     * @param framePregunta en el que se cargaran los componentes.
     */
    public void cargarEnGuiMultiple(String[] pregunta, int puntaje, AltaPregunta framePregunta) {
        framePregunta.lblEnunciadoMultiple.setText(enunciado);// Se carga el enunciado en Label
        framePregunta.txtRespuestaEspacios.setVisible(false);
        framePregunta.cboxOpcionesMultiple.setVisible(true);
        framePregunta.cboxVerdaderoOFalso.setVisible(false);
        framePregunta.panelRespuestaEspaciosVF.setVisible(false);
        this.panelEspaciosVF.setVisible(false);
        this.panelMultiple.setVisible(true);
        // Aparecen las opciones y el puntaje pero sin posibilidad de editar
        framePregunta.spnPuntajeMultiple.setEnabled(false);
        framePregunta.spnPuntajeMultiple.setValue(puntaje);
        framePregunta.txtOpc1.setText(pregunta[2]); // Las opciones son los tokens 2-5
        framePregunta.txtOpc2.setText(pregunta[3]);
        framePregunta.txtOpc3.setText(pregunta[4]);
        framePregunta.txtOpc4.setText(pregunta[5]);
        framePregunta.txtOpc1.setEnabled(false);
        framePregunta.txtOpc2.setEnabled(false);
        framePregunta.txtOpc3.setEnabled(false);
        framePregunta.txtOpc4.setEnabled(false);
        framePregunta.btnFinalizarMultiple.setText("Siguiente");
        // Elimina el resultado cargado en la pregunta anterior
        framePregunta.cboxOpcionesMultiple.setSelectedIndex(0);
    }

    /**
     * Método que permite cargar en un JFrame de tipo pregunta una pregunta de
     * tipo vf.
     *
     * @param puntaje       asociado a la pregunta.
     * @param framePregunta en el que se cargaran los componentes.
     */
    public void cargarEnGuiVF(int puntaje, AltaPregunta framePregunta) {
        this.panelMultiple.setVisible(false);
        framePregunta.txtEnunciadoEspaciosVF.setText(enunciado); // Se carga el enunciado en el txtArea
        this.panelEspaciosVF.setVisible(true);
        framePregunta.txtRespuestaEspacios.setVisible(false);
        framePregunta.panelRespuestaEspaciosVF.setVisible(true);
        framePregunta.cboxVerdaderoOFalso.setVisible(true);
        framePregunta.lblTipo.setText("True/False");
        framePregunta.spnPuntajeEspaciosVF.setEnabled(false);
        framePregunta.spnPuntajeEspaciosVF.setValue(puntaje);
        framePregunta.btnFinalizarEspaciosVF.setText("Siguiente");
        // Elimina el resultado cargado en la pregunta anterior
        framePregunta.cboxVerdaderoOFalso.setSelectedIndex(0);
    }

    /**
     * Método que permite cargar en un JFrame de tipo pregunta una pregunta de
     * tipo espacios.
     *
     * @param puntaje       asociado a la pregunta.
     * @param framePregunta en el que se cargaran los componentes.
     */
    public void cargarEnGuiEspacios(int puntaje, AltaPregunta framePregunta) {
        this.panelMultiple.setVisible(false);
        framePregunta.txtEnunciadoEspaciosVF.setText(enunciado);// Se carga el enunciado en el txtArea
        framePregunta.cboxVerdaderoOFalso.setVisible(false);
        framePregunta.txtRespuestaEspacios.setVisible(true);
        this.panelEspaciosVF.setVisible(true);
        framePregunta.panelRespuestaEspaciosVF.setVisible(true);
        framePregunta.spnPuntajeEspaciosVF.setEnabled(false);
        framePregunta.spnPuntajeEspaciosVF.setValue(puntaje);
        framePregunta.lblTipo.setText("Respuesta/s");
        framePregunta.btnFinalizarEspaciosVF.setText("Siguiente");
        // Elimina el resultado cargado en la pregunta anterior
        framePregunta.txtRespuestaEspacios.setText("");
    }

    /**
     * Metodo que permite cargar en un JFrame de tipo pregunta con la pregunta
     * actual.
     *
     * @param pregunta      actual.
     * @param framePregunta en el que se agregarán los componentes.
     */
    public void cargarEnGui(String[] pregunta, AltaPregunta framePregunta) {
        String tipo = pregunta[0];
        this.setEnunciado(pregunta[1]);
        int puntaje = Integer.parseInt(pregunta[pregunta.length - 1]); // El último token es el puntaje

        framePregunta.setLocationRelativeTo(null);
        framePregunta.panelEnunciado.setVisible(false); // Solo visible al crear la pregunta, en este caso es realizar
                                                        // evaluación como estudiante
        framePregunta.setVisible(true);

        switch (tipo) {
            case "Multiple":
                this.cargarEnGuiMultiple(pregunta, puntaje, framePregunta);
                break;
            case "VF":
                this.cargarEnGuiVF(puntaje, framePregunta);
                break;
            case "Completar":
                this.cargarEnGuiEspacios(puntaje, framePregunta);
                break;
        }
    }

    /**
     * Método que inicializa la cantidad de preguntas y las respuestas si se
     * cierra la evaluación a medias desde el rol estudiante.
     *
     * @param evt
     */
    public void valoresPorDefecto(java.awt.event.WindowEvent evt) {
        if(rol.equals("estudiante")){
            AltaPregunta.setCantidadPreguntas(0);
            this.setRespuestas("");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntSiguiente;
    private javax.swing.JButton btnConsolaEspaciosVF;
    private javax.swing.JButton btnConsolaMultiple;
    private javax.swing.JButton btnFinalizarEspaciosVF;
    private javax.swing.JButton btnFinalizarMultiple;
    private javax.swing.JComboBox<String> cboxOpcionesMultiple;
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
    private javax.swing.JPanel panelEnunciado;
    private javax.swing.JPanel panelEspaciosVF;
    private javax.swing.JPanel panelMultiple;
    private javax.swing.JPanel panelPuntaje;
    private javax.swing.JPanel panelRespuestaEspaciosVF;
    private javax.swing.JSpinner spnPuntajeEspaciosVF;
    private javax.swing.JSpinner spnPuntajeMultiple;
    private javax.swing.JTextArea txtEnunciado;
    private javax.swing.JTextArea txtEnunciadoEspaciosVF;
    private javax.swing.JTextField txtOpc1;
    private javax.swing.JTextField txtOpc2;
    private javax.swing.JTextField txtOpc3;
    private javax.swing.JTextField txtOpc4;
    private javax.swing.JTextField txtRespuestaEspacios;
    // End of variables declaration//GEN-END:variables
}
