package gui;

import consola.Comandos;
import consola.Ejecutar;
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
 * Clase que representa los elementos graficos de la consola y los eventos asociados 
 * 
 * @author Gabriel, Anna, Santiago, Juan y Gonzalo
 */
public class LaConsola extends javax.swing.JFrame {
    
   /**
    * Colecciones necesarias para crear la consola de Linux
    */ 
    private Comandos hashComandos;
    private Ficheros listaFicheros;
    private Procesos listaProcesos;   
   /**
    * String para almacenar el ultimo comando ejecutado
    */ 
    private String ultimoComando;
   /**
    * Prompt y estilos
    */
    private StyledDocument doc;
    private Style estiloPrompt, estiloComando, estiloError, estiloOK;
    private String prompt = "LaConsola@inet:~$ ";
    private int posicionPrompt;   
   /**
    * Constructor de la consola
    */
    public LaConsola() {
        
        hashComandos = new Comandos();        
        listaFicheros = new Ficheros();
        listaProcesos = new Procesos();        
        listaFicheros.cargarPrimerNivel(); // Primer nivel cargado desde memoria
        
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        // Estilos para el texto
        Color rojosuave= new Color(255, 120, 123);        

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

        // Mostrar el prompt inicial
        mostrarPrompt();
        // Garantizar que el foco esté en el JTextPane y el cursor en la posición correcta al abrir la ventana
        SwingUtilities.invokeLater(() -> {
            consola.requestFocusInWindow();
            consola.setCaretPosition(posicionPrompt);
        });
        //Mostrar el Jframe
        setLocationRelativeTo(null); 
        setVisible(true);
        
    }
    
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
     * Metodo que colocar el prompt y el cursor en la posicion correcta
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
     * Método que muestra el resultado de la ejecucion aplicando un estilo concreto.
     * 
     * @param  mensaje que se desea mostrar con el estillo correcto
     * @throws BadLocationException 
     */
    private void mostrarConEstilo(String mensaje) throws BadLocationException {      
            
        if (mensaje.trim().startsWith(">>")&& mensaje.trim().endsWith("<<")){
            // Mostrar el mensaje de error en rojo
            doc.insertString(doc.getLength(), "\n\n" + mensaje + "\n", estiloError);
        } else if (mensaje.trim().startsWith("-") && (mensaje.trim().endsWith("-")||mensaje.endsWith("\n"))){
            // Mostrar el mensaje en celeste
            doc.insertString(doc.getLength(), "\n\n" + mensaje + "\n", estiloOK);            
        } else {
            doc.insertString(doc.getLength(), "\n\n" + mensaje + "\n", estiloComando);// Mostrar en blanco            
            }
        }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento presionar una tecla
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
        if (evt.getKeyCode() == KeyEvent.VK_UP||evt.getKeyCode() == KeyEvent.VK_DOWN)  {
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
        
        if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            evt.consume();      
        
        
        }
            
    }//GEN-LAST:event_consolaKeyPressed
        
    /**
     * Evento de escritura en la consola
     * 
     */
    private void consolaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consolaKeyTyped
       if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
        // Obtener el comando introducido al presionar enter
        
        try {
            String comando = doc.getText(posicionPrompt, doc.getLength() - posicionPrompt).trim();
            ultimoComando = comando.trim(); // Guardar el último comando
            
            Validar validador = new Validar(comando);        
            Boolean comandoValido = validador.validarComando(hashComandos);//Valida sintaxis completa con todos los parametros       
            String[] tokens = validador.getTokens();
            Ejecutar ejecutar = new Ejecutar(tokens);            
           
           if (comando.equals("exit")) { // Comando salir
             this.dispose(); 
           } else if (validador.posicionPipe()!=0){            
                consola.setText(ejecutar.ejecutarPipe(validador.posicionPipe(),listaFicheros)+ "\n");               
           } else if (comandoValido) { // Si el comando es válido           
                String resultado = ejecutar.ejecutarComando(hashComandos, listaFicheros, listaProcesos, consola);
                mostrarConEstilo(resultado);                    
           } else {                     
                if(!comando.isEmpty()){
                doc.insertString(doc.getLength(), "\n>> Comando ingresado " + comando + " incorrecto <<\n"+
                "[Intente man "+tokens[0]+"]\n", estiloError); // Si el comando no es válido                        
                }      
           }
            // Mostrar el nuevo prompt
            mostrarPrompt();
        }catch (BadLocationException ex) {
        }
        // Prevenir la nueva línea predeterminada del JTextPane
        evt.consume();                
        } else {
        consola.setCharacterAttributes(estiloComando, true);                    
        // Asegurar que el texto que se escribe aparece en blanco
        }        
    }//GEN-LAST:event_consolaKeyTyped
     
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
     * Método que permite modificar la colección de comandos, dada otra
     * colección de comandos.
     *
     * @param comandos
     */
    public void setHashComandos(Comandos comandos) {
        this.hashComandos = comandos;
    }

    /**
     * Método que permite modificar la colección de ficheros, dada otra
     * colección de ficheros.
     *
     * @param ficheros
     */
    public void setListaFicheros(Ficheros ficheros) {
        this.listaFicheros = ficheros;
    }

    /**
     * Método que permite modificar la colección de procesos, dada otra
     * colección de procesos.
     *
     * @param procesos
     */
    public void setListaProcesos(Procesos procesos) {
        this.listaProcesos = procesos;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane consola;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
