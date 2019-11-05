package pw.react.backend.reactbackend.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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

    @RequestMapping(value="{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        userRepository.deleteById(id);
    }


    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user){
        User existingUser = userRepository.getOne(id);
        BeanUtils.copyProperties(user,existingUser,"id");
        return userRepository.saveAndFlush(existingUser);
    }

    @Configuration
    public class ResourceConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
        }
    }
}

