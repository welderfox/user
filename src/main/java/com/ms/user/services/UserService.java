package com.ms.user.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer  userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer){
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    //insere usuario na base e publica o mesmo na fila
    @Transactional
    public UserModel save(UserModel userModel)
    {
        //Gravando o usuario na base de dados e publicando na fila.
        userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
