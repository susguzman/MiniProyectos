package serversocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Jesus
 */
public class Server {

    public ServerSocket server;
    public Socket socket;
    public int puerto = 9000;
    public BufferedReader in;
    public DataOutputStream out;

    public Server() throws IOException {
        server = new ServerSocket(puerto);
    }

    public void iniciar() throws IOException {
        socket = server.accept();

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());

        String mensaje;
        do {
            mensaje = in.readLine().trim();
            if (mensaje != null) {
                System.out.println("cliente: " + mensaje);
                if (mensaje.equals("adios")) {
                    socket.close();
                }
            }
        } while (!socket.isClosed());
    }

    public void enviarMensaje(String texto) throws IOException {
        if (socket != null && !socket.isClosed() && texto != null) {
            System.out.println("server: " + texto);
            out.writeUTF(texto + System.getProperty("line.separator"));

            if (texto.equals("adios")) {
                socket.close();
            }
        } else {
            System.out.println("no enviado :( " + texto);
        }
    }
}
