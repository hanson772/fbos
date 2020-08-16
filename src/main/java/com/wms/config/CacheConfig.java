package com.wms.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.wms.bean.pojo.Employee;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author max.chen
 * @class
 */
@Component
public class CacheConfig {
    public static final String COOKIE_ALIAS = "user-cookie";

    static Cache<String, Employee> userCache = CacheBuilder.newBuilder()
            .maximumSize(100000) // 设置缓存的最大容量
            .expireAfterWrite(3,TimeUnit.DAYS) // 设置缓存在写入3天后失效
            .concurrencyLevel(10) // 设置并发级别为10
            .recordStats() // 开启缓存统计
            .build();


    public synchronized void put(String key, Employee value) {
        userCache.put(key, value);
    }

    public synchronized Employee get(String key) {
        return userCache.getIfPresent(key);
    }

    public synchronized void remove(String key){
        userCache.invalidate(key);
    }
}
