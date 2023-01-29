package com.eldorado.employeeservice.service;


import com.eldorado.employeeservice.domain.model.Employee;
import com.eldorado.employeeservice.domain.model.Message;
import com.eldorado.employeeservice.publisher.RabbitMessagePublisher;
import com.eldorado.employeeservice.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository userRepository;
    private static final String MESSAGE = "Cadastro realizado\nFuncionario: %s\nSenha: %s";
    private static final String SUBJECT = "NÃO RESPONDA";

    private final ObjectMapper objectMapper;

    private final RabbitMessagePublisher publisher;

    public Employee create(@Validated Employee data) {

        var password = passwordGeneretor();

        data.setPassword(Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString());

        var response = userRepository.insert(data);
        log.info("User Saved with sucefull {}", data);

        sendMessage(response, password);

        return response;
    }

    public Employee update(@Validated Employee data){
        var response = userRepository.save(data);
        return response;
    }

    public List<Employee> getAll(){
        return userRepository.findAll();
    }

    public Employee getByDocument(String documentNumber){
        var response = userRepository.findByDocument(documentNumber);
        return response;
    }

    public String delete(String documentNumber){
        userRepository.deleteByDocument(documentNumber);
        return documentNumber;
    }

    @SneakyThrows
    private void sendMessage(Employee data, String password) {
        var message = Message
                .builder()
                .to(data.getEmail())
                .message(String.format(MESSAGE, data.getEmail(), password))
                .subject(SUBJECT)
                .build();

        publisher.createMessage(objectMapper.writeValueAsString(message));
        log.info("Message to queue {}", message);
    }

    private String passwordGeneretor() {
        RandomStringUtils.randomAlphabetic(10);
        return Base64.encodeBase64String(RandomStringUtils.randomAlphanumeric(10).getBytes());
    }
}
