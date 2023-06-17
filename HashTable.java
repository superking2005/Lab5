package lab5;

public class HashTable {
	private Currency[] table;
	private int numItems;
	private double loadFactor;
	private int numTotalCollisions;
	private int size = 0;
	private final int c1 = 1;
	private final int c2 = 1;
	private boolean[] emptySinceStart = {true};
	
	public HashTable(int numBuckets) {
		size = numBuckets;
		table = new Currency[numBuckets];
		emptySinceStart = new boolean[numBuckets];
		emptySinceStart.fill(true);
	}
	
	private int hash(Currency item) {
		int m = 2;
		int n = 3;
		int w = item.getWholePart();
		int f = item.getFracPart();
		int size = 29;
		return (m*w + n*f) % size;
	}
	
	private int quadProbeLoc(int H, int i) {
		return (H + c1 *i + c2 * i * i) % size;
	}
	
	// should insert return boolean success, int bucket, or int numCollisions?
	public boolean insert(Currency item) {
		// TODO: Detect circular hash.
		int H = hash(item);
		int bucket = H;
		
		for (int i = 0; i < size; i++) {
			bucket = quadProbeLoc(H, i);
			if (table[bucket] == null) {
				table[bucket] = item;
				emptySinceStart[bucket] = false;
				return true;
			}
			numTotalCollisions++;
		}
		return false;
	}
	
	public int search(Currency item) {
		// TODO: Detect and handle circular hash.
		int H = hash(item);
		int bucket = H;
		for (int i = 0; i < size && !emptySinceStart[bucket]; i++) {
			bucket = quadProbeLoc(H, i);
			try {
				if (table[bucket].isEqual(item)) {
					return bucket;
				}
			} catch (Exception e) {
				// CurrencyMismatch just means the found item isn't the one being searched for.
				// I don't foresee a CurrencyMismatch occurring (2023/6/14).
			}
		}
		// if key not found
		return -1;
	}
	
	public boolean remove(Currency item) {
		int bucket = search(item);
		if (bucket == -1) {
			return false;
		}
		table[bucket] = null;
		return true;
	}
	
	private void resize() {
		
	}
}
