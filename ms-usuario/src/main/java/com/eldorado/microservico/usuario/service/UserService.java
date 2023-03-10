package com.eldorado.microservico.usuario.service;


import com.eldorado.microservico.usuario.domain.model.UserEntity;
import com.eldorado.microservico.usuario.dto.MessageDto;
import com.eldorado.microservico.usuario.dto.UserDto;
import com.eldorado.microservico.usuario.publisher.UserPublisher;
import com.eldorado.microservico.usuario.repository.UserRepository;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private static final String MESSAGE = "Cadastro realizado\nUsuario: %s\nSenha: %s";
    private static final String SUBJECT = "NÃO RESPONDA";

    private final ObjectMapper objectMapper;

    private final UserPublisher userPublisher;

    public UserDto create(@Validated UserDto userDto) {
        var userEntity = modelMapper.map(userDto, UserEntity.class);

        var password = passwordGeneretor();

        userEntity.setPassword(Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString());

        userEntity = userRepository.insert(userEntity);
        log.info("User Saved with sucefull {}", userEntity);

        sendMessage(userDto, password);

        return userDto;
    }

    public UserDto update(@Validated UserDto userDto){
        var userEntity = modelMapper.map(userDto, UserEntity.class);
        var response = userRepository.save(userEntity);
        return modelMapper.map(response, UserDto.class);
    }

    public List<UserDto> getAll(){
        return userRepository.findAll().stream().map(data -> modelMapper.map(data, UserDto.class)).collect(Collectors.toList());
    }

    public UserDto getByDocument(String documentNumber){
        var entity = userRepository.findByDocument(documentNumber);
        return modelMapper.map(entity, UserDto.class);
    }

    public String delete(String documentNumber){
        userRepository.deleteByDocument(documentNumber);
        return documentNumber;
    }

    @SneakyThrows
    private void sendMessage(UserDto userDto, String password) {
        var message = MessageDto
                .builder()
                .to(userDto.getEmail())
                .message(String.format(MESSAGE, userDto.getEmail(), password))
                .subject(SUBJECT)
                .build();

        userPublisher.createMessage(objectMapper.writeValueAsString(message));
        log.info("Message to queue {}", message);
    }

    private String passwordGeneretor() {
        RandomStringUtils.randomAlphabetic(10);
        return Base64.encodeBase64String(RandomStringUtils.randomAlphanumeric(10).getBytes());
    }
}
