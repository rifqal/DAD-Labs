package tcpserverwordcounter;

/*
 * This function is here to count the amount of words in a sentence
 */

public class WordCounter {

	public static int countWordAmount(String sentence)
	{
		   // return sentence.length();
		
		if (sentence == null || sentence.isEmpty()) {
		      return 0;
		    }

		    String[] words = sentence.split("\\s+");
		    return words.length;
	}
}
