package com.sanket.springRedisDemo.Repository;

import com.sanket.springRedisDemo.User;

import java.util.List;

public interface UserRepositoryIf {

    void save(User user);
    List<User>  getAllUser();
    void delete (Integer userId);
    User findById(Integer id);
    List<String> getTopUsersName();


}
