package pw.react.backend.reactbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.models.User;
import pw.react.backend.reactbackend.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
            public List<User> list(){
        return userRepository.findAll();
    }
    @GetMapping
            @RequestMapping("{id}")
    public User get(@PathVariable Long id){
        return userRepository.getOne(id);
    }
    @PostMapping
    public User create(@RequestBody final User user){
        return userRepository.saveAndFlush(user);
    }
}

