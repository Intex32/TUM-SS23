package gad.simplesort;

import java.util.*;

public interface PivotFinder {

	int findPivot(int[] numbers, int from, int to);

	static PivotFinder getLastPivot() {
		return new PivotFinder() {

			@Override
			public int findPivot(int[] numbers, int from, int to) {
				return to;
			}

			@Override
			public String toString() {
				return "The last element";
			}
		};
	}

	static PivotFinder getMidPivot() {
		return new PivotFinder() {

			@Override
			public int findPivot(int[] numbers, int from, int to) {
				return from + (to - from) / 2;
			}

			@Override
			public String toString() {
				return "The middle element";
			}
		};
	}

	static PivotFinder getRandomPivot(int seed) {
		Random random = new Random(seed);

		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				return random.nextInt((to - from) + 1) + from;
			}

			@Override
			public String toString() {
				return "A random element";
			}
		};
	}

	static PivotFinder getMedianPivotFront(int numberOfConsideredElements) {
		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				/*if(numberOfConsideredElements < (to - from) + 1)
					to = from + (numberOfConsideredElements - 1);
				var arr = Arrays.copyOfRange(numbers, from, to + 1);
				Arrays.sort(arr);
				var midIndex = arr.length / 2;
				var midValue = arr[midIndex];
                for (int i = from; i <= to; i++) {
					if(numbers[i] == midValue)
						return i;
                }
				return -1;*/

				var meso = Math.min(to - from + 1, numberOfConsideredElements);
				var arr = new Map.Entry[meso];
				for(int i = 0; i < meso; i++) {
					var index = from + i;
					arr[i] = new AbstractMap.SimpleEntry<>(numbers[index], index);
				}
				Arrays.sort(arr, Comparator.comparingInt(value -> (int) value.getKey()));
				var midIndex = arr.length / 2;
				var midValue = arr[midIndex];
				return (int) midValue.getValue();
			}

			@Override
			public String toString() {
				return "The median of the first " + numberOfConsideredElements + " elements";
			}
		};
	}

	public static void main(String[] args){
		System.out.println(getMedianPivotFront(3).findPivot(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 9));
		System.out.println(getMedianPivotFront(5).findPivot(new int[]{9, 1, 8, 5, 2, 3, 5, 1, 0, 7}, 0, 9));
		System.out.println(getMedianPivotFront(5).findPivot(new int[]{9, 1, 8, 5, 2, 3, 5, 1, 0, 7, 8}, 1, 9));
	}

	static PivotFinder getMedianPivotDistributed(int numberOfConsideredElements) {
		return new PivotFinder() {
			@Override
			public int findPivot(int[] numbers, int from, int to) {
				var gap = (int) Math.floor((to - from) / ((float) (numberOfConsideredElements - 1)));
				var arr = new Map.Entry[numberOfConsideredElements];
				for(int i = 0; i < numberOfConsideredElements; i++) {
					var index = from + i * gap;
					arr[i] = new AbstractMap.SimpleEntry<>(numbers[index], index);
				}
				Arrays.sort(arr, Comparator.comparingInt(value -> (int) value.getKey()));
				int midIndex;
				if(arr.length % 2 == 0)
					midIndex = arr.length / 2 - 1;
				else
					midIndex= arr.length / 2;
				var midValue = arr[midIndex];
				return (int) midValue.getValue();
			}

			@Override
			public String toString() {
				return "The median of " + numberOfConsideredElements + " elements distributed throughout the array";
			}
		};
	}
}