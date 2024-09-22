package gui;

import consola.Comandos;
import consola.Ficheros;
import consola.Procesos;
import consola.Validar;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Clase que representa los elementos graficos de la consola y los eventos
 * asociados. Esta clase instancia las colecciones para garantizar única
 * instancia.
 *
 * @author Gabriel, Ana, Santiago, Juan y Gonzalo
 */
public class Consola extends javax.swing.JFrame {

    //Colecciones necesarias para el manejo de la consola
    private Comandos hashComandos;
    private Ficheros listaFicheros;
    private Procesos listaProcesos;

    /**
     * String para almacenar el ultimo comando ejecutado
     */
    private String ultimoComando;

    //Estilos
    private final String prompt = "LaConsola@inet:~$ ";
    private int posicionPrompt;
    private StyledDocument doc;
    private Style estiloPrompt, estiloComando, estiloError, estiloOK;

    /**
     * Constructor de la consola encargado de inicializar las colecciones
     * necesarias y los componentes de la interfaz.
     */
    public Consola() {
        hashComandos = new Comandos();
        listaFicheros = new Ficheros();
        listaProcesos = new Procesos();
        listaFicheros.cargarPrimerNivel(); // Primer nivel cargado desde memoria 
        initComponents();
        this.inicializarInterfaz();
    }

    /**
     * Método que permite obtener la colección de comandos del sistema.
     *
     * @return colección de comandos disponibles.
     */
    public Comandos getHashComandos() {
        return hashComandos;
    }

    /**
     * Método que permite obtener la colección de ficheros del sistema.
     *
     * @return colección de ficheros actuales.
     */
    public Ficheros getListaFicheros() {
        return listaFicheros;
    }

    /**
     * Método que permite obtener la colección de procesos actuales del sistema.
     *
     * @return colección de procesos actuales.
     */
    public Procesos getListaProcesos() {
        return listaProcesos;
    }

    /**
     * Método que permite obtener el último comando ingresado.
     *
     * @return último comando ingresado.
     */
    public String getUltimoComando() {
        return ultimoComando;
    }

    /**
     * Método que permite modificar la colección de comandos, dada otra
     * colección de comandos.
     *
     * @param comandos actuales del sistema.
     */
    public void setHashComandos(Comandos comandos) {
        this.hashComandos = comandos;
    }

    /**
     * Método que permite modificar la colección de ficheros, dada otra
     * colección de ficheros.
     *
     * @param ficheros ficheros actuales del sistema.
     */
    public void setListaFicheros(Ficheros ficheros) {
        this.listaFicheros = ficheros;
    }

    /**
     * Método que permite modificar la colección de procesos, dada otra
     * colección de procesos.
     *
     * @param procesos procesos actuales del sistema.
     */
    public void setListaProcesos(Procesos procesos) {
        this.listaProcesos = procesos;
    }

    /**
     * Método que permite modificar el último comando ingresado, dado otro
     * comando.
     *
     * @param ultimoComando el nuevo comando ingresado.
     */
    public void setUltimoComando(String ultimoComando) {
        this.ultimoComando = ultimoComando;
    }

