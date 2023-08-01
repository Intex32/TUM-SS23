package de.tum.in.ase.eist;

import java.util.Arrays;

import org.w3c.dom.ranges.Range;

public class MergeSort {
    
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // base case: already sorted
        }
        
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        
        // fill the left &  right arrays
        System.arraycopy(arr, 0, left, 0, left.length);
        System.arraycopy(arr, mid, right, 0, right.length);
        
        // recursively sort the left& right arrays
        mergeSort(left);
        mergeSort(right);
        
        // merge the sorted left and right arrays
        merge(arr, left, right);
    }

    /**
     * This function merges two sorted integer arrays (left and right) into one sorted array.
     *
     * The arrays are merged in a way that maintains the overall sorted order.
     * The original array is directly modified by this function.
     *
     * @param arr The original array which is to be modified to contain the merged result.
     * @param left The first sorted array to be merged.
     * @param right The second sorted array to be merged.
     *
     * Procedure:
     * 1. It uses three pointers: one for the current index in the left array (i), one for the current index in the right array (j), and one for the current index in the original array (k).
     * 2. As long as there are remaining elements in both the left and right arrays, it compares the current elements of both arrays and picks the smaller one. 
     *    This smaller element is then placed in the original array, and the pointer of that array (left or right) and the pointer of the original array are incremented.
     * 3. If there are any remaining elements in the left or right array after all elements from the other array have been placed in the original array, 
     *    these remaining elements are copied into the original array. This is because these elements are already sorted and are therefore guaranteed to be larger 
     *    than all elements already in the original array.
     */
    private static void merge(int[] arr, int[] left, int[] right) {
        // TODO: Find correct merge implementation from one of the branches.
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
        // END of TODO
    }


    
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 1, 2, 3};
        
        System.out.println("Original array: " + Arrays.toString(arr));
        
        mergeSort(arr);
        
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}

