package com.name.nset.impl;

import com.name.nset.Entry;
import com.name.nset.Policy;

import java.sql.Timestamp;


public class MRUPolicy<V> implements Policy<V> {

    @Override
    public int evictEntryIndex(Entry<V>[] entries, int startIndex, int endIndex) {
        int index = 0;
        Timestamp latestTime = new Timestamp(0);
        for (int i = startIndex; i <= endIndex; i++) {
            if (entries[i].occupied && entries[i].timestamp.after(latestTime)) {
                latestTime = entries[i].timestamp;
                index = i;
            }
        }
        return index;
    }

}
