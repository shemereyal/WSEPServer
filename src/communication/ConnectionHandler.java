package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
	private BufferedReader in;
    private PrintWriter out;
    Socket clientSocket;
    Protocol protocol;

    public ConnectionHandler(Socket acceptedSocket, Protocol p) {
      in = null;
      out = null;
      clientSocket = acceptedSocket;
      protocol = p;
    }

    private void initialize() throws IOException {
        // Initialize I/O
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
        out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"UTF-8"), true);
    }
    
	public void run() {
		try {
			initialize();
		} catch (IOException e) {
			System.out.println("Could not initialize buffers.");
		}
		System.out.println("connection initialized");
		out.println("Server says: Connected!EOM");
		try {
			/*String msg, response;
			char cbuf[] = new char[256];
			while (in.read(cbuf) > 0 && (msg = new String(cbuf)) != null && !msg.isEmpty()) {
				if (msg.length() > 3) {
					System.out.println("msg recieved: " + msg);
					response = protocol.processMessage(msg);
					System.out.println("response sent: " + response);
					out.println(response); 
				}
			}
			System.out.println("connection closed");*/
			while (true) {
				
				String msg, response;
				msg = recieveMessage().trim();
				System.out.println("msg recieved: " + msg);
				if (msg.equals("")) {
					response = protocol.processMessage(msg);
					System.out.println("response sent: " + response);
					sendMessage(response);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		close();
	}

	public void close() {
        try {
          if (in != null) {
            in.close();
          }
          if (out != null) {
            out.close();
          }
          clientSocket.close();
        } catch (IOException e) {
          System.out.println("Exception in closing I/O");
        }
      }

	public String recieveMessage() throws UnsupportedEncodingException, IOException {
		char[] input = new char [256];
		String msg="", curr="";
		while(in.read(input, 0, 255) != -1) {
			curr = new String(input);
			if (curr.contains("EOM")) {
				break;
			} else {
				msg += curr;
			}
		}
			msg += curr;
		System.out.println("AFTER LOOP");
		msg += curr;
		
		return msg.substring(0, msg.length()-3);
	}

	public void sendMessage(String msg) throws UnsupportedEncodingException, IOException
	{
		while(msg.length()>=253)
		{
			out.print(msg.substring(0, 255));
			System.out.println(msg.substring(0, 255));
			System.out.println(msg.substring(256));
			msg=msg.substring(256);
		}
		out.print(msg+"EOM");
	}
}
