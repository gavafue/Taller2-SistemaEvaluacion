/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import conexion.Cliente;

/**
 *
 * @author Gabriel
 */
public class VerRespuestasPanel extends javax.swing.JPanel {

    private Cliente cliente;
    private String titulo;
    private JPanel panelContent;
    private String rol;

    public JPanel getPanelContent() {
        return panelContent;
    }

    /**
     * Creates new form VerRespuestasPanel
     */
    public VerRespuestasPanel(Cliente cliente, String titulo, String rol, JPanel panelContent) {
        this.cliente = cliente;
        this.titulo = titulo;
        this.panelContent = panelContent;
        this.rol = rol;
        initComponents();
        this.solicitarPreguntasYRespuestas();
    }

    /**
     * Método que permite obtener el cliente actual conectado.
     *
     * @return el cliente actualmente conectado.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Método que permite obtener el título de la evaluación.
     *
     * @return el título de la evaluación.
     */
    public String getTitulo() {
        return titulo;
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
     * Método que permite modificar el título de la evluación dado otro título.
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Solicita al cliente las preguntas y respuestas correctas de la evaluación
     * seleccionada
     * y actualiza la tabla con la información recibida.
     */
    public void solicitarPreguntasYRespuestas() {
        try {
            // Prepara el mensaje de solicitud y lo envía al cliente
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Evaluaciones",
                    "ObtenerCorrectas");
            this.getCliente().intercambiarMensajes(instruccion);

            // Verifica el código de respuesta del cliente
            if (this.getCliente().obtenerCodigo().equals("200")) {
                cargarPreguntasYRespuestas();
            } else {
                throw new RuntimeException("Código de respuesta inesperado: " + this.getCliente().obtenerCodigo());
            }
        } catch (IOException e) {
            // Manejo de errores de entrada/salida, como problemas de red
            System.err.println("Error de comunicación con el servidor. Detalles: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de comunicación con el servidor. Detalles: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            // Manejo de errores de puntero nulo, por ejemplo, si cliente o respuesta son
            // null
            System.err.println("Referencia nula detectada. Detalles: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: datos incompletos o nulos. Detalles: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException e) {
            // Manejo de excepciones en caso de respuestas inesperadas del cliente
            System.err.println("Error en la respuesta del cliente. Detalles: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error en la respuesta del cliente. Detalles: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            System.err.println(
                    "Ha ocurrido un error inesperado al solicitar preguntas y respuestas. Detalles: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado. Detalles: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga las preguntas y respuestas desde el cliente y actualiza la tabla y el
     * título.
     */
    public void cargarPreguntasYRespuestas() {
        try {
            // Obtiene el mensaje del cliente y lo divide en preguntas y respuestas
            String[] preguntasYRespuestas = this.getCliente().obtenerMensaje().split(";;;");
            String[] columnas = { "Enunciado", "Respuesta" };

            // Crea el modelo de la tabla con las columnas especificadas
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            for (String preguntaYRespuesta : preguntasYRespuestas) {
                // Divide cada entrada en pregunta y respuesta
                String[] separarPreguntaYRespuesta = preguntaYRespuesta.split(",,,");
                if (separarPreguntaYRespuesta.length < 2) {
                    // Verifica si la entrada tiene un formato esperado
                    throw new IllegalArgumentException(
                            "Formato de datos incorrecto para la entrada: " + preguntaYRespuesta);
                }

                // Verifica si la respuesta contiene un asterisco y ajusta según sea necesario
                if (separarPreguntaYRespuesta[1].contains("*")) {
                    String[] separarRespuestas = separarPreguntaYRespuesta[1].split("\\*");
                    if (separarRespuestas.length > 1 && "null".equals(separarRespuestas[1])) {
                        separarRespuestas[1] = "";
                        separarPreguntaYRespuesta[1] = separarRespuestas[0];
                    }
                }

                // Agrega la fila a la tabla
                Object[] fila = { separarPreguntaYRespuesta[0], separarPreguntaYRespuesta[1] };
                modelo.addRow(fila);
            }

            // Actualiza el modelo de la tabla y el título
            jTable1.setModel(modelo);
            labelTitulo.setText("Respuestas correctas: " + this.getTitulo());

        } catch (NullPointerException e) {
            // Maneja el caso en que el mensaje del cliente es null
            System.err.println("Error: El mensaje del cliente es nulo. Detalles: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            // Maneja el caso en que la división de la cadena resulta en un índice fuera de
            // los límites
            System.err.println("Error: Error al procesar el formato del mensaje. Detalles: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Manejo de errores de formato de datos incorrecto
            System.err.println("Error: Datos de entrada inválidos. Detalles: " + e.getMessage());
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            System.err.println("Error inesperado al cargar preguntas y respuestas. Detalles: " + e.getMessage());
        }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        labelTitulo = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(730, 520));

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null },
                        { null, null },
                        { null, null },
                        { null, null }
                },
                new String[] {
                        "Enunciado", "Respuesta"
                }));
        jScrollPane1.setViewportView(jTable1);

        labelTitulo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelTitulo.setText("Respuestas correctas:");

        btnAtras.setBackground(new java.awt.Color(51, 51, 51));
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnAtrasActionPerformed(evt);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(btnAtras)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(labelTitulo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 443,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(240, 240, 240))))
                                .addGap(0, 21, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_btnAtrasActionPerformed
        GestionEvaluacionesPanel panelEvaluaciones = new GestionEvaluacionesPanel(cliente, rol, panelContent);
        panelEvaluaciones.setSize(730, 520);
        panelEvaluaciones.setLocation(0, 0);
        panelContent.removeAll();
        panelContent.add(panelEvaluaciones);
        panelContent.revalidate();
        panelContent.repaint();
    }// GEN-LAST:event_btnAtrasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
