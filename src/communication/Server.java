package communication;

import java.io.IOException;
//import java.net.InetAddress;
import java.net.ServerSocket;

public class Server {
	static ServerSocket serverSocket = null;
    static ProtocolFactory protocolFactory = new ProtocolFactory();
    static int port = 8002;
    
    
    public static void main(String[] args) throws IOException {
    	//InetAddress host = InetAddress.getLocalHost();
    	//System.out.println(host);
    	serverSocket = new ServerSocket(port);
    	
    	boolean isShutDown = false;
		while (!isShutDown) {
            try {
            	
              ConnectionHandler newConnection = new ConnectionHandler(serverSocket.accept(), protocolFactory.create());
              new Thread(newConnection).start();
            } catch (IOException e) {
            	e.printStackTrace();
            }
          }
    }
}
