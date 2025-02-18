package com.ms.email.Consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.DTOS.EmailDTO;

@Component
public class EmailConsumer {
    
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailDTO emailDTO){
        System.out.println(emailDTO.emailTo());
    }
}
