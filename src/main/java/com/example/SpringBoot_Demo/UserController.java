package com.example.SpringBoot_Demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping(path="/example/users")
    public @ResponseBody Iterable<User> getAllUsers(){
        return repo.findAll();
    }

    @PostMapping(path="/example/addNewUser")
    public ResponseEntity<String> addNewUser(@RequestBody User user){
        repo.save(user);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }

    @PutMapping(path="/example/updateUserPw/{id}")
    public java.util.Optional<Object> changePassword(@RequestBody User newUser, @PathVariable Long id) {
        return repo.findById(id)
                .map(user -> {
                    user.setPassword(newUser.getPassword());
                    return repo.save(user);
                });
    }

    @DeleteMapping(path="/example/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        repo.deleteById(id);
        return new ResponseEntity<>("User " + id + "has been deleted.", HttpStatus.ACCEPTED);
    }
}
