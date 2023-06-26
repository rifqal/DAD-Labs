import java.util.Date;

/**
 * This class generates date.
 * 
 * @author emalianakasmuri
 *
 */
public class DateGenerator {

	
	/**
	 * This method generates current date.
	 * 
	 * @return current date
	 */
	public String getCurrentDate() {
		
		String currentDate = new Date().toString();
		
		return currentDate;
		
	}
	
}
