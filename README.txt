Author: Aditya Deorha

This is an implementation of N-way Set-Associative Cache. The entry points can be ClientCache.java or NWayCache.java. So these are the two classes where you can have your public static void main(String[] args) function. This project was built in Eclipse and the package name is com.name.nset.

User can provide cacheSize and blockSize which will be used to calculate the number of sets. The user also has the option of providing just the number of sets instead of cacheSize and blockSize.

public NWayCache(int associativity, int cacheSize, int blockSize, String evictionPolicy)
public NWayCache(int associativity, int numSets, String evictionPolicy)

The other required PARAMS are associativity and evictionPolicy. Associativity determines the number of cache lines per set. EvictionPolicy can be "LRU" (Least Recently Used), "MRU" (Most Recently Used) or "CUSTOM" (User defined). The Custom replacement policy needs to be implemented in ClientCustomPolicy.java. There is a default implementation provided.

For more details on the design, refer to the documentation.