package gad.dynamicarray;

public class DynamicStack implements Stack {
    private DynamicArray array;
    private Result result;

    private int currentElementIndex;

    public DynamicStack(int growthFactor, int maxOverhead, Result result) {
        this.result = result;
        this.array = new DynamicArray(growthFactor, maxOverhead);
    }

    @Override
    public int size() {
        return currentElementIndex;
    }

    @Override
    public void pushBack(int value) {
        Interval interval = currentElementIndex == 0 ? Interval.EmptyInterval.getEmptyInterval() : new Interval.NonEmptyInterval(0, currentElementIndex - 1);
        var newSize = currentElementIndex + 1;
        array.reportUsage(interval, newSize);
        array.set(currentElementIndex, value);
        currentElementIndex++;
        array.reportArray(result);
    }

    @Override
    public int popBack() {
        if(currentElementIndex == 0)
            throw new IllegalStateException();
        var itemIndex = currentElementIndex - 1;
        Interval interval = itemIndex - 1 < 0 ? Interval.EmptyInterval.getEmptyInterval() : new Interval.NonEmptyInterval(0, itemIndex - 1);
        int poppedValue = array.get(itemIndex);
        array.reportUsage(interval, currentElementIndex - 1);
        currentElementIndex--;
        array.reportArray(result);
        return poppedValue;
    }

    @Override
    public String toString() {
        return array + ", length: " + size();
    }

}