package gad.simplesort;

public class DualPivotQuicksort extends SortAlgorithm {
	private DualPivotFinder pivotFinder;
	private int selectionSortSize;
	private Selectionsort selectionSort;

	public DualPivotQuicksort(DualPivotFinder pivotFinder, int selectionSortSize) {
		this.pivotFinder = pivotFinder;
		this.selectionSortSize = selectionSortSize;
		// TODO: Selectionsort Optimierung
		selectionSort = new Selectionsort();
	}

	@Override
	public void sort(int[] numbers, Result result, int from, int to) {
		if (from >= to) {
			return;
		}
		result.startDualPivotQuicksort(numbers, from, to);

		if (to - from + 1 <= selectionSortSize) {
			selectionSort.sort(numbers, result, from, to);
			return;
		}

		int[] selectedPivots = pivotFinder.findPivot(numbers, from, to);
		int selectedPivot1 = selectedPivots[0];
		int selectedPivot2 = selectedPivots[1];
		if (numbers[selectedPivot1] > numbers[selectedPivot2]) {
			int temp = selectedPivot1;
			selectedPivot1 = selectedPivot2;
			selectedPivot2 = temp;
		}

		int pivot1 = numbers[selectedPivot1];
		int pivot2 = numbers[selectedPivot2];

		if(selectedPivot1 == to && selectedPivot2 == from) {
			swap(numbers, selectedPivot1, selectedPivot2);
		} else if(selectedPivot1 != from || selectedPivot2 != to) {
			swap(numbers, selectedPivot1, from);
			if(from == selectedPivot2)
				swap(numbers, selectedPivot1, to);
			else
				swap(numbers, selectedPivot2, to);
		}

		int indexL = from + 1;
		int indexR = to - 1;
		int indexM = indexL;

		while (indexM <= indexR) {
			if (numbers[indexM] < pivot1) {
				swap(numbers, indexM, indexL);
				indexL++;
			} else if (numbers[indexM] >= pivot2) {
				while (numbers[indexR] > pivot2 && indexM < indexR) {
					indexR--;
				}
				swap(numbers, indexM, indexR);
				indexR--;

				if (numbers[indexM] < pivot1) {
					swap(numbers, indexM, indexL);
					indexL++;
				}
			}
			indexM++;
		}

		indexL--;
		indexR++;

		swap(numbers, from, indexL);
		swap(numbers, to, indexR);

		// Recursively sort the partitions
		result.logPartialArray(numbers, from, indexL - 1);
		result.logPartialArray(numbers, indexL + 1, indexR - 1);
		result.logPartialArray(numbers, indexR + 1, to);

		sort(numbers, result, from, indexL - 1);
		sort(numbers, result, indexL + 1, indexR - 1);
		sort(numbers, result, indexR + 1, to);
	}

	@Override
	public String toString() {
		return "DualPivotQuicksort (Pivot: " + pivotFinder + ", Selectionsort for: " + selectionSortSize + ")";
	}
}