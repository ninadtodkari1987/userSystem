package com.solution.userSystem.Controller;

import com.solution.userSystem.Model.User;
import com.solution.userSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 8. List Persons
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 1. Add Person (id, firstName, lastName)
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // 2. Edit Person (firstName, lastName)
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId, @RequestBody User user) throws HttpClientErrorException {

        User existingUser = userRepository.findById(userId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        existingUser.setId(user.getId());
        existingUser.setLastName(user.getLastName());
        existingUser.setFirstName(user.getFirstName());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // 3. Delete Person (id)
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") int userId)throws HttpClientErrorException {

        User existingUser = userRepository.findById(userId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        userRepository.delete(existingUser);

        return ResponseEntity.ok(existingUser);
    }

    // 7. Count Number of Persons
    @GetMapping("/users/count")
    public long getUsersCount() {
        return userRepository.count();
    }
}
