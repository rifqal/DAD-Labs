package Exercise7;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class RainfallDataWriter {
    public static void main(String[] args) {
        String outFile = "rainfallData2.txt";
        String dates[] = {"13/06/2023", "14/06/2023", "15/06/2023", "16/06/2023", "17/06/2023", "18/06/2023"};
        double rainfallAmount[] = {0.0, 3.0, 1.0, 0.0, 0.0, 2.0};

        try (Writer writer = new FileWriter(outFile)) {
            for (int i = 0; i < dates.length; i++) {
                String line = dates[i] + "\t" + rainfallAmount[i] + System.lineSeparator();
                writer.write(line);
            }
            System.out.println("Data successfully written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
