package com.name.nset;

public interface Policy<V> {

    int evictEntryIndex(Entry<V>[] entries, int startIndex, int endIndex);
}
