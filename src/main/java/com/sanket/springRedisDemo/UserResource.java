package com.sanket.springRedisDemo;

import com.sanket.springRedisDemo.Repository.UserRepositoryIfImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/user")
public class UserResource {

    private UserRepositoryIfImpl userRepository;

    public UserResource(UserRepositoryIfImpl userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/ping")
    public String ping(){
        return "PONG";
    }

    @PostMapping("/add")
    public User add(@RequestBody User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId());
    }

    @GetMapping("/update/{id}/{name}")
    public User update(@PathVariable("id") final Integer id,
                       @PathVariable("name") final String name) {
        userRepository.save(new User(id, name, 1000));
        return userRepository.findById(id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final Integer id) {
        userRepository.delete(id);
        return "SUCCESS";
    }

    @GetMapping("/all")
    public List<User> all() {
        return userRepository.getAllUser();
    }
}
