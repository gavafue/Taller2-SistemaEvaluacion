package conexion;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import gui.Login;
import java.io.IOException;

/**
 * Clase que da inicio al programa. 
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class Main {

    /**
     * Método que da inicio al programa.
     *
     * @param args argumentos del main.
     * @throws IOException si da error de conexión.
     */
    public static void main(String[] args) throws IOException {
        FlatMacLightLaf.setup();
        Login login = new Login();
        login.setVisible(true);
    }
}
