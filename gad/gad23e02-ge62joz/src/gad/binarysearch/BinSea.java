package gad.binarysearch;

import gad.binarysearch.Interval.NonEmptyInterval;

import java.util.ArrayList;
import java.util.List;

public final class BinSea {

    private BinSea() {
    }

    public static int search(int[] sortedData, int value, Result result) {
        if(sortedData.length == 0)
            return -1;

        int min = 0;
        int max = sortedData.length - 1;

        while(max - min >= 1) {
            // split in two halves
            int mid = (max - min) / 2 + min;
            int midValue = sortedData[mid];
            result.addStep(mid);

            //System.out.println(min + "-" + max + "  mid " + mid);

            // check mid
            if(value == midValue) // found value
                return mid;
//            if((mid - 1 < 0 || diff(sortedData[mid - 1], value) >= diff(midValue, value)) && (mid + 1 >= sortedData.length || diff(sortedData[mid + 1], value) >= diff(midValue, value))) // mid value is closer to actual value than neighboring numbers of halves
//                return mid;

            // calculate next window
            if(value < midValue) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        //System.out.println("meso " + min + " " + max);

        //if(diff(sortedData[min], value) < diff(sortedData[max], value))
        //    return min;
        //else return max;
        return min;
    }


    private static int diff(int a, int b) {
        return Math.abs(a - b);
    }

    public static int search(int[] sortedData, int value, boolean lowerBound, Result result) {
        var index = search(sortedData, value, result);
        if(index == -1)
            return -1;
        if(lowerBound) {
            // kleinstmöglicher Index, wo der Wert >= dem Gesuchten
            int a = index;
            for(int ax = index; ax >= 0 && sortedData[ax] == value; ax--) {
                a = ax;
            }
            for(int i = a; i < sortedData.length; i++) {
                if(sortedData[i] >= value)
                    return i;
            }
        } else {
            // größtmöglicher Index, wo der Wert <= dem Gesuchten
            int a = index;
            for(int ax = index; ax < sortedData.length && sortedData[ax] == value; ax++) {
                a = ax;
            }
            for(int i = a; i >= 0; i--) {
                if(sortedData[i] <= value)
                    return i;
            }
        }
        return -1;
    }

    public static Interval search(int[] sortedData, NonEmptyInterval valueRange, Result resultLower, Result resultHigher) {
        int left = search(sortedData, valueRange.getFrom(), true, resultLower);
        if(left == -1)
            return Interval.EmptyInterval.getEmptyInterval();
        int right = search(sortedData, valueRange.getTo(), false, resultHigher);
        if(right == -1)
            return Interval.EmptyInterval.getEmptyInterval();
        return Interval.fromArrayIndices(left, right);
    }

    public static void main(String[] args) {
        int[] array = new int[] { 2, 7, 7, 42, 69, 1337, 2000, 9001 };
        int[] array2 = new int[] { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2056 };
        int[] array4 = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5 };
        int[] array3 = new int[] {  };

//        System.out.println(search(array, 100, new StudentResult()));
//        System.out.println(search(array, 100, new StudentResult()));
//
//        System.out.println(search(array4, 5, true, new StudentResult()));
//        System.out.println(search(array, 100, true, new StudentResult()));
//
        System.out.println(search(array, new NonEmptyInterval(-234, 1), new StudentResult(), new StudentResult()));
        System.out.println(search(array, new NonEmptyInterval(9002, 10000), new StudentResult(), new StudentResult()));
    }
}