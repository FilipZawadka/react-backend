package pw.react.backend.reactbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.models.User;
import pw.react.backend.reactbackend.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByLogin(String login) {
        List<User> users = userRepository.findByLogin(login);
        if (users == null || users.isEmpty()){
            System.out.println(login+" not found");
            return null;
        }
        return users;
    }

    public User create(User newUser) {
        List<User> user = userRepository.findByLogin(newUser.getLogin());
        if (user != null){
            System.out.println("Already exists");
            return null;
        }
        return userRepository.save(newUser);
    }

    public User save(User someUser) {
        return userRepository.save(someUser);
    }

    public User delete(int id) {
        User someUser = userRepository.findById(id);
        if (someUser == null) {
            System.out.println(id+" not found");
            return null;
        }
        userRepository.delete(someUser);
        return someUser;
    }
    public User update(int id, User newUser) {
        User oldUser = userRepository.findById(id);
        if (oldUser == null) {
            System.out.println(id+" not found");
            return null;
        }
        oldUser.setBirth(newUser.getBirth());
        oldUser.setActive(newUser.isActive());
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setLogin(newUser.getLogin());
        return userRepository.save(oldUser);
    }


}