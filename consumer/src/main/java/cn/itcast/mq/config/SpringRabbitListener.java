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


    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg){
        System.out.println("消费者1接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg){
        System.out.println("消费者2接收到Fanout消息：【" + msg + "】");
    }


}
