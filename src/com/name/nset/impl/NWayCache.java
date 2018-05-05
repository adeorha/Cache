package com.name.nset.impl;

import com.name.nset.Cache;
import com.name.nset.Entry;
import com.name.nset.Policy;

import java.lang.reflect.Array;
import java.sql.Timestamp;

/**
 * @author Aditya
 */
public class NWayCache<K, V> implements Cache<K, V> {

    private Entry<V>[] entries;
    private int associativity;
    private int numSets;
    private Policy<V> replacementPolicy = new LRUPolicy<>();

    public NWayCache(int associativity, int cacheSize, int blockSize, Policy<V> evictionPolicy) {
        this(associativity, cacheSize/blockSize, evictionPolicy);
    }

    public NWayCache(int associativity, int numSets, Policy<V> evictionPolicy) {
        this.associativity = associativity;
        this.numSets = numSets;
        this.entries = (Entry<V>[]) Array.newInstance(Entry.class, associativity * numSets);
        for (int i = 0; i < entries.length; i++) {
            entries[i] = new Entry<>();
        }
        if (evictionPolicy != null) {
            this.replacementPolicy = evictionPolicy;
        }
    }

    @Override
    public V get(K key) {
        Utility util = new Utility();
        int hashKey = util.hash(key);
        int startIndex = util.getStartIndex(hashKey, numSets, associativity);
        int endIndex = util.getEndIndex(startIndex, associativity);

        for (int i = startIndex; i <= endIndex; i++) {
            if (entries[i].occupied && entries[i].tag == hashKey) {
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                entries[i].timestamp = currentTime;
                return entries[i].value;
            }
        }

        return null;
    }

    @Override
    public void put(K key, V value) {

        Utility util = new Utility();
        int hashKey = util.hash(key);
        int startIndex = util.getStartIndex(hashKey, numSets, associativity);
        int endIndex = util.getEndIndex(startIndex, associativity);

        Timestamp currentTime = new Timestamp(System.nanoTime());

        Entry<V> newEntry = new Entry<V>(value, currentTime, hashKey, false);
        //If entry is already present
        for (int i = startIndex; i <= endIndex; i++) {
            if (entries[i].occupied && entries[i].tag == newEntry.tag) {
                entries[i] = newEntry;
                entries[i].occupied = true;
                return;
            }
        }
        //If there is a line available
        for (int i = startIndex; i <= endIndex; i++) {
            if (!entries[i].occupied) {
                entries[i] = newEntry;
                entries[i].occupied = true;
                return;
            }
        }
        //If eviction will be required
        int evictIndex = replacementPolicy.evictEntryIndex(entries, startIndex, endIndex);
        entries[evictIndex] = newEntry;
        entries[evictIndex].occupied = true;
    }

}
