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
	 * @param rotorList : array of Rotors
	 * @param str       : string to encrypt/decrypt
	 * @param Refl      : reflector
	 * @return the encrypted string
	 */
	public static String encrypt(Rotor[] rotorList, String str, Reflector Refl) {
		/*
		 * The user inputs the key and the list of rotors. The rotors are in sequence.
		 * Rotor[0] is the first rotor that will spin on every input Rotor[1] will spin
		 * when Rotor[0] does a full spin
		 * 
		 */

		// Create alphabet array I/O
		char[] alph = new char[26];
		int alphIndex = 0;
		for (char c = 'a'; c <= 'z'; c++) {
			alph[alphIndex] = c;
			alphIndex++;
		}

		// Create Result string
		String result = "";

		// Get Reflector columns as arrays
		String keySet = Refl.getMap().keySet().toString();
		String valueSet = Refl.getMap().values().toString();
		keySet = keySet.replace(" ", "");
		keySet = keySet.replace(",", "");
		keySet = keySet.replace("[", "");
		keySet = keySet.replace("]", "");
		valueSet = valueSet.replace(" ", "");
		valueSet = valueSet.replace(",", "");
		valueSet = valueSet.replace("[", "");
		valueSet = valueSet.replace("]", "");
		char[] keySetClean = keySet.toCharArray();
		char[] valueSetClean = valueSet.toCharArray();

		// encrypt/decrypt letter by letter
		for (int i = 0; i < str.length(); i++) {
			// keep track of letter going in, letter going out, IN index and OUT index
			char inChar;
			char outChar;
			int inIndex;
			int outIndex = 0;

			/*
			 * Input to Reflector : Get input letter position in the I/O (alphabet array)
			 * then go towards Reflector
			 */

			// Loop over the alphabet array
			for (int j = 0; j < 26; j++) {
				if (str.charAt(i) == alph[j]) {
					inChar = alph[j];
					System.out.println("inputChar = " + inChar);
					inIndex = j;
					// Loop from right most rotor to the left most
					for (int k = rotorList.length - 1; k >= 0; k--) {

						/* HANDLE ROTATIONS BEFORE */

						// get corresponding IN letter
						inChar = rotorList[k].getAlphRight()[inIndex];
						System.out.println("inChar in R" + k + "= " + inChar);

						// find the OUT letter which is the alphRight Character (loop to find it)
						for (int l = 0; l < 26; l++) {
							if (rotorList[k].getAlphLeft()[l] == inChar) {
								outChar = inChar;
								outIndex = l;
								inIndex = l;
								System.out.println("outChar in R" + k + "= " + outChar);
								break; // no need to keep looping after finding OUT letter
							}
						}
					}

					break; // no need to keep looping
				}
			}

			/* Reflector */
			// Get letter from right col (valueSet) of Reflector at outIndex
			char rightReflChar = valueSetClean[outIndex];
			System.out.println("right char reflector = " + rightReflChar);
			char leftReflChar = keySetClean[outIndex];
			System.out.println("left char reflector = " + leftReflChar);

			/*
			 * Reflector to Output : Get output letter position in the reflector then go
			 * towards the output
			 */

			// look for the same character from the left column in the right column of the
			// reflector and take its index
			for (int j = 0; j < 26; j++) {
				if (leftReflChar == valueSetClean[j]) {

					outChar = leftReflChar;
					System.out.println("outChar(from reflector) = " + outChar);
					outIndex = j;

					// Loop from left most rotor to right most
					for (int k = 0; k < rotorList.length; k++) {
						// no rotations
						// Get corresponding IN letter
						inChar = rotorList[k].getAlphLeft()[outIndex];
						System.out.println("inChar in R" + k + "= " + inChar);

						// find OUT letter with loop
						for (int l = 0; l < 26; l++) {
							if (rotorList[k].getAlphRight()[l] == inChar) {
								outChar = inChar;
								outIndex = l;
//								inIndex = l;
								System.out.println("outChar in R" + k + "= " + outChar);
//								System.out.println("outIndex = " + outIndex);
								break; // no need to keep looping after finding OUT letter
							}
						}
					}

					break; // no need to keep looping
				}
			}

			/* Ouput */
			// Get the corresponding letter in the alphabet array at position outIndex;
			char encryptedChar = alph[outIndex];
			System.out.println("encrypted char = " + encryptedChar);
			System.out.println();

			/*
			 * 
			 * ININDEX (USELESS ?)
			 * 
			 * 
			 */
		}

		return result;
	}

	/**
	 * Visualize Rotors Function
	 * 
	 * @param rotorList : the array of Rotors
	 */
	public static void visualizeRotors(Reflector Refl, Rotor[] rotorList) {
		// Reflector
		System.out.print("Refl" + "\t\t");
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
			System.out.print("|" + keySetClean[i] + " - " + valueSetClean[i] + "|\t\t");

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
//		Rotor R2 = generateRotor('g');
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

		Rotor[] rotorList = { R0, R1 };
		visualizeRotors(Refl, rotorList);

		encrypt(rotorList, "bob", Refl);
	}

}
