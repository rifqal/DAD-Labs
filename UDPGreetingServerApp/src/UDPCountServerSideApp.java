import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import serverside.SentenceProcessor;



public class UDPCountServerSideApp {

    public static void main(String[] args) {

        System.out.println("Running UDPCountServer....");

        // Permissible port for this application
        int portNo = 8083;

        try {

            // 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);

            // Continually listen for request
            while (true) {

                // 2. Variable to received data from the port
                // 65535 is the maximum size for UDP packet
                byte[] receivedData = new byte[65535];

                // 3. Object represents packet from client
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);

                // 4. Receive packet
                datagramSocket.receive(receivedPacket);

                // 5. Extract data from packet
                receivedData = receivedPacket.getData();

                // 6. Further processing
                SentenceProcessor processor = new SentenceProcessor(receivedData);
                String sentence = processor.getSentence();
                System.out.println("\nSentence received from client: '" + sentence + "' .\n");

                // Get the number of vowels, consonants and punctuation
                int vowels = processor.getVowels();
                int consonants = processor.getConsonants();
                int punctuation = processor.getPunctuation();

                byte[] outVowelData = processor.convertToByteArray(vowels);
                byte[] outConsonantData = processor.convertToByteArray(consonants);
                byte[] outPunctuationData = processor.convertToByteArray(punctuation);

                // 7. Get the client information
                InetAddress clientAddress = receivedPacket.getAddress();
                int clientPort = receivedPacket.getPort();
                // int sizeOutData = outData.length;

                // 8. Wrap data into datagram packet
                // wrap vowels data in datagram packet
                DatagramPacket outVowelPacket = new DatagramPacket(outVowelData, outVowelData.length, clientAddress,
                        clientPort);

                // wrap consonants data in datagram packet
                DatagramPacket outConsonantPacket = new DatagramPacket(outConsonantData, outConsonantData.length,
                        clientAddress, clientPort);

                // wrap punctuation data in datagram packet
                DatagramPacket outPunctuationPacket = new DatagramPacket(outPunctuationData, outPunctuationData.length,
                        clientAddress, clientPort);

                // 9. Reply to client
                datagramSocket.send(outVowelPacket);
                datagramSocket.send(outConsonantPacket);
                datagramSocket.send(outPunctuationPacket);

                System.out.println("Message sent\n(total vowels): " + vowels
                        + "\n(total consonants): " + consonants
                        + "\n(total punctuation): " + punctuation
                        + "\n");

            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("UDPCountServer: End of program.");
    }
}