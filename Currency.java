// Lab 5 - Hash Tables
// Alexander Mochizuki & Saranya Kolachana
// Utilize hash tables

// Changed: print() now returns a String instead of void.
// print() no longer actually prints.
// I think this to to follow a practice of only printing in main().
// I didn't have to do that.

package lab5;

public abstract class Currency {
	private int wholePart;
	private int fracPart; // 1/100 whole part

	// Default constructor
	public Currency() {
		wholePart = 00;
		fracPart = 00;
	}

	// Construct with 1 dbl: no negs
	public Currency(double amt) throws Exception {
		try {
			if (amt < 0.00) {
				throw new Exception("Invalid construction: Negative intitialization");
			}
			wholePart = (int) amt;
			fracPart = (int) (amt * 100.0) % 100;
			// TODO: Verify that fracPart works as intended.
			// Curr intent is to take 2 decimals.				// Alt: fP=amt*100 - whole*100
		} catch (Exception negAmt) {
			throw negAmt;
		}
	}
	//Copy const/assgn
	// I can't instantiate Currency objs.
	public Currency(Currency currency) {
		try {
			// What about negative currencies?
			this.wholePart = currency.wholePart;
			this.fracPart = currency.fracPart;
		} catch (Exception negAmt) {
			throw negAmt;
		}
	}

	// Destr: Does Java need destrs?

	// Getters get instance variables
	// Pre: none.
	// Post: none.
	// Return: instance variables
	public int getWholePart() {
		return wholePart;
	}

	public int getFracPart() {
		return fracPart;
	}

	public abstract String getCurrencyName();

	//Setters set instance variables.
	// Pre: none.
	// Post: Instance variable mutated.
	// Reurn: void.
	public void setWholePart(int wholePart) throws Exception {
		try {
			if (wholePart < 0) {
				throw new Exception("Invalid mutate: negative amount");
			}
			this.wholePart = wholePart;
		} catch (Exception negAmt) {
			throw negAmt;
		}
	}

	public void setFracPart(int fracPart) throws Exception {
		try {
			if (fracPart < 0) {
				throw new Exception("Invalid mutate: negative amount");
			}
			this.fracPart = fracPart;
		} catch (Exception negAmt) {
			throw negAmt;
		}
	}

	// Adds a currency of the same type.
	// Pre: toAdd - an object of the same subclass as this object.
	// Post: This object is increased by the value of toAdd.
	// Return: void.
	public void add(Currency toAdd) throws Exception {
		try {
			if (!(this.getCurrencyName().equals(toAdd.getCurrencyName()))) {
				throw new Exception("Invalid addition: currency mismatch"); 
			}
			// There shouldn't be a way to add a negative, and I don't believe there is.
			this.wholePart += toAdd.wholePart;
			this.fracPart += toAdd.fracPart;
			// 100 fracParts equals 1 wholePart
			if (fracPart > 100) {
				wholePart += 1;
				fracPart -= 100;
			}
		} catch (Exception currencyMismatch) {
			throw currencyMismatch;
		}
	}

	// Subtracts the value of a currency of the same type from this object.
	// Pre: toSubtract - a Currency child object of the same class as this object.
	// Post: This object is decreased by the value of toSubtract
	// Return: void.
	public void subtract(Currency toSubtract) throws Exception {
		try {
			if (!(this.getCurrencyName().equals(toSubtract.getCurrencyName()))) {
				throw new Exception("Invalid subtraction: currency mismatch");
			}
			if (toSubtract.isGreater(this)) {
				throw new Exception("Invalid subtraction: would result in negative");
			}
			fracPart -= toSubtract.fracPart;
			if (fracPart < 0) {
				wholePart--;
				fracPart += 100;
			}
			wholePart -= toSubtract.wholePart;
		} catch (Exception excpt) {
			throw excpt;
		}

	}

	// Checks if a Currency subclass object is equal to this.
	// Pre: toCompare - a Currency subclass object of the same class as this.
	// Post: none.
	// Return: true or false.
	public boolean isEqual(Currency toCompare) throws Exception {
		try {
			if (!(this.getCurrencyName().equals(toCompare.getCurrencyName()))) {
				throw new Exception("Invalid comparison: Currency mismatch");
			}
			return (wholePart == toCompare.wholePart && fracPart == toCompare.fracPart);
		} catch (Exception currencyMismatch) {
			throw currencyMismatch;
		}
	}

	// Checks if this object is greater than toCompare
	// Pre: toCompare - a Currency subclass object of the same class as this.
	// Post: none.
	// Return: true or false.
	public boolean isGreater(Currency toCompare) throws Exception {
		try {
			if (!(this.getCurrencyName().equals(toCompare.getCurrencyName()))) {
				throw new Exception("Invalid comparison: Currency mismatch");
			}
			if (wholePart > toCompare.wholePart) {
				return true;
			} else if (wholePart < toCompare.wholePart) {
				return false;
			} else if (fracPart > toCompare.fracPart) {
				return true;
			} else {
				return false;
			}	
		} catch (Exception currencyMismatch) {
			throw currencyMismatch;
		}
	}

	// Returns the value of the object and its name, in xx.yy Currency format.
	// Pre: none.
	// Post: none.
	// Return: String.
	// CHANGED: No longer prints to screen. Now returns a String.
	public String print() {
		//String toReturn = wholePart
		//System.out.printf("%d.%02d %s ", wholePart, fracPart, this.getCurrencyName());
		return String.format("%d.%02d %s", wholePart, fracPart, this.getCurrencyName());
	}

}

