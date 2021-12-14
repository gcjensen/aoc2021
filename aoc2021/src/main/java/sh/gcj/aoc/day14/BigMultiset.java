package sh.gcj.aoc.day14;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// A very simple (and likely naive) version of guava's Multiset that can handle bigger numbers
public final class BigMultiset<T> {
    private final Map<T, Long> backingMap;

    public BigMultiset() {
        backingMap = new LinkedHashMap<>();
    }

    public BigMultiset(Map<T, Long> backingMap) {
        this.backingMap = backingMap;
    }

    public static <T> BigMultiset<T> create() {
        return new BigMultiset<>();
    }

    public Set<Map.Entry<T, Long>> entrySet() {
        return backingMap.entrySet();
    }

    public BigMultiset<T> copy() {
        return new BigMultiset<>(new LinkedHashMap<>(backingMap));
    }

    public Long add(T element) {
        return add(element, 1L);
    }

    public Long add(T element, Long occurrences) {
        Long currentCount = backingMap.getOrDefault(element, 0L);
        backingMap.put(element, currentCount + occurrences);
        return currentCount + occurrences;
    }

    public Long max() {
        return backingMap.values().stream().mapToLong(c -> c).max().orElseThrow();
    }

    public Long min() {
        return backingMap.values().stream().mapToLong(c -> c).min().orElseThrow();
    }
}
