package com.fh.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisPoolUtil {

    private static JedisPool jedisPool;

    static {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        poolConfig.setMinIdle(2);
        poolConfig.setMaxWaitMillis(30000);
        jedisPool = new JedisPool(poolConfig,"192.168.187.134",6379);
    }

    public static Jedis getRedisPool(){
        return jedisPool.getResource();
    }

    public static void setRedis(Jedis jedis){
        jedisPool.returnResource(jedis);
    }

}
