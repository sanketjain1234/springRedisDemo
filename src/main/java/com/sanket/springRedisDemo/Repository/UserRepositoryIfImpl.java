package com.sanket.springRedisDemo.Repository;

import com.sanket.springRedisDemo.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryIfImpl implements UserRepositoryIf {

    private static String KEY = "User";

    public UserRepositoryIfImpl(RedisTemplate<String,User>redisTemplate ){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    private RedisTemplate<String,User> redisTemplate;
    private HashOperations hashOperations;

    @Override
    public void save(User user) {
        hashOperations.put(KEY,user.getId(),user);
    }

    @Override
    public List<User> getAllUser() {
        Map<Integer,User> allUser = hashOperations.entries(KEY);
        List<User> responseList = new ArrayList<>(allUser.values());
        return responseList;
    }

    @Override
    public void delete(Integer userId) {
        hashOperations.delete(KEY,userId);
    }

    @Override
    public User findById(Integer id) {
       return (User) hashOperations.get(KEY,id);
    }

    //TODO
    @Override
    public List<String> getTopUsersName() {
       return null;
    }
}
