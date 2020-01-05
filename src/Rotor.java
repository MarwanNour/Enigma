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
	 * Function to Rotate the rotor. The rotor will rotate once the user inputs one
	 * keystroke
	 * 
	 */
	public void rotateRotor() {
		
		
	}

}
