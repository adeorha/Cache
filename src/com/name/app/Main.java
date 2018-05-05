package com.name.app;

import com.name.nset.Cache;
import com.name.nset.CacheFactory;
import com.name.nset.impl.LRUPolicy;

public class Main {
    public static void main(String [] args){
        Cache<String, String> cache = CacheFactory.newCache(1,3, new LRUPolicy<>());
        cache.put("a", "b");
        cache.get("a");

        cache = CacheFactory.newCache(1,3, new CustomCachePolicy<>());

        cache.put("a", "b");
        cache.get("a");

    }
}
