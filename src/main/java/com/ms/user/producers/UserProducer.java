package com.ms.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.dtos.EmailDto;

import com.ms.user.models.UserModel;

@Component
public class UserProducer {
    // injetando o template do rabbit
    // o rabbitTemplate é o responsável por enviar mensagens para a fila
    final RabbitTemplate rabbitTemplate;

    // o construtor é utilizado para injetar o rabbitTemplate
    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    // o routingKey é a chave de roteamento que será utilizada para enviar a
    // mensagem para a fila
    // o routingKey é lido do arquivo application.properties
    // crinado a mensagem a ser publicada
    public void publishMessageEmail(UserModel userModel) {
        var emailDto = new EmailDto();

        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!!");
        emailDto.setText(userModel.getName() + ", seja bem vindo(a)! \nAgradecemos seu cadastro...");

        // publica mensagem
        // o método convertAndSend é utilizado para enviar a mensagem para a fila
        // o primeiro parâmetro é a chave de roteamento (routingKey) e o segundo
        // parâmetro é a mensagem (emailDto)
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
