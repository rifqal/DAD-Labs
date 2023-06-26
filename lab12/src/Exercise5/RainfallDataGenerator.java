package Exercise5;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class RainfallDataGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String outFile = "rainfallData.txt";
		
		String dates[]= {"13/06/2023","	14/06/2023","15/06/2023","16/06/2023","17/06/2023","18/06/2023"};
		
		double rainfallAmount[]= {0.0,3.0,1.0,0.0,0.0,2.0};
		
		try {
			
			// 2. Create stream to read data
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(outFile));
			
			// Process data
			for (int index = 0; index < dates.length; index++) {
				
				// 3. Write data into data stream
				dos.writeUTF(dates[index]);
				dos.writeDouble(rainfallAmount[index]);
				
				// 4. Flush for each writing
				dos.flush();
			}
			
			// 5. Close stream
			dos.close();
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		// Indicate end of program - Could be successful
		System.out.println("End of program. Check out " + outFile); 
	}
}


