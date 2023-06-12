package gad.radix;

public class BinaryBucket {

	private int[] bucket;

	private int indexLeft;
	private int indexRight;

	public BinaryBucket(int size) {
		this.bucket = new int[size];
		this.indexLeft = 0;
		this.indexRight = size - 1;
	}

	public void reset() {
		this.indexLeft = 0;
		this.indexRight = bucket.length - 1;
	}

	public void insertLeft(int number) {
		bucket[indexLeft++] = number;
	}

	public void insertRight(int number) {
		bucket[indexRight--] = number;
	}

	public int[] getArray() {
		return this.bucket;
	}

	public int getMid() {
		return indexLeft;
	}

	public void logArray(Result result) {
		result.logArray(bucket);
	}
}
