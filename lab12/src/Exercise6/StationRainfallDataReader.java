package Exercise6;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class StationRainfallDataReader {

	public static void main(String[] args) {
	
	String districts[]= {"Alor Gajah","Durian Tunggal","Melaka Tengah"};
	
	String stations[]= {"Durian Tunggal","Melaka Pindah","Telok Rimba","Chohong","Klebang Besar","Batu Hampar"};
	
	int noOfDistricts=0;
	int noOftotalRecords=0;
	
	for(String station: stations)
	try {
		
		String fileName = station + ".txt";
		// 2. Create stream to read data
		DataInputStream dis = new DataInputStream(new FileInputStream(fileName));

		// Variables for processing byte-based data
		double rainfallAmount = 0;
		double totalRainfall = 0;

		int noOfRecords = 0;
		String date = "";
		System.out.println("District: "+districts[noOfDistricts]);
		System.out.println("Station: "+station);
		// 3. Process data until end-of-file
		while(dis.available() > 0) {
			
			// Read data
			date = dis.readUTF();
			rainfallAmount = dis.readDouble();
			System.out.println( date + "\t" + rainfallAmount);
			
			// Calculate total rainfall
			totalRainfall += rainfallAmount;
			noOfRecords ++;
			noOftotalRecords ++;
			if(noOftotalRecords%12==0) {
				noOfDistricts++;
			}
		}
		
		// 4. Close stream
		dis.close();
		
		// Calculate average rainfall
		double averageRainfall = totalRainfall / 6;
		String formattedAverage = String.format("%.1f", averageRainfall);
		System.out.println("Average rainfall for " + noOfRecords 
				+ " Days: " + formattedAverage + "\n\n");
		
	} catch (Exception ex) {
		
		ex.printStackTrace();
	}

	// Indicate end of program - Could be successful
	System.out.println("\nEnd of program.");
	}
}
