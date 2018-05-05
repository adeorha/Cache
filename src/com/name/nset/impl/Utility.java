package com.name.nset.impl;

public class Utility {
    public int getStartIndex(int hashKey, int numSets, int associativity) {
        int startIndex = (hashKey % numSets) * associativity;
        return startIndex;
    }

    public int getEndIndex(int startIndex, int associativity) {
        int endIndex = startIndex + associativity - 1;
        return endIndex;
    }

    public int hash(Object key) {
        return (key.hashCode() * 37 + 17);
    }
}
