package gui;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import conexion.Cliente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;

/**
 * JFrame destinado a permitir visualizar las respuestas del estudiante
 * en una evaluación.
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class VerRespuestasEstudiante extends javax.swing.JPanel {

    /**
     * Cliente actual del sistema.
     */
    private Cliente cliente;

    /**
     * Rol del cliente actual.
     */
    private String rol;

    /**
     * Título de la evaluación de la que se mostrarán las preguntas y
     * respuestas.
     */
    private String titulo;

    /**
     * La id del estudiante.
     */
    private String idEstudiante;
    
    /**
     * Panel de contenido.
     */
    private JPanel panelContent;

    /**
     * Constructor común encargado de inicializar los elementos de la interfaz y
     * los atributos de la clase.
     * @param cliente cliente actual.
     * @param titulo titulo de la evaluación.
     * @param idEstudiante id del estudiante.
     * @param rol rol del cliente actual.
     * @param panelContent panel de contenido para manejo de interfaz.
     */
    public VerRespuestasEstudiante(Cliente cliente, String titulo, String idEstudiante, String rol, JPanel panelContent) {
        this.cliente = cliente;
        this.titulo = titulo;
        this.idEstudiante = idEstudiante;
        this.panelContent = panelContent;
        this.rol = rol;
        initComponents();        
        solicitarRespuestasEstudiante();        
        
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
     * Permite obtener la id del estudiante.
     * @return la id del estudiante.
     */
    public String getIdEstudiante() {
        return idEstudiante;
    }

    /**
     * Método que permite obtener el panel de contenido.
     *
     * @return el panel de contenido.
     */
    public JPanel getPanelContent() {
        return panelContent;
    }

    /**
     * Método que permite modificar el cliente actual dado otro cliente.
     *
     * @param cliente actual.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que permite modificar el título de la evluación dado otro título.
     *
     * @param titulo de la evaluación.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Permite establecer la id del estudiante.
     * @param idEstudiante id del estudiante.
     */
    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
  
    /**
     * Solicita al cliente las respuestas de un estudiante a una evaluación
     * seleccionada y actualiza la tabla con la información recibida.
     */
    public void solicitarRespuestasEstudiante() {
        try {
            // Prepara el mensaje de solicitud y lo envía al cliente
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo() + ";;;" + this.getIdEstudiante(), "Evaluaciones",
                    "ObtenerRespuestas");
            this.getCliente().intercambiarMensajes(instruccion);

            // Verifica el código de respuesta del cliente
            if (this.getCliente().obtenerCodigo().equals("200")) {
               cargarRespuestas();
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
     *
     * Método que carga las preguntas y respuestas del estudiante en la tabla
     *
     */
    public void cargarRespuestas() {
        try {
            // Obtiene el mensaje del cliente y lo divide en preguntas y respuestas
            String[] preguntasYRespuestas = this.getCliente().obtenerMensaje().split(";;;");
            String[] columnas = {"Enunciado", "Respuesta del Estudiante"};

            // Crea el modelo de la tabla con las columnas especificadas
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Todas las celdas no serán editables
                }
            };
            for (String preguntaYRespuesta : preguntasYRespuestas) {
                // Divide cada entrada en pregunta y respuesta
                String[] separarPreguntaYRespuesta = preguntaYRespuesta.split(",,,");
                if (separarPreguntaYRespuesta.length < 2) {
                    // Verifica si la entrada tiene un formato esperado
                    throw new IllegalArgumentException(
                            "Formato de datos incorrecto para la entrada: " + preguntaYRespuesta);
                }
                // Verifica si la respuesta contiene una coma y ajusta según sea necesario
                if (separarPreguntaYRespuesta[1].contains(",")) {
                    String[] separarRespuestas = separarPreguntaYRespuesta[1].split(",");
                    if (separarRespuestas.length > 1 && "null".equals(separarRespuestas[1])) {
                        separarRespuestas[1] = "";
                        separarPreguntaYRespuesta[1] = separarRespuestas[0];
                    }
                }
                // Agrega la fila a la tabla
                Object[] fila = {separarPreguntaYRespuesta[0], separarPreguntaYRespuesta[1]};
                modelo.addRow(fila);
            }
            this.darEstiloTabla();
            tableRespuestas.setModel(modelo);
            labelTitulo.setText("Respuestas de " + this.getTitulo());

            /**
             * Se hace la solicitud al server de comprar las respuestas con las
             * correctas Devolvera algo del tipo
             * Correcto,,,Incorrecto,,,Correcto,;,200 En base a este codigo se
             * modificar el renderizador de la tabla para colorearla
             */
            solicitarCompararRespuestas();
            RendererColores colorearRespuestas = new RendererColores(this.getCliente().obtenerMensaje().split(",,,"));
            tableRespuestas.getColumnModel().getColumn(1).setCellRenderer(colorearRespuestas);
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
     * Metodo que solicita al servidor comparar las respuestas del estudiando con las correctas
     * para colorear la tabla
     * 
     */
    public void solicitarCompararRespuestas() {
        try {
            // Prepara el mensaje de solicitud y lo envía al cliente
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo() + ";;;" + this.getIdEstudiante(), "Evaluaciones",
                    "CompararRespuestas");
            this.getCliente().intercambiarMensajes(instruccion);

            // Verifica el código de respuesta del cliente
            if (this.getCliente().obtenerCodigo().equals("200")) {
                //cargarRespuestas();
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
     * Método que da estilo a la tabla que contiene las repuestas correctas de
     * una evaluación.
     */
    public void darEstiloTabla() {
        // Estilo de la tabla
        tableRespuestas.setGridColor(new Color(0, 0, 153));
        tableRespuestas.setShowGrid(true);
        tableRespuestas.setRowHeight(30);
        tableRespuestas.setIntercellSpacing(new Dimension(0, 0)); // Espacio entre celdas verticalmente

        // Suponiendo que tableEvaluaciones ya está en un JScrollPane
        JScrollPane scrollPane = (JScrollPane) tableRespuestas.getParent().getParent();
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 153), 2)); // Borde azul alrededor del
        // JScrollPane

        // Configurar el encabezado
        JTableHeader header = tableRespuestas.getTableHeader();
        header.setBackground(new Color(0, 0, 153));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRespuestas = new javax.swing.JTable();
        btnAtras = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(730, 520));

        labelTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelTitulo.setText("Respuestas de");

        tableRespuestas.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tableRespuestas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Enunciado", "Respuesta"
            }
        ));
        jScrollPane1.setViewportView(tableRespuestas);

        btnAtras.setBackground(new java.awt.Color(0, 0, 153));
        btnAtras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                    .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtras)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que da funcionamiento al botón "atrás", que redirecciona a la
     * pestaña de gestión.
     *
     * @param evt
     */
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {
        VerHistorialesPanel panelEvaluaciones = new VerHistorialesPanel(titulo, cliente, rol, panelContent);
        panelEvaluaciones.setSize(730, 520);
        panelEvaluaciones.setLocation(0, 0);
        panelContent.removeAll();
        panelContent.add(panelEvaluaciones);
        panelContent.revalidate();
        panelContent.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tableRespuestas;
    // End of variables declaration//GEN-END:variables
}
