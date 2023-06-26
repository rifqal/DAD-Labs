package Exercise6;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class StationRainfallDataGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		String dates[]= {"13/06/2023","14/06/2023","15/06/2023","16/06/2023","17/06/2023","18/06/2023"};
		
		String districts[]= {"Alor Gajah","Durian Tunggal","Melaka Tengah"};
		
		String stations[]= {"Durian Tunggal","Melaka Pindah","Telok Rimba","Chohong","Klebang Besar","Batu Hampar"};
		
		double rainfallAmount[][]= {
				{0.0,6.0,1.0,0.0,0.0,1.0},
				{0.0,1.5,14.0,0.0,0.0,2.0},
				{3.0,23.0,0.0,0.0,0.0,12.0},
				{0.0,11.5,0.0,0.0,0.0,4.0},
				{0.0,0.0,15.0,0.0,0.0,4.0},
				{0.0,0.0,0.5,0.0,0.0,4.0},
		};

		try {
			
		//Process data
		for (int index1 =0;index1<districts.length;index1++) {
			for(int index2 =0;index2<stations.length;index2++) {

				String fileName = stations[index2] + ".txt";
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));
				
					for (int index3 = 0; index3 < dates.length; index3++) {
					// 3. Write data into data stream
					dos.writeUTF(dates[index3]);
					dos.writeDouble(rainfallAmount[index2][index3]);
					
					// 4. Flush for each writing
					dos.flush();
					
					}
				dos.close();
				}
			}
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}

}