    /**
     * Este método inicializa los componentes de la interfaz en sus valores por
     * defecto.
     */
    private void inicializarInterfaz() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Al presionar la cruz de cierre hace dispose  
        darEstilos();
        mostrarPrompt();
        // Garantizar que el foco esté en el JTextPane y el cursor en la posición correcta al abrir la ventana
        SwingUtilities.invokeLater(() -> {
            consola.requestFocusInWindow();
            consola.setCaretPosition(posicionPrompt);
        });
        // Mostrar el Jframe
        setLocationRelativeTo(null); // Centrar
        setVisible(true);
    }

    /**
     * Este método da estilo a los componentes de la interfaz.
     */
    private void darEstilos() {
        // Estilos para el texto
        Color rojosuave = new Color(255, 120, 123);
        doc = consola.getStyledDocument();
        estiloPrompt = consola.addStyle("Prompt", null);
        StyleConstants.setForeground(estiloPrompt, Color.GREEN);

        estiloComando = consola.addStyle("Comando", null);
        StyleConstants.setForeground(estiloComando, Color.WHITE);

        estiloOK = consola.addStyle("OK", null);
        StyleConstants.setForeground(estiloOK, Color.CYAN);

        estiloError = consola.addStyle("Error", null);
        StyleConstants.setForeground(estiloError, rojosuave);

        // Personalizar las barras de desplazamiento
        colorScroll(scrollPane.getVerticalScrollBar());
        colorScroll(scrollPane.getHorizontalScrollBar());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    /**
     * Este método modifica el color de la barra de desplazamiento.
     *
     * @param scrollBar
     */
    private void colorScroll(JScrollBar scrollBar) {
        scrollBar.setBackground(Color.BLACK);
        scrollBar.setForeground(Color.DARK_GRAY);
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.DARK_GRAY;
                this.trackColor = Color.BLACK;
                this.thumbHighlightColor = Color.GRAY;
                this.thumbDarkShadowColor = Color.BLACK;

            }
        });
    }

    /**
     * Este método coloca el prompt y el cursor en la posicion correcta
     */
    private void mostrarPrompt() {
        try {
            // Insertar el prompt en verde
            doc.insertString(doc.getLength(), prompt, estiloPrompt);
            posicionPrompt = doc.getLength();  // Establece la posición a partir de la cual se puede escribir
            consola.setCaretPosition(posicionPrompt);  // Coloca el cursor al final del prompt
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que muestra el resultado de la ejecucion aplicando un estilo
     * concreto.
     *
     * @param mensaje que se desea mostrar con el estillo correcto
     * @throws BadLocationException
     */
    private void mostrarConEstilo(String mensaje) throws BadLocationException {
        if (!mensaje.isEmpty()) {
            if (mensaje.trim().startsWith(">>") && (mensaje.trim().endsWith("<<"))) {
                // Mostrar el mensaje de error en rojo
                doc.insertString(doc.getLength(), "\n\n" + mensaje + "\n", estiloError);
            } else if (mensaje.trim().startsWith("-") && (mensaje.endsWith("\n") || mensaje.trim().endsWith("-"))) {
                // Mostrar el mensaje en celeste
                doc.insertString(doc.getLength(), "\n\n" + mensaje + "\n", estiloOK);
            } else {
                doc.insertString(doc.getLength(), "\n\n" + mensaje + "\n", estiloComando);// Mostrar en blanco            
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(0, 0));
        setSize(new java.awt.Dimension(1024, 500));
        setType(java.awt.Window.Type.UTILITY);

        scrollPane.setPreferredSize(new java.awt.Dimension(1024, 768));
        scrollPane.setRowHeaderView(null);

        consola.setBackground(new java.awt.Color(0, 0, 0));
        consola.setBorder(null);
        consola.setFont(new java.awt.Font("Lucida Console", 0, 23)); // NOI18N
        consola.setCaretColor(new java.awt.Color(255, 255, 255));
        consola.setMaximumSize(new java.awt.Dimension(1024, 768));
        consola.setMinimumSize(new java.awt.Dimension(1024, 768));
        consola.setPreferredSize(new java.awt.Dimension(1024, 768));
        consola.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                consolaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                consolaKeyTyped(evt);
            }
        });
        scrollPane.setViewportView(consola);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento presionar una tecla.
     *
     * Se utiliza para controlar la pulsaciòn de teclas fuera de zonas
     * permitidas, ademàs de mostrar el último comando ejecutado al presionar
     * las flechas.
     *
     * @param evt
     */
    private void consolaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consolaKeyPressed
        int caretPosition = consola.getCaretPosition();
        // Evitar que el cursor se mueva fuera del área del prompt
        if (caretPosition < posicionPrompt) {
            consola.setCaretPosition(posicionPrompt);
        }
        // Comprobar la tecla Backspace y evitar que borre el prompt
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (caretPosition <= posicionPrompt) {
                // Consumir el evento para que no borre nada antes del prompt
                evt.consume();
            }
        }
        // Comprobar las flechas para mostrar el último comando ingresado
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (ultimoComando != null && !ultimoComando.isEmpty()) {
                // Limpiar cualquier texto que se haya escrito después del prompt
                try {
                    doc.remove(posicionPrompt, doc.getLength() - posicionPrompt);
                    // Insertar el último comando después del prompt
                    doc.insertString(posicionPrompt, ultimoComando, estiloComando);
                    // Colocar el cursor al final del texto
                    consola.setCaretPosition(doc.getLength());
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
            // Prevenir el comportamiento predeterminado de mover el cursor con las flechas
            evt.consume();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || (evt.getKeyCode() == KeyEvent.VK_A && (evt.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            evt.consume();//Evitar el comportamiento natural de las teclas ENTER y CONTROL+A 
        }
    }//GEN-LAST:event_consolaKeyPressed

    /**
     * Evento de escritura en la consola.
     *
     * Al presionar ENTER se validan y ejecutan los comandos.
     *
     */
    private void consolaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consolaKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            // Obtener el comando introducido al presionar enter
            try {
                String comando = doc.getText(posicionPrompt, doc.getLength() - posicionPrompt).trim();
                ultimoComando = comando.trim(); // Guardar el último comando            
                Validar validador = new Validar(comando);
                String comandoaValidar = validador.validarComando(hashComandos);//Valida sintaxis completa con todos los parametros       
                //String[] tokens = validador.getTokens();
                if (comando.equals("exit")) { // Comando salir
                    this.dispose();
                } else if (comando.equals("")) { // Salto de linea si no hay comando                
                    doc.insertString(doc.getLength(), "\n", null);
                } else if (validador.tienePipe()) { // Si aparece un pipe                    
                        mostrarConEstilo(validador.validarPipe(listaFicheros));                  
                } else if (comandoaValidar.equals("200")) { // Si es un unico comando valido         
                    String resultado = validador.comenzarEjecucion(hashComandos, listaFicheros, listaProcesos, consola);
                    mostrarConEstilo(resultado);
                } else {
                    doc.insertString(doc.getLength(), "\n\n" + comandoaValidar + "\n", estiloError); // Si el comando no es válido                        
                }
                mostrarPrompt(); // Mostrar el nuevo prompt
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            // Prevenir la nueva línea predeterminada del JTextPane
            //evt.consume();                
        } else {
            consola.setCharacterAttributes(estiloComando, true);
            // Aseguranos de que el texto que se escribe aparece en blanco mientras no se presiona enter
        }
    }//GEN-LAST:event_consolaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane consola;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
