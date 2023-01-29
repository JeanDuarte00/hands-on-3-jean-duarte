package com.eldorado.employeeservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ApiEntrypoint <T> {

    ResponseEntity<T> create(@RequestBody T data);
    ResponseEntity<T> update(@RequestBody T data);
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> get(@PathVariable String identification);
    ResponseEntity<String> delete(@PathVariable String identification);
}
