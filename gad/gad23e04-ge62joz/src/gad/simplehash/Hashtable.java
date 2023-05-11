package gad.simplehash;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hashtable<K, V> {

    public static void main(String[] args){
        System.out.println(fastModulo(25, 16));
    }

    private List<Pair<K, V>>[] table;
    private final int[] a;

    @SuppressWarnings("unchecked")
    public Hashtable(int minSize, int[] a) {
        table = (List<Pair<K, V>>[]) new List[getNextPowerOfTwo(minSize)];
        for (int i = 0; i < table.length; i++) {
            table[i] = new ArrayList<>();
        }
        this.a = a;
    }

    public List<Pair<K, V>>[] getTable() {
        return table;
    }

    public static int getNextPowerOfTwo(int i) {
        if(i == 0)
            return 1;
        return (int) Math.pow(2, Math.ceil(Math.log(i) / Math.log(2)));
    }

    public static int fastModulo(int i, int divisor) {
        return i & (divisor - 1);
    }

    private byte[] bytes(K k) {
        return k.toString().getBytes(StandardCharsets.UTF_8);
    }

    public int h(K k, ModuloHelper mH) {
        byte[] x = bytes(k);
        int meso = 0;
        for (int i = 0; i < x.length; i++) {
            meso += x[i] * a[i % a.length];
        }
        return fastModulo(meso, table.length);
    }

    public void insert(K k, V v, ModuloHelper mH) {
        int index = h(k, mH);
        table[index].add(new Pair<>(k, v));
    }

    public boolean remove(K k, ModuloHelper mH) {
        int index = h(k, mH);
        return table[index].removeIf(kvPair -> kvPair.one().equals(k));
    }

    public Optional<V> find(K k, ModuloHelper mH) {
//        var meso = table[h(k, mH)];
//        if(meso.isEmpty())
//            return Optional.empty();
//        return meso.stream()
//        .filter(kvPair -> kvPair.one().equals(k))
//        .findFirst()
//        .map(Pair::two);

        List<Pair<K, V>> pairs = table[h(k, mH)];
        for (int i = 0; i < pairs.size(); i++) {
            Pair<K, V> pair = pairs.get(i);
            if (pair.one().equals(k)) {
                return Optional.of(pair.two());
            }
        }
        return Optional.empty();
    }

    public List<V> findAll(K k, ModuloHelper mH) {
//        return table[h(k, mH)]
//        .stream()
//        .filter(kvPair -> kvPair.one().equals(k))
//        .map(Pair::two)
//        .collect(Collectors.toList());

        List<V> values = new ArrayList<>();
        List<Pair<K, V>> pairs = table[h(k, mH)];
        for (int i = 0; i < pairs.size(); i++) {
            Pair<K, V> pair = pairs.get(i);
            if (pair.one().equals(k)) {
                values.add(pair.two());
            }
        }
        return values;
    }

    public Stream<Pair<K, V>> stream() {
        return Stream.of(table).filter(Objects::nonNull).flatMap(List::stream);
    }

    public Stream<K> keys() {
        return stream().map(Pair::one).distinct();
    }

    public Stream<V> values() {
        return stream().map(Pair::two);
    }
}