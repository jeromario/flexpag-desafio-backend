package com.flexpag.paymentscheduler.api.controller;


import com.flexpag.paymentscheduler.model.User;
import com.flexpag.paymentscheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<User>> buscarTodos(){
        return ResponseEntity.ok(userService.buscarTodos());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(userService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<User> salvar(@RequestBody User user){

        userService.salvar(user);

        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(@PathVariable Long id,@RequestBody User user){
        userService.atualizar(id, user);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deletar(@RequestBody Long id) {
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
