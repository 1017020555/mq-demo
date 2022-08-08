package cn.itcast.mq.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage(String message) throws InterruptedException {
        System.out.println("我收到1： "+message+"--"+ LocalDateTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage2(String message) throws InterruptedException {
        System.out.println("我收到2： "+message+"--"+ LocalDateTime.now());
        Thread.sleep(20);
    }

}
