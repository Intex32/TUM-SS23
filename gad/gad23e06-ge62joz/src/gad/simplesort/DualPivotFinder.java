package gad.simplesort;

import java.util.*;

public interface DualPivotFinder {

	int[] findPivot(int[] numbers, int from, int to);

	static DualPivotFinder getFirstLastPivot() {
		return new DualPivotFinder() {

			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				return new int[] { from, to };
			}

			@Override
			public String toString() {
				return "The first and last element";
			}
		};
	}

	static DualPivotFinder getRandomPivot(int seed) {
		Random random = new Random(seed);

		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				var a = random.nextInt(to - from + 1) + from;
				int b;
				do {
					b = random.nextInt(to - from + 1) + from;
				} while(a == b);
				return new int[] { a, b };
			}

			@Override
			public String toString() {
				return "Two random elements";
			}
		};
	}

	static DualPivotFinder getMedianPivotFront(int numberOfConsideredElements) {
		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
				/*if(numberOfConsideredElements < (to - from) + 1)
					to = from + (numberOfConsideredElements - 1);
				var gap = (to - from + 1 - 2) / 3;
				return new int[] { gap + from, 2 * gap + 1 + from };*/

				var meso = Math.min(to - from + 1, numberOfConsideredElements);
				var arr = new Map.Entry[meso];
				for(int i = 0; i < meso; i++) {
					var index = from + i;
					arr[i] = new AbstractMap.SimpleEntry<>(numbers[index], index);
				}
				Arrays.sort(arr, Comparator.comparingInt(value -> (int) value.getKey()));
				var gap = (arr.length - 2) / 3;
				return new int[] { (int) arr[gap].getValue(), (int) arr[2 * gap + 1].getValue() };
			}

			@Override
			public String toString() {
				return "The thirds of the first " + numberOfConsideredElements + " elements";
			}
		};
	}

	public static void main(String[] args){
		System.out.println(Arrays.toString(getMedianPivotDistributed(5).findPivot(new int[]{0, 1, 2}, 0, 2)));
	}

	static DualPivotFinder getMedianPivotDistributed(int numberOfConsideredElements) {
		return new DualPivotFinder() {
			@Override
			public int[] findPivot(int[] numbers, int from, int to) {
                var gap = (int) Math.floor((to - from) / ((float) (numberOfConsideredElements - 1)));
				if(gap == 0)
					return new int[] { from, from + 1 };
                var arr = new Map.Entry[numberOfConsideredElements];
                for (int i = 0; i < numberOfConsideredElements; i++) {
                    var index = from + i * gap;
                    arr[i] = new AbstractMap.SimpleEntry<>(numbers[index], index);
                }
                Arrays.sort(arr, Comparator.comparingInt(value -> (int) value.getKey()));

				var gap2 = (arr.length - 2) / 3;
				return new int[] { (int) arr[gap2].getValue(), (int) arr[2 * gap2 + 1].getValue() };
			}

			@Override
			public String toString() {
				return "The thirds of " + numberOfConsideredElements + " elements distributed thoughout the array";
			}
		};
	}
}