package Exercise5;


import java.io.DataInputStream;
import java.io.FileInputStream;

public class RainfallDataReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		// 1. Declare output file 
		String sourceFile = "rainfallData.txt";
		System.out.println("Reading data from " + sourceFile + "\n");

		
		try {

			// 2. Create stream to read data
			DataInputStream dis = new DataInputStream(new FileInputStream(sourceFile));

			// Variables for processing byte-based data
			double rainfallAmount = 0;
			double totalRainfall = 0;

			int noOfRecords = 0;
			String date = "";
			
			// 3. Process data until end-of-file
			while(dis.available() > 0) {
				
				// Read data
				date = dis.readUTF();
				rainfallAmount = dis.readDouble();
				System.out.println( date + "\t" + rainfallAmount);
				
				// Calculate total utilization
				totalRainfall += rainfallAmount;
				noOfRecords ++;
			}
			
			// 4. Close stream
			dis.close();
			
			// Calculate average utlization
			double averageRainfall = totalRainfall / noOfRecords;
			String formattedAverage = String.format("%.1f", averageRainfall);
			System.out.print("\nAverage rainfall for " + noOfRecords 
					+ " dates: " + formattedAverage);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

		// Indicate end of program - Could be successful
		System.out.println("\nEnd of program.");
		

	}
	
}
