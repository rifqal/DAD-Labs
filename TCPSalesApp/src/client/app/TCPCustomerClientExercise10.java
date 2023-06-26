package client.app;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import client.view.CustomerViewer;
import model.Customer;
import server.controller.CustomerDataManager;

public class TCPCustomerClientExercise10 {

	public static void main(String[] args) {

        Customer customer;
        CustomerViewer customerViewer = new CustomerViewer();
        CustomerDataManager manager = new CustomerDataManager();

        try {

            // Server information
            int serverPortNo = 8087;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // 1. Connect to remote machine
            Socket socket = new Socket(serverAddress, serverPortNo);

            // get customer from server
            // Create stream to receive response from the server
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            // read customer object from server
            // // 3. Read respond from the server - cast the object
            List<Customer> customers = (List<Customer>) ois.readObject();

            // display customers
            customerViewer.displayCustomers(customers);

            // 4. Close connection
            socket.close();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }
	
}
