package clientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jesus
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Cliente c = new Cliente();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        c.recibirMensajes();
                    } catch (IOException ex) {
                        System.out.println("Se pederdio la conexion");
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
            
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            String mensaje;
            do{
                mensaje = entrada.readLine();
                c.enviarMensaje(mensaje);
            }while(!c.socket.isClosed());
            
        } catch (IOException ex) {
            System.out.println("No se encuentra ningun servidor");
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
