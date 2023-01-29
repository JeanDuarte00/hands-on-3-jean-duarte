package com.eldorado.microservico.usuario.controller;

import com.eldorado.microservico.usuario.dto.UserDto;
import com.eldorado.microservico.usuario.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements ApiEntrypoint<UserDto> {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        var response = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        var response = userService.update(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        var response = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{identification}")
    public ResponseEntity<UserDto> get(@PathVariable String identification) {
        var response = userService.getByDocument(identification);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<String> delete(String identification) {
        var response = userService.delete(identification);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
