package server.app;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import model.Customer;
import server.controller.CustomerDataManager;

public class TCPCustomerServerExercise4 {

	public static void main(String[] args) {

        int portNo = 8087;

        CustomerDataManager manager = new CustomerDataManager();

        System.out.println("\n\tExecuting TCPCustomersServerApp");

        List<Customer> customers;

        try {

            System.out.println("\tWaiting for next request");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server need to continually run to listen to request
            while (true) {

                // 3. Accept request from client
                Socket clientSocket = serverSocket.accept();

                // Get customers
                customers = manager.getCustomers();

                // send customer to client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(customers);

                System.out.println("\tSending Customer object to client.. ");

            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }
	
}
