package org.example.repository;

import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;



//we are making this repository as redis treat it self as data base stroing in key value format
//So that is why we are writing repository for redis
@Repository
public class UserCacheRepository {
    public static final String USER_CACHE_PREFIX="usr::";
    public static final Integer USER_CACHE_KEY_EXPIRY=600;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    //caches will have only two type of functions i.e get and set

    public User get(Integer userId){
        Object result = redisTemplate.opsForValue().get(getKey(userId));
        if(result==null){
            return null;
        }
        return (User)result;
    }

    public void set(User user){
        redisTemplate.opsForValue().set(getKey(user.getId()),user,USER_CACHE_KEY_EXPIRY, TimeUnit.SECONDS);
    }

    private String getKey(Integer id){
        return USER_CACHE_PREFIX+id;
    }
}
