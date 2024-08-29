package br.qziul.test_docker.controller;

import br.qziul.test_docker.model.UserModel;
import br.qziul.test_docker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<Object> getUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@RequestBody UserModel userModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        UserModel user = userRepository.findById(id).orElse(null);
        if(Objects.isNull(user)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found. ID ["+id+"]");
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
