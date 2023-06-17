// Lab 5 - Hash Tables
// Alexander Mochizuki & Saranya Kolachana
// Utilize hash tables

package lab5;

public class Krone extends Currency {
	protected final String currencyName = "Krone";

	public Krone() {
		super();
	}

	public Krone(double amt) throws Exception {
		super(amt);
	}

	// Gets currencyName ("Krone").
	// Pre: none.
	// Post: none.
	// Return: currencyName ("Krone").
	@Override
	public String getCurrencyName() {
		return currencyName;
	}

}
