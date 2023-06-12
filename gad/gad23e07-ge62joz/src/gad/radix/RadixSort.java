package gad.radix;

import java.util.ArrayList;
import java.util.List;

public final class RadixSort {
    private static final int DIGITS = 10;

    private RadixSort() {
    }

    public static void main(String[] args){
        System.out.println(key(5678, 7));
        System.out.println(getMaxDecimalPlaces(new int[] {234, 23, 423, 5234, 28747 }));
    }

    public static int key(int element, int decPlace) {
        return (int) (element / Math.pow(10, decPlace)) % 10;
    }

    public static int getMaxDecimalPlaces(int[] elements) {
        int max = 0;
        for (int i = 0; i < elements.length; i++) {
            if(elements[i] > max)
                max = elements[i];
        }
        if(max == 0)
            return 0;
        return (int) Math.log10(max) + 1;
    }

    public static void concatenate(List<Integer>[] buckets, int[] elements) {
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                elements[k++] = buckets[i].get(j);
            }
        }
    }

    public static void kSort(int[] elements, int decPlace) {
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[DIGITS];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>(elements.length / DIGITS);
        }
        for (int i = 0; i < elements.length; i++) {
            buckets[key(elements[i], decPlace)].add(elements[i]);
        }
        concatenate(buckets, elements);
    }

    public static void sort(int[] elements, Result result) {
        int decPlaces = getMaxDecimalPlaces(elements);
        for (int decPlace = 0; decPlace < decPlaces; decPlace++) {
            kSort(elements, decPlace);
            result.logArray(elements);
        }
    }
}