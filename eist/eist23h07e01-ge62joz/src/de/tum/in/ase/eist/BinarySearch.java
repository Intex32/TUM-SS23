package de.tum.in.ase.eist;

import java.util.List;

public class BinarySearch implements SearchStrategy {
    @Override
    public int performSearch(List<Chapter> list, String q) {
        return runBinarySearchRecursively(list, q, 0, list.size());
    }

    public int runBinarySearchRecursively(List<Chapter> sortedArray, String key, int low, int high) {
        int middle = low + ((high - low) / 2);

        if (high < low) {
            return -1;
        }

        if (key.equals(sortedArray.get(middle).getName())) {
            return sortedArray.get(middle).getPageNumber();
        } else if (key.compareTo(sortedArray.get(middle).getName()) < 0) {
            return runBinarySearchRecursively(sortedArray, key, low, middle - 1);
        } else {
            return runBinarySearchRecursively(sortedArray, key, middle + 1, high);
        }
    }
}
