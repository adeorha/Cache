package com.name.nset;

import java.sql.Timestamp;

public class Entry<V> {
    public V value;
    public Timestamp timestamp;
    public int tag;
    public boolean occupied;

    public Entry() {
        this.value = null;
        this.timestamp = null;
        this.tag = 0;
        this.occupied = false;
    }

    public Entry(V value, Timestamp timestamp, int tag, boolean occupied) {
        this.value = value;
        this.timestamp = timestamp;
        this.tag = tag;
        this.occupied = occupied;
    }

    /**
     * @return the value
     */
    public V getValue() {
        return value;
    }

    /**
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @return the tag
     */
    public int getTag() {
        return tag;
    }

    /**
     * @return the occupied
     */
    public boolean isOccupied() {
        return occupied;
    }
}
