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
    
    public Server() throws IOException{
        server = new ServerSocket(puerto);
    }
    
    public void iniciar() throws IOException{
        socket = server.accept();
        
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());
        
        String mensaje;
        do{
            mensaje = in.readLine();
            System.out.println("cliente: " + mensaje);
            
        }while(!mensaje.equals("adios"));
        
        socket.close();
        
    }
    
    public void enviarMensaje(String texto) throws IOException{
        if(socket != null && socket.isConnected()){
            System.out.println("server: " + texto);
            out.writeUTF(texto + System.getProperty("line.separator"));
            
            if(texto.equals("adios")){
                socket.close();
            }
        }else{
            System.out.println("no enviado :( " + texto);
        }
    }
}
