import java.util.HashMap;
import java.util.Random;

/**
 * 
 * @author Marwan Nour
 *
 */
public class Driver {

	/**
	 * Generate random Rotor function
	 * 
	 * @param alphRight   : the random characters
	 * @param notchLetter : the letter where the notch is present
	 * @return the Rotor with random characters
	 */
	public static Rotor generateRotor(char notchLetter) {
		char[] alphRight = new char[26];

		// populate the array
		int index = 0;
		for (char c = 'a'; c <= 'z'; c++) {
			alphRight[index++] = c;
		}

		// shuffle the array
		Random rand = new Random();
		for (int i = 0; i < alphRight.length; i++) {
			int randomIndextoSwap = rand.nextInt(alphRight.length);
			char temp = alphRight[randomIndextoSwap];
			alphRight[randomIndextoSwap] = alphRight[i];
			alphRight[i] = temp;
		}

		return new Rotor(alphRight, notchLetter);
	}

	/**
	 * Encryption/Decryption Function
	 * 
	 * @param key       : the key size is the number of rotors
	 * @param rotorList : array of Rotors
	 * @return the encrypted string
	 */
	public String encrypt(char[] key, Rotor[] rotorList, String str) {
		/*
		 * The user inputs the key and the list of rotors. The rotors are in sequence.
		 * Rotor[0] is the first rotor that will spin on every input Rotor[1] will spin
		 * when Rotor[0] does a full spin
		 * 
		 */
		for (int i = 0; i < str.length(); i++) {
			// encrypt/decrypt letter
		}

		return null;
	}

	/**
	 * Visualize Rotors Function
	 * 
	 * @param rotorList : the array of Rotors
	 */
	public static void visualizeRotors(Reflector Refl, Rotor[] rotorList) {
		// Reflector
		System.out.print("Refl" + "\t");
		int rotorIndex = 0;
		for (int i = 0; i < rotorList.length; i++) {
			System.out.print(" R" + rotorIndex + "\t");
			rotorIndex++;
		}
		// In/out
		System.out.print("\tI/O");
		System.out.println();

		// AlphAr for In/out
		char[] alphAr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };

		// Extract the key set and value set from Reflector map
		String keySet = Refl.getMap().keySet().toString();
		String valueSet = Refl.getMap().values().toString();

//		System.out.println(keySet);
//		System.out.println(valueSet);

		// Clean the strings
		keySet = keySet.replace(" ", "");
		keySet = keySet.replace(",", "");
		keySet = keySet.replace("[", "");
		keySet = keySet.replace("]", "");
//		System.out.println(keySet);

		valueSet = valueSet.replace(" ", "");
		valueSet = valueSet.replace(",", "");
		valueSet = valueSet.replace("[", "");
		valueSet = valueSet.replace("]", "");
//		System.out.println(valueSet);

		// Arrays look like this now: "[a,b,c...]". Must get rid of "[,] " when printing
		// Create cleaner arrays and fill them with alphabets
		char[] keySetClean = keySet.toCharArray();
		char[] valueSetClean = valueSet.toCharArray();

		// Loop over each character (to print each line)
		for (int i = 0; i < 26; i++) {
			// Print the Reflector
			System.out.print("|" + keySetClean[i] + " - " + valueSetClean[i] + "| ");

			// Print the Rotors
			for (Rotor r : rotorList) {
				if (r.getAlphLeft()[i] == r.getNotchLetter()) {
					System.out.print("|" + r.getAlphLeft()[i] + "*- " + r.getAlphRight()[i] + "| ");
				} else {
					System.out.print("|" + r.getAlphLeft()[i] + " - " + r.getAlphRight()[i] + "| ");
				}
			}

			// Print the I/O
			System.out.print("\t|" + alphAr[i] + "|");

			System.out.println();
		}

		System.out.println();
	}

	/**
	 * Create Full Key Function : Create a full key for the rotor list by creating
	 * partial keys for each rotor
	 * 
	 * @param key
	 * @param rotorList
	 * @throws Exception
	 */
	public static void createFullKey(String key, Rotor[] rotorList) throws Exception {
		System.out.println("key length " + key.length());
		System.out.println("Rotor list length " + rotorList.length);
		if (key.length() != rotorList.length) {
			throw new Exception("Key must of the same size of the number of rotors");
		}
		char[] keyAr = key.toCharArray();
		for (int i = 0; i < rotorList.length; i++) {
			rotorList[i].createPartialKey(keyAr[i]);
		}
	}

	/**
	 * Generate Reflector Function that create a random Reflector
	 * 
	 * @return a random Reflector
	 */
	public static Reflector generateReflector() {
		HashMap<Character, Character> map = new HashMap<Character, Character>();

		// create alphabet array
		char[] alphRight = new char[26];

		// populate the array
		int index = 0;
		for (char c = 'a'; c <= 'z'; c++) {
			alphRight[index++] = c;
		}

		// shuffle the array
		Random rand = new Random();
		for (int i = 0; i < alphRight.length; i++) {
			int randomIndextoSwap = rand.nextInt(alphRight.length);
			char temp = alphRight[randomIndextoSwap];
			alphRight[randomIndextoSwap] = alphRight[i];
			alphRight[i] = temp;
		}

		char[] leftPart = new char[13];
		char[] rightPart = new char[13];

		for (int i = 0; i < 13; i++) {
			leftPart[i] = alphRight[i];
		}

		for (int i = 0; i < 13; i++) {
			rightPart[i] = alphRight[i + 13];
		}

		// fill the map
		for (int i = 0; i < 13; i++) {
			map.put(leftPart[i], rightPart[i]);
		}

		for (int i = 0; i < 13; i++) {
			map.put(rightPart[i], leftPart[i]);
		}

		return new Reflector(map);
	}

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Rotor R0 = generateRotor('a');
		Rotor R1 = generateRotor('e');
		Rotor R2 = generateRotor('g');
//
//		// Testing Rotation
//		String str = "bijthaenlyumxpdkzvgsrwfqco";
//		char[] charAr = str.toCharArray();
//		Rotor R3 = new Rotor(charAr, 'h');
//		Rotor[] rotorList = { R0, R1, R2, R3 };
//		visualizeRotors(rotorList);
//		
//		R3.rotateRotorByOne();
//		visualizeRotors(rotorList);
//		
//		// Testing Key production
//		String myKey = "doge";
//		createFullKey(myKey, rotorList);
//		visualizeRotors(rotorList);

		Reflector Refl = generateReflector();
		System.out.println();

		Rotor[] rotorList = { R0, R1, R2 };
		visualizeRotors(Refl, rotorList);
	}

}
