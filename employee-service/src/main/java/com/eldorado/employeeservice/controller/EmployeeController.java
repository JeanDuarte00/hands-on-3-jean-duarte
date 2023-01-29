package com.eldorado.employeeservice.controller;

import com.eldorado.employeeservice.domain.model.Employee;
import com.eldorado.employeeservice.service.EmployeeService;
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
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController implements ApiEntrypoint<Employee> {

    private final EmployeeService service;

    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody Employee userDto) {
        var response = service.create(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee userDto) {
        var response = service.update(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        var response = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{identification}")
    public ResponseEntity<Employee> get(@PathVariable String identification) {
        var response = service.getByDocument(identification);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<String> delete(String identification) {
        var response = service.delete(identification);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
