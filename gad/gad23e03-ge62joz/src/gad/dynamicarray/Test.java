package gad.dynamicarray;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        var arr = new DynamicArray(3, 4);

        arr.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 1);
        arr.set(2, 1);
        arr.get(1);
        arr.set(0, 3);
        arr.reportUsage(new Interval.NonEmptyInterval(1, 2), 4);
        arr.reportUsage(new Interval.NonEmptyInterval(3, 6), 4);
        arr.reportUsage(new Interval.NonEmptyInterval(1, 1), 1);
        arr.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 2);
        arr.reportUsage(new Interval.NonEmptyInterval(2, 0), 4);
        arr.reportUsage(new Interval.NonEmptyInterval(5, 1), 9);
        arr.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);

        System.out.println();
        System.out.println();
        System.out.println();

        var q = new RingQueue(3, 4, new StudentResult());
        q.pushBack(1);
        q.pushBack(2);
        q.pushBack(3);
        q.pushBack(4);
        q.popFront();
        q.popFront();
        q.popFront();
        q.pushBack(5);
        q.pushBack(6);
        q.popFront();
        q.pushBack(7);
        q.pushBack(8);
        System.out.println(q.toString());
        System.out.println(q.getInterval());
    }

    public static void logArr(DynamicArray arr) {
        System.out.println(arr.getLength() + " " + Arrays.toString(arr.getArr()));
    }
}