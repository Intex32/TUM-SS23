package gad.dynamicarray;

public class RingQueue implements Queue {
    private DynamicArray array;
    private Result result;

    private Interval interval = Interval.EmptyInterval.getEmptyInterval();

    public RingQueue(int growthFactor, int maxOverhead, Result result) {
        this.result = result;
        this.array = new DynamicArray(growthFactor, maxOverhead);
    }

    @Override
    public int size() {
        if(interval.isEmpty())
            return 0;
        var to = interval.getTo();
        if (to < interval.getFrom()) to += array.getLength();
        return to - interval.getFrom() + 1;
    }

    @Override
    public void pushBack(int value) {
        if (interval.isEmpty() || interval.getTo() - interval.getFrom() == -1 || size() == array.getLength()) { // enlarge array
            var previousSize = array.getLength();
            interval = array.reportUsage(interval, array.getLength() + 1);
            array.set(previousSize, value);
            interval = interval.isEmpty() ? new Interval.NonEmptyInterval(0, 0) : new Interval.NonEmptyInterval(interval.getFrom(), interval.getTo() + 1);
        } else { // append item
            var appendIndex = interval.isEmpty() ? 0 : (interval.getTo() + 1)  % array.getLength();
            interval = new Interval.NonEmptyInterval(interval.isEmpty() ? 0 : interval.getFrom(), appendIndex);
            array.set(appendIndex, value);
        }
        array.reportArray(result);
    }

    @Override
    public int popFront() {
        if (interval.isEmpty())
            return -1;

        int poppedValue = array.get(interval.getFrom());
        var size = size();
        if (size == 1)
            interval = Interval.EmptyInterval.getEmptyInterval();
        else
            interval = new Interval.NonEmptyInterval((interval.getFrom() + 1) % array.getLength(), interval.getTo());
        interval = array.reportUsage(interval, size - 1);
        array.reportArray(result);
        return poppedValue;
    }

    @Override
    public String toString() {
        return array + ", size: " + size();
    }

    public Interval getInterval() {
        return interval;
    }
}