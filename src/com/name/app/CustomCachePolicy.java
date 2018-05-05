package com.name.app;

import com.name.nset.Policy;
import com.name.nset.Entry;

public class CustomCachePolicy<V> implements Policy<V> {

    @Override
    public int evictEntryIndex(Entry<V>[] entries, int startIndex, int endIndex) {
        return (startIndex + (endIndex - startIndex) / 2);
    }

}
