package clientesocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Jesus
 */
public class Cliente {

    public Socket socket;
    public int puerto_server = 9000;
    public String ip_server = "127.0.0.1";
    private BufferedReader entrada;
    private DataOutputStream salida;

    public Cliente() throws IOException {
        socket = new Socket(ip_server, puerto_server);
    }

    public void recibirMensajes() throws IOException {
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String mensaje;
        do {
            mensaje = entrada.readLine().trim();
            if (mensaje != null) {
                System.out.println("Server: " + mensaje.trim());
                if (mensaje.equals("adios")) {
                    socket.close();
                }
            }

        } while (!socket.isClosed());
    }

    public void enviarMensaje(String mensaje) throws IOException {
        if (socket != null && !socket.isClosed() && mensaje != null) {
            salida = new DataOutputStream(socket.getOutputStream());

            salida.writeUTF(mensaje + System.getProperty("line.separator"));
            System.out.println("Yo: " + mensaje);
            
            if (mensaje.equals("adios")) {
                socket.close();
            }
        } else {
            System.out.println("No se envio :( " + mensaje);
        }
    }

}
