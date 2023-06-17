// CIS 22C: Lab 5: Hash Tables
// Alexander Mochizuki and Saranya Kolachana
// Store and retrieve values using a hash table.

package lab5;
import java.util.*;

public class Lab5Main {
	public static void main(String args[]) {
		Scanner scnr = new Scanner(System.in);
		// 20 Krone objects
		double[] seedArray = {
				57.12, 23.44, 87.43, 68.99, 111.22,
				44.55, 77.77, 18.36, 543.21, 20.21,
				345.67, 36.18, 48.48, 101.00, 11.00,
				21.00, 51.00, 1.00, 251.00, 151.00};

		HashTable kroneHashTable = new HashTable(29);

		for (double seedVal : seedArray) {
			try {
				kroneHashTable.insert(new Krone(seedVal));
			} catch (Exception e) {
				
			}
		}

		System.out.println("# items in kroneHashTable: " + kroneHashTable.getNumItems());
		System.out.println("Load factor of kroneHashTable: " + kroneHashTable.getLoadFactor());
		System.out.println("# collisions for kroneHashTable: " + kroneHashTable.getNumTotalCollisions());

		// Ask user for Krone to search for.
		// If found, print index
		// Else, print "Invalid Data"
		// Ask user to either check again or terminate
		boolean userSaysTerminate = false;
		double kroneVal;
		int bucket;
		while (!userSaysTerminate) {
			System.out.print("Enter a value of a Krone to search for: ");
			try {
				kroneVal = scnr.nextDouble();
				bucket = kroneHashTable.search(new Krone(kroneVal));
				if (bucket == -1) {
					System.out.println("Invalid Data");
				} else {
					System.out.println("Found at: " + bucket);
				}
				// End of try
			} catch (Exception e) {
				System.out.println("Encountered some problem");
			}
			System.out.print("Enter '1' to check again, or '2' to end: ");
			try {
				userSaysTerminate = (scnr.nextInt() == 2);
			} catch (Exception e) {
				String notOneOrTwo = scnr.next();
				System.out.println(notOneOrTwo + " Is not 1 or 2. Running another loop.");
			}
			// End of while
		}
		scnr.close();
		System.out.println("Goodbye");
	}
}
