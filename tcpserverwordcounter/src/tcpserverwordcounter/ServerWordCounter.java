package tcpserverwordcounter;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWordCounter {

public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = null;
		
		try {
		
			int portNo = 4221
					
					;
			serverSocket = new ServerSocket(portNo);
			// Generate sentence
			int wordAmount = WordCounter.countWordAmount("Hello World! What a day huh");
			// Server needs to be alive forever
			System.out.println(wordAmount);
			while (true) {



				// Accept client request for connection
				Socket clientSocket = serverSocket.accept();

				// Create stream to write data on the network
				DataOutputStream outputStream = 
						new DataOutputStream(clientSocket.getOutputStream());

				// Send current date back to the client
				outputStream.writeInt(wordAmount);

				// Close the socket
				clientSocket.close();


				//serverSocket.close();
			}
		} catch (IOException ioe) {
			if(serverSocket !=null)
				serverSocket.close();
			ioe.printStackTrace();
		}
		

	}
}