package org.training.capital.microservice.msnotify;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sms-message-q",durable = "true",autoDelete = "false"),
            exchange = @Exchange(name = "message-exchange",durable = "true",autoDelete = "false",type = ExchangeTypes.DIRECT),
            key = "sms-message"
        )
    )
    public void handleSMSEvent(String event){
        System.out.println("I got SMS Event : " + event);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "email-message-q",durable = "true",autoDelete = "false"),
            exchange = @Exchange(name = "message-exchange",durable = "true",autoDelete = "false",type = ExchangeTypes.DIRECT),
            key = "email-message"
    )
    )
    public void handleEmailEvent(String event){
        System.out.println("I got Email Event : " + event);
    }

    // eu.west.turkey.istanbul.sms.adv.class1.
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic-message-eu-q",durable = "true",autoDelete = "false"),
            exchange = @Exchange(name = "message-topic-exchange",durable = "true",autoDelete = "false",type = ExchangeTypes.TOPIC),
            key = "eu.#"
    )
    )
    public void handleEuropeEvent(String event){
        System.out.println("I got Europe Event : " + event);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic-sms-message-eu-q",durable = "true",autoDelete = "false"),
            exchange = @Exchange(name = "message-topic-exchange",durable = "true",autoDelete = "false",type = ExchangeTypes.TOPIC),
            key = "eu.*.*.*.sms.#"
    )
    )
    public void handleEuropeSMSEvent(String event){
        System.out.println("I got Europe SMS Event : " + event);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic-email-message-eu-q",durable = "true",autoDelete = "false"),
            exchange = @Exchange(name = "message-topic-exchange",durable = "true",autoDelete = "false",type = ExchangeTypes.TOPIC),
            key = "eu.*.*.*.email.#"
    )
    )
    public void handleEuropeEmailEvent(String event){
        System.out.println("I got Europe Email Event : " + event);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic-email-message-west-eu-q",durable = "true",autoDelete = "false"),
            exchange = @Exchange(name = "message-topic-exchange",durable = "true",autoDelete = "false",type = ExchangeTypes.TOPIC),
            key = "eu.west.*.*.email.#"
    )
    )
    public void handleEuropeWestEvent(String event){
        System.out.println("I got Europe West Event : " + event);
    }

}
