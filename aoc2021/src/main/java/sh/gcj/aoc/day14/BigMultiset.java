package sh.gcj.aoc.day14;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/*
 * A very simple (and likely naive) version of guava's Multiset that can handle bigger numbers.
 * This allows convenient counting of different elements, without the need to check for presence in a map first etc.
 */
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

    // Provides access to the entry set of the backing map
    public Set<Map.Entry<T, Long>> entrySet() {
        return backingMap.entrySet();
    }

    public BigMultiset<T> copy() {
        return new BigMultiset<>(new LinkedHashMap<>(backingMap));
    }

    // Adds an element to the set if it doesn't exist already (with it's count set to 1), or increments the count by one
    // if the element is already in the set
    public Long add(T element) {
        return add(element, 1L);
    }

    // Adds an element to the set if it doesn't exist already (with it's count set to the provided amount), or increments
    // the count by the provided amount if the element is already in the set
    public Long add(T element, Long occurrences) {
        Long currentCount = backingMap.getOrDefault(element, 0L);
        backingMap.put(element, currentCount + occurrences);
        return currentCount + occurrences;
    }

    // Returns the largest element count in the set
    public Long max() {
        return backingMap.values().stream().mapToLong(c -> c).max().orElseThrow();
    }

    // Returns the small element count in the set
    public Long min() {
        return backingMap.values().stream().mapToLong(c -> c).min().orElseThrow();
    }
}
