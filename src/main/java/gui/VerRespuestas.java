package gui;

import conexion.Cliente;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * JFrame destinado a mostrar las respuestas correctas de una evaluación en
 * particular dado su título.
 *
 */
public class VerRespuestas extends javax.swing.JFrame {

    Cliente cliente;
    String titulo;

    /**
     * Constructor común que permite crear una instancia de la clase, dado el
     * cliente y el titulo de la evaluación.
     */
    public VerRespuestas(Cliente cliente, String titulo) {
        this.cliente = cliente;
        this.titulo = titulo;
        initComponents();
        this.solicitarPreguntasYRespuestas();
        setLocationRelativeTo(null);
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
                    }
                    separarPreguntaYRespuesta[1] = separarRespuestas[0];
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

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        labelTitulo = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443,
                                                Short.MAX_VALUE)
                                        .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAtras)
                                .addGap(26, 26, 26)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que proveé funcionamiento al botón "Atrás", que permite cerrar la
     * ventana actual.
     *
     * @param evt
     */
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
    }// GEN-LAST:event_btnAtrasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
