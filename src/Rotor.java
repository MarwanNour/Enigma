import java.util.Arrays;

/**
 * 
 * @author Marwan Nour
 *
 */
public class Rotor {

	private char[] alphRight;
	private char notchLetter;
	private char[] alphLeft = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public Rotor(char[] alphRight, char notchLetter) {

		this.alphRight = alphRight;
		this.notchLetter = notchLetter;
	}

	public char[] getAlphRight() {
		return alphRight;
	}

	public void setAlphRight(char[] alphRight) {
		this.alphRight = alphRight;
	}

	public char[] getAlphLeft() {
		return alphLeft;
	}

	public void setAlphLeft(char[] alphLeft) {
		this.alphLeft = alphLeft;
	}

	public char getNotchLetter() {
		return notchLetter;
	}

	public void setNotchLetter(char notchLetter) {
		this.notchLetter = notchLetter;
	}

	@Override
	public String toString() {
		return "Rotor [	alphRight=" + Arrays.toString(alphRight) + ", notchLetter=" + notchLetter + "]";
	}

	/**
	 * Rotate the rotor by one function. The rotor will rotate once when the user
	 * inputs one keystroke
	 * 
	 */
	public void rotateRotorByOne() {
		// Rotate Left Array
		char tempLeft = this.alphLeft[0];
		int i;
		for (i = 0; i < 25; i++) {
			this.alphLeft[i] = this.alphLeft[i + 1];
		}
		alphLeft[i] = tempLeft;

		// Rotate Right Array
		char tempRight = this.alphRight[0];
		for (i = 0; i < 25; i++) {
			this.alphRight[i] = this.alphRight[i + 1];
		}
		alphRight[i] = tempRight;

	}

	/**
	 * Create Partial Key function: Creates a single character key by rotating the
	 * rotors
	 * 
	 * @param key : character
	 */
	public void createPartialKey(char key) {
		for (char c = 'a'; c < key; c++) {
			rotateRotorByOne();
		}
	}

}
