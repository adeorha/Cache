package com.name.nset.impl;

import com.name.nset.Entry;
import com.name.nset.Policy;

import java.sql.Timestamp;


public class LRUPolicy<V> implements Policy<V> {

    @Override
    public int evictEntryIndex(Entry<V>[] entries, int startIndex, int endIndex) {
        int index = 0;
        Timestamp earliestTime = new Timestamp(System.nanoTime());
        for (int i = startIndex; i <= endIndex; i++) {
            if (entries[i].occupied && entries[i].timestamp.before(earliestTime)) {
                earliestTime = entries[i].timestamp;
                index = i;
            }
        }
        return index;
    }

}
