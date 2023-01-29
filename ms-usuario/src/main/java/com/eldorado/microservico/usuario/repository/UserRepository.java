package com.eldorado.microservico.usuario.repository;

import com.eldorado.microservico.usuario.domain.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    public UserEntity findByDocument(String documentNumber);
    public void deleteByDocument(String documentNumber);

}
