package info.pello.android.threads;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * NameGenerator
 * generates random names alternating vowels/consonants and sometimes
 * duplicating them. It makes use of a BlockingQueue to share names
 * with a consumer that will take care of notifying to our Android Activity.
 * @author Pello Xabier Altadill Izura
 * @greetz OOoooh I'm using threads and queues again oooo uuuu aaa
 *
 */
public class NameGenerator extends Thread {
	private int size = 2;
	private String [] vowels = {"a","e","i","o","u","y","ee","oo"}; 
	private String [] consonants = {"b","c","d","f","g","h","j","k","l",
									"m","n","p","q","r","s","t","v","w","x","y","z"};
	private String [][] alternate = {vowels,consonants};
	private int [] sizes = {vowels.length, consonants.length};
	
	private BlockingQueue<String> generatedNames;
	
	private int isVowel = 0;
	private Random random = new Random();
	private int total = 100;
	private static final int DUPLICATION_POSSIBILITY = 20;
	private static final int SLEEP_TIME = 2000;
	
	
	
	/**
	 * default constructor
	 * @param thread name
	 */
	public NameGenerator (String name) {
		super(name);
	}
	
	/**
	 * Constructor with size parameter
	 * @param size
	 */
	public NameGenerator (String name, int size, BlockingQueue<String> generatedNames, int total) {
		super(name);
		this.size = size;
		this.generatedNames = generatedNames;
		this.total  = total;
	}

	/**
	 * method executed when thread is started
	 */
	public void run () {
		String name = "";
		while (true) {
			if (total == 0) {
				System.err.println(this.getName() + " > Our work is done here. Bye!");
				return; 
			}
			name = generate();
			System.out.println("Generated name: " + name);
			try {
				generatedNames.put(name);
				total--;
				sleep(random.nextInt(SLEEP_TIME) + SLEEP_TIME);
			} catch (InterruptedException ie) {
				System.err.println("Exception in generation thread: " + ie.getMessage());
			}
		}
	}
	
	/**
	 * generates a name
	 * @return
	 */
	private String generate () {
		String result = "";
		int counter = 0;
		String lastChar = "";
		isVowel = counter = random.nextInt(2);
		
		for (int i = 0; i < this.size; i++) {
			lastChar = generateChar();
			lastChar = duplicateOrNot(lastChar);
			result += lastChar;
			
			// We prepare next round
			counter++;
			isVowel = counter % 2;
		}
		
		return result;
	}

	/**
	 * we generate a random char from vowels or consonants
	 * @return
	 */
	private String generateChar() {
		String lastChar;
		lastChar = alternate[isVowel][random.nextInt(sizes[isVowel])];
		return lastChar;
	}

	/**
	 * We apply 20% possibilities to duplicate vowel or consonant
	 * @param lastChar
	 * @return
	 */
	private String duplicateOrNot(String lastChar) {
		lastChar += (random.nextInt(100)< DUPLICATION_POSSIBILITY)?lastChar:"";
		return lastChar;
	}
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

}

//... OOooo uuuuu aaaaa ... xDDDD