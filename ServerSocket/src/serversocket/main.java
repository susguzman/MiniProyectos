

package serversocket;

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
            Server s = new Server();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        s.iniciar();
                    } catch (IOException ex) {
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
                        
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String m;
            do{
                m = br.readLine();
                s.enviarMensaje(m);
            }while(m != null && !m.equals("adios"));
            
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
