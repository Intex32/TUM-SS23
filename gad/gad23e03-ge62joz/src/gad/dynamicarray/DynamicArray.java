package gad.dynamicarray;

import java.util.Arrays;

public class DynamicArray {
    private int[] elements;

    private final int growthFactor;
    private final int maxOverhead;

    public DynamicArray(int growthFactor, int maxOverhead) {
        if(growthFactor < 1 || maxOverhead < 1 || maxOverhead < growthFactor)
            throw new IllegalArgumentException();
        elements = new int[0];
        this.growthFactor = growthFactor;
        this.maxOverhead = maxOverhead;
    }

    public int getLength() {
        return elements.length;
    }

    public Interval reportUsage(Interval usage, int minSize) {
        if(!(minSize > elements.length || minSize * maxOverhead < elements.length)) {
            return usage;
        }

        var newSize = growthFactor * minSize;
        var newElements = new int[newSize];
        Interval intervall;
        if(usage.isEmpty()) {
            this.elements = newElements;
            intervall = Interval.EmptyInterval.getEmptyInterval();
        }  else {
            var to = usage.getTo();
            if (to < usage.getFrom()) to += elements.length;
            for(int i = usage.getFrom(), j = 0; i <= to; i++, j++)
                newElements[j] = elements[i % elements.length];
            this.elements = newElements;
            intervall = new Interval.NonEmptyInterval(0, to - usage.getFrom());
        }
        return intervall;
    }

    public int get(int index) {
        return elements[index];
    }

    public void set(int index, int value) {
        elements[index] = value;
    }

    public void reportArray(Result result) {
        result.logArray(elements);
    }

    public long[] getArr() {
        return Arrays.stream(elements).mapToLong(i -> i).toArray();
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}