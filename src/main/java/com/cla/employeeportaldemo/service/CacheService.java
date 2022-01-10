package com.cla.employeeportaldemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    CacheManager cacheManager;

    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
          .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Scheduled(fixedRate = 6000)
    public void evictAllcachesAtIntervals() {
        evictAllCaches();
    }

}
