package gad.radix;

import java.util.Arrays;
import java.util.Random;

public final class BinaryRadixSort {

    private BinaryRadixSort() {
    }

    public static int key(int element, int binPlace) {
        return (element >> binPlace) & 1;
    }

    public static void kSort(BinaryBucket from, BinaryBucket to, int binPlace) {
        for(int i = 0; i < from.getMid(); i++) {
            var value = from.getArray()[i];
            if(key(value, binPlace) == 0)
                to.insertLeft(value);
            else to.insertRight(value);
        }
        for(int i = from.getArray().length - 1; i >= from.getMid(); i--) {
            var value = from.getArray()[i];
            if(key(value, binPlace) == 0)
                to.insertLeft(value);
            else to.insertRight(value);
        }
    }

    public static void lastSort(BinaryBucket from, int[] to) {
        int k = 0;
        for(int i = from.getArray().length - 1; i >= from.getMid(); i--) {
            to[k++] = from.getArray()[i];
        }
        for(int i = 0; i < from.getMid(); i++) {
            to[k++] = from.getArray()[i];
        }
    }

    public static void sort(int[] elements, Result result) {
        int binPlaces = 32;
        var from = new BinaryBucket(elements.length);
        var to = new BinaryBucket(elements.length);
        for(int i = 0; i < elements.length; i++) {
            from.insertLeft(elements[i]);
        }
        for(int binPlace = 0; binPlace < binPlaces; binPlace++) {
            kSort(from, to, binPlace);
            var temp = from;
            from = to;
            temp.reset();
            to = temp;
            result.logArray(from.getArray());
        }
        lastSort(from, elements);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            System.out.println(key(-9, i));
        }

        var arr = new int[] {-3, -5, -7, 2, 7, 4 };
        sort(arr, array -> {});
        System.out.println(Arrays.toString(arr));

        int[] test = new int[10_000_000];
        Random random = new Random();
        for (int i = 0; i < test.length; i++) {
            test[i] = random.nextInt(Integer.MAX_VALUE);
        }
        int[] testTwo = Arrays.copyOf(test, test.length);

        long start = System.nanoTime();
        sort(test, ignored -> {
        });
        long binaryTime = System.nanoTime() - start;

        start = System.nanoTime();
        RadixSort.sort(testTwo, ignored -> {
        });
        long decimalTime = System.nanoTime() - start;

        System.out.println("Korrekt sortiert:" + Arrays.equals(test, testTwo));
        System.out.println("Binary: " + binaryTime / 1_000_000);
        System.out.println("Decimal: " + decimalTime / 1_000_000);
    }
}
